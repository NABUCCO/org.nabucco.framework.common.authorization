/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.ArrayList;
import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationRoleListMsg<p/>Message for passing an AuthorizationRole list<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationRoleListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationRoleList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<AuthorizationRole> authorizationRoleList;

    /** Constructs a new AuthorizationRoleListMsg instance. */
    public AuthorizationRoleListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationRole>(PROPERTY_NAMES[0],
                AuthorizationRole.class, PROPERTY_CONSTRAINTS[0], this.authorizationRoleList));
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
        final AuthorizationRoleListMsg other = ((AuthorizationRoleListMsg) obj);
        if ((this.authorizationRoleList == null)) {
            if ((other.authorizationRoleList != null))
                return false;
        } else if ((!this.authorizationRoleList.equals(other.authorizationRoleList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationRoleList == null) ? 0
                : this.authorizationRoleList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationRoleListMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationRoleList>" + this.authorizationRoleList) + "</authorizationRoleList>\n"));
        appendable.append("</AuthorizationRoleListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationRoleList.
     *
     * @return the List<AuthorizationRole>.
     */
    public List<AuthorizationRole> getAuthorizationRoleList() {
        if ((this.authorizationRoleList == null)) {
            this.authorizationRoleList = new ArrayList<AuthorizationRole>();
        }
        return this.authorizationRoleList;
    }
}
