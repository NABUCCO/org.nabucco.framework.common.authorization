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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;

/**
 * ResolveAuthorizationUserListServiceHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class ResolveAuthorizationUserListServiceHandlerImpl extends
        ResolveAuthorizationUserListServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    @Override
    protected AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg msg)
            throws ResolveException {

        this.helper = new PersistenceHelper(super.getEntityManager());

        AuthorizationUserListMsg response = new AuthorizationUserListMsg();
        List<AuthorizationUser> userList = msg.getAuthorizationUserList();

        for (AuthorizationUser user : userList) {

            try {
                AuthorizationUser resolvedUser = this.resolve(user);
                response.getAuthorizationUserList().add(resolvedUser);

            } catch (PersistenceException e) {
                throw new ResolveException("Cannot resolve AuthorizationUser with id "
                        + user.getId(), e);
            } catch (Exception e) {
                throw new ResolveException("Cannot resolve AuthorizationUser with id "
                        + user.getId(), e);
            }
        }

        return response;
    }

    /**
     * Resolve the current user and its child properties.
     * 
     * @param user
     *            the user to resolve
     * 
     * @return the resolved user
     * 
     * @throws PersistenceException
     *             when the user is not persistent and cannot be resolved
     */
    private AuthorizationUser resolve(AuthorizationUser user) throws PersistenceException {
        user = this.helper.find(AuthorizationUser.class, user);

        user.getRoleList().size();
        user.getPermissionList().size();

        return user;
    }

}
