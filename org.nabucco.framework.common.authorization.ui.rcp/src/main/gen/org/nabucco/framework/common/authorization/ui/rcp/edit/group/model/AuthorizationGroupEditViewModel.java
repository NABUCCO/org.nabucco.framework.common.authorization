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
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.model;

import java.io.Serializable;
import java.util.Map;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationGroupEditViewModel
 * <p/>
 * Edit view for datatype AuthorizationGroup
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationGroupEditViewModel extends EditViewModel implements Loggable {

    private AuthorizationGroup group;

    private AuthorizationGroup parentGroup;

    public static final String PROPERTY_GROUP_GROUPNAME = "groupGroupname";

    public static final String PROPERTY_GROUP_DESCRIPTION = "groupDescription";

    public static final String PROPERTY_GROUP_OWNER = "groupOwner";

    public static final String PROPERTY_PARENTGROUP_GROUPNAME = "parentGroupGroupname";

    public static final String PROPERTY_GROUP_GROUPTYPE = "groupGroupType";

    /** Constructs a new AuthorizationGroupEditViewModel instance. */
    public AuthorizationGroupEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     * 
     * @return the String.
     */
    @Override
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel";
    }

    /**
     * Getter for the Values.
     * 
     * @return the Map<String, Serializable>.
     */
    @Override
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_GROUP_DESCRIPTION, this.getGroupDescription());
        result.put(PROPERTY_PARENTGROUP_GROUPNAME, this.getParentGroupGroupname());
        result.put(PROPERTY_GROUP_GROUPNAME, this.getGroupGroupname());
        result.put(PROPERTY_GROUP_OWNER, this.getGroupOwner());
        result.put(PROPERTY_GROUP_GROUPTYPE, this.getGroupGroupType());
        return result;
    }

    /**
     * Setter for the Group.
     * 
     * @param newValue
     *            the AuthorizationGroup.
     */
    public void setGroup(AuthorizationGroup newValue) {
        AuthorizationGroup oldValue = this.group;
        this.group = newValue;
        this.updateProperty(PROPERTY_GROUP_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_GROUP_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_GROUP_GROUPNAME, ((oldValue != null) ? oldValue.getGroupname() : ""),
                ((newValue != null) ? newValue.getGroupname() : ""));
        this.updateProperty(PROPERTY_GROUP_GROUPTYPE, ((oldValue != null) ? oldValue.getGroupType() : null),
                ((newValue != null) ? newValue.getGroupType() : null));
    }

    /**
     * Setter for the Group.
     * 
     * @param newValue
     *            the AuthorizationGroup.
     */
    public void setGroupGroupType(Code newValue) {
        Code oldValue = getGroupGroupType();
        if (newValue != null && this.group != null) {
            this.group.setGroupType(newValue);
            this.updateProperty(PROPERTY_GROUP_GROUPTYPE, oldValue, newValue);
        }
    }

    /**
     * @return Returns the groupType.
     */
    public Code getGroupGroupType() {
        if (this.group == null) {
            return null;
        }
        return this.group.getGroupType();
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
     * Setter for the ParentGroup.
     * 
     * @param newValue
     *            the AuthorizationGroup.
     */
    public void setParentGroup(AuthorizationGroup newValue) {
        AuthorizationGroup oldValue = this.parentGroup;
        this.parentGroup = newValue;
        this.updateProperty(PROPERTY_PARENTGROUP_GROUPNAME, ((oldValue != null) ? oldValue.getGroupname() : ""),
                ((newValue != null) ? newValue.getGroupname() : ""));
    }

    /**
     * Getter for the ParentGroup.
     * 
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getParentGroup() {
        return this.parentGroup;
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
     * Setter for the ParentGroupGroupname.
     * 
     * @param newGroupname
     *            the String.
     */
    public void setParentGroupGroupname(String newGroupname) {
        if (((parentGroup != null) && (parentGroup.getGroupname() == null))) {
            Name groupname = new Name();
            parentGroup.setGroupname(groupname);
        }
        String oldVal = parentGroup.getGroupname().getValue();
        parentGroup.getGroupname().setValue(newGroupname);
        this.updateProperty(PROPERTY_PARENTGROUP_GROUPNAME, oldVal, newGroupname);
        if (((!oldVal.equals(newGroupname)) && parentGroup.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            parentGroup.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ParentGroupGroupname.
     * 
     * @return the String.
     */
    public String getParentGroupGroupname() {
        if ((((parentGroup == null) || (parentGroup.getGroupname() == null)) || (parentGroup.getGroupname().getValue() == null))) {
            return "";
        }
        return parentGroup.getGroupname().getValue();
    }
}
