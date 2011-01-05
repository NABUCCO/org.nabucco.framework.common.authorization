/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication;

import org.nabucco.framework.base.facade.component.connection.Connection;
import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.connection.ConnectionFactory;
import org.nabucco.framework.base.facade.component.connection.ConnectionSpecification;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationServiceDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.login.LoginDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.produce.ProduceAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.search.SearchAuthorizationDelegate;

/**
 * ServiceDelegateFactoryTemplate<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationComponentServiceDelegateFactory {

    private static AuthorizationComponentServiceDelegateFactory instance = new AuthorizationComponentServiceDelegateFactory();

    private AuthorizationComponent component;

    private LoginDelegate loginDelegate;

    private AuthorizationServiceDelegate authorizationServiceDelegate;

    private MaintainAuthorizationDelegate maintainAuthorizationDelegate;

    private SearchAuthorizationDelegate searchAuthorizationDelegate;

    private ProduceAuthorizationDelegate produceAuthorizationDelegate;

    private ResolveAuthorizationDelegate resolveAuthorizationDelegate;

    /** Constructs a new AuthorizationComponentServiceDelegateFactory instance. */
    private AuthorizationComponentServiceDelegateFactory() {
        super();
    }

    /**
     * Getter for the Component.
     *
     * @return the AuthorizationComponent.
     * @throws ConnectionException
     */
    private AuthorizationComponent getComponent() throws ConnectionException {
        if ((this.component == null)) {
            this.initComponent();
        }
        return this.component;
    }

    /**
     * InitComponent.
     *
     * @throws ConnectionException
     */
    private void initComponent() throws ConnectionException {
        ConnectionSpecification specification = ConnectionSpecification.getCurrentSpecification();
        Connection connection = ConnectionFactory.getInstance().createConnection(specification);
        this.component = AuthorizationComponentLocator.getInstance().getComponent(connection);
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
            throw new ClientException("Cannot locate service: Login", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
                this.authorizationServiceDelegate = new AuthorizationServiceDelegate(this
                        .getComponent().getAuthorizationService());
            }
            return this.authorizationServiceDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: AuthorizationService", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
                this.maintainAuthorizationDelegate = new MaintainAuthorizationDelegate(this
                        .getComponent().getMaintainAuthorization());
            }
            return this.maintainAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainAuthorization", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
                this.searchAuthorizationDelegate = new SearchAuthorizationDelegate(this
                        .getComponent().getSearchAuthorization());
            }
            return this.searchAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchAuthorization", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
                this.produceAuthorizationDelegate = new ProduceAuthorizationDelegate(this
                        .getComponent().getProduceAuthorization());
            }
            return this.produceAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceAuthorization", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
                this.resolveAuthorizationDelegate = new ResolveAuthorizationDelegate(this
                        .getComponent().getResolveAuthorization());
            }
            return this.resolveAuthorizationDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ResolveAuthorization", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
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
