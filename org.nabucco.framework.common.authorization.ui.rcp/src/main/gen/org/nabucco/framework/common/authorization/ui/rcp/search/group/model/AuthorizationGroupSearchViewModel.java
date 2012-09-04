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
package org.nabucco.framework.common.authorization.ui.rcp.search.group.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * AuthorizationGroupSearchViewModel
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationGroupSearchViewModel extends NabuccoComponentSearchViewModel<AuthorizationGroup> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.group.AuthorizationGroupSearchViewModel";

    private AuthorizationGroup group;

    public static final String PROPERTY_GROUP_GROUPNAME = "groupGroupname";

    public static final String PROPERTY_GROUP_OWNER = "groupOwner";

    public static final String PROPERTY_GROUP_DESCRIPTION = "groupDescription";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new AuthorizationGroupSearchViewModel instance.
     * 
     * @param viewId
     *            the String.
     */
    public AuthorizationGroupSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.group = new AuthorizationGroup();
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
     * Getter for the Group.
     * 
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getGroup() {
        return this.group;
    }

    /**
     * Setter for the GroupGroupname.
     * 
     * @param newGroupname
     *            the String.
     */
    public void setGroupGroupname(String newGroupname) {
        if (((group != null) && (group.getGroupname() == null))) {
            Name groupname = new Name();
            group.setGroupname(groupname);
        }
        String oldVal = group.getGroupname().getValue();
        group.getGroupname().setValue(newGroupname);
        this.updateProperty(PROPERTY_GROUP_GROUPNAME, oldVal, newGroupname);
        if (((!oldVal.equals(newGroupname)) && group.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupGroupname.
     * 
     * @return the String.
     */
    public String getGroupGroupname() {
        if ((((group == null) || (group.getGroupname() == null)) || (group.getGroupname().getValue() == null))) {
            return "";
        }
        return group.getGroupname().getValue();
    }

    /**
     * Setter for the GroupOwner.
     * 
     * @param newOwner
     *            the String.
     */
    public void setGroupOwner(String newOwner) {
        if (((group != null) && (group.getOwner() == null))) {
            Owner owner = new Owner();
            group.setOwner(owner);
        }
        String oldVal = group.getOwner().getValue();
        group.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_GROUP_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && group.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupOwner.
     * 
     * @return the String.
     */
    public String getGroupOwner() {
        if ((((group == null) || (group.getOwner() == null)) || (group.getOwner().getValue() == null))) {
            return "";
        }
        return group.getOwner().getValue();
    }

    /**
     * Setter for the GroupDescription.
     * 
     * @param newDescription
     *            the String.
     */
    public void setGroupDescription(String newDescription) {
        if (((group != null) && (group.getDescription() == null))) {
            Description description = new Description();
            group.setDescription(description);
        }
        String oldVal = group.getDescription().getValue();
        group.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_GROUP_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && group.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupDescription.
     * 
     * @return the String.
     */
    public String getGroupDescription() {
        if ((((group == null) || (group.getDescription() == null)) || (group.getDescription().getValue() == null))) {
            return "";
        }
        return group.getDescription().getValue();
    }

    @Override
    public String getId() {
        return AuthorizationGroupSearchViewModel.ID;
    }
}
