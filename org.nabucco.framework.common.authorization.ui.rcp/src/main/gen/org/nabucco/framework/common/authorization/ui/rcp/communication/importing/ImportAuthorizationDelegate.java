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
package org.nabucco.framework.common.authorization.ui.rcp.communication.importing;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ImportAuthorizationDelegate<p/>Authorization Import Service<p/>
 *
 * @version 1.0
 * @author Silas Schwarz, PRODYNA AG, 2011-02-10
 */
public class ImportAuthorizationDelegate extends ServiceDelegateSupport {

    private ImportAuthorization service;

    /**
     * Constructs a new ImportAuthorizationDelegate instance.
     *
     * @param service the ImportAuthorization.
     */
    public ImportAuthorizationDelegate(ImportAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * ImportAuthoritation.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportRq.
     * @return the ImportRs.
     * @throws ClientException
     */
    public ImportRs importAuthoritation(ImportRq message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportRq> request = new ServiceRequest<ImportRq>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportRs> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.importAuthoritation(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ImportAuthorization.class, "importAuthoritation", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ImportAuthorization.importAuthoritation");
    }
}
