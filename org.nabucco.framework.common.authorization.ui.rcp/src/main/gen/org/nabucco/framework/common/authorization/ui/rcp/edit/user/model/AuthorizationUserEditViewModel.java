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
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.model;

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
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPassword;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationUserEditViewModel
 * <p/>
 * Edit view for datatype AuthorizationUser
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationUserEditViewModel extends EditViewModel implements Loggable {

    private AuthorizationUser user;

    private Set<AuthorizationGroup> groupSet;

    public static final String PROPERTY_USER_USERNAME = "userUsername";

    public static final String PROPERTY_USER_DESCRIPTION = "userDescription";

    public static final String PROPERTY_USER_PASSWORD = "userPassword";

    public static final String PROPERTY_USER_TYPE = "userUserType";

    public static final String PROPERTY_USER_OWNER = "userOwner";

    private String groupSetGroupname;

    public static final String PROPERTY_GROUPSET_GROUPNAME = "groupSetGroupname";

    /** Constructs a new AuthorizationUserEditViewModel instance. */
    public AuthorizationUserEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     * 
     * @return the String.
     */
    @Override
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel";
    }

    /**
     * Getter for the Values.
     * 
     * @return the Map<String, Serializable>.
     */
    @Override
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_USER_OWNER, this.getUserOwner());
        result.put(PROPERTY_GROUPSET_GROUPNAME, this.getGroupSetGroupname());
        result.put(PROPERTY_USER_DESCRIPTION, this.getUserDescription());
        result.put(PROPERTY_USER_PASSWORD, this.getUserPassword());
        result.put(PROPERTY_USER_USERNAME, this.getUserUsername());
        result.put(PROPERTY_USER_TYPE, this.getUserUserType());
        return result;
    }

    /**
     * Setter for the User.
     * 
     * @param newValue
     *            the AuthorizationUser.
     */
    public void setUser(AuthorizationUser newValue) {
        AuthorizationUser oldValue = this.user;
        this.user = newValue;
        this.updateProperty(PROPERTY_USER_USERNAME, ((oldValue != null) ? oldValue.getUsername() : ""),
                ((newValue != null) ? newValue.getUsername() : ""));
        this.updateProperty(PROPERTY_USER_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_USER_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_USER_TYPE,
                ((oldValue != null) ? (oldValue.getUserType() != null ? oldValue.getUserType() : null) : null),
                ((newValue != null) ? (newValue.getUserType() != null ? newValue.getUserType() : null) : null));
    }

    /**
     * Getter for the User.
     * 
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getUser() {
        return this.user;
    }

    /**
     * @param userUserType
     *            The userUserType to set.
     */
    public void setUserUserType(Code userUserType) {
        if (this.user != null) {
            Code oldValue = this.user.getUserType();
            this.user.setUserType(userUserType);
            this.updateProperty(PROPERTY_USER_TYPE, oldValue, userUserType);
        }
    }

    /**
     * @return Returns the userUserType.
     */
    public Code getUserUserType() {
        if (this.user == null) {
            return null;
        }
        return this.user.getUserType();
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
     * Setter for the UserUsername.
     * 
     * @param newUsername
     *            the String.
     */
    public void setUserUsername(String newUsername) {
        if (((user != null) && (user.getUsername() == null))) {
            Name username = new Name();
            user.setUsername(username);
        }
        String oldVal = user.getUsername().getValue();
        user.getUsername().setValue(newUsername);
        this.updateProperty(PROPERTY_USER_USERNAME, oldVal, newUsername);
        if (((!oldVal.equals(newUsername)) && user.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserUsername.
     * 
     * @return the String.
     */
    public String getUserUsername() {
        if ((((user == null) || (user.getUsername() == null)) || (user.getUsername().getValue() == null))) {
            return "";
        }
        return user.getUsername().getValue();
    }

    /**
     * Setter for the UserDescription.
     * 
     * @param newDescription
     *            the String.
     */
    public void setUserDescription(String newDescription) {
        if (((user != null) && (user.getDescription() == null))) {
            Description description = new Description();
            user.setDescription(description);
        }
        String oldVal = user.getDescription().getValue();
        user.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_USER_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && user.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserDescription.
     * 
     * @return the String.
     */
    public String getUserDescription() {
        if ((((user == null) || (user.getDescription() == null)) || (user.getDescription().getValue() == null))) {
            return "";
        }
        return user.getDescription().getValue();
    }

    /**
     * Setter for the UserUsername.
     * 
     * @param newPassword
     *            the String.
     */
    public void setUserPassword(String newPassword) {
        if (user != null) {
            if (user.getPassword() == null) {
                AuthorizationUserPassword password = new AuthorizationUserPassword();
                password.setDatatypeState(DatatypeState.INITIALIZED);
                user.setPassword(password);
            }
            if (user.getPassword().getPassword() == null) {
                Password password = new Password();
                user.getPassword().setPassword(password);
            }
        }

        String oldVal = user.getPassword().getPassword().getValue();
        user.getPassword().getPassword().setValue(newPassword);
        this.updateProperty(PROPERTY_USER_PASSWORD, oldVal, newPassword);
        if (((!oldVal.equals(newPassword)) && user.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
            user.getPassword().setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserUsername.
     * 
     * @return the String.
     */
    public String getUserPassword() {
        if (user == null) {
            return "";
        }
        if (user.getPassword() == null || user.getPassword().getPassword() == null) {
            return "";
        }
        if (user.getPassword().getPassword().getValue() == null) {
            return "";
        }
        return user.getPassword().getPassword().getValue();
    }

    /**
     * Setter for the UserOwner.
     * 
     * @param newOwner
     *            the String.
     */
    public void setUserOwner(String newOwner) {
        if (((user != null) && (user.getOwner() == null))) {
            Owner owner = new Owner();
            user.setOwner(owner);
        }
        String oldVal = user.getOwner().getValue();
        user.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_USER_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && user.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserOwner.
     * 
     * @return the String.
     */
    public String getUserOwner() {
        if ((((user == null) || (user.getOwner() == null)) || (user.getOwner().getValue() == null))) {
            return "";
        }
        return user.getOwner().getValue();
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
}
