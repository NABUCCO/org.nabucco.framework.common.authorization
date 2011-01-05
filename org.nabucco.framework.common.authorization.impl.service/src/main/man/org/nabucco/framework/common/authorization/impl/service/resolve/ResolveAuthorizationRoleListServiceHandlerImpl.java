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

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;

/**
 * Implements service for resolving a role.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class ResolveAuthorizationRoleListServiceHandlerImpl extends
        ResolveAuthorizationRoleListServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    @Override
    protected AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg msg)
            throws ResolveException {

        this.helper = new PersistenceHelper(super.getEntityManager());

        AuthorizationRoleListMsg response = new AuthorizationRoleListMsg();
        List<AuthorizationRole> roleList = msg.getAuthorizationRoleList();

        for (AuthorizationRole role : roleList) {

            try {
                AuthorizationRole resolvedRole = this.resolve(role);
                response.getAuthorizationRoleList().add(resolvedRole);

            } catch (PersistenceException e) {
                throw new ResolveException("Cannot resolve AuthorizationRole with id "
                        + role.getId(), e);
            } catch (Exception e) {
                throw new ResolveException("Cannot resolve AuthorizationRole with id "
                        + role.getId(), e);
            }
        }

        return response;
    }

    /**
     * Resolve the current role and its child properties.
     * 
     * @param role
     *            the role to resolve
     * 
     * @return the resolved role
     * 
     * @throws PersistenceException
     *             when the role is not persistent and cannot be resolved
     */
    private AuthorizationRole resolve(AuthorizationRole role) throws PersistenceException {
        role = this.helper.find(AuthorizationRole.class, role);

        role.getPermissionList().size();

        return role;
    }

}
