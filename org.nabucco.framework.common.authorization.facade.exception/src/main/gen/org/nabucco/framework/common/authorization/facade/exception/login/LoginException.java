/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.exception.login;

import java.util.HashMap;
import java.util.Map;
import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * LoginException<p/>Exception for Login<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class LoginException extends ServiceException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> parameterMap = new HashMap<String, String>();

    /** Constructs a new LoginException instance. */
    public LoginException() {
        super();
    }

    /**
     * Constructs a new LoginException instance.
     *
     * @param throwable the Throwable.
     * @param message the String.
     */
    public LoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs a new LoginException instance.
     *
     * @param message the String.
     */
    public LoginException(String message) {
        super(message);
    }

    /**
     * Constructs a new LoginException instance.
     *
     * @param throwable the Throwable.
     */
    public LoginException(Throwable throwable) {
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
