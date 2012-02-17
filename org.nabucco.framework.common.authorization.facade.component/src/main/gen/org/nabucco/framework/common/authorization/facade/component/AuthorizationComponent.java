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
package org.nabucco.framework.common.authorization.facade.component;

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
import org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.login.Login;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * AuthorizationComponent<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.framework.common.authorization";

    final String COMPONENT_PREFIX = "auth";

    final String JNDI_NAME = ((((JNDI_PREFIX + "/") + COMPONENT_NAME) + "/") + "org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent");

    /**
     * Getter for the Login.
     *
     * @return the Login.
     * @throws ServiceException
     */
    Login getLogin() throws ServiceException;

    /**
     * Getter for the AuthorizationService.
     *
     * @return the AuthorizationService.
     * @throws ServiceException
     */
    AuthorizationService getAuthorizationService() throws ServiceException;

    /**
     * Getter for the MaintainAuthorization.
     *
     * @return the MaintainAuthorization.
     * @throws ServiceException
     */
    MaintainAuthorization getMaintainAuthorization() throws ServiceException;

    /**
     * Getter for the SearchAuthorization.
     *
     * @return the SearchAuthorization.
     * @throws ServiceException
     */
    SearchAuthorization getSearchAuthorization() throws ServiceException;

    /**
     * Getter for the ProduceAuthorization.
     *
     * @return the ProduceAuthorization.
     * @throws ServiceException
     */
    ProduceAuthorization getProduceAuthorization() throws ServiceException;

    /**
     * Getter for the ResolveAuthorization.
     *
     * @return the ResolveAuthorization.
     * @throws ServiceException
     */
    ResolveAuthorization getResolveAuthorization() throws ServiceException;

    /**
     * Getter for the ExportAuthorization.
     *
     * @return the ExportAuthorization.
     * @throws ServiceException
     */
    ExportAuthorization getExportAuthorization() throws ServiceException;

    /**
     * Getter for the ImportAuthorization.
     *
     * @return the ImportAuthorization.
     * @throws ServiceException
     */
    ImportAuthorization getImportAuthorization() throws ServiceException;
}
