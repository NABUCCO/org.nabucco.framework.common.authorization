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
package org.nabucco.framework.common.authorization.impl.service.resolve;

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;

/**
 * ResolveAuthorizationGroupServiceHandlerImpl
 * 
 * @author Jens Wurm, Nicolas Moser, PRODYNA AG
 */
public class ResolveAuthorizationGroupServiceHandlerImpl extends ResolveAuthorizationGroupServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationGroup group;

    private AuthorizationGroupMaintainMsg response;

    @Override
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg msg) throws ResolveException {

        this.group = msg.getAuthorizationGroup();
        this.response = new AuthorizationGroupMaintainMsg();

        try {
            this.resolve();

            this.loadParentGroup();

        } catch (PersistenceException e) {
            throw new ResolveException("Cannot resolve AuthorizationGroup with id " + this.group.getId(), e);
        } catch (Exception e) {
            throw new ResolveException("Cannot resolve AuthorizationGroup with id " + this.group.getId(), e);
        }

        this.response.setAuthorizationGroup(this.group);
        return response;
    }

    /**
     * Resolve the current group and its child properties.
     * 
     * @throws PersistenceException
     *             when the group is not persistent and cannot be resolved
     */
    private void resolve() throws PersistenceException {
        this.group = super.getPersistenceManager().find(this.group);

        this.group.getChildGroupList().size();
        this.group.getUserList().size();
        this.group.getRoleList().size();
        this.group.getPermissionList().size();
    }

    /**
     * Load the parent group of this group.
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void loadParentGroup() throws PersistenceException {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select pg from AuthorizationGroup pg");
        queryString.append(" inner join pg.childGroupListJPA cg");
        queryString.append(" where (cg.id = :groupId)");

        NabuccoQuery<AuthorizationGroup> query = super.getPersistenceManager().createQuery(queryString.toString());
        query.setParameter("groupId", this.group.getId());

        List<AuthorizationGroup> resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            this.response.setParentAuthorizationGroup(resultList.get(0));
        }
    }

}
