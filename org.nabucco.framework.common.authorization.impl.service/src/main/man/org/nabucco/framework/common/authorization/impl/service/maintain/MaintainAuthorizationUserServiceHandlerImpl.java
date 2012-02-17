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
import org.nabucco.framework.base.impl.service.maintain.PersistenceExceptionMapper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.impl.service.maintain.support.AuthorizationMaintainSupport;

/**
 * MaintainAuthorizationGroupServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationUserServiceHandlerImpl extends MaintainAuthorizationUserServiceHandler {

    private static final long serialVersionUID = 1L;

    /** The {@link AuthorizationUser} to maintain. */
    private AuthorizationUser user;

    /** The list of groups holding the user. */
    private List<AuthorizationGroup> groupList;

    @Override
    public AuthorizationUserMaintainMsg maintainAuthorizationUser(AuthorizationUserMaintainMsg msg)
            throws MaintainException {

        this.user = msg.getAuthorizationUser();
        this.groupList = msg.getAuthorizationGroupList();

        this.maintain();

        AuthorizationUserMaintainMsg response = new AuthorizationUserMaintainMsg();
        response.setAuthorizationUser(this.user);
        response.getAuthorizationGroupList().addAll(this.groupList);

        return response;
    }

    /**
     * Maintains the user object.
     * 
     * @throws MaintainException
     *             when the user or its relations
     */
    private void maintain() throws MaintainException {
        try {
            this.maintainUser();

            if (this.user.getDatatypeState() == DatatypeState.DELETED) {
                // TODO: Remove referencing relations!
            } else {
                this.maintainRelations();
            }

        } catch (Exception e) {
            throw new MaintainException("Error maintaining AuthorizationUser.", e);
        }
    }

    /**
     * Maintain the user.
     * 
     * @throws PersistenceException
     *             when the user cannot be maintained
     */
    private void maintainUser() throws PersistenceException {
        AuthorizationMaintainSupport support = new AuthorizationMaintainSupport(super.getPersistenceManager());
        this.user = support.maintainAuthorizationUser(this.user);
    }

    /**
     * Maintain the list of relations referencing the user.
     * 
     * @throws PersistenceException
     *             when the relations cannot be maintained
     */
    private void maintainRelations() throws PersistenceException {
        if (this.user.getId() == null) {
            throw new IllegalArgumentException("AuthorizationUser is not persistent.");
        }

        try {

            if (this.user.getDatatypeState() != DatatypeState.DELETED) {
                this.maintainGroupRelations();
            }

        } catch (javax.persistence.PersistenceException e) {
            throw PersistenceExceptionMapper.resolve(e, user.getClass().getName(), user.getId());
        }
    }

    /**
     * Maintain the Group-User relations.
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
        queryString.append(" inner join g.userListJPA r");
        queryString.append(" where r.user.id = :userId");

        if (!groupIdList.isEmpty()) {
            queryString.append(" and g.id not in (:groupIds)");
        }

        NabuccoQuery<AuthorizationGroupUserRelation> query = super.getPersistenceManager().createQuery(
                queryString.toString());
        query.setParameter("userId", this.user.getId());

        if (!groupIdList.isEmpty()) {
            query.setParameter("groupIds", groupIdList);
        }

        List<AuthorizationGroupUserRelation> removedRelations = query.getResultList();

        // Delete old relations!
        for (AuthorizationGroupUserRelation relation : removedRelations) {
            relation.setDatatypeState(DatatypeState.DELETED);
            super.getPersistenceManager().<AuthorizationGroupUserRelation> persist(relation);
        }

        // Create new relations!
        for (AuthorizationGroup group : this.groupList) {
            group = super.getPersistenceManager().find(group);

            boolean alreadyExistent = false;
            for (AuthorizationGroupUserRelation relation : group.getUserList()) {
                if (relation.getUser().getId() == this.user.getId()) {
                    alreadyExistent = true;
                }
            }

            if (!alreadyExistent) {
                AuthorizationGroupUserRelation relation = new AuthorizationGroupUserRelation();
                relation.setDatatypeState(DatatypeState.INITIALIZED);
                relation.setUser(this.user);

                relation = super.getPersistenceManager().persist(relation);
                group.getUserList().add(relation);
            }
        }
    }

}
