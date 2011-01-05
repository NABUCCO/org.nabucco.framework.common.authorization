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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;

import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorizationPermissionServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchAuthorizationPermissionServiceHandlerImpl extends
        SearchAuthorizationPermissionServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationSearchMsg request;

    private Map<String, Object> parameterMap;

    private Set<AuthorizationPermission> resultSet;

    @Override
    protected AuthorizationPermissionListMsg searchAuthorizationPermission(
            AuthorizationSearchMsg msg) throws SearchException {

        this.request = msg;
        this.resultSet = new HashSet<AuthorizationPermission>();

        try {
            this.search();
        } catch (SearchException se) {
            throw se;
        } catch (Exception e) {
            throw new SearchException("Error searching AuthorizationPermissions.", e);
        }

        AuthorizationPermissionListMsg response = new AuthorizationPermissionListMsg();
        for (AuthorizationPermission permission : this.resultSet) {
            permission.setDatatypeState(DatatypeState.PERSISTENT);
            response.getAuthorizationPermissionList().add(permission);
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
                this.searchByRole();
                break;

            case PERMISSION:
                throw new SearchException(
                        "Search Parameter is not valid. Cannot search for AuthorizationPermission referenced by AuthorizationPermission.");
            }

            this.parameterMap.clear();
        }
    }

    /**
     * Search Permissions by Group.
     */
    private void searchByGroup() {

        // Permissions on Group

        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permission p");
        query.append(" where (g.id = :id or :id is null)");
        query.append(" and (g.groupname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Permissions on Group -> Role

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join p.permission p");
        query.append(" where g.id = :id");
        query.append(" and (g.groupname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Permissions by User.
     */
    private void searchByUser() {

        // Permissions on User

        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.permissionListJPA up");
        query.append(" inner join up.permission p");
        query.append(" where (u.id = :id or :id is null)");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Permissions on User -> Role

        query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.id = :id");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Permissions on User -> Group

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permission p");
        query.append(" where u.id = :id");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // Permissions on User -> Group -> Role

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.id = :id");
        query.append(" and (u.username = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Permissions by Role.
     */
    private void searchByRole() {

        // Permissions on Role

        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationRole r");
        query.append(" inner join r.permissionList p");
        query.append(" where (r.id = :id or :id is null)");
        query.append(" and (r.rolename = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Execute a basic search.
     */
    private void searchBasic() {
        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationPermission p");

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
        query.append(" (p.permissionname = :name or :name is null)");
        query.append(" and (p.owner = :owner or :owner is null)");
        query.append(" and (p.permissionType = :type or :type is null)");
        query.append(" and (p.description like :description or :description is null)");

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
        List<AuthorizationPermission> resultList = query.getResultList();
        this.resultSet.addAll(resultList);
    }

}