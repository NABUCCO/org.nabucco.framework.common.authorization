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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * AuthorizationPermissionListMsg<p/>Message for passing an AuthorizationPermission list<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationPermissionListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationPermissionList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<AuthorizationPermission> authorizationPermissionList;

    /** Constructs a new AuthorizationPermissionListMsg instance. */
    public AuthorizationPermissionListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationPermission>(PROPERTY_NAMES[0],
                AuthorizationPermission.class, PROPERTY_CONSTRAINTS[0],
                this.authorizationPermissionList));
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
        final AuthorizationPermissionListMsg other = ((AuthorizationPermissionListMsg) obj);
        if ((this.authorizationPermissionList == null)) {
            if ((other.authorizationPermissionList != null))
                return false;
        } else if ((!this.authorizationPermissionList.equals(other.authorizationPermissionList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationPermissionList == null) ? 0
                : this.authorizationPermissionList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationPermissionListMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationPermissionList>" + this.authorizationPermissionList) + "</authorizationPermissionList>\n"));
        appendable.append("</AuthorizationPermissionListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationPermissionList.
     *
     * @return the List<AuthorizationPermission>.
     */
    public List<AuthorizationPermission> getAuthorizationPermissionList() {
        if ((this.authorizationPermissionList == null)) {
            this.authorizationPermissionList = new ArrayList<AuthorizationPermission>();
        }
        return this.authorizationPermissionList;
    }
}
