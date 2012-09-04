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
package org.nabucco.framework.common.authorization.impl.service;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;

/**
 * GetInformationServiceHandler<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public abstract class GetInformationServiceHandler extends PersistenceServiceHandlerSupport implements ServiceHandler,
        PersistenceServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.GetInformationServiceHandler";

    /** Constructs a new GetInformationServiceHandler instance. */
    public GetInformationServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<UserRq>.
     * @return the ServiceResponse<AuthorizationInformationRs>.
     * @throws AuthorizationException
     */
    protected ServiceResponse<AuthorizationInformationRs> invoke(ServiceRequest<UserRq> rq)
            throws AuthorizationException {
        ServiceResponse<AuthorizationInformationRs> rs;
        AuthorizationInformationRs msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.getInformation(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationInformationRs>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (AuthorizationException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            AuthorizationException wrappedException = new AuthorizationException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new AuthorizationException("Error during service invocation.", e);
        }
    }

    /**
     * Missing description at method getInformation.
     *
     * @param msg the UserRq.
     * @return the AuthorizationInformationRs.
     * @throws AuthorizationException
     */
    protected abstract AuthorizationInformationRs getInformation(UserRq msg) throws AuthorizationException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
