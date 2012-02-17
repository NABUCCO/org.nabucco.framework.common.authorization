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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;

/**
 * AuthorizationPermissionInitTest
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class AuthorizationPermissionInitTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void initPermission1() throws Exception {

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationPermission permision = new AuthorizationPermission();
        permision.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("Admin Permission");
        permision.setPermissionname(name);

        Description description = new Description();
        description.setValue("AdminPermission Description");
        permision.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        permision.setOwner(owner);

        permision.setPermissionTypeRefId(1l);
        
        msg.setAuthorizationPermission(permision);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationPermission(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationPermission());
    }

    @Test
    public void initPermission2() throws Exception {

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationPermission permision = new AuthorizationPermission();
        permision.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("User Permission");
        permision.setPermissionname(name);

        Description description = new Description();
        description.setValue("UserPermission Description");
        permision.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        permision.setOwner(owner);

        permision.setPermissionTypeRefId(1l);

        msg.setAuthorizationPermission(permision);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationPermission(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationPermission());
    }

    @Test
    public void initPermission3() throws Exception {

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationPermission permision = new AuthorizationPermission();
        permision.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        name.setValue("Test Permission");
        permision.setPermissionname(name);

        Description description = new Description();
        description.setValue("TestPermission Description");
        permision.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("TEST");
        permision.setOwner(owner);

        permision.setPermissionTypeRefId(1l);

        msg.setAuthorizationPermission(permision);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationPermission(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationPermission());
    }

}
