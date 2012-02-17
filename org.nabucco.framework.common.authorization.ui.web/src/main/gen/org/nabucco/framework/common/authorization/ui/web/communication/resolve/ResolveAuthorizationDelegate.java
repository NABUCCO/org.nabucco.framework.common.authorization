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
package org.nabucco.framework.common.authorization.ui.web.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
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
     * @param session the NabuccoSession.
     * @param message the AuthorizationGroupMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationGroupMsg> request = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupMaintainMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroup");
    }

    /**
     * ResolveAuthorizationGroupList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationGroupListMsg> request = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroupList");
    }

    /**
     * ResolveAuthorizationUser.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationUserMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationUserMsg> request = new ServiceRequest<AuthorizationUserMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserMaintainMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUser");
    }

    /**
     * ResolveAuthorizationUserList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationUserListMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ResolveException
     */
    public AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationUserListMsg> request = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUserList");
    }

    /**
     * ResolveAuthorizationRole.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationRoleMsg> request = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleMaintainMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRole");
    }

    /**
     * ResolveAuthorizationRoleList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationRoleListMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationRoleListMsg> request = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRoleList");
    }

    /**
     * ResolveAuthorizationPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationPermissionMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(AuthorizationPermissionMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionMaintainMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermission");
    }

    /**
     * ResolveAuthorizationPermissionList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationPermissionListMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionListMsg resolveAuthorizationPermissionList(AuthorizationPermissionListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<AuthorizationPermissionListMsg> request = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermissionList");
    }
}
