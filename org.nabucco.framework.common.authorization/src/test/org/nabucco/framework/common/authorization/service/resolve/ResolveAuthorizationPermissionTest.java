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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationPermissionTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationPermissionTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testResolveAuthorizationPermission() throws Exception {

        AuthorizationPermission permission = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyPermission());

        AuthorizationPermissionMsg msg = new AuthorizationPermissionMsg();
        msg.setAuthorizationPermission(permission);

        ServiceRequest<AuthorizationPermissionMsg> rq = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationPermissionMaintainMsg rs = this.component.getResolveAuthorization()
                .resolveAuthorizationPermission(rq).getResponseMessage();

        Assert.assertNotNull(rs);
        permission = rs.getAuthorizationPermission();

        Assert.assertNotNull(permission.getId());
        Assert.assertNotNull(permission.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, permission.getDatatypeState());
        Assert.assertEquals("Dummy Permission", permission.getPermissionname().getValue());

        AuthorizationTestUtility.remove(this.component, permission);
    }

    @Test
    public void testResolveAuthorizationPermissionList() throws Exception {

        AuthorizationPermission permission = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyPermission());

        AuthorizationPermissionListMsg msg = new AuthorizationPermissionListMsg();
        msg.getAuthorizationPermissionList().add(permission);

        ServiceRequest<AuthorizationPermissionListMsg> rq = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationPermissionListMsg rs = this.component.getResolveAuthorization()
                .resolveAuthorizationPermissionList(rq).getResponseMessage();

        Assert.assertNotNull(rs);
        Assert.assertEquals(1, rs.getAuthorizationPermissionList().size());

        permission = rs.getAuthorizationPermissionList().get(0);

        Assert.assertNotNull(permission.getId());
        Assert.assertNotNull(permission.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, permission.getDatatypeState());
        Assert.assertEquals("Dummy Permission", permission.getPermissionname().getValue());

        AuthorizationTestUtility.remove(this.component, permission);
    }
}
