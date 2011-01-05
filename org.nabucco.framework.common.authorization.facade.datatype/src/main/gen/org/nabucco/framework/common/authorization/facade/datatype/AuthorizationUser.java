/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.security.User;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;

/**
 * AuthorizationUser<p/>Represents a user within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationUser extends User implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "password", "roleList", "permissionList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "m0,n;", "m0,n;" };

    /** The user password. */
    private Password password;

    /** The list of relations to AuthorizationRole. */
    private List<AuthorizationUserRoleRelation> roleList;

    /** The list of relations to AuthorizationPermission. */
    private List<AuthorizationUserPermissionRelation> permissionList;

    /** Constructs a new AuthorizationUser instance. */
    public AuthorizationUser() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationUser.
     */
    protected void cloneObject(AuthorizationUser clone) {
        super.cloneObject(clone);
        if ((this.getPassword() != null)) {
            clone.setPassword(this.getPassword().cloneObject());
        }
        if ((this.roleList instanceof NabuccoList<?>)) {
            clone.roleList = ((NabuccoList<AuthorizationUserRoleRelation>) this.roleList)
                    .cloneCollection();
        }
        if ((this.permissionList instanceof NabuccoList<?>)) {
            clone.permissionList = ((NabuccoList<AuthorizationUserPermissionRelation>) this.permissionList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the RoleListJPA.
     *
     * @return the List<AuthorizationUserRoleRelation>.
     */
    List<AuthorizationUserRoleRelation> getRoleListJPA() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationUserRoleRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationUserRoleRelation>) this.roleList).getDelegate();
    }

    /**
     * Setter for the RoleListJPA.
     *
     * @param roleList the List<AuthorizationUserRoleRelation>.
     */
    void setRoleListJPA(List<AuthorizationUserRoleRelation> roleList) {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationUserRoleRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationUserRoleRelation>) this.roleList).setDelegate(roleList);
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationUserPermissionRelation>.
     */
    List<AuthorizationUserPermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationUserPermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationUserPermissionRelation>) this.permissionList)
                .getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationUserPermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationUserPermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationUserPermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationUserPermissionRelation>) this.permissionList)
                .setDelegate(permissionList);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Password>(PROPERTY_NAMES[0], Password.class,
                PROPERTY_CONSTRAINTS[0], this.password));
        properties.add(new ListProperty<AuthorizationUserRoleRelation>(PROPERTY_NAMES[1],
                AuthorizationUserRoleRelation.class, PROPERTY_CONSTRAINTS[1], this.roleList));
        properties.add(new ListProperty<AuthorizationUserPermissionRelation>(PROPERTY_NAMES[2],
                AuthorizationUserPermissionRelation.class, PROPERTY_CONSTRAINTS[2],
                this.permissionList));
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
        final AuthorizationUser other = ((AuthorizationUser) obj);
        if ((this.password == null)) {
            if ((other.password != null))
                return false;
        } else if ((!this.password.equals(other.password)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.password == null) ? 0 : this.password.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationUser>\n");
        appendable.append(super.toString());
        appendable.append((("<password>" + this.password) + "</password>\n"));
        appendable.append("</AuthorizationUser>\n");
        return appendable.toString();
    }

    @Override
    public AuthorizationUser cloneObject() {
        AuthorizationUser clone = new AuthorizationUser();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The user password.
     *
     * @return the Password.
     */
    public Password getPassword() {
        return this.password;
    }

    /**
     * The user password.
     *
     * @param password the Password.
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * The user password.
     *
     * @param password the String.
     */
    public void setPassword(String password) {
        if ((this.password == null)) {
            this.password = new Password();
        }
        this.password.setValue(password);
    }

    /**
     * The list of relations to AuthorizationRole.
     *
     * @return the List<AuthorizationUserRoleRelation>.
     */
    public List<AuthorizationUserRoleRelation> getRoleList() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationUserRoleRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.roleList;
    }

    /**
     * The list of relations to AuthorizationPermission.
     *
     * @return the List<AuthorizationUserPermissionRelation>.
     */
    public List<AuthorizationUserPermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationUserPermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }
}
