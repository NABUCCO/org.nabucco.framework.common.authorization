/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationPermissionEditViewModel<p/>Edit view for datatype AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermissionEditViewModel extends EditViewModel implements Loggable {

    private AuthorizationPermission permission;

    private Set<AuthorizationGroup> groupSet;

    private Set<AuthorizationUser> userSet;

    private Set<AuthorizationRole> roleSet;

    public static final String PROPERTY_PERMISSION_PERMISSIONNAME = "permissionPermissionname";

    public static final String PROPERTY_PERMISSION_DESCRIPTION = "permissionDescription";

    public static final String PROPERTY_PERMISSION_OWNER = "permissionOwner";

    public static final String PROPERTY_PERMISSION_PERMISSIONTYPE = "permissionPermissionType";

    private String groupSetGroupType;

    public static final String PROPERTY_GROUPSET_GROUPTYPE = "groupSetGroupType";

    private String userSetUserType;

    public static final String PROPERTY_USERSET_USERTYPE = "userSetUserType";

    private String roleSetRoleType;

    public static final String PROPERTY_ROLESET_ROLETYPE = "roleSetRoleType";

    /** Constructs a new AuthorizationPermissionEditViewModel instance. */
    public AuthorizationPermissionEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_PERMISSION_PERMISSIONTYPE, this.getPermissionPermissionType());
        result.put(PROPERTY_PERMISSION_DESCRIPTION, this.getPermissionDescription());
        result.put(PROPERTY_USERSET_USERTYPE, this.getUserSetUserType());
        result.put(PROPERTY_ROLESET_ROLETYPE, this.getRoleSetRoleType());
        result.put(PROPERTY_GROUPSET_GROUPTYPE, this.getGroupSetGroupType());
        result.put(PROPERTY_PERMISSION_OWNER, this.getPermissionOwner());
        result.put(PROPERTY_PERMISSION_PERMISSIONNAME, this.getPermissionPermissionname());
        return result;
    }

    /**
     * Setter for the Permission.
     *
     * @param newValue the AuthorizationPermission.
     */
    public void setPermission(AuthorizationPermission newValue) {
        AuthorizationPermission oldValue = this.permission;
        this.permission = newValue;
        this.updateProperty(PROPERTY_PERMISSION_DESCRIPTION,
                ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_PERMISSION_OWNER, ((oldValue != null) ? oldValue.getOwner()
                : ""), ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONNAME,
                ((oldValue != null) ? oldValue.getPermissionname() : ""),
                ((newValue != null) ? newValue.getPermissionname() : ""));
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONTYPE,
                ((oldValue != null) ? oldValue.getPermissionType() : ""),
                ((newValue != null) ? newValue.getPermissionType() : ""));
    }

    /**
     * Getter for the Permission.
     *
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getPermission() {
        return this.permission;
    }

    /**
     * Getter for the GroupSet.
     *
     * @return the Set<AuthorizationGroup>.
     */
    public Set<AuthorizationGroup> getGroupSet() {
        if ((this.groupSet == null)) {
            this.groupSet = new HashSet<AuthorizationGroup>();
        }
        return this.groupSet;
    }

    /**
     * Getter for the UserSet.
     *
     * @return the Set<AuthorizationUser>.
     */
    public Set<AuthorizationUser> getUserSet() {
        if ((this.userSet == null)) {
            this.userSet = new HashSet<AuthorizationUser>();
        }
        return this.userSet;
    }

    /**
     * Getter for the RoleSet.
     *
     * @return the Set<AuthorizationRole>.
     */
    public Set<AuthorizationRole> getRoleSet() {
        if ((this.roleSet == null)) {
            this.roleSet = new HashSet<AuthorizationRole>();
        }
        return this.roleSet;
    }

    /**
     * Setter for the PermissionPermissionname.
     *
     * @param newPermissionname the String.
     */
    public void setPermissionPermissionname(String newPermissionname) {
        if (((permission != null) && (permission.getPermissionname() == null))) {
            Name permissionname = new Name();
            permission.setPermissionname(permissionname);
        }
        String oldVal = permission.getPermissionname().getValue();
        permission.getPermissionname().setValue(newPermissionname);
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONNAME, oldVal, newPermissionname);
        if (((!oldVal.equals(newPermissionname)) && permission.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionPermissionname.
     *
     * @return the String.
     */
    public String getPermissionPermissionname() {
        if ((((permission == null) || (permission.getPermissionname() == null)) || (permission
                .getPermissionname().getValue() == null))) {
            return "";
        }
        return permission.getPermissionname().getValue();
    }

    /**
     * Setter for the PermissionDescription.
     *
     * @param newDescription the String.
     */
    public void setPermissionDescription(String newDescription) {
        if (((permission != null) && (permission.getDescription() == null))) {
            Description description = new Description();
            permission.setDescription(description);
        }
        String oldVal = permission.getDescription().getValue();
        permission.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_PERMISSION_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && permission.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionDescription.
     *
     * @return the String.
     */
    public String getPermissionDescription() {
        if ((((permission == null) || (permission.getDescription() == null)) || (permission
                .getDescription().getValue() == null))) {
            return "";
        }
        return permission.getDescription().getValue();
    }

    /**
     * Setter for the PermissionOwner.
     *
     * @param newOwner the String.
     */
    public void setPermissionOwner(String newOwner) {
        if (((permission != null) && (permission.getOwner() == null))) {
            Owner owner = new Owner();
            permission.setOwner(owner);
        }
        String oldVal = permission.getOwner().getValue();
        permission.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_PERMISSION_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && permission.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionOwner.
     *
     * @return the String.
     */
    public String getPermissionOwner() {
        if ((((permission == null) || (permission.getOwner() == null)) || (permission.getOwner()
                .getValue() == null))) {
            return "";
        }
        return permission.getOwner().getValue();
    }

    /**
     * Setter for the PermissionPermissionType.
     *
     * @param newPermissionType the String.
     */
    public void setPermissionPermissionType(String newPermissionType) {
        if (((permission != null) && (permission.getPermissionType() == null))) {
            CodeType permissionType = new CodeType();
            permission.setPermissionType(permissionType);
        }
        String oldVal = permission.getPermissionType().getValue();
        permission.getPermissionType().setValue(newPermissionType);
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONTYPE, oldVal, newPermissionType);
        if (((!oldVal.equals(newPermissionType)) && permission.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionPermissionType.
     *
     * @return the String.
     */
    public String getPermissionPermissionType() {
        if ((((permission == null) || (permission.getPermissionType() == null)) || (permission
                .getPermissionType().getValue() == null))) {
            return "";
        }
        return permission.getPermissionType().getValue();
    }

    /**
     * Getter for the GroupSetGroupType.
     *
     * @return the String.
     */
    public String getGroupSetGroupType() {
        return this.groupSetGroupType;
    }

    /**
     * Setter for the GroupSetGroupType.
     *
     * @param groupSetGroupType the String.
     */
    public void setGroupSetGroupType(String groupSetGroupType) {
        super.updateProperty(PROPERTY_GROUPSET_GROUPTYPE, this.groupSetGroupType,
                (this.groupSetGroupType = groupSetGroupType));
    }

    /**
     * Setter for the GroupSet.
     *
     * @param set the Set<AuthorizationGroup>.
     */
    public void setGroupSet(Set<AuthorizationGroup> set) {
        if ((set == null)) {
            set = new HashSet<AuthorizationGroup>();
        }
        this.groupSet = set;
        StringBuilder result = new StringBuilder();
        Iterator<AuthorizationGroup> iterator = set.iterator();
        while (iterator.hasNext()) {
            AuthorizationGroup datatype = iterator.next();
            if (((datatype == null) || (datatype.getGroupType() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getGroupType().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setGroupSetGroupType(result.toString());
    }

    /**
     * Getter for the UserSetUserType.
     *
     * @return the String.
     */
    public String getUserSetUserType() {
        return this.userSetUserType;
    }

    /**
     * Setter for the UserSetUserType.
     *
     * @param userSetUserType the String.
     */
    public void setUserSetUserType(String userSetUserType) {
        super.updateProperty(PROPERTY_USERSET_USERTYPE, this.userSetUserType,
                (this.userSetUserType = userSetUserType));
    }

    /**
     * Setter for the UserSet.
     *
     * @param set the Set<AuthorizationUser>.
     */
    public void setUserSet(Set<AuthorizationUser> set) {
        if ((set == null)) {
            set = new HashSet<AuthorizationUser>();
        }
        this.userSet = set;
        StringBuilder result = new StringBuilder();
        Iterator<AuthorizationUser> iterator = set.iterator();
        while (iterator.hasNext()) {
            AuthorizationUser datatype = iterator.next();
            if (((datatype == null) || (datatype.getUserType() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getUserType().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setUserSetUserType(result.toString());
    }

    /**
     * Getter for the RoleSetRoleType.
     *
     * @return the String.
     */
    public String getRoleSetRoleType() {
        return this.roleSetRoleType;
    }

    /**
     * Setter for the RoleSetRoleType.
     *
     * @param roleSetRoleType the String.
     */
    public void setRoleSetRoleType(String roleSetRoleType) {
        super.updateProperty(PROPERTY_ROLESET_ROLETYPE, this.roleSetRoleType,
                (this.roleSetRoleType = roleSetRoleType));
    }

    /**
     * Setter for the RoleSet.
     *
     * @param set the Set<AuthorizationRole>.
     */
    public void setRoleSet(Set<AuthorizationRole> set) {
        if ((set == null)) {
            set = new HashSet<AuthorizationRole>();
        }
        this.roleSet = set;
        StringBuilder result = new StringBuilder();
        Iterator<AuthorizationRole> iterator = set.iterator();
        while (iterator.hasNext()) {
            AuthorizationRole datatype = iterator.next();
            if (((datatype == null) || (datatype.getRoleType() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getRoleType().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setRoleSetRoleType(result.toString());
    }
}
