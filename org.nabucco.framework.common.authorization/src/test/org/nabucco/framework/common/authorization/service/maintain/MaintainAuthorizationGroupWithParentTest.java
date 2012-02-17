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
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * MaintainAuthorizationGroupWithParentTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationGroupWithParentTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testMaintainGroupWithParent() throws Exception {

        // INIT

        ServiceResponse<AuthorizationGroupMaintainMsg> rs;

        AuthorizationGroup parentA = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup("Parent A"));

        AuthorizationGroup parentB = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup("Parent B"));

        AuthorizationGroup child = AuthorizationTestUtility.dummyGroup("Child");

        // PARENT A

        rs = this.maintain(parentA, child);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        child = rs.getResponseMessage().getAuthorizationGroup();
        parentA = rs.getResponseMessage().getParentAuthorizationGroup();

        Assert.assertNotNull(child);
        Assert.assertNotNull(parentA);

        // PARENT B

        rs = this.maintain(parentB, child);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        child = rs.getResponseMessage().getAuthorizationGroup();
        parentB = rs.getResponseMessage().getParentAuthorizationGroup();

        Assert.assertNotNull(child);
        Assert.assertNotNull(parentB);

        // DELETE

        AuthorizationTestUtility.remove(this.component, child);
        AuthorizationTestUtility.remove(this.component, parentA);
        AuthorizationTestUtility.remove(this.component, parentB);
    }

    private ServiceResponse<AuthorizationGroupMaintainMsg> maintain(AuthorizationGroup parentA, AuthorizationGroup child)
            throws ServiceException {

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();

        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        msg.setParentAuthorizationGroup(parentA);
        msg.setAuthorizationGroup(child);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);

        return rs;
    }
}
