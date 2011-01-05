/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype.login;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * AuthenticationType<p/>Type of the login<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-25
 */
public enum AuthenticationType implements Enumeration {
    /** Authentication against the database. */
    DATABASE("D"),
    /** Authentication against LDAP. */
    LDAP("L"),
    /** No Authentication. */
    NONE("N"), ;

    private String id;

    /**
     * Constructs a new AuthenticationType instance.
     *
     * @param id the String.
     */
    AuthenticationType(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    @Override
    public Enumeration cloneObject() {
        return this;
    }
}
