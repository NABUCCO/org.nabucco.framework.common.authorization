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
package org.nabucco.framework.common.authorization.service.authorization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * AuthorizationServiceTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationServiceTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testHasPermission() throws Exception {

        AuthorizationUser user = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyUser("Dummy"));

        AuthorizationPermission permission = AuthorizationTestUtility.addUsers(this.component,
                AuthorizationTestUtility.dummyPermission("Dummy"), user);

        ServiceRequest<AuthorizationPermissionMsg> rq = this.createAuthorizedRequest(user, permission);

        user = AuthorizationTestUtility.find(this.component, user);

        ServiceResponse<AuthorizationRs> rs = this.component.getAuthorizationService().hasPermission(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getValid());
        Assert.assertNotNull(rs.getResponseMessage().getValid().getValue());
        Assert.assertTrue(rs.getResponseMessage().getValid().getValue());

        AuthorizationTestUtility.remove(this.component, user);
        AuthorizationTestUtility.remove(this.component, permission);
    }

    /**
     * Creates a service request with authorized information.
     * 
     * @param user
     *            the current user
     * @parame permission the permission
     * 
     * @return the request
     */
    private ServiceRequest<AuthorizationPermissionMsg> createAuthorizedRequest(AuthorizationUser user,
            AuthorizationPermission permission) {
        AuthorizationPermissionMsg msg = new AuthorizationPermissionMsg();
        msg.setAuthorizationPermission(permission);

        ServiceMessageContext context = super.createServiceContext();
        context.getSubject().setUser(user);
        context.getSubject().setToken(new byte[0]);

        ServiceRequest<AuthorizationPermissionMsg> rq = new ServiceRequest<AuthorizationPermissionMsg>(context);
        rq.setRequestMessage(msg);
        return rq;
    }

    @Test
    public void testHasPermissionByName() throws Exception {

        AuthorizationUser user = AuthorizationTestUtility.create(this.component,
                AuthorizationTestUtility.dummyUser("Dummy"));

        AuthorizationPermission permission = AuthorizationTestUtility.addUsers(this.component,
                AuthorizationTestUtility.dummyPermission("Dummy"), user);

        ServiceRequest<AuthorizationNameMsg> rq = this.createAuthorizedRequest(user, permission.getPermissionname());

        user = AuthorizationTestUtility.find(this.component, user);

        ServiceResponse<AuthorizationRs> rs = this.component.getAuthorizationService().hasPermissionByName(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getValid());
        Assert.assertNotNull(rs.getResponseMessage().getValid().getValue());
        Assert.assertTrue(rs.getResponseMessage().getValid().getValue());

        AuthorizationTestUtility.remove(this.component, user);
        AuthorizationTestUtility.remove(this.component, permission);
    }

    /**
     * Creates a service request with authorized information.
     * 
     * @param user
     *            the current user
     * @param the
     *            permission name
     * 
     * @return the request
     */
    private ServiceRequest<AuthorizationNameMsg> createAuthorizedRequest(AuthorizationUser user, Name permissionName) {
        AuthorizationNameMsg msg = new AuthorizationNameMsg();
        msg.setName(permissionName);

        ServiceMessageContext context = super.createServiceContext();
        context.getSubject().setUser(user);
        context.getSubject().setToken(new byte[0]);

        ServiceRequest<AuthorizationNameMsg> rq = new ServiceRequest<AuthorizationNameMsg>(context);
        rq.setRequestMessage(msg);
        return rq;
    }

}
