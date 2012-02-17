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
package org.nabucco.framework.common.authorization.impl.service.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorizationGroupServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchAuthorizationUserServiceHandlerImpl extends SearchAuthorizationUserServiceHandler {

    private static final long serialVersionUID = 1L;

    private AuthorizationSearchMsg request;

    private Map<String, Object> parameterMap;

    private Set<AuthorizationUser> resultSet;

    @Override
    public AuthorizationUserListMsg searchAuthorizationUser(AuthorizationSearchMsg msg) throws SearchException {

        this.request = msg;
        this.resultSet = new HashSet<AuthorizationUser>();

        try {
            this.search();
        } catch (SearchException se) {
            throw se;
        } catch (Exception e) {
            throw new SearchException("Error searching AuthorizationUser.", e);
        }

        AuthorizationUserListMsg response = new AuthorizationUserListMsg();
        response.getAuthorizationUserList().addAll(this.resultSet);

        return response;
    }

    /**
     * Execute the search.
     * 
     * @throws SearchException
     *             when the search did not finish normally
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void search() throws SearchException, PersistenceException {

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
                throw new SearchException(
                        "Search Parameter is not valid. Cannot search for AuthorizationUser referenced by AuthorizationUser.");

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
     * Search Users by Group.
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void searchByGroup() throws PersistenceException {

        // User for Group

        StringBuilder query = new StringBuilder();
        query.append("select u from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" where (g.id = :id or :id is null)");
        query.append(" and (g.groupname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Users by Role.
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void searchByRole() throws PersistenceException {

        // User with Role

        StringBuilder query = new StringBuilder();
        query.append("select u from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" where (r.id = :id or :id is null)");
        query.append(" and (r.rolename = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // User with Group -> Role

        query = new StringBuilder();
        query.append("select u from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where (r.id = :id or :id is null)");
        query.append(" and (r.rolename = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Search Users by Permission.
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void searchByPermission() throws PersistenceException {

        // User with Permission

        StringBuilder query = new StringBuilder();
        query.append("select u from AuthorizationUser u");
        query.append(" inner join u.permissionListJPA up");
        query.append(" inner join up.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // User with Role -> Permission

        query = new StringBuilder();
        query.append("select u from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // User with Group -> Permission

        query = new StringBuilder();
        query.append("select u from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permissionList p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());

        // User with Group -> Role -> Permission

        query = new StringBuilder();
        query.append("select u from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where (p.id = :id or :id is null)");
        query.append(" and (p.permissionname = :refName or :refName is null)");

        this.appendBasicParameter(query);
        this.executeQuery(query.toString());
    }

    /**
     * Execute a basic search.
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void searchBasic() throws PersistenceException {
        StringBuilder query = new StringBuilder();
        query.append("select u from AuthorizationUser u");

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
        query.append(" (u.username = :name or :name is null)");
        query.append(" and (u.owner = :owner or :owner is null)");
        query.append(" and (u.description like :description or :description is null)");

        this.parameterMap.put("name", this.request.getName());
        this.parameterMap.put("owner", this.request.getOwner());
        this.parameterMap.put("description", QuerySupport.searchParameter(this.request.getDescription()));
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
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private void executeQuery(String queryString) throws PersistenceException {
        NabuccoQuery<AuthorizationUser> query = super.getPersistenceManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        this.resultSet.addAll(query.getResultList());
    }

}
