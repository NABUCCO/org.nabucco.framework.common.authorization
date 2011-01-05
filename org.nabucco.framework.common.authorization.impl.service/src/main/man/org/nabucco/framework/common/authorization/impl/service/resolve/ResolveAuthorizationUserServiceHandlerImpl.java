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

import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * Implements resolve AuthorizationUser.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class ResolveAuthorizationUserServiceHandlerImpl extends
        ResolveAuthorizationUserServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    private AuthorizationUser user;

    private AuthorizationUserMaintainMsg response;

    @Override
    protected AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg msg)
            throws ResolveException {

        this.response = new AuthorizationUserMaintainMsg();
        this.helper = new PersistenceHelper(super.getEntityManager());
        this.user = msg.getAuthorizationUser();

        try {
            this.resolve();
            
            this.loadParentGroups();
            
        } catch (PersistenceException e) {
            throw new ResolveException("Cannot resolve AuthorizationUser with id "
                    + this.user.getId());
        }

        this.user.setPassword((Password) null);

        this.response.setAuthorizationUser(this.user);
        return response;
    }

    /**
     * Resolve the user with its roles and permissions.
     * 
     * @throws PersistenceException
     *             when the user is not persistent
     */
    private void resolve() throws PersistenceException {
        this.user = this.helper.find(AuthorizationUser.class, this.user);

        this.user.getRoleList().size();
        this.user.getPermissionList().size();
    }

    /**
     * Load all groups containing the user.
     */
    private void loadParentGroups() {

        StringBuilder queryString = new StringBuilder();
        queryString.append("select g from AuthorizationGroup g");
        queryString.append(" inner join g.userListJPA gu");
        queryString.append(" inner join gu.user u");
        queryString.append(" where (u.id = :userId)");

        Query query = super.getEntityManager().createQuery(queryString.toString());
        query.setParameter("userId", this.user.getId());

        @SuppressWarnings("unchecked")
        List<AuthorizationGroup> resultList = query.getResultList();
        this.response.getAuthorizationGroupList().addAll(resultList);
    }

}
