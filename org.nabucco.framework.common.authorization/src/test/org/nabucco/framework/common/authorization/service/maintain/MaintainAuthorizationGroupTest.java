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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;

/**
 * MaintainAuthorizationGroupTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationGroupTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testMaintainGroup() throws Exception {

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();
        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationGroup group = new AuthorizationGroup();
        group.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        group.setGroupname(name);
        name.setValue("Admin Group");

        Description description = new Description();
        description.setValue("AdminGroup Description");
        group.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        group.setOwner(owner);

        CodeType type = new CodeType();
        type.setValue("ADMIN");
        group.setGroupType(type);

        msg.setAuthorizationGroup(group);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        group = rs.getResponseMessage().getAuthorizationGroup();
        Assert.assertNotNull(group);
        
        Assert.assertNotNull(group.getId());
        Assert.assertNotNull(group.getVersion());
        
        long id = group.getId();
        long version = group.getVersion();
        
        Assert.assertEquals("Admin Group", group.getGroupname().getValue());
        Assert.assertEquals("AdminGroup Description", group.getDescription().getValue());
        Assert.assertEquals("PRODYNA", group.getOwner().getValue());
        Assert.assertEquals("ADMIN", group.getGroupType().getValue());

        group.setDatatypeState(DatatypeState.MODIFIED);
        group.getGroupname().setValue("Other admin group");
        group.getDescription().setValue("Other adminGroup Description");
        group.getOwner().setValue("NABUCCO");
        group.getGroupType().setValue("SUPER_ADMIN");

        msg.setAuthorizationGroup(group);

        rs = component.getMaintainAuthorization().maintainAuthorizationGroup(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        group = rs.getResponseMessage().getAuthorizationGroup();
        Assert.assertNotNull("Authorization Grup must not be null.", group);

        Assert.assertNotNull(group.getId());
        Assert.assertNotNull(group.getVersion());
        
        Assert.assertEquals(id, group.getId().longValue());
        Assert.assertEquals(version + 1, group.getVersion().longValue());

        Assert.assertEquals("Other admin group", group.getGroupname().getValue());
        Assert.assertEquals("Other adminGroup Description", group.getDescription().getValue());
        Assert.assertEquals("NABUCCO", group.getOwner().getValue());
        Assert.assertEquals("SUPER_ADMIN", group.getGroupType().getValue());

        group.setDatatypeState(DatatypeState.DELETED);

        msg.setAuthorizationGroup(group);

        rs = component.getMaintainAuthorization().maintainAuthorizationGroup(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationGroup());
    }
}
