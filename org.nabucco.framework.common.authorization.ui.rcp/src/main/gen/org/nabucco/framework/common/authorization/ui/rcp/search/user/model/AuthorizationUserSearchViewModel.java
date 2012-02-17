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
package org.nabucco.framework.common.authorization.ui.rcp.search.user.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * AuthorizationUserSearchViewModel
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationUserSearchViewModel extends NabuccoComponentSearchViewModel<AuthorizationUser> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.user.AuthorizationUserSearchViewModel";

    private AuthorizationUser user;

    public static final String PROPERTY_USER_USERNAME = "userUsername";

    public static final String PROPERTY_USER_OWNER = "userOwner";

    public static final String PROPERTY_USER_DESCRIPTION = "userDescription";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new AuthorizationUserSearchViewModel instance.
     * 
     * @param viewId
     *            the String.
     */
    public AuthorizationUserSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.user = new AuthorizationUser();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
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

    @Override
    public String getId() {
        return AuthorizationUserSearchViewModel.ID;
    }
}
