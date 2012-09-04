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
package org.nabucco.framework.common.authorization.facade.exception;

import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * AuthorizationException<p/>Common Exception for Authorization<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationException instance. */
    public AuthorizationException() {
        super();
    }

    /**
     * Constructs a new AuthorizationException instance.
     *
     * @param message the String.
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthorizationException instance.
     *
     * @param cause the Throwable.
     */
    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AuthorizationException instance.
     *
     * @param cause the Throwable.
     * @param message the String.
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
