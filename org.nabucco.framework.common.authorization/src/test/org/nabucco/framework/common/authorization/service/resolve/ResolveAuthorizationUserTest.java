/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.service.resolve;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationUserTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationUserTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testResolveAuthorizationUser() throws Exception {

        AuthorizationUser user = AuthorizationTestUtility.create(this.component, AuthorizationTestUtility.dummyUser());

        AuthorizationUserMsg msg = new AuthorizationUserMsg();
        msg.setAuthorizationUser(user);

        ServiceRequest<AuthorizationUserMsg> rq = new ServiceRequest<AuthorizationUserMsg>(super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationUserMaintainMsg rs = this.component.getResolveAuthorization().resolveAuthorizationUser(rq)
                .getResponseMessage();

        Assert.assertNotNull(rs);
        user = rs.getAuthorizationUser();

        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, user.getDatatypeState());
        Assert.assertEquals("Dummy User", user.getUsername().getValue());

        AuthorizationTestUtility.remove(this.component, user);
    }

    @Test
    public void testResolveAuthorizationUserList() throws Exception {

        AuthorizationUser user = AuthorizationTestUtility.create(this.component, AuthorizationTestUtility.dummyUser());

        AuthorizationUserListMsg msg = new AuthorizationUserListMsg();
        msg.getAuthorizationUserList().add(user);

        ServiceRequest<AuthorizationUserListMsg> rq = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationUserListMsg rs = this.component.getResolveAuthorization().resolveAuthorizationUserList(rq)
                .getResponseMessage();

        Assert.assertNotNull(rs);
        Assert.assertEquals(1, rs.getAuthorizationUserList().size());

        user = rs.getAuthorizationUserList().get(0);

        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, user.getDatatypeState());
        Assert.assertEquals("Dummy User", user.getUsername().getValue());

        AuthorizationTestUtility.remove(this.component, user);
    }
}
