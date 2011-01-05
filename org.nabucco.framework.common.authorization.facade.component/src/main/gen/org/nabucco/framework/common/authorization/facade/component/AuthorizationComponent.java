/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.component;

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
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
}
