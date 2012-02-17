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
package org.nabucco.framework.common.authorization.impl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;

/**
 * GetInformationServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class GetInformationServiceHandlerImpl extends GetInformationServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected AuthorizationInformationRs getInformation(UserRq rq) throws AuthorizationException {
        UserId userId = rq.getUserId();

        if (userId == null || userId.getValue() == null) {
            throw new AuthorizationException("Cannot resolve authorization information for user with id [null].");
        }

        Name username = new Name(userId.getValue());

        try {
            List<AuthorizationGroup> groups = this.loadGroups(username);
            List<AuthorizationRole> roles = this.loadRoles(username);
            List<AuthorizationPermission> permissions = this.loadPermissions(username);

            AuthorizationInformationRs rs = new AuthorizationInformationRs();
            rs.getGroupList().addAll(groups);
            rs.getRoleList().addAll(roles);
            rs.getPermissionList().addAll(permissions);

            return rs;

        } catch (PersistenceException pe) {
            throw new AuthorizationException("Cannot resolve authorization information for user with id ["
                    + userId + "].", pe);
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
    private List<AuthorizationGroup> loadGroups(Name username) throws PersistenceException {

        List<AuthorizationGroup> groupList = new ArrayList<AuthorizationGroup>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", username);

        StringBuilder query = new StringBuilder();
        query.append("select g from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" where u.username = :userId");

        groupList.addAll(this.<AuthorizationGroup> executeQuery(query.toString(), parameter));

        return groupList;
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
    private List<AuthorizationRole> loadRoles(Name username) throws PersistenceException {

        List<AuthorizationRole> roleList = new ArrayList<AuthorizationRole>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", username);

        StringBuilder query = new StringBuilder();
        query.append("select r from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" where u.username = :userId");

        roleList.addAll(this.<AuthorizationRole> executeQuery(query.toString(), parameter));

        query = new StringBuilder();
        query.append("select r from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" where u.username = :userId");

        roleList.addAll(this.<AuthorizationRole> executeQuery(query.toString(), parameter));

        return roleList;
    }

    /**
     * Checks whether a user has the given permission.
     * 
     * @param username
     *            the name of the user
     * 
     * @return the list of permissions of a user
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<AuthorizationPermission> loadPermissions(Name username) throws PersistenceException {

        List<AuthorizationPermission> permissionList = new ArrayList<AuthorizationPermission>();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", username);

        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.permissionListJPA up");
        query.append(" inner join up.permission p");
        query.append(" where u.username = :userId");

        permissionList.addAll(this.<AuthorizationPermission> executeQuery(query.toString(), parameter));

        query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.username = :userId");

        permissionList.addAll(this.<AuthorizationPermission> executeQuery(query.toString(), parameter));

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permission p");
        query.append(" where u.username = :userId");

        permissionList.addAll(this.<AuthorizationPermission> executeQuery(query.toString(), parameter));

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.username = :userId");

        permissionList.addAll(this.<AuthorizationPermission> executeQuery(query.toString(), parameter));

        return permissionList;
    }

    /**
     * Execute the search query.
     * 
     * @param queryString
     *            the query string
     * @param parameterMap
     *            the map of parameters
     * 
     * @return the list of authorization datatypes
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private <T extends NabuccoDatatype> List<T> executeQuery(String queryString, Map<String, Object> parameterMap)
            throws PersistenceException {

        NabuccoQuery<T> query = this.getPersistenceManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        List<T> result = query.getResultList();

        return result;
    }

}
