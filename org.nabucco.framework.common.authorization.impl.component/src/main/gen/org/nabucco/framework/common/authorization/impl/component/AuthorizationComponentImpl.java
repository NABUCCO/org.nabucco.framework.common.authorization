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
package org.nabucco.framework.common.authorization.impl.component;

import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocal;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentRemote;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
import org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization;
import org.nabucco.framework.common.authorization.facade.service.login.Login;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * AuthorizationComponentImpl<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationComponentImpl extends ComponentSupport implements AuthorizationComponentLocal,
        AuthorizationComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "AuthorizationComponent";

    /** Constructs a new AuthorizationComponentImpl instance. */
    public AuthorizationComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE,
                ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL,
                ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public Login getLoginLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.LOGIN_LOCAL, Login.class);
    }

    @Override
    public Login getLogin() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.LOGIN_REMOTE, Login.class);
    }

    @Override
    public AuthorizationService getAuthorizationServiceLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.AUTHORIZATION_SERVICE_LOCAL, AuthorizationService.class);
    }

    @Override
    public AuthorizationService getAuthorizationService() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.AUTHORIZATION_SERVICE_REMOTE, AuthorizationService.class);
    }

    @Override
    public MaintainAuthorization getMaintainAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.MAINTAIN_AUTHORIZATION_LOCAL, MaintainAuthorization.class);
    }

    @Override
    public MaintainAuthorization getMaintainAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.MAINTAIN_AUTHORIZATION_REMOTE, MaintainAuthorization.class);
    }

    @Override
    public SearchAuthorization getSearchAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.SEARCH_AUTHORIZATION_LOCAL, SearchAuthorization.class);
    }

    @Override
    public SearchAuthorization getSearchAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.SEARCH_AUTHORIZATION_REMOTE, SearchAuthorization.class);
    }

    @Override
    public ProduceAuthorization getProduceAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.PRODUCE_AUTHORIZATION_LOCAL, ProduceAuthorization.class);
    }

    @Override
    public ProduceAuthorization getProduceAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.PRODUCE_AUTHORIZATION_REMOTE, ProduceAuthorization.class);
    }

    @Override
    public ResolveAuthorization getResolveAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.RESOLVE_AUTHORIZATION_LOCAL, ResolveAuthorization.class);
    }

    @Override
    public ResolveAuthorization getResolveAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.RESOLVE_AUTHORIZATION_REMOTE, ResolveAuthorization.class);
    }

    @Override
    public ExportAuthorization getExportAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.EXPORT_AUTHORIZATION_LOCAL, ExportAuthorization.class);
    }

    @Override
    public ExportAuthorization getExportAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.EXPORT_AUTHORIZATION_REMOTE, ExportAuthorization.class);
    }

    @Override
    public ImportAuthorization getImportAuthorizationLocal() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.IMPORT_AUTHORIZATION_LOCAL, ImportAuthorization.class);
    }

    @Override
    public ImportAuthorization getImportAuthorization() throws ServiceException {
        return super.lookup(AuthorizationComponentJndiNames.IMPORT_AUTHORIZATION_REMOTE, ImportAuthorization.class);
    }
}
