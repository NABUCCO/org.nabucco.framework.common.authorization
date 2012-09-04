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
package org.nabucco.framework.common.authorization.ui.rcp.communication;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationServiceDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.export.ExportAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.importing.ImportAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.login.LoginDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.produce.ProduceAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateFactorySupport;

/**
 * ServiceDelegateFactoryTemplate<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<AuthorizationComponent> {

    private static AuthorizationComponentServiceDelegateFactory instance = new AuthorizationComponentServiceDelegateFactory();

    private LoginDelegate loginDelegate;

    private AuthorizationServiceDelegate authorizationServiceDelegate;

    private MaintainAuthorizationDelegate maintainAuthorizationDelegate;

    private SearchAuthorizationDelegate searchAuthorizationDelegate;

    private ProduceAuthorizationDelegate produceAuthorizationDelegate;

    private ResolveAuthorizationDelegate resolveAuthorizationDelegate;

    private ExportAuthorizationDelegate exportAuthorizationDelegate;

    private ImportAuthorizationDelegate importAuthorizationDelegate;

    /** Constructs a new AuthorizationComponentServiceDelegateFactory instance. */
    private AuthorizationComponentServiceDelegateFactory() {
        super(AuthorizationComponentLocator.getInstance());
    }

    /**
     * Getter for the Login.
     *
     * @return the LoginDelegate.
     * @throws ClientException
     */
    public LoginDelegate getLogin() throws ClientException {
        try {
            if ((this.loginDelegate == null)) {
                this.loginDelegate = new LoginDelegate(this.getComponent().getLogin());
            }
            return this.loginDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: Login", e);
        }
    }

    /**
     * Getter for the AuthorizationService.
     *
     * @return the AuthorizationServiceDelegate.
     * @throws ClientException
     */
    public AuthorizationServiceDelegate getAuthorizationService() throws ClientException {
        try {
            if ((this.authorizationServiceDelegate == null)) {
                this.authorizationServiceDelegate = new AuthorizationServiceDelegate(this.getComponent()
                        .getAuthorizationService());
            }
            return this.authorizationServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: AuthorizationService", e);
        }
    }

    /**
     * Getter for the MaintainAuthorization.
     *
     * @return the MaintainAuthorizationDelegate.
     * @throws ClientException
     */
    public MaintainAuthorizationDelegate getMaintainAuthorization() throws ClientException {
        try {
            if ((this.maintainAuthorizationDelegate == null)) {
                this.maintainAuthorizationDelegate = new MaintainAuthorizationDelegate(this.getComponent()
                        .getMaintainAuthorization());
            }
            return this.maintainAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: MaintainAuthorization", e);
        }
    }

    /**
     * Getter for the SearchAuthorization.
     *
     * @return the SearchAuthorizationDelegate.
     * @throws ClientException
     */
    public SearchAuthorizationDelegate getSearchAuthorization() throws ClientException {
        try {
            if ((this.searchAuthorizationDelegate == null)) {
                this.searchAuthorizationDelegate = new SearchAuthorizationDelegate(this.getComponent()
                        .getSearchAuthorization());
            }
            return this.searchAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: SearchAuthorization", e);
        }
    }

    /**
     * Getter for the ProduceAuthorization.
     *
     * @return the ProduceAuthorizationDelegate.
     * @throws ClientException
     */
    public ProduceAuthorizationDelegate getProduceAuthorization() throws ClientException {
        try {
            if ((this.produceAuthorizationDelegate == null)) {
                this.produceAuthorizationDelegate = new ProduceAuthorizationDelegate(this.getComponent()
                        .getProduceAuthorization());
            }
            return this.produceAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ProduceAuthorization", e);
        }
    }

    /**
     * Getter for the ResolveAuthorization.
     *
     * @return the ResolveAuthorizationDelegate.
     * @throws ClientException
     */
    public ResolveAuthorizationDelegate getResolveAuthorization() throws ClientException {
        try {
            if ((this.resolveAuthorizationDelegate == null)) {
                this.resolveAuthorizationDelegate = new ResolveAuthorizationDelegate(this.getComponent()
                        .getResolveAuthorization());
            }
            return this.resolveAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ResolveAuthorization", e);
        }
    }

    /**
     * Getter for the ExportAuthorization.
     *
     * @return the ExportAuthorizationDelegate.
     * @throws ClientException
     */
    public ExportAuthorizationDelegate getExportAuthorization() throws ClientException {
        try {
            if ((this.exportAuthorizationDelegate == null)) {
                this.exportAuthorizationDelegate = new ExportAuthorizationDelegate(this.getComponent()
                        .getExportAuthorization());
            }
            return this.exportAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ExportAuthorization", e);
        }
    }

    /**
     * Getter for the ImportAuthorization.
     *
     * @return the ImportAuthorizationDelegate.
     * @throws ClientException
     */
    public ImportAuthorizationDelegate getImportAuthorization() throws ClientException {
        try {
            if ((this.importAuthorizationDelegate == null)) {
                this.importAuthorizationDelegate = new ImportAuthorizationDelegate(this.getComponent()
                        .getImportAuthorization());
            }
            return this.importAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: AuthorizationComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ImportAuthorization", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the AuthorizationComponentServiceDelegateFactory.
     */
    public static AuthorizationComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
