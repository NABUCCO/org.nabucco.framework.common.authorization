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
package org.nabucco.framework.common.authorization.impl.service.importing;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandlerSupport;

/**
 * ImportAuthoritationServiceHandler<p/>Authorization Import Service<p/>
 *
 * @version 1.0
 * @author Silas Schwarz, PRODYNA AG, 2011-02-10
 */
public abstract class ImportAuthoritationServiceHandler extends PersistenceServiceHandlerSupport implements
        ServiceHandler, PersistenceServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.importing.ImportAuthoritationServiceHandler";

    /** Constructs a new ImportAuthoritationServiceHandler instance. */
    public ImportAuthoritationServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<ImportRq>.
     * @return the ServiceResponse<ImportRs>.
     * @throws ImportException
     */
    protected ServiceResponse<ImportRs> invoke(ServiceRequest<ImportRq> rq) throws ImportException {
        ServiceResponse<ImportRs> rs;
        ImportRs msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.importAuthoritation(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<ImportRs>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ImportException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ImportException wrappedException = new ImportException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ImportException("Error during service invocation.", e);
        }
    }

    /**
     * Import the authorization information (groups, users, roles, permissions).
     *
     * @param msg the ImportRq.
     * @return the ImportRs.
     * @throws ImportException
     */
    protected abstract ImportRs importAuthoritation(ImportRq msg) throws ImportException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
