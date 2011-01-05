/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.nabucco.framework.common.authorization.service.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationUserTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchAuthorizationUserTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testSearchAuthorizationUser() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup());

        AuthorizationUser user = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyUser(), group);

        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(user.getUsername());

        ServiceRequest<AuthorizationSearchMsg> rq = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationUserListMsg> rs = this.component.getSearchAuthorization()
                .searchAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertEquals(1, rs.getResponseMessage().getAuthorizationUserList().size());
        user = rs.getResponseMessage().getAuthorizationUserList().get(0);
        Assert.assertNotNull(user);

        AuthorizationTestUtility.remove(this.component, group);
        AuthorizationTestUtility.remove(this.component, user);
    }

}
