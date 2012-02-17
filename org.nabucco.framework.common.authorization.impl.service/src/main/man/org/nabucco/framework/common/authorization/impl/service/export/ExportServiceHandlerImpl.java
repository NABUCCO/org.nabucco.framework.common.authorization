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
package org.nabucco.framework.common.authorization.impl.service.export;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Data;
import org.nabucco.framework.base.facade.datatype.exporting.ExportContainer;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationException;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationResult;
import org.nabucco.framework.base.facade.datatype.serialization.xml.XmlSerializer;
import org.nabucco.framework.base.facade.datatype.text.TextContent;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ExportException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.exporting.ExportRs;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * ExportAuthorizationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ExportServiceHandlerImpl extends ExportServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String QUERY = "select g from AuthorizationGroup g";

    @Override
    protected ExportRs export(EmptyServiceMessage msg) throws ExportException {
        ExportRs result = new ExportRs();

        try {
            List<AuthorizationGroup> resultList = this.loadGroups();

            XmlSerializer xmlSerializer = new XmlSerializer();
            ExportContainer container = new ExportContainer();

            SerializationResult serializationResult = xmlSerializer.serialize(resultList, XmlSerializer.DEFAULT_INDENT,
                    true);
            container.setResult(new TextContent(serializationResult.getContent()));
            container.setResourceData(new Data(serializationResult.getResourceContainer().toByteArray()));

            result.setContainer(container);

        } catch (PersistenceException pe) {
            throw new ExportException("Failed to Load AuthorizationGroup.", pe);
        } catch (SerializationException se) {
            throw new ExportException("Failed to Serialize AuthorizationGroup.", se);
        }
        return result;

    }

    /**
     * Loads all AuthorizationGroups from Database.
     * 
     * @return the list of persistent groups
     * 
     * @throws PersistenceException
     *             when the query execution fails
     */
    private List<AuthorizationGroup> loadGroups() throws PersistenceException {
        NabuccoQuery<AuthorizationGroup> groupQuery = super.getPersistenceManager().createQuery(QUERY);

        return groupQuery.getResultList();
    }
}
