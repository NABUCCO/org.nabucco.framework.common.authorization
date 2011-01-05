/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationRoleMsg<p/>Message for passing an AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationRoleMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationRole" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private AuthorizationRole authorizationRole;

    /** Constructs a new AuthorizationRoleMsg instance. */
    public AuthorizationRoleMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationRole>(PROPERTY_NAMES[0],
                AuthorizationRole.class, PROPERTY_CONSTRAINTS[0], this.authorizationRole));
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
        final AuthorizationRoleMsg other = ((AuthorizationRoleMsg) obj);
        if ((this.authorizationRole == null)) {
            if ((other.authorizationRole != null))
                return false;
        } else if ((!this.authorizationRole.equals(other.authorizationRole)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationRole == null) ? 0 : this.authorizationRole
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationRoleMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationRole>" + this.authorizationRole) + "</authorizationRole>\n"));
        appendable.append("</AuthorizationRoleMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationRole.
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getAuthorizationRole() {
        return this.authorizationRole;
    }

    /**
     * Missing description at method setAuthorizationRole.
     *
     * @param authorizationRole the AuthorizationRole.
     */
    public void setAuthorizationRole(AuthorizationRole authorizationRole) {
        this.authorizationRole = authorizationRole;
    }
}
