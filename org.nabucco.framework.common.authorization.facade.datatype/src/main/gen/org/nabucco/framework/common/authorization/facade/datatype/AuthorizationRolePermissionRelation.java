/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * AuthorizationRolePermissionRelation<p/>Relation with a target AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationRolePermissionRelation extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "permission" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    /** The AuthorizationPermission relation target. */
    private AuthorizationPermission permission;

    /** Constructs a new AuthorizationRolePermissionRelation instance. */
    public AuthorizationRolePermissionRelation() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationRolePermissionRelation.
     */
    protected void cloneObject(AuthorizationRolePermissionRelation clone) {
        super.cloneObject(clone);
        if ((this.getPermission() != null)) {
            clone.setPermission(this.getPermission().cloneObject());
        }
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<AuthorizationPermission>(PROPERTY_NAMES[0],
                AuthorizationPermission.class, PROPERTY_CONSTRAINTS[0], this.permission));
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
        final AuthorizationRolePermissionRelation other = ((AuthorizationRolePermissionRelation) obj);
        if ((this.permission == null)) {
            if ((other.permission != null))
                return false;
        } else if ((!this.permission.equals(other.permission)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.permission == null) ? 0 : this.permission.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationRolePermissionRelation>\n");
        appendable.append(super.toString());
        appendable.append((("<permission>" + this.permission) + "</permission>\n"));
        appendable.append("</AuthorizationRolePermissionRelation>\n");
        return appendable.toString();
    }

    @Override
    public AuthorizationRolePermissionRelation cloneObject() {
        AuthorizationRolePermissionRelation clone = new AuthorizationRolePermissionRelation();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The AuthorizationPermission relation target.
     *
     * @param permission the AuthorizationPermission.
     */
    public void setPermission(AuthorizationPermission permission) {
        this.permission = permission;
    }

    /**
     * The AuthorizationPermission relation target.
     *
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getPermission() {
        return this.permission;
    }
}
