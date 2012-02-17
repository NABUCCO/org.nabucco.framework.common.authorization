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
package org.nabucco.framework.common.authorization.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * MaintainAuthorizationDelegate<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class MaintainAuthorizationDelegate extends ServiceDelegateSupport {

    private MaintainAuthorization service;

    /**
     * Constructs a new MaintainAuthorizationDelegate instance.
     *
     * @param service the MaintainAuthorization.
     */
    public MaintainAuthorizationDelegate(MaintainAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * MaintainAuthorizationGroup.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationGroupMaintainMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationGroupMaintainMsg maintainAuthorizationGroup(AuthorizationGroupMaintainMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationGroupMaintainMsg> request = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainAuthorizationGroup(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainAuthorization.class, "maintainAuthorizationGroup", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainAuthorization.maintainAuthorizationGroup");
    }

    /**
     * MaintainAuthorizationGroupList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ClientException
     */
    public AuthorizationGroupListMsg maintainAuthorizationGroupList(AuthorizationGroupListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationGroupListMsg> request = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainAuthorizationGroupList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainAuthorization.class, "maintainAuthorizationGroupList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationGroupList");
    }

    /**
     * MaintainAuthorizationUser.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationUserMaintainMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationUserMaintainMsg maintainAuthorizationUser(AuthorizationUserMaintainMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationUserMaintainMsg> request = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainAuthorizationUser(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainAuthorization.class, "maintainAuthorizationUser", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainAuthorization.maintainAuthorizationUser");
    }

    /**
     * MaintainAuthorizationRole.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationRoleMaintainMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMaintainMsg maintainAuthorizationRole(AuthorizationRoleMaintainMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationRoleMaintainMsg> request = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainAuthorizationRole(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainAuthorization.class, "maintainAuthorizationRole", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainAuthorization.maintainAuthorizationRole");
    }

    /**
     * MaintainAuthorizationPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationPermissionMaintainMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMaintainMsg maintainAuthorizationPermission(
            AuthorizationPermissionMaintainMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationPermissionMaintainMsg> request = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainAuthorizationPermission(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainAuthorization.class, "maintainAuthorizationPermission", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationPermission");
    }
}
