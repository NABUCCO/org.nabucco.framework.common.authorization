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
package org.nabucco.framework.common.authorization.util;

import java.util.Arrays;

import org.junit.Assert;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.visitor.DatatypeVisitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * AuthorizationTestUtility
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationTestUtility {

    private AuthorizationTestUtility() {
    }

    public static AuthorizationUser dummyUser() {
        return dummyUser("Dummy User");
    }

    public static AuthorizationUser dummyUser(String name) {

        Name username = new Name();
        username.setValue(name);

        Description description = new Description();
        description.setValue("This is a test user");

        Owner owner = new Owner();
        owner.setValue("PRODYNA");

        AuthorizationUser user = new AuthorizationUser();
        user.setUsername(username);
        user.setDescription(description);
        user.setOwner(owner);
        user.setUserTypeRefId(1l);
        user.setDatatypeState(DatatypeState.INITIALIZED);

        return user;
    }

    public static AuthorizationGroup dummyGroup() {
        return dummyGroup("Dummy Group");
    }

    public static AuthorizationGroup dummyGroup(String name) {

        Name groupName = new Name();
        groupName.setValue(name);

        Description description = new Description();
        description.setValue("AdminGroup Description");

        Owner owner = new Owner();
        owner.setValue("PRODYNA");

        AuthorizationGroup group = new AuthorizationGroup();
        group.setGroupname(groupName);
        group.setDescription(description);
        group.setOwner(owner);
        group.setGroupTypeRefId(1l);
        group.setDatatypeState(DatatypeState.INITIALIZED);

        return group;
    }

    public static AuthorizationRole dummyRole() {
        return dummyRole("Dummy Role");
    }

    public static AuthorizationRole dummyRole(String name) {

        Name roleName = new Name();
        roleName.setValue(name);

        Description description = new Description();
        description.setValue("Role Description");

        Owner owner = new Owner();
        owner.setValue("PRODYNA");

        AuthorizationRole role = new AuthorizationRole();
        role.setRolename(roleName);
        role.setDescription(description);
        role.setOwner(owner);
        role.setRoleTypeRefId(1l);
        role.setDatatypeState(DatatypeState.INITIALIZED);

        return role;
    }

    public static AuthorizationPermission dummyPermission() {
        return dummyPermission("Dummy Permission");
    }

    public static AuthorizationPermission dummyPermission(String name) {

        Name permissionName = new Name();
        permissionName.setValue(name);

        Description description = new Description();
        description.setValue("Permission Description");

        Owner owner = new Owner();
        owner.setValue("PRODYNA");

        AuthorizationPermission permission = new AuthorizationPermission();
        permission.setPermissionname(permissionName);
        permission.setDescription(description);
        permission.setOwner(owner);
        permission.setPermissionTypeRefId(1l);
        permission.setDatatypeState(DatatypeState.INITIALIZED);

        return permission;
    }

    public static AuthorizationGroup create(AuthorizationComponent component, AuthorizationGroup group)
            throws ServiceException {

        group.setDatatypeState(DatatypeState.INITIALIZED);

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();
        msg.setAuthorizationGroup(group);

        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationGroup(rq);

        return rs.getResponseMessage().getAuthorizationGroup();
    }

    public static AuthorizationGroup find(AuthorizationComponent component, AuthorizationGroup group)
            throws ServiceException {

        AuthorizationGroupMsg msg = new AuthorizationGroupMsg();
        msg.setAuthorizationGroup(group);

        ServiceRequest<AuthorizationGroupMsg> rq = new ServiceRequest<AuthorizationGroupMsg>(
                RuntimeTestSupport.createServiceContext());

        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationGroupMaintainMsg> rs = component.getResolveAuthorization()
                .resolveAuthorizationGroup(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationGroup());

        return rs.getResponseMessage().getAuthorizationGroup();
    }

    public static void remove(AuthorizationComponent component, AuthorizationGroup group) throws ServiceException {

        group = find(component, group);

        group.setDatatypeState(DatatypeState.DELETED);

        AuthorizationGroupMaintainMsg msg = new AuthorizationGroupMaintainMsg();
        msg.setAuthorizationGroup(group);

        ServiceRequest<AuthorizationGroupMaintainMsg> rq = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);
        component.getMaintainAuthorization().maintainAuthorizationGroup(rq);
    }

    public static AuthorizationUser create(AuthorizationComponent component, AuthorizationUser user,
            AuthorizationGroup... groups) throws ServiceException {

        user.setDatatypeState(DatatypeState.INITIALIZED);

        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        msg.setAuthorizationUser(user);
        msg.getAuthorizationGroupList().addAll(Arrays.asList(groups));

        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationUser(rq);

        return rs.getResponseMessage().getAuthorizationUser();
    }

    public static AuthorizationUser find(AuthorizationComponent component, AuthorizationUser user)
            throws ServiceException {

        AuthorizationUserMsg msg = new AuthorizationUserMsg();
        msg.setAuthorizationUser(user);

        ServiceRequest<AuthorizationUserMsg> rq = new ServiceRequest<AuthorizationUserMsg>(
                RuntimeTestSupport.createServiceContext());

        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationUserMaintainMsg> rs = component.getResolveAuthorization()
                .resolveAuthorizationUser(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationUser());

        return rs.getResponseMessage().getAuthorizationUser();
    }

    public static void remove(AuthorizationComponent component, AuthorizationUser user) throws ServiceException {

        user = find(component, user);

        user.setDatatypeState(DatatypeState.DELETED);

        AuthorizationUserMaintainMsg msg = new AuthorizationUserMaintainMsg();
        msg.setAuthorizationUser(user);

        ServiceRequest<AuthorizationUserMaintainMsg> rq = new ServiceRequest<AuthorizationUserMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);
        component.getMaintainAuthorization().maintainAuthorizationUser(rq);
    }

    public static AuthorizationRole create(AuthorizationComponent component, AuthorizationRole role)
            throws ServiceException {

        role.setDatatypeState(DatatypeState.INITIALIZED);

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        msg.setAuthorizationRole(role);

        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationRole(rq);

        return rs.getResponseMessage().getAuthorizationRole();
    }

    public static AuthorizationRole find(AuthorizationComponent component, AuthorizationRole role)
            throws ServiceException {

        AuthorizationRoleMsg msg = new AuthorizationRoleMsg();
        msg.setAuthorizationRole(role);

        ServiceRequest<AuthorizationRoleMsg> rq = new ServiceRequest<AuthorizationRoleMsg>(
                RuntimeTestSupport.createServiceContext());

        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationRoleMaintainMsg> rs = component.getResolveAuthorization()
                .resolveAuthorizationRole(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationRole());

        return rs.getResponseMessage().getAuthorizationRole();
    }

    public static void remove(AuthorizationComponent component, AuthorizationRole role) throws ServiceException {

        role = find(component, role);

        role.setDatatypeState(DatatypeState.DELETED);

        AuthorizationRoleMaintainMsg msg = new AuthorizationRoleMaintainMsg();
        msg.setAuthorizationRole(role);

        ServiceRequest<AuthorizationRoleMaintainMsg> rq = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);
        component.getMaintainAuthorization().maintainAuthorizationRole(rq);
    }

    public static void removeAll(final AuthorizationComponent component, AuthorizationGroup group)
            throws ServiceException {

        DatatypeVisitor visitor = new DatatypeVisitor() {

            @Override
            public void visit(Datatype datatype) throws VisitorException {
                try {
                    if (datatype instanceof AuthorizationGroup) {
                        remove(component, (AuthorizationGroup) datatype);
                    } else if (datatype instanceof AuthorizationUser) {
                        remove(component, (AuthorizationUser) datatype);
                    } else if (datatype instanceof AuthorizationRole) {
                        remove(component, (AuthorizationRole) datatype);
                    } else if (datatype instanceof AuthorizationPermission) {
                        remove(component, (AuthorizationPermission) datatype);
                    }
                } catch (ServiceException se) {
                    throw new VisitorException(se);
                }

                super.visit(datatype);
            }
        };

        try {
            group.accept(visitor);
        } catch (VisitorException e) {
            throw new ServiceException(e.getCause());
        }
    }

    public static AuthorizationPermission addUsers(AuthorizationComponent component,
            AuthorizationPermission permission, AuthorizationUser... user) throws ServiceException {

        permission.setDatatypeState(DatatypeState.INITIALIZED);

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        msg.setAuthorizationPermission(permission);
        msg.getAuthorizationUserList().addAll(Arrays.asList(user));

        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = component.getMaintainAuthorization()
                .maintainAuthorizationPermission(rq);

        return rs.getResponseMessage().getAuthorizationPermission();
    }

    public static AuthorizationPermission create(AuthorizationComponent component,
            AuthorizationPermission authorizationPermission) throws Exception {

        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                RuntimeTestSupport.createServiceContext());

        AuthorizationPermissionMaintainMsg requestMessage = new AuthorizationPermissionMaintainMsg();
        requestMessage.setAuthorizationPermission(authorizationPermission);
        rq.setRequestMessage(requestMessage);
        authorizationPermission = component.getMaintainAuthorization().maintainAuthorizationPermission(rq)
                .getResponseMessage().getAuthorizationPermission();

        return authorizationPermission;
    }

    public static AuthorizationPermission find(AuthorizationComponent component, AuthorizationPermission permission)
            throws ServiceException {

        AuthorizationPermissionMsg msg = new AuthorizationPermissionMsg();
        msg.setAuthorizationPermission(permission);

        ServiceRequest<AuthorizationPermissionMsg> rq = new ServiceRequest<AuthorizationPermissionMsg>(
                RuntimeTestSupport.createServiceContext());

        rq.setRequestMessage(msg);

        ServiceResponse<AuthorizationPermissionMaintainMsg> rs = component.getResolveAuthorization()
                .resolveAuthorizationPermission(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());
        Assert.assertNotNull(rs.getResponseMessage().getAuthorizationPermission());

        return rs.getResponseMessage().getAuthorizationPermission();
    }

    public static void remove(AuthorizationComponent component, AuthorizationPermission permission)
            throws ServiceException {

        permission = find(component, permission);

        permission.setDatatypeState(DatatypeState.DELETED);

        AuthorizationPermissionMaintainMsg msg = new AuthorizationPermissionMaintainMsg();
        msg.setAuthorizationPermission(permission);

        ServiceRequest<AuthorizationPermissionMaintainMsg> rq = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                RuntimeTestSupport.createServiceContext());
        rq.setRequestMessage(msg);
        component.getMaintainAuthorization().maintainAuthorizationPermission(rq);
    }

}
