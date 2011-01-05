/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * AuthorizationGroupMsg<p/>Message for passing an AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationGroupMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationGroup" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private AuthorizationGroup authorizationGroup;

    /** Constructs a new AuthorizationGroupMsg instance. */
    public AuthorizationGroupMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationGroup>(PROPERTY_NAMES[0],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[0], this.authorizationGroup));
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
        final AuthorizationGroupMsg other = ((AuthorizationGroupMsg) obj);
        if ((this.authorizationGroup == null)) {
            if ((other.authorizationGroup != null))
                return false;
        } else if ((!this.authorizationGroup.equals(other.authorizationGroup)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationGroup == null) ? 0
                : this.authorizationGroup.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationGroupMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationGroup>" + this.authorizationGroup) + "</authorizationGroup>\n"));
        appendable.append("</AuthorizationGroupMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationGroup.
     *
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getAuthorizationGroup() {
        return this.authorizationGroup;
    }

    /**
     * Missing description at method setAuthorizationGroup.
     *
     * @param authorizationGroup the AuthorizationGroup.
     */
    public void setAuthorizationGroup(AuthorizationGroup authorizationGroup) {
        this.authorizationGroup = authorizationGroup;
    }
}
