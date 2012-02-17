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
package org.nabucco.framework.common.authorization.init;

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
 * AuthorizationRoleInitTest
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class AuthorizationRoleInitTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void initRole1() throws Exception {

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationRole role = new AuthorizationRole();
        role.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("Admin Role");
        role.setRolename(name);

        Description description = new Description();
        description.setValue("AdminRole Description");
        role.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        role.setOwner(owner);
        
        role.setRoleTypeRefId(1l);

        msg.setAuthorizationRole(role);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationRole(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationRole());
    }

    @Test
    public void initRole2() throws Exception {

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationRole role = new AuthorizationRole();
        role.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("User Role");
        role.setRolename(name);

        Description description = new Description();
        description.setValue("UserRole Description");
        role.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        role.setOwner(owner);
        
        role.setRoleTypeRefId(1l);

        msg.setAuthorizationRole(role);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationRole(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationRole());
    }

    @Test
    public void initRole3() throws Exception {

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationRole role = new AuthorizationRole();
        role.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("Test Role");
        role.setRolename(name);

        Description description = new Description();
        description.setValue("TestRole Description");
        role.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("TEST");
        role.setOwner(owner);
        
        role.setRoleTypeRefId(1l);

        msg.setAuthorizationRole(role);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationRole(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationRole());
    }

}
