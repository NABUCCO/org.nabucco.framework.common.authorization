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
package org.nabucco.framework.common.authorization.impl.service.resolve;

import java.util.List;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;

/**
 * ResolveAuthorizationRoleServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationRoleServiceHandlerImpl extends
        ResolveAuthorizationRoleServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    private AuthorizationRole role;

    private AuthorizationRoleMaintainMsg response;

    @Override
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg msg)
            throws ResolveException {

        this.helper = new PersistenceHelper(super.getEntityManager());
        this.response = new AuthorizationRoleMaintainMsg();
        this.role = msg.getAuthorizationRole();

        try {
            this.resolve();

            this.loadParentGroups();
            this.loadParentUsers();

        } catch (PersistenceException e) {
            throw new ResolveException("Cannot resolve AuthorizationRole with id "
                    + this.role.getId());
        }

        this.response.setAuthorizationRole(this.role);
        return response;
    }

    /**
     * Resolve the role with its permissions.
     * 
     * @throws PersistenceException
     *             when the role is not persistent
     */
    private void resolve() throws PersistenceException {
        this.role = this.helper.find(AuthorizationRole.class, this.role);

        this.role.getPermissionList().size();
    }

    /**
     * Load all groups holding the role.
     */
    private void loadParentGroups() {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select g from AuthorizationGroup g");
        queryString.append(" inner join g.roleListJPA gr");
        queryString.append(" inner join gr.role r");
        queryString.append(" where (r.id = :roleId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("roleId", this.role.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationGroup> resultList = query.getResultList();
        this.response.getAuthorizationGroupList().addAll(resultList);
    }

    /**
     * Load all users holding the role.
     */
    private void loadParentUsers() {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select u from AuthorizationUser u");
        queryString.append(" inner join u.roleListJPA ur");
        queryString.append(" inner join ur.role r");
        queryString.append(" where (r.id = :roleId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("roleId", this.role.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationUser> resultList = query.getResultList();
        this.response.getAuthorizationUserList().addAll(resultList);
    }

}
