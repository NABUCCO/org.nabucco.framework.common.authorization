/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationUserRoleRelation<p/>Relation with a target AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationUserRoleRelation extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "role" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    /** The AuthorizationRole relation target. */
    private AuthorizationRole role;

    /** Constructs a new AuthorizationUserRoleRelation instance. */
    public AuthorizationUserRoleRelation() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationUserRoleRelation.
     */
    protected void cloneObject(AuthorizationUserRoleRelation clone) {
        super.cloneObject(clone);
        if ((this.getRole() != null)) {
            clone.setRole(this.getRole().cloneObject());
        }
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationRole>(PROPERTY_NAMES[0],
                AuthorizationRole.class, PROPERTY_CONSTRAINTS[0], this.role));
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
        final AuthorizationUserRoleRelation other = ((AuthorizationUserRoleRelation) obj);
        if ((this.role == null)) {
            if ((other.role != null))
                return false;
        } else if ((!this.role.equals(other.role)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.role == null) ? 0 : this.role.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationUserRoleRelation>\n");
        appendable.append(super.toString());
        appendable.append((("<role>" + this.role) + "</role>\n"));
        appendable.append("</AuthorizationUserRoleRelation>\n");
        return appendable.toString();
    }

    @Override
    public AuthorizationUserRoleRelation cloneObject() {
        AuthorizationUserRoleRelation clone = new AuthorizationUserRoleRelation();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The AuthorizationRole relation target.
     *
     * @param role the AuthorizationRole.
     */
    public void setRole(AuthorizationRole role) {
        this.role = role;
    }

    /**
     * The AuthorizationRole relation target.
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getRole() {
        return this.role;
    }
}
