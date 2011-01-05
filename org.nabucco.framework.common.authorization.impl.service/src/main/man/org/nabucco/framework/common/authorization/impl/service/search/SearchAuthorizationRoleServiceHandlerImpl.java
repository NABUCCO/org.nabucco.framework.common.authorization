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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;

import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorizationRoleServiceHandlerImpl
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class SearchAuthorizationRoleServiceHandlerImpl extends
        SearchAuthorizationRoleServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationSearchMsg request;

    private Map<String, Object> parameterMap;

    private Set<AuthorizationRole> resultSet;

    @Override
    public AuthorizationRoleListMsg searchAuthorizationRole(AuthorizationSearchMsg msg)
            throws SearchException {

        this.request = msg;
        this.resultSet = new HashSet<AuthorizationRole>();

        try {
            this.search();
        } catch (SearchException se) {
            throw se;
        } catch (Exception e) {
            throw new SearchException("Error searching AuthorizationRoles.", e);
        }

        AuthorizationRoleListMsg response = new AuthorizationRoleListMsg();
        for (AuthorizationRole role : this.resultSet) {
            role.setDatatypeState(DatatypeState.PERSISTENT);
            response.getAuthorizationRoleList().add(role);
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
                this.searchByGroup();
                break;

            case USER:
                this.searchByUser();
                break;

            case ROLE:
                throw new SearchException(
                        "Search Parameter is not valid. Cannot search for AuthorizationRole referenced by AuthorizationRole.");

            case PERMISSION:
                this.searchByPermission();
                break;
            }

            this.parameterMap.clear();
        }
    }

    /**
     * Search Role by Group.
     */
    private void searchByGroup() {

        // Role for Group
        
        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationGroup g");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where (g.id = :id or :id is null)");
        query.append(" and (g.groupname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Role by User.
     */
    private void searchByUser() {

        // Role for User

        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationUser u");
        query.append(" inner join u.roleList r");
        query.append(" where (u.id = :id or :id is null)");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Role for Group -> User

        query = new StringBuilder();
        query.append("select r from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where u.id = :id");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Role by Permission.
     */
    private void searchByPermission() {

        // Role for Permission

        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationRole r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Execute a basic search.
     */
    private void searchBasic() {
        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationRole r");

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
        query.append(" (r.rolename = :name or :name is null)");
        query.append(" and (r.owner = :owner or :owner is null)");
        query.append(" and (r.roleType = :type or :type is null)");
        query.append(" and (r.description like :description or :description is null)");

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
        List<AuthorizationRole> resultList = query.getResultList();
        this.resultSet.addAll(resultList);
    }

}
