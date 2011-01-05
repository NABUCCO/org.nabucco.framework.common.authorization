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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationPermissionMaintainMsg<p/>Message for persisting an AuthorizationPermission with its AuthorizationGroup, AuthorizationUser and AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-24
 */
public class AuthorizationPermissionMaintainMsg extends ServiceMessageSupport implements
        ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationPermission",
            "authorizationRoleList", "authorizationUserList", "authorizationGroupList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m0,n;", "m0,n;", "m0,n;" };

    private AuthorizationPermission authorizationPermission;

    private List<AuthorizationRole> authorizationRoleList;

    private List<AuthorizationUser> authorizationUserList;

    private List<AuthorizationGroup> authorizationGroupList;

    /** Constructs a new AuthorizationPermissionMaintainMsg instance. */
    public AuthorizationPermissionMaintainMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationPermission>(PROPERTY_NAMES[0],
                AuthorizationPermission.class, PROPERTY_CONSTRAINTS[0],
                this.authorizationPermission));
        properties.add(new ListProperty<AuthorizationRole>(PROPERTY_NAMES[1],
                AuthorizationRole.class, PROPERTY_CONSTRAINTS[1], this.authorizationRoleList));
        properties.add(new ListProperty<AuthorizationUser>(PROPERTY_NAMES[2],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[2], this.authorizationUserList));
        properties.add(new ListProperty<AuthorizationGroup>(PROPERTY_NAMES[3],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[3], this.authorizationGroupList));
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
        final AuthorizationPermissionMaintainMsg other = ((AuthorizationPermissionMaintainMsg) obj);
        if ((this.authorizationPermission == null)) {
            if ((other.authorizationPermission != null))
                return false;
        } else if ((!this.authorizationPermission.equals(other.authorizationPermission)))
            return false;
        if ((this.authorizationRoleList == null)) {
            if ((other.authorizationRoleList != null))
                return false;
        } else if ((!this.authorizationRoleList.equals(other.authorizationRoleList)))
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
        result = ((PRIME * result) + ((this.authorizationPermission == null) ? 0
                : this.authorizationPermission.hashCode()));
        result = ((PRIME * result) + ((this.authorizationRoleList == null) ? 0
                : this.authorizationRoleList.hashCode()));
        result = ((PRIME * result) + ((this.authorizationUserList == null) ? 0
                : this.authorizationUserList.hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0
                : this.authorizationGroupList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationPermissionMaintainMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationPermission>" + this.authorizationPermission) + "</authorizationPermission>\n"));
        appendable
                .append((("<authorizationRoleList>" + this.authorizationRoleList) + "</authorizationRoleList>\n"));
        appendable
                .append((("<authorizationUserList>" + this.authorizationUserList) + "</authorizationUserList>\n"));
        appendable
                .append((("<authorizationGroupList>" + this.authorizationGroupList) + "</authorizationGroupList>\n"));
        appendable.append("</AuthorizationPermissionMaintainMsg>\n");
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
