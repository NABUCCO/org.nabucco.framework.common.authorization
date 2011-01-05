/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationUserEditViewModel<p/>Edit view for datatype AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationUserEditViewModel extends EditViewModel implements Loggable {

    private AuthorizationUser user;

    private Set<AuthorizationGroup> groupSet;

    public static final String PROPERTY_USER_USERNAME = "userUsername";

    public static final String PROPERTY_USER_DESCRIPTION = "userDescription";

    public static final String PROPERTY_USER_OWNER = "userOwner";

    public static final String PROPERTY_USER_USERTYPE = "userUserType";

    private String groupSetGroupType;

    public static final String PROPERTY_GROUPSET_GROUPTYPE = "groupSetGroupType";

    /** Constructs a new AuthorizationUserEditViewModel instance. */
    public AuthorizationUserEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_USER_OWNER, this.getUserOwner());
        result.put(PROPERTY_USER_DESCRIPTION, this.getUserDescription());
        result.put(PROPERTY_GROUPSET_GROUPTYPE, this.getGroupSetGroupType());
        result.put(PROPERTY_USER_USERTYPE, this.getUserUserType());
        result.put(PROPERTY_USER_USERNAME, this.getUserUsername());
        return result;
    }

    /**
     * Setter for the User.
     *
     * @param newValue the AuthorizationUser.
     */
    public void setUser(AuthorizationUser newValue) {
        AuthorizationUser oldValue = this.user;
        this.user = newValue;
        this.updateProperty(PROPERTY_USER_USERNAME, ((oldValue != null) ? oldValue.getUsername()
                : ""), ((newValue != null) ? newValue.getUsername() : ""));
        this.updateProperty(PROPERTY_USER_DESCRIPTION,
                ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_USER_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_USER_USERTYPE, ((oldValue != null) ? oldValue.getUserType()
                : ""), ((newValue != null) ? newValue.getUserType() : ""));
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
     * @param newUsername the String.
     */
    public void setUserUsername(String newUsername) {
        if (((user != null) && (user.getUsername() == null))) {
            Name username = new Name();
            user.setUsername(username);
        }
        String oldVal = user.getUsername().getValue();
        user.getUsername().setValue(newUsername);
        this.updateProperty(PROPERTY_USER_USERNAME, oldVal, newUsername);
        if (((!oldVal.equals(newUsername)) && user.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
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
     * @param newDescription the String.
     */
    public void setUserDescription(String newDescription) {
        if (((user != null) && (user.getDescription() == null))) {
            Description description = new Description();
            user.setDescription(description);
        }
        String oldVal = user.getDescription().getValue();
        user.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_USER_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && user.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserDescription.
     *
     * @return the String.
     */
    public String getUserDescription() {
        if ((((user == null) || (user.getDescription() == null)) || (user.getDescription()
                .getValue() == null))) {
            return "";
        }
        return user.getDescription().getValue();
    }

    /**
     * Setter for the UserOwner.
     *
     * @param newOwner the String.
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
     * Setter for the UserUserType.
     *
     * @param newUserType the String.
     */
    public void setUserUserType(String newUserType) {
        if (((user != null) && (user.getUserType() == null))) {
            CodeType userType = new CodeType();
            user.setUserType(userType);
        }
        String oldVal = user.getUserType().getValue();
        user.getUserType().setValue(newUserType);
        this.updateProperty(PROPERTY_USER_USERTYPE, oldVal, newUserType);
        if (((!oldVal.equals(newUserType)) && user.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            user.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the UserUserType.
     *
     * @return the String.
     */
    public String getUserUserType() {
        if ((((user == null) || (user.getUserType() == null)) || (user.getUserType().getValue() == null))) {
            return "";
        }
        return user.getUserType().getValue();
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
}
