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
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.model;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationRoleEditViewModel
 * <p/>
 * Edit view for datatype AuthorizationRole
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationRoleEditViewModel extends EditViewModel implements Loggable {

    private AuthorizationRole role;

    private Set<AuthorizationGroup> groupSet;

    private Set<AuthorizationUser> userSet;

    public static final String PROPERTY_ROLE_ROLENAME = "roleRolename";

    public static final String PROPERTY_ROLE_DESCRIPTION = "roleDescription";

    public static final String PROPERTY_ROLE_TYPE = "roleRoleType";

    public static final String PROPERTY_ROLE_OWNER = "roleOwner";

    private String groupSetGroupname;

    public static final String PROPERTY_GROUPSET_GROUPNAME = "groupSetGroupname";

    private String userSetUsername;

    public static final String PROPERTY_USERSET_USERNAME = "userSetUsername";

    /** Constructs a new AuthorizationRoleEditViewModel instance. */
    public AuthorizationRoleEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     * 
     * @return the String.
     */
    @Override
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel";
    }

    /**
     * Getter for the Values.
     * 
     * @return the Map<String, Serializable>.
     */
    @Override
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_ROLE_OWNER, this.getRoleOwner());
        result.put(PROPERTY_GROUPSET_GROUPNAME, this.getGroupSetGroupname());
        result.put(PROPERTY_ROLE_ROLENAME, this.getRoleRolename());
        result.put(PROPERTY_ROLE_DESCRIPTION, this.getRoleDescription());
        result.put(PROPERTY_USERSET_USERNAME, this.getUserSetUsername());
        result.put(PROPERTY_ROLE_TYPE, this.getRoleRoleType());
        return result;
    }

    /**
     * Setter for the Role.
     * 
     * @param newValue
     *            the AuthorizationRole.
     */
    public void setRole(AuthorizationRole newValue) {
        AuthorizationRole oldValue = this.role;
        this.role = newValue;
        this.updateProperty(PROPERTY_ROLE_ROLENAME, ((oldValue != null) ? oldValue.getRolename() : ""),
                ((newValue != null) ? newValue.getRolename() : ""));
        this.updateProperty(PROPERTY_ROLE_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_ROLE_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_ROLE_TYPE,
                ((oldValue != null) ? (oldValue.getOwner() != null ? oldValue.getOwner() : null) : null),
                ((newValue != null) ? (newValue.getOwner() != null ? newValue.getOwner() : null) : null));
    }

    /**
     * Getter for the Role.
     * 
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getRole() {
        return this.role;
    }

    /**
     * @param roleRoleType
     *            The roleRoleType to set.
     */
    public void setRoleRoleType(Code roleRoleType) {
        if (this.role != null) {
            Code oldValue = this.role.getRoleType();
            this.role.setRoleType(roleRoleType);
            this.updateProperty(PROPERTY_ROLE_TYPE, oldValue, roleRoleType);
        }
    }

    /**
     * @return Returns the roleRoleType.
     */
    public Code getRoleRoleType() {
        if (this.role == null) {
            return null;
        }
        return this.role.getRoleType();
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
     * Setter for the RoleRolename.
     * 
     * @param newRolename
     *            the String.
     */
    public void setRoleRolename(String newRolename) {
        if (((role != null) && (role.getRolename() == null))) {
            Name rolename = new Name();
            role.setRolename(rolename);
        }
        String oldVal = role.getRolename().getValue();
        role.getRolename().setValue(newRolename);
        this.updateProperty(PROPERTY_ROLE_ROLENAME, oldVal, newRolename);
        if (((!oldVal.equals(newRolename)) && role.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            role.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the RoleRolename.
     * 
     * @return the String.
     */
    public String getRoleRolename() {
        if ((((role == null) || (role.getRolename() == null)) || (role.getRolename().getValue() == null))) {
            return "";
        }
        return role.getRolename().getValue();
    }

    /**
     * Setter for the RoleDescription.
     * 
     * @param newDescription
     *            the String.
     */
    public void setRoleDescription(String newDescription) {
        if (((role != null) && (role.getDescription() == null))) {
            Description description = new Description();
            role.setDescription(description);
        }
        String oldVal = role.getDescription().getValue();
        role.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_ROLE_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && role.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            role.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the RoleDescription.
     * 
     * @return the String.
     */
    public String getRoleDescription() {
        if ((((role == null) || (role.getDescription() == null)) || (role.getDescription().getValue() == null))) {
            return "";
        }
        return role.getDescription().getValue();
    }

    /**
     * Setter for the RoleOwner.
     * 
     * @param newOwner
     *            the String.
     */
    public void setRoleOwner(String newOwner) {
        if (((role != null) && (role.getOwner() == null))) {
            Owner owner = new Owner();
            role.setOwner(owner);
        }
        String oldVal = role.getOwner().getValue();
        role.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_ROLE_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && role.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            role.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the RoleOwner.
     * 
     * @return the String.
     */
    public String getRoleOwner() {
        if ((((role == null) || (role.getOwner() == null)) || (role.getOwner().getValue() == null))) {
            return "";
        }
        return role.getOwner().getValue();
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
}
