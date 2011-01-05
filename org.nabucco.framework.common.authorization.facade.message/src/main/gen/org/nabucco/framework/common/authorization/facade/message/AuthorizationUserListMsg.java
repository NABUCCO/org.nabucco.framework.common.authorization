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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserListMsg<p/>Message for passing an AuthorizationUser list<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationUserListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationUserList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<AuthorizationUser> authorizationUserList;

    /** Constructs a new AuthorizationUserListMsg instance. */
    public AuthorizationUserListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationUser>(PROPERTY_NAMES[0],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[0], this.authorizationUserList));
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
        final AuthorizationUserListMsg other = ((AuthorizationUserListMsg) obj);
        if ((this.authorizationUserList == null)) {
            if ((other.authorizationUserList != null))
                return false;
        } else if ((!this.authorizationUserList.equals(other.authorizationUserList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationUserList == null) ? 0
                : this.authorizationUserList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationUserListMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationUserList>" + this.authorizationUserList) + "</authorizationUserList>\n"));
        appendable.append("</AuthorizationUserListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getAuthorizationUserList.
     *
     * @return the List<AuthorizationUser>.
     */
    public List<AuthorizationUser> getAuthorizationUserList() {
        if ((this.authorizationUserList == null)) {
            this.authorizationUserList = new ArrayList<AuthorizationUser>();
        }
        return this.authorizationUserList;
    }
}
