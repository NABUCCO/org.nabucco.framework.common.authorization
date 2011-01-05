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
package org.nabucco.framework.common.authorization.impl.service.maintain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.impl.service.maintain.support.AuthorizationMaintainSupport;

/**
 * MaintainAuthorizationRoleServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationRoleServiceHandlerImpl extends
        MaintainAuthorizationRoleServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationRole role;

    private List<AuthorizationUser> userList;

    private List<AuthorizationGroup> groupList;

    private PersistenceHelper helper;

    @Override
    public AuthorizationRoleMaintainMsg maintainAuthorizationRole(AuthorizationRoleMaintainMsg msg)
            throws MaintainException {

        this.role = msg.getAuthorizationRole();
        this.userList = msg.getAuthorizationUserList();
        this.groupList = msg.getAuthorizationGroupList();

        this.helper = new PersistenceHelper(super.getEntityManager());

        this.maintain();

        AuthorizationRoleMaintainMsg response = new AuthorizationRoleMaintainMsg();
        response.getAuthorizationGroupList().addAll(this.groupList);
        response.getAuthorizationUserList().addAll(this.userList);
        response.setAuthorizationRole(this.role);

        return response;
    }

    /**
     * Maintains the role object.
     * 
     * @throws MaintainException
     *             when the role or its relations cannot be maintained
     */
    private void maintain() throws MaintainException {
        try {
            this.maintainRole();

            if (this.role.getDatatypeState() == DatatypeState.DELETED) {
                // TODO: Remove referencing relations!
            } else {
                this.maintainGroupRelations();
                this.maintainUserRelations();
            }

        } catch (Exception e) {
            throw new MaintainException("Error maintaining AuthorizationUser.", e);
        }
    }

    /**
     * Maintains the role object.
     * 
     * @throws MaintainException
     *             when the role cannot be maintained
     */
    private void maintainRole() throws PersistenceException {
        AuthorizationMaintainSupport support = new AuthorizationMaintainSupport(this.helper);
        this.role = support.maintainAuthorizationRole(this.role);
    }

    /**
     * Maintain the Group-Role relations.
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
        queryString.append(" inner join g.roleListJPA r");
        queryString.append(" where r.role.id = :roleId");

        if (!groupIdList.isEmpty()) {
            queryString.append(" and g.id not in (:groupIds)");
        }

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("roleId", this.role.getId());

        if (!groupIdList.isEmpty()) {
            query.setParameter("groupIds", groupIdList);
        }

        @SuppressWarnings("unchecked")
        List<AuthorizationGroupRoleRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationGroupRoleRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            this.helper.<AuthorizationGroupRoleRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationGroup group : this.groupList) {
            group = this.helper.find(AuthorizationGroup.class, group);

            boolean alreadyExistent = false;
            for (AuthorizationGroupRoleRelation relation : group.getRoleList()) {
                if (relation.getRole().getId() == this.role.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationGroupRoleRelation relation = new AuthorizationGroupRoleRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setRole(this.role);

                relation = this.helper.persist(relation);
                group.getRoleList().add(relation);
            }
        }
    }

    /**
     * Maintain the User-Role relations.
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
        queryString.append(" inner join g.roleListJPA r");
        queryString.append(" where r.role.id = :roleId");

        if (!userIdList.isEmpty()) {
            queryString.append(" and g.id not in (:userIds)");
        }

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("roleId", this.role.getId());

        if (!userIdList.isEmpty()) {
            query.setParameter("userIds", userIdList);
        }

        @SuppressWarnings("unchecked")
        List<AuthorizationUserRoleRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationUserRoleRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            this.helper.<AuthorizationUserRoleRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationUser user : this.userList) {
            user = this.helper.find(AuthorizationUser.class, user);

            boolean alreadyExistent = false;
            for (AuthorizationUserRoleRelation relation : user.getRoleList()) {
                if (relation.getRole().getId() == this.role.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationUserRoleRelation relation = new AuthorizationUserRoleRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setRole(this.role);

                relation = this.helper.persist(relation);
                user.getRoleList().add(relation);
            }
        }
    }

}
