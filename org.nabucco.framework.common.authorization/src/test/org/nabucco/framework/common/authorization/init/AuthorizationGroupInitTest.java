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
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;

/**
 * AuthorizationGroupInitTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationGroupInitTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void initGroup1() throws Exception {

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
        
        group.setGroupTypeRefId(1l);

        msg.setAuthorizationGroup(group);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationGroup());
    }

    @Test
    public void initGroup2() throws Exception {

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();
        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationGroup group = new AuthorizationGroup();
        group.setDatatypeState(DatatypeState.INITIALIZED);

        Name name = new Name();
        group.setGroupname(name);
        name.setValue("User Group");

        Description description = new Description();
        description.setValue("UserGroup Description");
        group.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("PRODYNA");
        group.setOwner(owner);

        group.setGroupTypeRefId(1l);
        
        msg.setAuthorizationGroup(group);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationGroup());
    }

    @Test
    public void initGroup3() throws Exception {

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();
        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationGroup group = new AuthorizationGroup();
        group.setDatatypeState(DatatypeState.INITIALIZED);
        Name name = new Name();
        group.setGroupname(name);
        name.setValue("Test Group");

        Description description = new Description();
        description.setValue("TestGroup Description");
        group.setDescription(description);

        Owner owner = new Owner();
        owner.setValue("TEST");
        group.setOwner(owner);

        group.setGroupTypeRefId(1l);

        msg.setAuthorizationGroup(group);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationGroup());
    }

}
