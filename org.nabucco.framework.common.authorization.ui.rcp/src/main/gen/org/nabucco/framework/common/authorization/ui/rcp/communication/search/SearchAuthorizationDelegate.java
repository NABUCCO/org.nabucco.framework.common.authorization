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
package org.nabucco.framework.common.authorization.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * SearchAuthorizationDelegate<p/>Authorization search service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class SearchAuthorizationDelegate extends ServiceDelegateSupport {

    private SearchAuthorization service;

    /**
     * Constructs a new SearchAuthorizationDelegate instance.
     *
     * @param service the SearchAuthorization.
     */
    public SearchAuthorizationDelegate(SearchAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * SearchAuthorizationGroup.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationSearchMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ClientException
     */
    public AuthorizationGroupListMsg searchAuthorizationGroup(AuthorizationSearchMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationGroupListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchAuthorizationGroup(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchAuthorization.class, "searchAuthorizationGroup", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchAuthorization.searchAuthorizationGroup");
    }

    /**
     * SearchAuthorizationUser.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationSearchMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ClientException
     */
    public AuthorizationUserListMsg searchAuthorizationUser(AuthorizationSearchMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationUserListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchAuthorizationUser(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchAuthorization.class, "searchAuthorizationUser", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchAuthorization.searchAuthorizationUser");
    }

    /**
     * SearchAuthorizationRole.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationSearchMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ClientException
     */
    public AuthorizationRoleListMsg searchAuthorizationRole(AuthorizationSearchMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationRoleListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchAuthorizationRole(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchAuthorization.class, "searchAuthorizationRole", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchAuthorization.searchAuthorizationRole");
    }

    /**
     * SearchAuthorizationPermission.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the AuthorizationSearchMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionListMsg searchAuthorizationPermission(AuthorizationSearchMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<AuthorizationPermissionListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchAuthorizationPermission(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchAuthorization.class, "searchAuthorizationPermission", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchAuthorization.searchAuthorizationPermission");
    }
}
