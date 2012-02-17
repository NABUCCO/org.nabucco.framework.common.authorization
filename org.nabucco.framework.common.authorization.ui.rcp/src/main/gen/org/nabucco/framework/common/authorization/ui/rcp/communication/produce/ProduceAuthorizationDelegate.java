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
package org.nabucco.framework.common.authorization.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ProduceAuthorizationDelegate<p/>Authorization produce service<p/>
 *
 * @version 1.0
 * @author Stefanie Feld, PRODYNA AG, 2010-03-29
 */
public class ProduceAuthorizationDelegate extends ServiceDelegateSupport {

    private ProduceAuthorization service;

    /**
     * Constructs a new ProduceAuthorizationDelegate instance.
     *
     * @param service the ProduceAuthorization.
     */
    public ProduceAuthorizationDelegate(ProduceAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * ProduceAuthorizationGroup.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the AuthorizationGroupMsg.
     * @throws ClientException
     */
    public AuthorizationGroupMsg produceAuthorizationGroup(EmptyServiceMessage message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceAuthorizationGroup(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceAuthorization.class, "produceAuthorizationGroup", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceAuthorization.produceAuthorizationGroup");
    }

    /**
     * ProduceAuthorizationUser.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the AuthorizationUserMsg.
     * @throws ClientException
     */
    public AuthorizationUserMsg produceAuthorizationUser(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceAuthorizationUser(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceAuthorization.class, "produceAuthorizationUser", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceAuthorization.produceAuthorizationUser");
    }

    /**
     * ProduceAuthorizationRole.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the AuthorizationRoleMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMsg produceAuthorizationRole(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceAuthorizationRole(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceAuthorization.class, "produceAuthorizationRole", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceAuthorization.produceAuthorizationRole");
    }

    /**
     * ProduceAuthorizationPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the AuthorizationPermissionMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMsg produceAuthorizationPermission(EmptyServiceMessage message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceAuthorizationPermission(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceAuthorization.class, "produceAuthorizationPermission", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceAuthorization.produceAuthorizationPermission");
    }
}
