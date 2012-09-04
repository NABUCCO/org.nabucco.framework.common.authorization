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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * AuthorizationUserInitTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationUserInitTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void initUser1() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(component, AuthorizationTestUtility.dummyGroup());

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
        msg.getAuthorizationGroupList().add(group);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationUser(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationUser());
    }

    @Test
    public void initUser2() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(component, AuthorizationTestUtility.dummyGroup());

        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationUser user = new AuthorizationUser();
        user.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        user.setUsername(name);
        name.setValue("User User");

        Description description = new Description();
        description.setValue("UserUser Description");
        user.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        user.setOwner(owner);

        user.setUserTypeRefId(1l);

        msg.setAuthorizationUser(user);
        msg.getAuthorizationGroupList().add(group);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationUser(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationUser());
    }

    @Test
    public void initUser3() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(component, AuthorizationTestUtility.dummyGroup());

        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationUser user = new AuthorizationUser();
        user.setDatatypeState(DatatypeState.INITIALIZED);
        Name name = new Name();
        user.setUsername(name);
        name.setValue("Test User");

        Description description = new Description();
        description.setValue("TestUser Description");
        user.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("TEST");
        user.setOwner(owner);

        user.setUserTypeRefId(1l);

        msg.setAuthorizationUser(user);
        msg.getAuthorizationGroupList().add(group);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationUser(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationUser());
    }

}
