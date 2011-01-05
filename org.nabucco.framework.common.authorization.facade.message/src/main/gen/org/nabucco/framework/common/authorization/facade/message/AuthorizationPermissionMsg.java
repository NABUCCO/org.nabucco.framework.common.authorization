/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * AuthorizationPermissionMsg<p/>Message for passing an AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationPermissionMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationPermission" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private AuthorizationPermission authorizationPermission;

    /** Constructs a new AuthorizationPermissionMsg instance. */
    public AuthorizationPermissionMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationPermission>(PROPERTY_NAMES[0],
                AuthorizationPermission.class, PROPERTY_CONSTRAINTS[0],
                this.authorizationPermission));
        return properties;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final AuthorizationPermissionMsg other = ((AuthorizationPermissionMsg) obj);
        if ((this.authorizationPermission == null)) {
            if ((other.authorizationPermission != null))
                return false;
        } else if ((!this.authorizationPermission.equals(other.authorizationPermission)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationPermission == null) ? 0
                : this.authorizationPermission.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationPermissionMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationPermission>" + this.authorizationPermission) + "</authorizationPermission>\n"));
        appendable.append("</AuthorizationPermissionMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationPermission.
     *
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getAuthorizationPermission() {
        return this.authorizationPermission;
    }

    /**
     * Missing description at method setAuthorizationPermission.
     *
     * @param authorizationPermission the AuthorizationPermission.
     */
    public void setAuthorizationPermission(AuthorizationPermission authorizationPermission) {
        this.authorizationPermission = authorizationPermission;
    }
}
