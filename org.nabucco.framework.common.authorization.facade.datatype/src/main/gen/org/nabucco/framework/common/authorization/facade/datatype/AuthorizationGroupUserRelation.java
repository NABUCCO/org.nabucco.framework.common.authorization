/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationGroupUserRelation<p/>Relation with a target AuthorizationUser<p/>
 *
 * @version 1.0
 * @author nmoser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationGroupUserRelation extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "user" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    /** The AuthorizationUser relation target. */
    private AuthorizationUser user;

    /** Constructs a new AuthorizationGroupUserRelation instance. */
    public AuthorizationGroupUserRelation() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationGroupUserRelation.
     */
    protected void cloneObject(AuthorizationGroupUserRelation clone) {
        super.cloneObject(clone);
        if ((this.getUser() != null)) {
            clone.setUser(this.getUser().cloneObject());
        }
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationUser>(PROPERTY_NAMES[0],
                AuthorizationUser.class, PROPERTY_CONSTRAINTS[0], this.user));
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
        final AuthorizationGroupUserRelation other = ((AuthorizationGroupUserRelation) obj);
        if ((this.user == null)) {
            if ((other.user != null))
                return false;
        } else if ((!this.user.equals(other.user)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.user == null) ? 0 : this.user.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationGroupUserRelation>\n");
        appendable.append(super.toString());
        appendable.append((("<user>" + this.user) + "</user>\n"));
        appendable.append("</AuthorizationGroupUserRelation>\n");
        return appendable.toString();
    }

    @Override
    public AuthorizationGroupUserRelation cloneObject() {
        AuthorizationGroupUserRelation clone = new AuthorizationGroupUserRelation();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The AuthorizationUser relation target.
     *
     * @param user the AuthorizationUser.
     */
    public void setUser(AuthorizationUser user) {
        this.user = user;
    }

    /**
     * The AuthorizationUser relation target.
     *
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getUser() {
        return this.user;
    }
}
