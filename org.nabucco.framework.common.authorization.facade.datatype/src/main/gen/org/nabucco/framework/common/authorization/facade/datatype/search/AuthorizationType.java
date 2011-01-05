/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype.search;

import org.nabucco.framework.base.facade.datatype.Enumeration;

/**
 * AuthorizationType<p/>Type of the authorization element<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-25
 */
public enum AuthorizationType implements Enumeration {
    GROUP("G"),
    USER("U"),
    ROLE("R"),
    PERMISSION("P"), ;

    private String id;

    /**
     * Constructs a new AuthorizationType instance.
     *
     * @param id the String.
     */
    AuthorizationType(String id) {
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
