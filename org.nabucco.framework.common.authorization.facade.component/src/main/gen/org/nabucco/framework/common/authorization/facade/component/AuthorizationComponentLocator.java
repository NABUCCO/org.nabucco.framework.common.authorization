/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.component;

import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for AuthorizationComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class AuthorizationComponentLocator extends ComponentLocatorSupport<AuthorizationComponent>
        implements ComponentLocator<AuthorizationComponent> {

    private static final String JNDI_NAME = ((((ComponentLocator.COMPONENTS + "/") + AuthorizationComponent.COMPONENT_NAME) + "/") + "org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent");

    private static AuthorizationComponentLocator instance;

    /**
     * Constructs a new AuthorizationComponentLocator instance.
     *
     * @param component the Class<AuthorizationComponent>.
     * @param jndiName the String.
     */
    private AuthorizationComponentLocator(String jndiName, Class<AuthorizationComponent> component) {
        super(jndiName, component);
    }

    /**
     * Getter for the Instance.
     *
     * @return the AuthorizationComponentLocator.
     */
    public static AuthorizationComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new AuthorizationComponentLocator(JNDI_NAME, AuthorizationComponent.class);
        }
        return instance;
    }
}
