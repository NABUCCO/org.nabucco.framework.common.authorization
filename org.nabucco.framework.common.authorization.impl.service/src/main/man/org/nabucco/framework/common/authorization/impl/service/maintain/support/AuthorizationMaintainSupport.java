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
package org.nabucco.framework.common.authorization.impl.service.maintain.support;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionAccessor;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPassword;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;
import org.nabucco.framework.common.authorization.impl.service.util.EncryptionUtility;

/**
 * AuthorizationMaintainSupport
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationMaintainSupport {

    private PersistenceManager manager;

    /**
     * Creates a new {@link AuthorizationMaintainSupport} instance.
     * 
     * @param manager
     *            the persistence manager
     */
    public AuthorizationMaintainSupport(PersistenceManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Persistence Helper must be defined.");
        }
        this.manager = manager;
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
    public AuthorizationGroup maintainAuthorizationGroup(AuthorizationGroup group) throws PersistenceException {

        if (group == null) {
            throw new PersistenceException("Cannot maintain authorization group [null].");
        }

        if (group.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationGroup currentGroup = this.manager.find(group);

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
            if (group.getUserList().isTraversable()) {
                List<AuthorizationGroupUserRelation> unassignedUsers = NabuccoCollectionAccessor.getInstance()
                        .getUnassignedList(group.getUserList());

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
            if (group.getRoleList().isTraversable()) {
                List<AuthorizationGroupRoleRelation> unassignedRoles = NabuccoCollectionAccessor.getInstance()
                        .getUnassignedList(group.getRoleList());

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
            if (group.getPermissionList().isTraversable()) {
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

        group = this.manager.persist(group);
        if (group.getChildGroupList().isTraversable()) {
            group.getChildGroupList().size();
        }
        if (group.getUserList().isTraversable()) {
            group.getUserList().size();
        }
        if (group.getRoleList().isTraversable()) {
            group.getRoleList().size();
        }
        if (group.getPermissionList().isTraversable()) {
            group.getPermissionList().size();
        }

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
    public AuthorizationUser maintainAuthorizationUser(AuthorizationUser user) throws PersistenceException {

        if (user == null) {
            throw new PersistenceException("Cannot maintain authorization user [null].");
        }

        if (user.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationUser currentUser = this.manager.find(user);

            for (AuthorizationUserRoleRelation relation : currentUser.getRoleList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }
            for (AuthorizationUserPermissionRelation relation : currentUser.getPermissionList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }

            this.removeUserRelations(currentUser.getId());

            if (user.getPassword() != null) {
                user.getPassword().setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(user.getPassword());
            }

        } else {

            // Roles
            if (user.getRoleList().isTraversable()) {
                List<AuthorizationUserRoleRelation> unassignedRoles = NabuccoCollectionAccessor.getInstance()
                        .getUnassignedList(user.getRoleList());

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
            if (user.getPermissionList().isTraversable()) {
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

            AuthorizationUserPassword password = user.getPassword();
            if (password == null || password.getPassword() == null || password.getPassword().getValue() == null) {
                password = new AuthorizationUserPassword();
                password.setDatatypeState(DatatypeState.INITIALIZED);
                password.setPassword("");

                user.setPassword(password);
            }

            if (password.getDatatypeState() != DatatypeState.PERSISTENT) {
                String encrypted = EncryptionUtility.encrypt(password.getPassword().getValue());
                user.getPassword().getPassword().setValue(encrypted);

                password = this.manager.persist(password);
                user.setPassword(password);
            }
        }

        user = this.manager.persist(user);
        if (user.getRoleList().isTraversable()) {
            user.getRoleList().size();
        }
        if (user.getPermissionList().isTraversable()) {
            user.getPermissionList().size();
        }

        return user;
    }

    /**
     * Load and remove the loading relations pointing on an authorization user.
     * 
     * @throws PersistenceException
     *             when the relations cannot be removed
     */
    private void removeUserRelations(Long id) throws PersistenceException {
        NabuccoQuery<AuthorizationGroupUserRelation> query = this.manager
                .createQuery("select r from AuthorizationGroupUserRelation r where r.user.id = :id");

        query.setParameter(AuthorizationUser.ID, id);

        List<AuthorizationGroupUserRelation> relations = query.getResultList();
        for (AuthorizationGroupUserRelation relation : relations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            this.manager.persist(relation);
        }
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
    public AuthorizationRole maintainAuthorizationRole(AuthorizationRole role) throws PersistenceException {

        if (role == null) {
            throw new PersistenceException("Cannot maintain authorization role [null].");
        }

        if (role.getDatatypeState() == DatatypeState.DELETED) {

            AuthorizationRole currentRole = this.manager.find(role);

            for (AuthorizationRolePermissionRelation relation : currentRole.getPermissionList()) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.maintainRelation(relation);
            }

            this.removeRoleRelations(currentRole.getId());

        } else {

            // Permissions
            if (role.getPermissionList().isTraversable()) {
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

        role = this.manager.persist(role);
        if (role.getPermissionList().isTraversable()) {
            role.getPermissionList().size();
        }

        return role;
    }

    /**
     * Load and remove the loading relations pointing on an authorization role.
     * 
     * @throws PersistenceException
     *             when the relations cannot be removed
     */
    private void removeRoleRelations(Long id) throws PersistenceException {

        // Groups
        {
            NabuccoQuery<AuthorizationGroupRoleRelation> query = this.manager
                    .createQuery("select r from AuthorizationGroupRoleRelation r where r.role.id = :id");

            query.setParameter(AuthorizationUser.ID, id);

            List<AuthorizationGroupRoleRelation> relations = query.getResultList();
            for (AuthorizationGroupRoleRelation relation : relations) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(relation);
            }
        }

        // Users
        {
            NabuccoQuery<AuthorizationUserRoleRelation> query = this.manager
                    .createQuery("select r from AuthorizationUserRoleRelation r where r.role.id = :id");

            query.setParameter(AuthorizationUser.ID, id);

            List<AuthorizationUserRoleRelation> relations = query.getResultList();
            for (AuthorizationUserRoleRelation relation : relations) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(relation);
            }
        }
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
    public List<AuthorizationPermission> maintainAuthorizationPermission(List<AuthorizationPermission> permissionList)
            throws PersistenceException {
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
    public AuthorizationPermission maintainAuthorizationPermission(AuthorizationPermission permission)
            throws PersistenceException {

        if (permission == null) {
            throw new PersistenceException("Cannot maintain authorization permission [null].");
        }

        if (permission.getDatatypeState() == DatatypeState.DELETED) {
            this.removePermissionRelations(permission.getId());
        }

        permission = this.manager.persist(permission);

        return permission;
    }

    /**
     * Load and remove the loading relations pointing on an authorization permission.
     * 
     * @throws PersistenceException
     *             when the relations cannot be removed
     */
    private void removePermissionRelations(Long id) throws PersistenceException {

        // Groups
        {
            NabuccoQuery<AuthorizationGroupPermissionRelation> query = this.manager
                    .createQuery("select r from AuthorizationGroupPermissionRelation r where r.permission.id = :id");

            query.setParameter(AuthorizationPermission.ID, id);

            List<AuthorizationGroupPermissionRelation> relations = query.getResultList();
            for (AuthorizationGroupPermissionRelation relation : relations) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(relation);
            }
        }

        // Users
        {
            NabuccoQuery<AuthorizationUserPermissionRelation> query = this.manager
                    .createQuery("select r from AuthorizationUserPermissionRelation r where r.permission.id = :id");

            query.setParameter(AuthorizationPermission.ID, id);

            List<AuthorizationUserPermissionRelation> relations = query.getResultList();
            for (AuthorizationUserPermissionRelation relation : relations) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(relation);
            }
        }

        // Roles
        {
            NabuccoQuery<AuthorizationRolePermissionRelation> query = this.manager
                    .createQuery("select r from AuthorizationRolePermissionRelation r where r.permission.id = :id");

            query.setParameter(AuthorizationPermission.ID, id);

            List<AuthorizationRolePermissionRelation> relations = query.getResultList();
            for (AuthorizationRolePermissionRelation relation : relations) {
                relation.setDatatypeState(DatatypeState.DELETED);
                this.manager.persist(relation);
            }
        }
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

        this.manager.persist(relation);

        return relation;
    }

}
