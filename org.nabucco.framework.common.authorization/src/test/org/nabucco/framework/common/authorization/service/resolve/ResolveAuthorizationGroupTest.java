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
package org.nabucco.framework.common.authorization.service.resolve;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * SearchAuthorizationGroupTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationGroupTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testResolveAuthorizationGroup() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup());

        AuthorizationGroupMsg msg = new AuthorizationGroupMsg();
        msg.setAuthorizationGroup(group);

        ServiceRequest<AuthorizationGroupMsg> rq = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationGroupMaintainMsg rs = this.component.getResolveAuthorization().resolveAuthorizationGroup(rq)
                .getResponseMessage();

        Assert.assertNotNull(rs);
        group = rs.getAuthorizationGroup();

        Assert.assertNotNull(group.getId());
        Assert.assertNotNull(group.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, group.getDatatypeState());
        Assert.assertEquals("Dummy Group", group.getGroupname().getValue());

        AuthorizationTestUtility.remove(this.component, group);
    }

    @Test
    public void testResolveAuthorizationGroupList() throws Exception {

        AuthorizationGroup group = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup());

        AuthorizationGroupListMsg msg = new AuthorizationGroupListMsg();
        msg.getAuthorizationGroupList().add(group);

        ServiceRequest<AuthorizationGroupListMsg> rq = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        AuthorizationGroupListMsg rs = this.component.getResolveAuthorization().resolveAuthorizationGroupList(rq)
                .getResponseMessage();

        Assert.assertNotNull(rs);
        Assert.assertEquals(1, rs.getAuthorizationGroupList().size());

        group = rs.getAuthorizationGroupList().get(0);

        Assert.assertNotNull(group.getId());
        Assert.assertNotNull(group.getVersion());
        Assert.assertEquals(DatatypeState.PERSISTENT, group.getDatatypeState());
        Assert.assertEquals("Dummy Group", group.getGroupname().getValue());

        AuthorizationTestUtility.remove(this.component, group);
    }
}
