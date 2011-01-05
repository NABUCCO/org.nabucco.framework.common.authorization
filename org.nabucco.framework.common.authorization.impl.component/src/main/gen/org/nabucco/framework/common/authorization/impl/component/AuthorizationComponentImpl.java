/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
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
public class AuthorizationComponentImpl extends ComponentSupport implements AuthorizationComponent {

    private static final long serialVersionUID = 1L;

    private ComponentRelationService componentRelationService;

    private Login login;

    private AuthorizationService authorizationService;

    private MaintainAuthorization maintainAuthorization;

    private SearchAuthorization searchAuthorization;

    private ProduceAuthorization produceAuthorization;

    private ResolveAuthorization resolveAuthorization;

    /** Constructs a new AuthorizationComponentImpl instance. */
    public AuthorizationComponentImpl() {
        super();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.componentRelationService;
    }

    /**
     * Getter for the Login.
     *
     * @return the Login.
     */
    public Login getLogin() {
        return this.login;
    }

    /**
     * Getter for the AuthorizationService.
     *
     * @return the AuthorizationService.
     */
    public AuthorizationService getAuthorizationService() {
        return this.authorizationService;
    }

    /**
     * Getter for the MaintainAuthorization.
     *
     * @return the MaintainAuthorization.
     */
    public MaintainAuthorization getMaintainAuthorization() {
        return this.maintainAuthorization;
    }

    /**
     * Getter for the SearchAuthorization.
     *
     * @return the SearchAuthorization.
     */
    public SearchAuthorization getSearchAuthorization() {
        return this.searchAuthorization;
    }

    /**
     * Getter for the ProduceAuthorization.
     *
     * @return the ProduceAuthorization.
     */
    public ProduceAuthorization getProduceAuthorization() {
        return this.produceAuthorization;
    }

    /**
     * Getter for the ResolveAuthorization.
     *
     * @return the ResolveAuthorization.
     */
    public ResolveAuthorization getResolveAuthorization() {
        return this.resolveAuthorization;
    }
}
