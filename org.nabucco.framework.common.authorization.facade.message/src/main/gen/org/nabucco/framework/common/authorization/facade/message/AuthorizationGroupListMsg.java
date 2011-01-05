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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * AuthorizationGroupListMsg<p/>Message for passing an AuthorizationGroup list<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationGroupListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "authorizationGroupList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    private List<AuthorizationGroup> authorizationGroupList;

    /** Constructs a new AuthorizationGroupListMsg instance. */
    public AuthorizationGroupListMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationGroup>(PROPERTY_NAMES[0],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[0], this.authorizationGroupList));
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
        final AuthorizationGroupListMsg other = ((AuthorizationGroupListMsg) obj);
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
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0
                : this.authorizationGroupList.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationGroupListMsg>\n");
        appendable.append(super.toString());
        appendable
                .append((("<authorizationGroupList>" + this.authorizationGroupList) + "</authorizationGroupList>\n"));
        appendable.append("</AuthorizationGroupListMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
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
