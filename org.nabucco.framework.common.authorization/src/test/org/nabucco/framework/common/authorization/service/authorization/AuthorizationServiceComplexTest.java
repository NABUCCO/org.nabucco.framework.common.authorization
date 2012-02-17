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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * AuthorizationServiceComplexTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationServiceComplexTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    private AuthorizationGroup group;

    @Before
    public void setUp() throws Exception {
        this.component = super.getComponent(AuthorizationComponentLocator.getInstance());

        this.group = this.createDummyData();
    }

    @After
    public void tearDown() throws Exception {
        AuthorizationTestUtility.removeAll(this.component, this.group);
    }

    @Test
    public void testAuthorizeByGroup() throws Exception {

        Assert.assertNotNull(this.group);
        Assert.assertEquals(1, this.group.getUserList().size());
        Assert.assertNotNull(this.group.getUserList().get(0));
        Assert.assertNotNull(this.group.getUserList().get(0).getUser());

        AuthorizationUser user = this.group.getUserList().get(0).getUser();

        ServiceRequest<AuthorizationNameMsg> rq = this.createAuthorizedRequest(user, "Dummy");

        ServiceResponse<AuthorizationRs> rs = this.component.getAuthorizationService().hasPermissionByName(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        Assert.assertNotNull(rs.getResponseMessage().getValid());
        Assert.assertTrue(rs.getResponseMessage().getValid().getValue());
    }

    /**
     * Create dummy data for test.
     * 
     * @return the maintained group
     * 
     * @throws ServiceException
     */
    private AuthorizationGroup createDummyData() throws ServiceException {
        AuthorizationPermission permission = AuthorizationTestUtility.dummyPermission("Dummy");
        AuthorizationRolePermissionRelation rolePermission = new AuthorizationRolePermissionRelation();
        rolePermission.setDatatypeState(DatatypeState.INITIALIZED);
        rolePermission.setPermission(permission);

        AuthorizationRole role = AuthorizationTestUtility.dummyRole("Dummy");
        role.getPermissionList().add(rolePermission);
        AuthorizationGroupRoleRelation groupRole = new AuthorizationGroupRoleRelation();
        groupRole.setDatatypeState(DatatypeState.INITIALIZED);
        groupRole.setRole(role);

        AuthorizationUser user = AuthorizationTestUtility.dummyUser("Dummy");
        AuthorizationGroupUserRelation groupUser = new AuthorizationGroupUserRelation();
        groupUser.setDatatypeState(DatatypeState.INITIALIZED);
        groupUser.setUser(user);

        AuthorizationGroup group = AuthorizationTestUtility.dummyGroup("Dummy");
        group.getUserList().add(groupUser);
        group.getRoleList().add(groupRole);

        group = AuthorizationTestUtility.create(component, group);
        return group;
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
    private ServiceRequest<AuthorizationNameMsg> createAuthorizedRequest(AuthorizationUser user, String permissionName) {
        AuthorizationNameMsg msg = new AuthorizationNameMsg();
        msg.setName(new Name(permissionName));

        ServiceMessageContext context = super.createServiceContext();
        context.getSubject().setUser(user);
        context.getSubject().setToken(new byte[0]);

        ServiceRequest<AuthorizationNameMsg> rq = new ServiceRequest<AuthorizationNameMsg>(context);
        rq.setRequestMessage(msg);
        return rq;
    }
}
