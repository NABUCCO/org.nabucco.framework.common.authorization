/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserMsg<p/>Message for passing an AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationUserMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationUser" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private AuthorizationUser authorizationUser;

    /** Constructs a new AuthorizationUserMsg instance. */
    public AuthorizationUserMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationUser>(PROPERTY_NAMES[0],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[0], this.authorizationUser));
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
        final AuthorizationUserMsg other = ((AuthorizationUserMsg) obj);
        if ((this.authorizationUser == null)) {
            if ((other.authorizationUser != null))
                return false;
        } else if ((!this.authorizationUser.equals(other.authorizationUser)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationUser == null) ? 0 : this.authorizationUser
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationUserMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationUser>" + this.authorizationUser) + "</authorizationUser>\n"));
        appendable.append("</AuthorizationUserMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationUser.
     *
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getAuthorizationUser() {
        return this.authorizationUser;
    }

    /**
     * Missing description at method setAuthorizationUser.
     *
     * @param authorizationUser the AuthorizationUser.
     */
    public void setAuthorizationUser(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
    }
}
