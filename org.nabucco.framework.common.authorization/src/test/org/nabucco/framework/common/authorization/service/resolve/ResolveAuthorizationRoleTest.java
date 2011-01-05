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
package org.nabucco.framework.common.authorization.service.resolve;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationRoleTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationRoleTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testResolveAuthorizationRole() throws Exception {

        AuthorizationRole role = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyRole());

        AuthorizationRoleMsg msg = new AuthorizationRoleMsg();
        msg.setAuthorizationRole(role);

        ServiceRequest<AuthorizationRoleMsg> rq = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationRoleMaintainMsg rs = this.component.getResolveAuthorization()
                .resolveAuthorizationRole(rq).getResponseMessage();

        Assert.assertNotNull(rs);
        role = rs.getAuthorizationRole();

        Assert.assertNotNull(role.getId());
        Assert.assertNotNull(role.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, role.getDatatypeState());
        Assert.assertEquals("Dummy Role", role.getRolename().getValue());

        AuthorizationTestUtility.remove(this.component, role);
    }

    @Test
    public void testResolveAuthorizationRoleList() throws Exception {

        AuthorizationRole role = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyRole());

        AuthorizationRoleListMsg msg = new AuthorizationRoleListMsg();
        msg.getAuthorizationRoleList().add(role);

        ServiceRequest<AuthorizationRoleListMsg> rq = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationRoleListMsg rs = this.component.getResolveAuthorization()
                .resolveAuthorizationRoleList(rq).getResponseMessage();

        Assert.assertNotNull(rs);
        Assert.assertEquals(1, rs.getAuthorizationRoleList().size());

        role = rs.getAuthorizationRoleList().get(0);

        Assert.assertNotNull(role.getId());
        Assert.assertNotNull(role.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, role.getDatatypeState());
        Assert.assertEquals("Dummy Role", role.getRolename().getValue());

        AuthorizationTestUtility.remove(this.component, role);
    }
}
