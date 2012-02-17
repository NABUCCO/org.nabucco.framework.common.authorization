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
package org.nabucco.framework.common.authorization.impl.service.maintain;

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.impl.service.maintain.support.AuthorizationMaintainSupport;

/**
 * MaintainAuthorizationPermissionServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationPermissionServiceHandlerImpl extends MaintainAuthorizationPermissionServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationPermission permission;

    private List<AuthorizationRole> roleList;

    private List<AuthorizationUser> userList;

    private List<AuthorizationGroup> groupList;

    @Override
    public AuthorizationPermissionMaintainMsg maintainAuthorizationPermission(AuthorizationPermissionMaintainMsg msg)
            throws MaintainException {

        this.permission = msg.getAuthorizationPermission();
        this.roleList = msg.getAuthorizationRoleList();
        this.userList = msg.getAuthorizationUserList();
        this.groupList = msg.getAuthorizationGroupList();

        this.maintain();

        AuthorizationPermissionMaintainMsg response = new AuthorizationPermissionMaintainMsg();
        response.getAuthorizationGroupList().addAll(this.groupList);
        response.getAuthorizationUserList().addAll(this.userList);
        response.getAuthorizationRoleList().addAll(this.roleList);
        response.setAuthorizationPermission(this.permission);

        return response;
    }

    /**
     * Maintains the permission object.
     * 
     * @throws MaintainException
     */
    private void maintain() throws MaintainException {
        try {
            this.maintainPermission();

            if (this.permission.getDatatypeState() == DatatypeState.DELETED) {
                // TODO: Remove referencing relations!
            } else {
                this.maintainGroupRelations();
                this.maintainUserRelations();
                this.maintainRoleRelations();
            }

        } catch (Exception e) {
            throw new MaintainException("Error maintaining AuthorizationPermission.", e);
        }
    }

    /**
     * Maintains the permission object.
     * 
     * @throws MaintainException
     *             when the permission cannot be maintained
     */
    private void maintainPermission() throws PersistenceException {
        AuthorizationMaintainSupport support = new AuthorizationMaintainSupport(super.getPersistenceManager());
        this.permission = support.maintainAuthorizationPermission(this.permission);
    }

    /**
     * Maintain the Group-Permission relations.
     * 
     * @throws PersistenceException
     *             when the relations cannot be maintained
     */
    private void maintainGroupRelations() throws PersistenceException {

        List<Long> groupIdList = new ArrayList<Long>();
        for (AuthorizationGroup group : this.groupList) {
            if (group == null || group.getId() == null) {
                throw new PersistenceException("AuthorizationGroup is not persistent.");
            }
            groupIdList.add(group.getId());
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append("select r from AuthorizationGroup g");
        queryString.append(" inner join g.permissionListJPA r");
        queryString.append(" where r.permission.id = :permissionId");

        if (!groupIdList.isEmpty()) {
            queryString.append(" and g.id not in (:groupIds)");
        }

        NabuccoQuery<AuthorizationGroupPermissionRelation> query = super.getPersistenceManager().createQuery(
                queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        if (!groupIdList.isEmpty()) {
            query.setParameter("groupIds", groupIdList);
        }

        List<AuthorizationGroupPermissionRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationGroupPermissionRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            super.getPersistenceManager().<AuthorizationGroupPermissionRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationGroup group : this.groupList) {
            group = super.getPersistenceManager().find(group);

            boolean alreadyExistent = false;
            for (AuthorizationGroupPermissionRelation relation : group.getPermissionList()) {
                if (relation.getPermission().getId() == this.permission.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationGroupPermissionRelation relation = new AuthorizationGroupPermissionRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setPermission(this.permission);

                relation = super.getPersistenceManager().persist(relation);
                group.getPermissionList().add(relation);
            }
        }
    }

    /**
     * Maintain the User-Permission relations.
     * 
     * @throws PersistenceException
     *             when the relations cannot be maintained
     */
    private void maintainUserRelations() throws PersistenceException {

        List<Long> userIdList = new ArrayList<Long>();
        for (AuthorizationUser user : this.userList) {
            if (user == null || user.getId() == null) {
                throw new PersistenceException("AuthorizationUser is not persistent.");
            }
            userIdList.add(user.getId());
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append("select r from AuthorizationUser g");
        queryString.append(" inner join g.permissionListJPA r");
        queryString.append(" where r.permission.id = :permissionId");

        if (!userIdList.isEmpty()) {
            queryString.append(" and g.id not in (:userIds)");
        }

        NabuccoQuery<AuthorizationUserPermissionRelation> query = super.getPersistenceManager().createQuery(
                queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        if (!userIdList.isEmpty()) {
            query.setParameter("userIds", userIdList);
        }

        List<AuthorizationUserPermissionRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationUserPermissionRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            super.getPersistenceManager().<AuthorizationUserPermissionRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationUser user : this.userList) {
            user = super.getPersistenceManager().find(user);

            boolean alreadyExistent = false;
            for (AuthorizationUserPermissionRelation relation : user.getPermissionList()) {
                if (relation.getPermission().getId() == this.permission.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationUserPermissionRelation relation = new AuthorizationUserPermissionRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setPermission(this.permission);

                relation = super.getPersistenceManager().persist(relation);
                user.getPermissionList().add(relation);
            }
        }
    }

    /**
     * Maintain the Role-Permission relations.
     * 
     * @throws PersistenceException
     *             when the relations cannot be maintained
     */
    private void maintainRoleRelations() throws PersistenceException {

        List<Long> roleIdList = new ArrayList<Long>();
        for (AuthorizationRole role : this.roleList) {
            if (role == null || role.getId() == null) {
                throw new PersistenceException("AuthorizationRole is not persistent.");
            }
            roleIdList.add(role.getId());
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append("select r from AuthorizationRole g");
        queryString.append(" inner join g.permissionListJPA r");
        queryString.append(" where r.permission.id = :permissionId");

        if (!roleIdList.isEmpty()) {
            queryString.append(" and g.id not in (:roleIds)");
        }

        NabuccoQuery<AuthorizationRolePermissionRelation> query = super.getPersistenceManager().createQuery(
                queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        if (!roleIdList.isEmpty()) {
            query.setParameter("roleIds", roleIdList);
        }

        List<AuthorizationRolePermissionRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationRolePermissionRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            super.getPersistenceManager().<AuthorizationRolePermissionRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationRole role : this.roleList) {
            role = super.getPersistenceManager().find(role);

            boolean alreadyExistent = false;
            for (AuthorizationRolePermissionRelation relation : role.getPermissionList()) {
                if (relation.getPermission().getId() == this.permission.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationRolePermissionRelation relation = new AuthorizationRolePermissionRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setPermission(this.permission);

                relation = super.getPersistenceManager().persist(relation);
                role.getPermissionList().add(relation);
            }
        }
    }

}
