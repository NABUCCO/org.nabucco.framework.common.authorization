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
import org.nabucco.framework.base.facade.datatype.security.Group;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.authorization.GroupListRs;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * GetGroupsServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class GetGroupsServiceHandlerImpl extends GetGroupsServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected GroupListRs getGroups(UserRq rq) throws SearchException {
        UserId userId = rq.getUserId();

        if (userId == null || userId.getValue() == null) {
            throw new SearchException("Cannot resolve user groups for id [null].");
        }

        Name username = new Name(userId.getValue());

        try {
            List<Group> groups = this.loadGroups(username);

            GroupListRs rs = new GroupListRs();
            rs.getGroups().addAll(groups);

            return rs;

        } catch (PersistenceException pe) {
            throw new SearchException("Cannot resolve user groups for user-id [" + userId + "].", pe);
        }
    }

    /**
     * Checks whether a user has the given group.
     * 
     * @param username
     *            the name of the user
     * 
     * @return the list of groups of a user
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<Group> loadGroups(Name username) throws PersistenceException {

        List<Group> groupList = new ArrayList<Group>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", username);

        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" where u.username = :userId");

        groupList.addAll(this.executeQuery(query.toString(), parameter));

        return groupList;
    }

    /**
     * Execute the search query.
     * 
     * @param queryString
     *            the query string
     * @param parameterMap
     *            the map of parameters
     * 
     * @return the list of groups
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<AuthorizationGroup> executeQuery(String queryString, Map<String, Object> parameterMap)
            throws PersistenceException {

        NabuccoQuery<AuthorizationGroup> query = this.getPersistenceManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        List<AuthorizationGroup> result = query.getResultList();

        return result;
    }

}
