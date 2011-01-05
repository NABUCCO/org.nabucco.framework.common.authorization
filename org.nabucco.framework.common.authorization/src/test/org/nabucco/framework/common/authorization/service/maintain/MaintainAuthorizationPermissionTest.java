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
package org.nabucco.framework.common.authorization.service.maintain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;

/**
 * MaintainAuthorizationPermissionTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationPermissionTest extends RuntimeTestSupport{


    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }
    
    @Test
    public void testMaintainAuthorizationPermission() throws Exception {

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        
        rq.setRequestMessage(msg);

        AuthorizationPermission permission = new AuthorizationPermission();

        permission.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        permission.setPermissionname(name);
        name.setValue("Admin Permission");

        Description description = new Description();
        description.setValue("AdminPermission Description");
        permission.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        permission.setOwner(owner);

        CodeType type = new CodeType();
        type.setValue("ADMIN");
        permission.setPermissionType(type);

        msg.setAuthorizationPermission(permission);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = this.component.getMaintainAuthorization()
                .maintainAuthorizationPermission(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        permission = rs.getResponseMessage().getAuthorizationPermission();
        
        Assert.assertNotNull(permission);

        Assert.assertEquals("Admin Permission", permission.getPermissionname().getValue());
        Assert.assertEquals("AdminPermission Description", permission.getDescription().getValue());
        Assert.assertEquals("PRODYNA", permission.getOwner().getValue());
        Assert.assertEquals("ADMIN", permission.getPermissionType().getValue());

        permission.setDatatypeState(DatatypeState.MODIFIED);
        permission.getPermissionname().setValue("Other admin permission");
        permission.getDescription().setValue("Other adminPermission Description");
        permission.getOwner().setValue("NABUCCO");
        permission.getPermissionType().setValue("SUPER_ADMIN");

        msg.setAuthorizationPermission(permission);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationPermission(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        permission = rs.getResponseMessage().getAuthorizationPermission();
        Assert.assertNotNull(permission);

        Assert.assertEquals("Other admin permission", permission.getPermissionname().getValue());
        Assert.assertEquals("Other adminPermission Description", permission.getDescription().getValue());
        Assert.assertEquals("NABUCCO", permission.getOwner().getValue());
        Assert.assertEquals("SUPER_ADMIN", permission.getPermissionType().getValue());

        permission.setDatatypeState(DatatypeState.DELETED);

        msg.setAuthorizationPermission(permission);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationPermission(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationPermission());
        
    }
    
}
