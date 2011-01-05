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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationRoleMaintainMsg<p/>Message for persisting an AuthorizationRole with its AuthorizationGroup and AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-24
 */
public class AuthorizationRoleMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationRole", "authorizationUserList",
            "authorizationGroupList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m0,n;", "m0,n;" };

    private AuthorizationRole authorizationRole;

    private List<AuthorizationUser> authorizationUserList;

    private List<AuthorizationGroup> authorizationGroupList;

    /** Constructs a new AuthorizationRoleMaintainMsg instance. */
    public AuthorizationRoleMaintainMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationRole>(PROPERTY_NAMES[0],
                AuthorizationRole.class, PROPERTY_CONSTRAINTS[0], this.authorizationRole));
        properties.add(new ListProperty<AuthorizationUser>(PROPERTY_NAMES[1],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[1], this.authorizationUserList));
        properties.add(new ListProperty<AuthorizationGroup>(PROPERTY_NAMES[2],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[2], this.authorizationGroupList));
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
        final AuthorizationRoleMaintainMsg other = ((AuthorizationRoleMaintainMsg) obj);
        if ((this.authorizationRole == null)) {
            if ((other.authorizationRole != null))
                return false;
        } else if ((!this.authorizationRole.equals(other.authorizationRole)))
            return false;
        if ((this.authorizationUserList == null)) {
            if ((other.authorizationUserList != null))
                return false;
        } else if ((!this.authorizationUserList.equals(other.authorizationUserList)))
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
        result = ((PRIME * result) + ((this.authorizationRole == null) ? 0 : this.authorizationRole
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationUserList == null) ? 0
                : this.authorizationUserList.hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0
                : this.authorizationGroupList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationRoleMaintainMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationRole>" + this.authorizationRole) + "</authorizationRole>\n"));
        appendable
                .append((("<authorizationUserList>" + this.authorizationUserList) + "</authorizationUserList>\n"));
        appendable
                .append((("<authorizationGroupList>" + this.authorizationGroupList) + "</authorizationGroupList>\n"));
        appendable.append("</AuthorizationRoleMaintainMsg>\n");
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
