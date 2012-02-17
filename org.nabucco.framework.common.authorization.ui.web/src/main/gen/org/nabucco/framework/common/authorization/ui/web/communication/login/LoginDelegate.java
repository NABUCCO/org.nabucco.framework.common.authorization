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
package org.nabucco.framework.common.authorization.ui.web.communication.login;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.facade.service.login.Login;

/**
 * LoginDelegate<p/>Authorization login service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class LoginDelegate extends ServiceDelegateSupport {

    private Login service;

    /**
     * Constructs a new LoginDelegate instance.
     *
     * @param service the Login.
     */
    public LoginDelegate(Login service) {
        super();
        this.service = service;
    }

    /**
     * Login.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the LoginMsg.
     * @return the LoginRs.
     * @throws LoginException
     */
    public LoginRs login(LoginMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws LoginException {
        ServiceRequest<LoginMsg> request = new ServiceRequest<LoginMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<LoginRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.login(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(Login.class, "login", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new LoginException("Cannot execute service operation: Login.login");
    }
}
