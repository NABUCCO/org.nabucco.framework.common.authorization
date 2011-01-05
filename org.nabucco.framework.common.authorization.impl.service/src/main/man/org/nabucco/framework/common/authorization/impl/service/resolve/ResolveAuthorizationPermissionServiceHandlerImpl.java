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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;

/**
 * ResolveAuthorizationPermissionServiceHandlerImpl
 * 
 * @author Jens Wurm, PRODYNA AG
 */
public class ResolveAuthorizationPermissionServiceHandlerImpl extends
        ResolveAuthorizationPermissionServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    private AuthorizationPermissionMaintainMsg response;

    private AuthorizationPermission permission;

    @Override
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(
            AuthorizationPermissionMsg msg) throws ResolveException {

        this.permission = msg.getAuthorizationPermission();
        this.response = new AuthorizationPermissionMaintainMsg();
        this.helper = new PersistenceHelper(super.getEntityManager());

        try {
            this.resolve();

            this.loadParentGroups();
            this.loadParentUsers();
            this.loadParentRoles();

        } catch (PersistenceException e) {
            throw new ResolveException("Cannot resolve AuthorizationPermission with id "
                    + this.permission.getId());
        }

        this.response.setAuthorizationPermission(this.permission);
        return response;
    }

    /**
     * Resolve the permission.
     * 
     * @throws PersistenceException
     *             when the permission is not persistent
     */
    private void resolve() throws PersistenceException {
        this.permission = this.helper.find(AuthorizationPermission.class, this.permission);
    }

    /**
     * Load all groups holding the permission.
     */
    private void loadParentGroups() throws ResolveException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select g from AuthorizationGroup g");
        queryString.append(" inner join g.permissionListJPA gp");
        queryString.append(" inner join gp.permission p");
        queryString.append(" where (p.id = :permissionId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationGroup> resultList = query.getResultList();
        this.response.getAuthorizationGroupList().addAll(resultList);
    }

    /**
     * Load all users holding the permission.
     */
    private void loadParentUsers() throws ResolveException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select u from AuthorizationUser u");
        queryString.append(" inner join u.permissionListJPA up");
        queryString.append(" inner join up.permission p");
        queryString.append(" where (p.id = :permissionId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationUser> resultList = query.getResultList();
        this.response.getAuthorizationUserList().addAll(resultList);
    }

    /**
     * Load all roles holding the permission.
     */
    private void loadParentRoles() throws ResolveException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select r from AuthorizationRole r");
        queryString.append(" inner join r.permissionListJPA rp");
        queryString.append(" inner join rp.permission p");
        queryString.append(" where (p.id = :permissionId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("permissionId", this.permission.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationRole> resultList = query.getResultList();
        this.response.getAuthorizationRoleList().addAll(resultList);
    }

}
