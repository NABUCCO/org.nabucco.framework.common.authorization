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
package org.nabucco.framework.common.authorization.impl.service.maintain.support;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;
import org.nabucco.framework.common.authorization.impl.service.util.EncryptionUtility;

/**
 * AuthorizationMaintainSupport
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationMaintainSupport {

    private PersistenceHelper helper;

    /**
     * Creates a new {@link AuthorizationMaintainSupport} instance.
     * 
     * @param helper
     *            the persistence helper
     */
    public AuthorizationMaintainSupport(PersistenceHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Persistence Helper must be defined.");
        }
        this.helper = helper;
    }

    /**
     * Maintain a single {@link AuthorizationGroup} instance.
     * 
     * @param group
     *            the group to maintain
     * 
     * @return the persistet group
     * 
     * @throws PersistenceException
     */
    public AuthorizationGroup maintainAuthorizationGroup(AuthorizationGroup group)
            throws PersistenceException {

        if (group == null) {
            throw new PersistenceException("Cannot maintain authorization group [null].");
        }

        if (group.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationGroup currentGroup = this.helper.find(AuthorizationGroup.class, group);

            for (AuthorizationGroupUserRelation relation : currentGroup.getUserList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }
            for (AuthorizationGroupRoleRelation relation : currentGroup.getRoleList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }
            for (AuthorizationGroupPermissionRelation relation : currentGroup.getPermissionList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }

        } else {

            // Users
            if (isTraversable(group.getUserList())) {
                List<AuthorizationGroupUserRelation> unassignedUsers = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(group.getUserList());

                for (AuthorizationGroupUserRelation relation : group.getUserList()) {
                    this.maintainAuthorizationUser(relation.getUser());
                    this.maintainRelation(relation);
                }
                for (AuthorizationGroupUserRelation relation : unassignedUsers) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }

            // Roles
            if (isTraversable(group.getRoleList())) {
                List<AuthorizationGroupRoleRelation> unassignedRoles = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(group.getRoleList());

                for (AuthorizationGroupRoleRelation relation : group.getRoleList()) {
                    this.maintainAuthorizationRole(relation.getRole());
                    this.maintainRelation(relation);
                }
                for (AuthorizationGroupRoleRelation relation : unassignedRoles) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }

            // Permissions
            if (isTraversable(group.getPermissionList())) {
                List<AuthorizationGroupPermissionRelation> unassignedPermissions = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(group.getPermissionList());

                for (AuthorizationGroupPermissionRelation relation : group.getPermissionList()) {
                    this.maintainAuthorizationPermission(relation.getPermission());
                    this.maintainRelation(relation);
                }
                for (AuthorizationGroupPermissionRelation relation : unassignedPermissions) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }
        }

        group = this.helper.persist(group);

        return group;
    }

    /**
     * Maintain a single {@link AuthorizationUser} instance.
     * 
     * @param user
     *            the user to maintain
     * 
     * @return the persistet user
     * 
     * @throws PersistenceException
     */
    public AuthorizationUser maintainAuthorizationUser(AuthorizationUser user)
            throws PersistenceException {

        if (user == null) {
            throw new PersistenceException("Cannot maintain authorization user [null].");
        }

        if (user.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationUser currentUser = this.helper.find(AuthorizationUser.class, user);

            for (AuthorizationUserRoleRelation relation : currentUser.getRoleList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }
            for (AuthorizationUserPermissionRelation relation : currentUser.getPermissionList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }

        } else {

            // Roles
            if (isTraversable(user.getRoleList())) {
                List<AuthorizationUserRoleRelation> unassignedRoles = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(user.getRoleList());

                for (AuthorizationUserRoleRelation relation : user.getRoleList()) {
                    this.maintainAuthorizationRole(relation.getRole());
                    this.maintainRelation(relation);
                }
                for (AuthorizationUserRoleRelation relation : unassignedRoles) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }

            // Permissions
            if (isTraversable(user.getPermissionList())) {
                List<AuthorizationUserPermissionRelation> unassignedPermissions = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(user.getPermissionList());

                for (AuthorizationUserPermissionRelation relation : user.getPermissionList()) {
                    this.maintainAuthorizationPermission(relation.getPermission());
                    this.maintainRelation(relation);
                }
                for (AuthorizationUserPermissionRelation relation : unassignedPermissions) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }

            Password pwd = user.getPassword();
            if (pwd == null || pwd.getValue() == null) {
                pwd = new Password("");
            }

            String encrypted = EncryptionUtility.encrypt(pwd.getValue());
            user.setPassword(encrypted);
        }

        user = this.helper.persist(user);

        return user;
    }

    /**
     * Maintain a single {@link AuthorizationRole} instance.
     * 
     * @param role
     *            the role to maintain
     * 
     * @return the persistet role
     * 
     * @throws PersistenceException
     */
    public AuthorizationRole maintainAuthorizationRole(AuthorizationRole role)
            throws PersistenceException {

        if (role == null) {
            throw new PersistenceException("Cannot maintain authorization role [null].");
        }

        if (role.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationRole currentRole = this.helper.find(AuthorizationRole.class, role);

            for (AuthorizationRolePermissionRelation relation : currentRole.getPermissionList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }

        } else {

            // Permissions
            if (isTraversable(role.getPermissionList())) {
                List<AuthorizationRolePermissionRelation> unassignedPermissions = NabuccoCollectionAccessor
                        .getInstance().getUnassignedList(role.getPermissionList());

                for (AuthorizationRolePermissionRelation relation : role.getPermissionList()) {
                    this.maintainAuthorizationPermission(relation.getPermission());
                    this.maintainRelation(relation);
                }
                for (AuthorizationRolePermissionRelation relation : unassignedPermissions) {
                    relation.setDatatypeState(DatatypeState.DELETED);
                    this.maintainRelation(relation);
                }
            }
        }

        role = this.helper.persist(role);

        return role;
    }

    /**
     * Maintain a list of {@link AuthorizationPermission} instances.
     * 
     * @param groupList
     *            the list of authorization permissions
     * 
     * @return the persistet permissions
     * 
     * @throws PersistenceException
     */
    public List<AuthorizationPermission> maintainAuthorizationPermission(
            List<AuthorizationPermission> permissionList) throws PersistenceException {
        for (AuthorizationPermission permission : permissionList) {
            this.maintainAuthorizationPermission(permission);
        }
        return permissionList;
    }

    /**
     * Maintain a single {@link AuthorizationPermission} instance.
     * 
     * @param group
     *            the permission to maintain
     * 
     * @return the persistet permission
     * 
     * @throws PersistenceException
     */
    public AuthorizationPermission maintainAuthorizationPermission(
            AuthorizationPermission permission) throws PersistenceException {

        if (permission == null) {
            throw new PersistenceException("Cannot maintain authorization permission [null].");
        }

        permission = this.helper.persist(permission);

        return permission;
    }

    /**
     * Maintain the Authorization Datatype Relations.
     * 
     * @param <T>
     *            the relation type
     * @param relation
     *            the relation instance
     * 
     * @return the maintained relation
     * 
     * @throws PersistenceException
     *             when the relation cannot be maintained
     */
    private <T extends NabuccoDatatype> T maintainRelation(T relation) throws PersistenceException {
        if (relation == null) {
            throw new PersistenceException("Cannot maintain authorization relation [null].");
        }

        this.helper.persist(relation);

        return relation;
    }

    /**
     * Checks whether a list is traversable or not. Only {@link NabuccoList} that are not in state
     * LAZY are considered.
     * 
     * @param list
     *            the list to check
     * 
     * @return <b>true</b> if the list is traversable, <b>false</b> otherwise
     */
    private <T extends NabuccoDatatype> boolean isTraversable(List<T> list) {
        if (list == null) {
            return false;
        }
        if (!(list instanceof NabuccoList<?>)) {
            return true;
        }

        NabuccoList<T> nabuccoList = (NabuccoList<T>) list;
        if (nabuccoList.getState() != NabuccoCollectionState.LAZY) {
            return true;
        }
        return false;
    }

}
