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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.User;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;

/**
 * HasPermissionServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class HasPermissionByNameServiceHandlerImpl extends HasPermissionByNameServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected AuthorizationRs hasPermissionByName(AuthorizationNameMsg msg) throws AuthorizationException {

        AuthorizationRs rsMsg = new AuthorizationRs();

        User user = this.validateSubject();

        try {
            Flag valid = new Flag(this.checkPermission(user, msg.getName()));
            rsMsg.setValid(valid);

        } catch (PersistenceException se) {
            throw new AuthorizationException("Cannot load permissions for user '" + user.getUsername() + "'.", se);
        }

        return rsMsg;
    }

    /**
     * Validate the current subject and returns the specified user.
     * 
     * @return the current user
     * 
     * @throws AuthorizationException
     */
    private User validateSubject() throws AuthorizationException {
        ServiceMessageContext context = super.getContext();
        Subject subject = context.getSubject();

        if (subject == null) {
            throw new AuthorizationException("Cannot authorize User for subject [null].");
        }
        if (subject.getUser() == null) {
            throw new AuthorizationException("Cannot authorize User [null].");
        }
        if (subject.getUser().getUsername() == null) {
            throw new AuthorizationException("Cannot authorize User with username [null].");
        }
        if (subject.getUser().getId() == null) {
            throw new AuthorizationException("Cannot authorize User with id [null].");
        }
        if (subject.getToken() == null) {
            throw new AuthorizationException("User " + subject.getUser().getUsername() + " is not logged in.");
        }

        return subject.getUser();
    }

    /**
     * Checks whether a user has the given permission.
     * 
     * @param user
     *            the user
     * 
     * @param permissionName
     *            the permission
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private boolean checkPermission(User user, Name permissionName) throws PersistenceException {

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("userId", user.getId());
        parameterMap.put("permissionName", permissionName);

        StringBuilder query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.permissionListJPA up");
        query.append(" inner join up.permission p");
        query.append(" where u.id = :userId");
        query.append(" and p.permissionname = :permissionName");

        if (this.executeQuery(query.toString(), parameterMap)) {
            return true;
        }

        query = new StringBuilder();
        query.append("select p from AuthorizationUser u");
        query.append(" inner join u.roleListJPA ur");
        query.append(" inner join ur.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.id = :userId");
        query.append(" and p.permissionname = :permissionName");

        if (this.executeQuery(query.toString(), parameterMap)) {
            return true;
        }

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.permissionListJPA gp");
        query.append(" inner join gp.permission p");
        query.append(" where u.id = :userId");
        query.append(" and p.permissionname = :permissionName");

        if (this.executeQuery(query.toString(), parameterMap)) {
            return true;
        }

        query = new StringBuilder();
        query.append("select p from AuthorizationGroup g");
        query.append(" inner join g.userListJPA gu");
        query.append(" inner join gu.user u");
        query.append(" inner join g.roleListJPA gr");
        query.append(" inner join gr.role r");
        query.append(" inner join r.permissionListJPA rp");
        query.append(" inner join rp.permission p");
        query.append(" where u.id = :userId");
        query.append(" and p.permissionname = :permissionName");

        if (this.executeQuery(query.toString(), parameterMap)) {
            return true;
        }

        return false;
    }

    /**
     * Execute the search query.
     * 
     * @param queryString
     *            the query string
     * @param parameterMap
     *            the map of parameters
     * 
     * @return <b>true</b> when the permission was found, <b>false</b> if not
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private boolean executeQuery(String queryString, Map<String, Object> parameterMap) throws PersistenceException {

        NabuccoQuery<AuthorizationPermission> query = super.getPersistenceManager().createQuery(queryString);

        for (String key : parameterMap.keySet()) {
            query.setParameter(key, parameterMap.get(key));
        }

        List<AuthorizationPermission> result = query.getResultList();

        if (result != null && !result.isEmpty()) {
            return true;
        }
        return false;
    }

}
