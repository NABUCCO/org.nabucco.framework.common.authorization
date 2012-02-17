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
package org.nabucco.framework.common.authorization.facade.service.resolve;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * ResolveAuthorization<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-06
 */
public interface ResolveAuthorization extends Service {

    /**
     * Resolve an authorization group from the database.
     *
     * @param rq the ServiceRequest<AuthorizationGroupMsg>.
     * @return the ServiceResponse<AuthorizationGroupMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationGroupMaintainMsg> resolveAuthorizationGroup(ServiceRequest<AuthorizationGroupMsg> rq)
            throws ResolveException;

    /**
     * Resolve a list of authorization groups from the database.
     *
     * @param rq the ServiceRequest<AuthorizationGroupListMsg>.
     * @return the ServiceResponse<AuthorizationGroupListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationGroupListMsg> resolveAuthorizationGroupList(
            ServiceRequest<AuthorizationGroupListMsg> rq) throws ResolveException;

    /**
     * Resolve an authorization user from the database.
     *
     * @param rq the ServiceRequest<AuthorizationUserMsg>.
     * @return the ServiceResponse<AuthorizationUserMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationUserMaintainMsg> resolveAuthorizationUser(ServiceRequest<AuthorizationUserMsg> rq)
            throws ResolveException;

    /**
     * Resolve a list of authorization users from the database.
     *
     * @param rq the ServiceRequest<AuthorizationUserListMsg>.
     * @return the ServiceResponse<AuthorizationUserListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationUserListMsg> resolveAuthorizationUserList(ServiceRequest<AuthorizationUserListMsg> rq)
            throws ResolveException;

    /**
     * Resolve an authorization role from the database.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationRoleMaintainMsg> resolveAuthorizationRole(ServiceRequest<AuthorizationRoleMsg> rq)
            throws ResolveException;

    /**
     * Resolve a list of authorization roles from the database.
     *
     * @param rq the ServiceRequest<AuthorizationRoleListMsg>.
     * @return the ServiceResponse<AuthorizationRoleListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationRoleListMsg> resolveAuthorizationRoleList(ServiceRequest<AuthorizationRoleListMsg> rq)
            throws ResolveException;

    /**
     * Resolve an authorization permission from the database.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionMsg>.
     * @return the ServiceResponse<AuthorizationPermissionMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationPermissionMaintainMsg> resolveAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMsg> rq) throws ResolveException;

    /**
     * Resolve a list of authorization permissions from the database.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionListMsg>.
     * @return the ServiceResponse<AuthorizationPermissionListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationPermissionListMsg> resolveAuthorizationPermissionList(
            ServiceRequest<AuthorizationPermissionListMsg> rq) throws ResolveException;
}
