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

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for AuthorizationComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class AuthorizationComponentLocator extends ComponentLocatorSupport<AuthorizationComponent> implements
        ComponentLocator<AuthorizationComponent> {

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

    @Override
    public AuthorizationComponent getComponent() throws ConnectionException {
        AuthorizationComponent component = super.getComponent();
        if ((component instanceof AuthorizationComponentLocal)) {
            return new AuthorizationComponentLocalProxy(((AuthorizationComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the AuthorizationComponentLocator.
     */
    public static AuthorizationComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new AuthorizationComponentLocator(AuthorizationComponent.JNDI_NAME, AuthorizationComponent.class);
        }
        return instance;
    }
}
