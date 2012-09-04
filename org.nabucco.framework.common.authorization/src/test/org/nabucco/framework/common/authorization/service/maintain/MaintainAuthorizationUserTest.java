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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * MaintainAuthorizationUserTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationUserTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testMaintainAuthorizationUser() throws Exception {

        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationUser user = new AuthorizationUser();

        user.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        user.setUsername(name);
        name.setValue("Admin User");

        Description description = new Description();
        description.setValue("AdminUser Description");
        user.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        user.setOwner(owner);

        user.setUserTypeRefId(1l);

        msg.setAuthorizationUser(user);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = this.component.getMaintainAuthorization()
                .maintainAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        user = rs.getResponseMessage().getAuthorizationUser();

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, user.getDatatypeState());

        long id = user.getId().longValue();
        long version = user.getVersion().longValue();

        Assert.assertEquals("Admin User", user.getUsername().getValue());
        Assert.assertEquals("AdminUser Description", user.getDescription().getValue());
        Assert.assertEquals("PRODYNA", user.getOwner().getValue());
        Assert.assertEquals(1l, user.getUserTypeRefId().longValue());

        user.setDatatypeState(DatatypeState.MODIFIED);
        user.getUsername().setValue("Other admin user");
        user.getDescription().setValue("Other adminUser Description");
        user.getOwner().setValue("NABUCCO");

        msg.setAuthorizationUser(user);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        user = rs.getResponseMessage().getAuthorizationUser();
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, user.getDatatypeState());

        Assert.assertEquals(id, user.getId().longValue());
        Assert.assertEquals(version + 1, user.getVersion().longValue());

        Assert.assertEquals("Other admin user", user.getUsername().getValue());
        Assert.assertEquals("Other adminUser Description", user.getDescription().getValue());
        Assert.assertEquals("NABUCCO", user.getOwner().getValue());

        user.setDatatypeState(DatatypeState.DELETED);

        msg.setAuthorizationUser(user);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        user = rs.getResponseMessage().getAuthorizationUser();
        Assert.assertNotNull(user);
        Assert.assertEquals(DatatypeState.DESTROYED, user.getDatatypeState());

    }

}
