/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.security.Role;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;

/**
 * AuthorizationRole<p/>Represents a role within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationRole extends Role implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "permissionList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    /** The list of relations to AuthorizationPermission. */
    private List<AuthorizationRolePermissionRelation> permissionList;

    /** Constructs a new AuthorizationRole instance. */
    public AuthorizationRole() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationRole.
     */
    protected void cloneObject(AuthorizationRole clone) {
        super.cloneObject(clone);
        if ((this.permissionList instanceof NabuccoList<?>)) {
            clone.permissionList = ((NabuccoList<AuthorizationRolePermissionRelation>) this.permissionList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationRolePermissionRelation>.
     */
    List<AuthorizationRolePermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationRolePermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationRolePermissionRelation>) this.permissionList)
                .getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationRolePermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationRolePermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationRolePermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationRolePermissionRelation>) this.permissionList)
                .setDelegate(permissionList);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationRolePermissionRelation>(PROPERTY_NAMES[0],
                AuthorizationRolePermissionRelation.class, PROPERTY_CONSTRAINTS[0],
                this.permissionList));
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append(super.toString());
        return appendable.toString();
    }

    @Override
    public AuthorizationRole cloneObject() {
        AuthorizationRole clone = new AuthorizationRole();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The list of relations to AuthorizationPermission.
     *
     * @return the List<AuthorizationRolePermissionRelation>.
     */
    public List<AuthorizationRolePermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationRolePermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }
}
