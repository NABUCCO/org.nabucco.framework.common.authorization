/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.common.authorization.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * MaintainAuthorization<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public interface MaintainAuthorization extends Service {

    /**
     * Maintain a single authorization group to the database.
     *
     * @param rq the ServiceRequest<AuthorizationGroupMaintainMsg>.
     * @return the ServiceResponse<AuthorizationGroupMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationGroupMaintainMsg> maintainAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMaintainMsg> rq) throws MaintainException;

    /**
     * Maintain a list of authorization group to the database.
     *
     * @param rq the ServiceRequest<AuthorizationGroupListMsg>.
     * @return the ServiceResponse<AuthorizationGroupListMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationGroupListMsg> maintainAuthorizationGroupList(
            ServiceRequest<AuthorizationGroupListMsg> rq) throws MaintainException;

    /**
     * Maintain a single authorization user to the database.
     *
     * @param rq the ServiceRequest<AuthorizationUserMaintainMsg>.
     * @return the ServiceResponse<AuthorizationUserMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationUserMaintainMsg> maintainAuthorizationUser(
            ServiceRequest<AuthorizationUserMaintainMsg> rq) throws MaintainException;

    /**
     * Maintain a single authorization role to the database.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMaintainMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationRoleMaintainMsg> maintainAuthorizationRole(
            ServiceRequest<AuthorizationRoleMaintainMsg> rq) throws MaintainException;

    /**
     * Maintain a single authorization permission to the database.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionMaintainMsg>.
     * @return the ServiceResponse<AuthorizationPermissionMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationPermissionMaintainMsg> maintainAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMaintainMsg> rq) throws MaintainException;
}
