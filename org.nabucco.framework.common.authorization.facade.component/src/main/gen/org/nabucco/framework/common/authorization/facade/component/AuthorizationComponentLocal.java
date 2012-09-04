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
package org.nabucco.framework.common.authorization.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
import org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.login.Login;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * AuthorizationComponentLocal<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationComponentLocal extends AuthorizationComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the LoginLocal.
     *
     * @return the Login.
     * @throws ServiceException
     */
    Login getLoginLocal() throws ServiceException;

    /**
     * Getter for the AuthorizationServiceLocal.
     *
     * @return the AuthorizationService.
     * @throws ServiceException
     */
    AuthorizationService getAuthorizationServiceLocal() throws ServiceException;

    /**
     * Getter for the MaintainAuthorizationLocal.
     *
     * @return the MaintainAuthorization.
     * @throws ServiceException
     */
    MaintainAuthorization getMaintainAuthorizationLocal() throws ServiceException;

    /**
     * Getter for the SearchAuthorizationLocal.
     *
     * @return the SearchAuthorization.
     * @throws ServiceException
     */
    SearchAuthorization getSearchAuthorizationLocal() throws ServiceException;

    /**
     * Getter for the ProduceAuthorizationLocal.
     *
     * @return the ProduceAuthorization.
     * @throws ServiceException
     */
    ProduceAuthorization getProduceAuthorizationLocal() throws ServiceException;

    /**
     * Getter for the ResolveAuthorizationLocal.
     *
     * @return the ResolveAuthorization.
     * @throws ServiceException
     */
    ResolveAuthorization getResolveAuthorizationLocal() throws ServiceException;

    /**
     * Getter for the ExportAuthorizationLocal.
     *
     * @return the ExportAuthorization.
     * @throws ServiceException
     */
    ExportAuthorization getExportAuthorizationLocal() throws ServiceException;

    /**
     * Getter for the ImportAuthorizationLocal.
     *
     * @return the ImportAuthorization.
     * @throws ServiceException
     */
    ImportAuthorization getImportAuthorizationLocal() throws ServiceException;
}
