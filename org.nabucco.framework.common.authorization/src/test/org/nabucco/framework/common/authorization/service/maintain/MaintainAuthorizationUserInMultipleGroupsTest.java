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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * MaintainAuthorizationUserInMultipleGroupsTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationUserInMultipleGroupsTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testMaintainAuthorizationUserInMultipleGroups() throws Exception {

        AuthorizationUserMaintainMsg rs;

        AuthorizationGroup groupA = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup("GroupA"));

        AuthorizationGroup groupB = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyGroup("GroupB"));

        AuthorizationUser user = AuthorizationTestUtility.dummyUser("User");

        // GROUP A

        rs = this.maintain(groupA, user);

        user = rs.getAuthorizationUser();
        groupA = rs.getAuthorizationGroupList().get(0);

        Assert.assertNotNull(user);
        Assert.assertNotNull(groupA);

        // GROUP B

        rs = this.maintain(groupB, user);

        user = rs.getAuthorizationUser();
        groupB = rs.getAuthorizationGroupList().get(0);

        Assert.assertNotNull(user);
        Assert.assertNotNull(groupB);

        // REMOVE GROUP

        AuthorizationTestUtility.remove(this.component, groupA);
        AuthorizationTestUtility.remove(this.component, groupB);

        // REMOVE USER

        AuthorizationTestUtility.remove(this.component, user);
    }

    private AuthorizationUserMaintainMsg maintain(AuthorizationGroup group, AuthorizationUser user)
            throws ServiceException {

        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        rq.setRequestMessage(msg);

        msg.setAuthorizationUser(user);
        msg.getAuthorizationGroupList().add(group);

        rs = this.component.getMaintainAuthorization().maintainAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationUser());
        Assert.assertEquals(1, rs.getResponseMessage().getAuthorizationGroupList().size());

        return rs.getResponseMessage();
    }
}
