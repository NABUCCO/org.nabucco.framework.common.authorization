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
package org.nabucco.framework.common.authorization.service.maintain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;

/**
 * MaintainAuthorizationRoleTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationRoleTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testMaintainAuthorizationRole() throws Exception {

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());

        rq.setRequestMessage(msg);

        AuthorizationRole role = new AuthorizationRole();

        role.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        role.setRolename(name);
        name.setValue("Admin Role");

        Description description = new Description();
        description.setValue("AdminRole Description");
        role.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        role.setOwner(owner);

        role.setRoleTypeRefId(1l);

        msg.setAuthorizationRole(role);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = this.component.getMaintainAuthorization()
                .maintainAuthorizationRole(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        role = rs.getResponseMessage().getAuthorizationRole();

        Assert.assertNotNull(role);

        Assert.assertEquals("Admin Role", role.getRolename().getValue());
        Assert.assertEquals("AdminRole Description", role.getDescription().getValue());
        Assert.assertEquals("PRODYNA", role.getOwner().getValue());
        Assert.assertEquals(1l, role.getRoleTypeRefId().longValue());

        role.setDatatypeState(DatatypeState.MODIFIED);
        role.getRolename().setValue("Other admin role");
        role.getDescription().setValue("Other adminRole Description");
        role.getOwner().setValue("NABUCCO");

        msg.setAuthorizationRole(role);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationRole(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        role = rs.getResponseMessage().getAuthorizationRole();
        Assert.assertNotNull(role);

        Assert.assertEquals("Other admin role", role.getRolename().getValue());
        Assert.assertEquals("Other adminRole Description", role.getDescription().getValue());
        Assert.assertEquals("NABUCCO", role.getOwner().getValue());

        role.setDatatypeState(DatatypeState.DELETED);

        msg.setAuthorizationRole(role);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationRole(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationRole());

    }
}
