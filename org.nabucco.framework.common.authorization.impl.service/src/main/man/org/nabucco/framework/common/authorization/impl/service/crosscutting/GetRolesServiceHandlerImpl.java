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
package org.nabucco.framework.common.authorization.impl.service.crosscutting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.security.Role;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.authorization.RoleListRs;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * GetRolesServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class GetRolesServiceHandlerImpl extends GetRolesServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected RoleListRs getRoles(UserRq rq) throws SearchException {
        UserId userId = rq.getUserId();

        if (userId == null || userId.getValue() == null) {
            throw new SearchException("Cannot resolve user roles for id [null].");
        }

        Name username = new Name(userId.getValue());

        try {
            List<Role> roles = this.loadRoles(username);

            RoleListRs rs = new RoleListRs();
            rs.getRoles().addAll(roles);

            return rs;

        } catch (PersistenceException pe) {
            throw new SearchException("Cannot resolve user roles for user-id [" + userId + "].", pe);
        }
    }

    /**
     * Checks whether a user has the given role.
     * 
     * @param username
     *            the name of the user
     * 
     * @return the list of roles of a user
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<Role> loadRoles(Name username) throws PersistenceException {

        List<Role> roleList = new ArrayList<Role>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", username);

        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" where u.username = :userId");

        roleList.addAll(this.executeQuery(query.toString(), parameter));

        query = new StringBuilder();
        query.append("select r from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where u.username = :userId");

        roleList.addAll(this.executeQuery(query.toString(), parameter));

        return roleList;
    }

    /**
     * Execute the search query.
     * 
     * @param queryString
     *            the query string
     * @param parameterMap
     *            the map of parameters
     * 
     * @return the list of roles
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<AuthorizationRole> executeQuery(String queryString, Map<String, Object> parameterMap)
            throws PersistenceException {

        NabuccoQuery<AuthorizationRole> query = this.getPersistenceManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        List<AuthorizationRole> result = query.getResultList();

        return result;
    }

}
