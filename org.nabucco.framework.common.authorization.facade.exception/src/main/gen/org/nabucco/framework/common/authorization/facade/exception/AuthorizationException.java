/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.exception;

import java.util.HashMap;
import java.util.Map;
import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * AuthorizationException<p/>Common Exception for Authorization<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> parameterMap = new HashMap<String, String>();

    /** Constructs a new AuthorizationException instance. */
    public AuthorizationException() {
        super();
    }

    /**
     * Constructs a new AuthorizationException instance.
     *
     * @param throwable the Throwable.
     * @param message the String.
     */
    public AuthorizationException(String message, Throwable throwable) {
        super(message, throwable);
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
     * @param throwable the Throwable.
     */
    public AuthorizationException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Getter for the Parameters.
     *
     * @return the Map<String, String>.
     */
    public Map<String, String> getParameters() {
        return new HashMap<String, String>(this.parameterMap);
    }
}
