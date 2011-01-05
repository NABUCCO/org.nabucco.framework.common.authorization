/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.security.Permission;

/**
 * AuthorizationPermission<p/>Represents a permission within the authorization component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermission extends Permission implements Datatype {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationPermission instance. */
    public AuthorizationPermission() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationPermission.
     */
    protected void cloneObject(AuthorizationPermission clone) {
        super.cloneObject(clone);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append(super.toString());
        return appendable.toString();
    }

    @Override
    public AuthorizationPermission cloneObject() {
        AuthorizationPermission clone = new AuthorizationPermission();
        this.cloneObject(clone);
        return clone;
    }
}
