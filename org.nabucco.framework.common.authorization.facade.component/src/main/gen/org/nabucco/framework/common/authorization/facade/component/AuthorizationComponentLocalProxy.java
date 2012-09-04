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
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
import org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.login.Login;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * AuthorizationComponentLocalProxy<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationComponentLocalProxy implements AuthorizationComponent {

    private static final long serialVersionUID = 1L;

    private final AuthorizationComponentLocal delegate;

    /**
     * Constructs a new AuthorizationComponentLocalProxy instance.
     *
     * @param delegate the AuthorizationComponentLocal.
     */
    public AuthorizationComponentLocalProxy(AuthorizationComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public Login getLogin() throws ServiceException {
        return this.delegate.getLoginLocal();
    }

    @Override
    public AuthorizationService getAuthorizationService() throws ServiceException {
        return this.delegate.getAuthorizationServiceLocal();
    }

    @Override
    public MaintainAuthorization getMaintainAuthorization() throws ServiceException {
        return this.delegate.getMaintainAuthorizationLocal();
    }

    @Override
    public SearchAuthorization getSearchAuthorization() throws ServiceException {
        return this.delegate.getSearchAuthorizationLocal();
    }

    @Override
    public ProduceAuthorization getProduceAuthorization() throws ServiceException {
        return this.delegate.getProduceAuthorizationLocal();
    }

    @Override
    public ResolveAuthorization getResolveAuthorization() throws ServiceException {
        return this.delegate.getResolveAuthorizationLocal();
    }

    @Override
    public ExportAuthorization getExportAuthorization() throws ServiceException {
        return this.delegate.getExportAuthorizationLocal();
    }

    @Override
    public ImportAuthorization getImportAuthorization() throws ServiceException {
        return this.delegate.getImportAuthorizationLocal();
    }
}
