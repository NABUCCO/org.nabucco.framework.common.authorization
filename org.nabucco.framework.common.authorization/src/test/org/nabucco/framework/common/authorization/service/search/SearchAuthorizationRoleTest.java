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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationRoleTest
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class SearchAuthorizationRoleTest extends RuntimeTestSupport {
    
    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testSearchAuthorizationRole() throws Exception {

        AuthorizationRole role = AuthorizationTestUtility.create(component,
                AuthorizationTestUtility.dummyRole());
        
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(role.getRolename());

        ServiceRequest<AuthorizationSearchMsg> rq = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationRoleListMsg> rs = component.getSearchAuthorization()
                .searchAuthorizationRole(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertEquals(1, rs.getResponseMessage().getAuthorizationRoleList().size());
        role = rs.getResponseMessage().getAuthorizationRoleList().get(0);
        Assert.assertNotNull(role);

        AuthorizationTestUtility.remove(component, role);
    }

}
