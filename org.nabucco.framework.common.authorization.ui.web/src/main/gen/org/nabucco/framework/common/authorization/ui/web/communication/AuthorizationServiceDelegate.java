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
package org.nabucco.framework.common.authorization.ui.web.communication;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;

/**
 * AuthorizationServiceDelegate<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class AuthorizationServiceDelegate extends ServiceDelegateSupport {

    private AuthorizationService service;

    /**
     * Constructs a new AuthorizationServiceDelegate instance.
     *
     * @param service the AuthorizationService.
     */
    public AuthorizationServiceDelegate(AuthorizationService service) {
        super();
        this.service = service;
    }

    /**
     * HasPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationPermissionMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermission(AuthorizationPermissionMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws AuthorizationException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.hasPermission(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(AuthorizationService.class, "hasPermission", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new AuthorizationException("Cannot execute service operation: AuthorizationService.hasPermission");
    }

    /**
     * HasPermissionByName.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the AuthorizationNameMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermissionByName(AuthorizationNameMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws AuthorizationException {
        ServiceRequest<AuthorizationNameMsg> request = new ServiceRequest<AuthorizationNameMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.hasPermissionByName(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(AuthorizationService.class, "hasPermissionByName", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new AuthorizationException("Cannot execute service operation: AuthorizationService.hasPermissionByName");
    }

    /**
     * Getter for the Information.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the UserRq.
     * @return the AuthorizationInformationRs.
     * @throws AuthorizationException
     */
    public AuthorizationInformationRs getInformation(UserRq message, NabuccoSession session,
            ServiceSubContext... subContexts) throws AuthorizationException {
        ServiceRequest<UserRq> request = new ServiceRequest<UserRq>(super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationInformationRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.getInformation(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(AuthorizationService.class, "getInformation", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new AuthorizationException("Cannot execute service operation: AuthorizationService.getInformation");
    }
}
