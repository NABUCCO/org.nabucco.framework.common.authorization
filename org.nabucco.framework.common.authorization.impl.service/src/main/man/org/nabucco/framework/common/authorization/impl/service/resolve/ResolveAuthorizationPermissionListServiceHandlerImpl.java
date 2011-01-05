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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;

/**
 * ResolveAuthorizationPermissionListServiceHandler
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class ResolveAuthorizationPermissionListServiceHandlerImpl extends
        ResolveAuthorizationPermissionListServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected AuthorizationPermissionListMsg resolveAuthorizationPermissionList(
            AuthorizationPermissionListMsg msg) throws ResolveException {

        PersistenceHelper helper = new PersistenceHelper(super.getEntityManager());

        AuthorizationPermissionListMsg response = new AuthorizationPermissionListMsg();
        List<AuthorizationPermission> permissionList = msg.getAuthorizationPermissionList();

        for (AuthorizationPermission permission : permissionList) {

            try {
                AuthorizationPermission resolvedPermission = helper.find(
                        AuthorizationPermission.class, permission);
                response.getAuthorizationPermissionList().add(resolvedPermission);

            } catch (PersistenceException e) {
                throw new ResolveException("Cannot resolve AuthorizationPermission with id "
                        + permission.getId(), e);
            } catch (Exception e) {
                throw new ResolveException("Cannot resolve AuthorizationPermission with id "
                        + permission.getId(), e);
            }
        }

        return response;
    }
}