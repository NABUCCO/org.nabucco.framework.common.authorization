/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message.maintain;

import java.util.ArrayList;
import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserMaintainMsg<p/>Message for persisting an AuthorizationUser with its AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-02-22
 */
public class AuthorizationUserMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationUser", "authorizationGroupList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,n;" };

    private AuthorizationUser authorizationUser;

    private List<AuthorizationGroup> authorizationGroupList;

    /** Constructs a new AuthorizationUserMaintainMsg instance. */
    public AuthorizationUserMaintainMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationUser>(PROPERTY_NAMES[0],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[0], this.authorizationUser));
        properties.add(new ListProperty<AuthorizationGroup>(PROPERTY_NAMES[1],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[1], this.authorizationGroupList));
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
        final AuthorizationUserMaintainMsg other = ((AuthorizationUserMaintainMsg) obj);
        if ((this.authorizationUser == null)) {
            if ((other.authorizationUser != null))
                return false;
        } else if ((!this.authorizationUser.equals(other.authorizationUser)))
            return false;
        if ((this.authorizationGroupList == null)) {
            if ((other.authorizationGroupList != null))
                return false;
        } else if ((!this.authorizationGroupList.equals(other.authorizationGroupList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationUser == null) ? 0 : this.authorizationUser
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0
                : this.authorizationGroupList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationUserMaintainMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationUser>" + this.authorizationUser) + "</authorizationUser>\n"));
        appendable
                .append((("<authorizationGroupList>" + this.authorizationGroupList) + "</authorizationGroupList>\n"));
        appendable.append("</AuthorizationUserMaintainMsg>\n");
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

    /**
     * Missing description at method getAuthorizationGroupList.
     *
     * @return the List<AuthorizationGroup>.
     */
    public List<AuthorizationGroup> getAuthorizationGroupList() {
        if ((this.authorizationGroupList == null)) {
            this.authorizationGroupList = new ArrayList<AuthorizationGroup>();
        }
        return this.authorizationGroupList;
    }
}
