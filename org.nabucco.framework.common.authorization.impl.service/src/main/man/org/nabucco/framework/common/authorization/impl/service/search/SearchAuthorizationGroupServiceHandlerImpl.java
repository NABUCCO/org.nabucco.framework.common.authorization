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
package org.nabucco.framework.common.authorization.impl.service.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;

import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorizationGroupServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchAuthorizationGroupServiceHandlerImpl extends
        SearchAuthorizationGroupServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationSearchMsg request;

    private Map<String, Object> parameterMap;

    private Set<AuthorizationGroup> resultSet;

    @Override
    public AuthorizationGroupListMsg searchAuthorizationGroup(AuthorizationSearchMsg msg)
            throws SearchException {

        this.request = msg;
        this.resultSet = new HashSet<AuthorizationGroup>();

        try {
            this.search();
        } catch (SearchException se) {
            throw se;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new SearchException("Error searching AuthorizationGroup. " + e.getMessage());
        }

        AuthorizationGroupListMsg response = new AuthorizationGroupListMsg();
        for (AuthorizationGroup group : this.resultSet) {
            group.setDatatypeState(DatatypeState.PERSISTENT);
            response.getAuthorizationGroupList().add(group);
        }

        return response;
    }

    /**
     * Execute the search.
     * 
     * @throws SearchException
     *             when the search did not finish normally
     */
    private void search() throws SearchException {

        this.parameterMap = new HashMap<String, Object>();

        if (this.request.getParameterList().isEmpty()) {
            this.searchBasic();
        }

        for (AuthorizationSearchParameter param : this.request.getParameterList()) {
            this.putAuthorizationParameter(param);

            switch (param.getType()) {
            case GROUP:
                throw new SearchException(
                        "Search Parameter is not valid. Cannot search for AuthorizationGroup referenced by AuthorizationGroup.");

            case USER:
                this.searchByUser();
                break;

            case ROLE:
                this.searchByRole();
                break;

            case PERMISSION:
                this.searchByPermission();
                break;
            }

            this.parameterMap.clear();
        }
    }

    /**
     * Search Groups by user.
     */
    private void searchByUser() {

        // Group for User

        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" where (u.id = :id or :id is null)");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Groups by role.
     */
    private void searchByRole() {

        // Group for Role

        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where (r.id = :id or :id is null)");
        query.append(" and (r.rolename = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Groups by permission.
     */
    private void searchByPermission() {

        // Group for Permission

        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Group for Role -> Permission

        query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (r.permissioname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Execute a basic search.
     */
    private void searchBasic() {
        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Add basic parameters to the query.
     * 
     * @param query
     *            the query string
     */
    private void appendBasicParameter(StringBuilder query) {
        if (this.parameterMap.isEmpty()) {
            query.append(" where");
        } else {
            query.append(" and");
        }
        query.append(" (g.groupname = :name or :name is null)");
        query.append(" and (g.owner = :owner or :owner is null)");
        query.append(" and (g.groupType = :type or :type is null)");
        query.append(" and (g.description like :description or :description is null)");

        this.parameterMap.put("name", this.request.getName());
        this.parameterMap.put("owner", this.request.getOwner());
        this.parameterMap.put("type", this.request.getCodeType());
        this.parameterMap.put("description",
                QuerySupport.searchParameter(this.request.getDescription()));
    }

    /**
     * Add authorization search parameters to the query.
     * 
     * @param param
     *            the parameter
     */
    private void putAuthorizationParameter(AuthorizationSearchParameter param) {
        if (param.getId() != null) {
            this.parameterMap.put("id", param.getId().getValue());
        } else {
            this.parameterMap.put("id", null);
        }

        this.parameterMap.put("refName", param.getName());
    }

    /**
     * Execute the query
     * 
     * @param queryString
     *            the string holding the query
     */
    private void executeQuery(String queryString) {
        Query query = super.getEntityManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        @SuppressWarnings("unchecked")
        List<AuthorizationGroup> resultList = query.getResultList();
        this.resultSet.addAll(resultList);
    }

}
