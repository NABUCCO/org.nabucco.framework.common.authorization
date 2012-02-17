/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationPermissionEditViewModel
 * <p/>
 * Edit view for datatype AuthorizationPermission
 * <p/>
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

    private String groupSetGroupname;

    public static final String PROPERTY_GROUPSET_GROUPNAME = "groupSetGroupname";

    private String userSetUsername;

    public static final String PROPERTY_USERSET_USERNAME = "userSetUsername";

    private String roleSetRolename;

    public static final String PROPERTY_ROLESET_ROLENAME = "roleSetRolename";

    /** Constructs a new AuthorizationPermissionEditViewModel instance. */
    public AuthorizationPermissionEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     * 
     * @return the String.
     */
    @Override
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel";
    }

    /**
     * Getter for the Values.
     * 
     * @return the Map<String, Serializable>.
     */
    @Override
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_ROLESET_ROLENAME, this.getRoleSetRolename());
        result.put(PROPERTY_GROUPSET_GROUPNAME, this.getGroupSetGroupname());
        result.put(PROPERTY_PERMISSION_DESCRIPTION, this.getPermissionDescription());
        result.put(PROPERTY_PERMISSION_OWNER, this.getPermissionOwner());
        result.put(PROPERTY_PERMISSION_PERMISSIONNAME, this.getPermissionPermissionname());
        result.put(PROPERTY_USERSET_USERNAME, this.getUserSetUsername());
        result.put(PROPERTY_PERMISSION_PERMISSIONTYPE, this.getPermissionPermissionType());
        return result;
    }

    /**
     * @param permissionPermissionType
     *            The permissionPermissionType to set.
     */
    public void setPermissionPermissionType(Code permissionPermissionType) {
        if (this.getPermission() != null) {
            Code oldValue = this.getPermission().getPermissionType();
            this.getPermission().setPermissionType(permissionPermissionType);
            this.updateProperty(PROPERTY_PERMISSION_PERMISSIONTYPE, oldValue, permissionPermissionType);
        }
    }

    /**
     * @return Returns the permissionPermissionType.
     */
    public Code getPermissionPermissionType() {
        if (this.permission == null) {
            return null;
        }
        return this.permission.getPermissionType();
    }

    /**
     * Setter for the Permission.
     * 
     * @param newValue
     *            the AuthorizationPermission.
     */
    public void setPermission(AuthorizationPermission newValue) {
        AuthorizationPermission oldValue = this.permission;
        this.permission = newValue;
        this.updateProperty(PROPERTY_PERMISSION_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_PERMISSION_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONNAME,
                ((oldValue != null) ? oldValue.getPermissionname() : ""),
                ((newValue != null) ? newValue.getPermissionname() : ""));
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONTYPE,
                ((oldValue != null) ? (oldValue.getPermissionType() != null ? oldValue.getPermissionType() : null)
                        : null),
                ((newValue != null) ? (newValue.getPermissionType() != null ? newValue.getPermissionType() : null)
                        : null));
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
     * @param newPermissionname
     *            the String.
     */
    public void setPermissionPermissionname(String newPermissionname) {
        if (((permission != null) && (permission.getPermissionname() == null))) {
            Name permissionname = new Name();
            permission.setPermissionname(permissionname);
        }
        String oldVal = permission.getPermissionname().getValue();
        permission.getPermissionname().setValue(newPermissionname);
        this.updateProperty(PROPERTY_PERMISSION_PERMISSIONNAME, oldVal, newPermissionname);
        if (((!oldVal.equals(newPermissionname)) && permission.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionPermissionname.
     * 
     * @return the String.
     */
    public String getPermissionPermissionname() {
        if ((((permission == null) || (permission.getPermissionname() == null)) || (permission.getPermissionname()
                .getValue() == null))) {
            return "";
        }
        return permission.getPermissionname().getValue();
    }

    /**
     * Setter for the PermissionDescription.
     * 
     * @param newDescription
     *            the String.
     */
    public void setPermissionDescription(String newDescription) {
        if (((permission != null) && (permission.getDescription() == null))) {
            Description description = new Description();
            permission.setDescription(description);
        }
        String oldVal = permission.getDescription().getValue();
        permission.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_PERMISSION_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && permission.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionDescription.
     * 
     * @return the String.
     */
    public String getPermissionDescription() {
        if ((((permission == null) || (permission.getDescription() == null)) || (permission.getDescription().getValue() == null))) {
            return "";
        }
        return permission.getDescription().getValue();
    }

    /**
     * Setter for the PermissionOwner.
     * 
     * @param newOwner
     *            the String.
     */
    public void setPermissionOwner(String newOwner) {
        if (((permission != null) && (permission.getOwner() == null))) {
            Owner owner = new Owner();
            permission.setOwner(owner);
        }
        String oldVal = permission.getOwner().getValue();
        permission.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_PERMISSION_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && permission.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            permission.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the PermissionOwner.
     * 
     * @return the String.
     */
    public String getPermissionOwner() {
        if ((((permission == null) || (permission.getOwner() == null)) || (permission.getOwner().getValue() == null))) {
            return "";
        }
        return permission.getOwner().getValue();
    }

    /**
     * Getter for the GroupSetGroupname.
     * 
     * @return the String.
     */
    public String getGroupSetGroupname() {
        return this.groupSetGroupname;
    }

    /**
     * Setter for the GroupSetGroupname.
     * 
     * @param groupSetGroupname
     *            the String.
     */
    public void setGroupSetGroupname(String groupSetGroupname) {
        super.updateProperty(PROPERTY_GROUPSET_GROUPNAME, this.groupSetGroupname,
                (this.groupSetGroupname = groupSetGroupname));
    }

    /**
     * Setter for the GroupSet.
     * 
     * @param set
     *            the Set<AuthorizationGroup>.
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
            if (((datatype == null) || (datatype.getGroupname() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getGroupname().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setGroupSetGroupname(result.toString());
    }

    /**
     * Getter for the UserSetUsername.
     * 
     * @return the String.
     */
    public String getUserSetUsername() {
        return this.userSetUsername;
    }

    /**
     * Setter for the UserSetUsername.
     * 
     * @param userSetUsername
     *            the String.
     */
    public void setUserSetUsername(String userSetUsername) {
        super.updateProperty(PROPERTY_USERSET_USERNAME, this.userSetUsername, (this.userSetUsername = userSetUsername));
    }

    /**
     * Setter for the UserSet.
     * 
     * @param set
     *            the Set<AuthorizationUser>.
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
            if (((datatype == null) || (datatype.getUsername() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getUsername().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setUserSetUsername(result.toString());
    }

    /**
     * Getter for the RoleSetRolename.
     * 
     * @return the String.
     */
    public String getRoleSetRolename() {
        return this.roleSetRolename;
    }

    /**
     * Setter for the RoleSetRolename.
     * 
     * @param roleSetRolename
     *            the String.
     */
    public void setRoleSetRolename(String roleSetRolename) {
        super.updateProperty(PROPERTY_ROLESET_ROLENAME, this.roleSetRolename, (this.roleSetRolename = roleSetRolename));
    }

    /**
     * Setter for the RoleSet.
     * 
     * @param set
     *            the Set<AuthorizationRole>.
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
            if (((datatype == null) || (datatype.getRolename() == null))) {
                result.append("n/a");
            } else {
                result.append(datatype.getRolename().getValue());
            }
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        this.setRoleSetRolename(result.toString());
    }
}
