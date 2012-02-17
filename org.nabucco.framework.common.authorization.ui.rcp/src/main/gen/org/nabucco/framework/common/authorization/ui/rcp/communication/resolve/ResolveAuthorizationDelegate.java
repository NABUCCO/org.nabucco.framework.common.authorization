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
package org.nabucco.framework.common.authorization.ui.rcp.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
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
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ResolveAuthorizationDelegate<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-06
 */
public class ResolveAuthorizationDelegate extends ServiceDelegateSupport {

    private ResolveAuthorization service;

    /**
     * Constructs a new ResolveAuthorizationDelegate instance.
     *
     * @param service the ResolveAuthorization.
     */
    public ResolveAuthorizationDelegate(ResolveAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * ResolveAuthorizationGroup.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationGroupMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationGroupMsg> request = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationGroup(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationGroup", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroup");
    }

    /**
     * ResolveAuthorizationGroupList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ClientException
     */
    public AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg message,
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
                response = service.resolveAuthorizationGroupList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationGroupList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroupList");
    }

    /**
     * ResolveAuthorizationUser.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationUserMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationUserMsg> request = new ServiceRequest<AuthorizationUserMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationUser(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationUser", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUser");
    }

    /**
     * ResolveAuthorizationUserList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationUserListMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ClientException
     */
    public AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationUserListMsg> request = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationUserList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationUserList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUserList");
    }

    /**
     * ResolveAuthorizationRole.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationRoleMsg> request = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationRole(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationRole", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRole");
    }

    /**
     * ResolveAuthorizationRoleList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationRoleListMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ClientException
     */
    public AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationRoleListMsg> request = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationRoleList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationRoleList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRoleList");
    }

    /**
     * ResolveAuthorizationPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationPermissionMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(AuthorizationPermissionMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionMaintainMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationPermission(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationPermission", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermission");
    }

    /**
     * ResolveAuthorizationPermissionList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationPermissionListMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionListMsg resolveAuthorizationPermissionList(AuthorizationPermissionListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationPermissionListMsg> request = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveAuthorizationPermissionList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveAuthorization.class, "resolveAuthorizationPermissionList", duration,
                        exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermissionList");
    }
}
