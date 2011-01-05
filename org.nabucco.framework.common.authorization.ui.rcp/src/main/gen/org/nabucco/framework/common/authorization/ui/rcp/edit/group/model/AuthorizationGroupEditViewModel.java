/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.model;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * AuthorizationGroupEditViewModel<p/>Edit view for datatype AuthorizationGroup<p/>
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

    public static final String PROPERTY_GROUP_GROUPTYPE = "groupGroupType";

    public static final String PROPERTY_PARENTGROUP_GROUPNAME = "parentGroupGroupname";

    /** Constructs a new AuthorizationGroupEditViewModel instance. */
    public AuthorizationGroupEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_GROUP_DESCRIPTION, this.getGroupDescription());
        result.put(PROPERTY_PARENTGROUP_GROUPNAME, this.getParentGroupGroupname());
        result.put(PROPERTY_GROUP_GROUPTYPE, this.getGroupGroupType());
        result.put(PROPERTY_GROUP_GROUPNAME, this.getGroupGroupname());
        result.put(PROPERTY_GROUP_OWNER, this.getGroupOwner());
        return result;
    }

    /**
     * Setter for the Group.
     *
     * @param newValue the AuthorizationGroup.
     */
    public void setGroup(AuthorizationGroup newValue) {
        AuthorizationGroup oldValue = this.group;
        this.group = newValue;
        this.updateProperty(PROPERTY_GROUP_GROUPTYPE, ((oldValue != null) ? oldValue.getGroupType()
                : ""), ((newValue != null) ? newValue.getGroupType() : ""));
        this.updateProperty(PROPERTY_GROUP_DESCRIPTION,
                ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_GROUP_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
        this.updateProperty(PROPERTY_GROUP_GROUPNAME, ((oldValue != null) ? oldValue.getGroupname()
                : ""), ((newValue != null) ? newValue.getGroupname() : ""));
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
     * @param newValue the AuthorizationGroup.
     */
    public void setParentGroup(AuthorizationGroup newValue) {
        AuthorizationGroup oldValue = this.parentGroup;
        this.parentGroup = newValue;
        this.updateProperty(PROPERTY_PARENTGROUP_GROUPNAME,
                ((oldValue != null) ? oldValue.getGroupname() : ""),
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
     * @param newGroupname the String.
     */
    public void setGroupGroupname(String newGroupname) {
        if (((group != null) && (group.getGroupname() == null))) {
            Name groupname = new Name();
            group.setGroupname(groupname);
        }
        String oldVal = group.getGroupname().getValue();
        group.getGroupname().setValue(newGroupname);
        this.updateProperty(PROPERTY_GROUP_GROUPNAME, oldVal, newGroupname);
        if (((!oldVal.equals(newGroupname)) && group.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupGroupname.
     *
     * @return the String.
     */
    public String getGroupGroupname() {
        if ((((group == null) || (group.getGroupname() == null)) || (group.getGroupname()
                .getValue() == null))) {
            return "";
        }
        return group.getGroupname().getValue();
    }

    /**
     * Setter for the GroupDescription.
     *
     * @param newDescription the String.
     */
    public void setGroupDescription(String newDescription) {
        if (((group != null) && (group.getDescription() == null))) {
            Description description = new Description();
            group.setDescription(description);
        }
        String oldVal = group.getDescription().getValue();
        group.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_GROUP_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && group.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupDescription.
     *
     * @return the String.
     */
    public String getGroupDescription() {
        if ((((group == null) || (group.getDescription() == null)) || (group.getDescription()
                .getValue() == null))) {
            return "";
        }
        return group.getDescription().getValue();
    }

    /**
     * Setter for the GroupOwner.
     *
     * @param newOwner the String.
     */
    public void setGroupOwner(String newOwner) {
        if (((group != null) && (group.getOwner() == null))) {
            Owner owner = new Owner();
            group.setOwner(owner);
        }
        String oldVal = group.getOwner().getValue();
        group.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_GROUP_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && group.getDatatypeState()
                .equals(DatatypeState.PERSISTENT))) {
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
     * Setter for the GroupGroupType.
     *
     * @param newGroupType the String.
     */
    public void setGroupGroupType(String newGroupType) {
        if (((group != null) && (group.getGroupType() == null))) {
            CodeType groupType = new CodeType();
            group.setGroupType(groupType);
        }
        String oldVal = group.getGroupType().getValue();
        group.getGroupType().setValue(newGroupType);
        this.updateProperty(PROPERTY_GROUP_GROUPTYPE, oldVal, newGroupType);
        if (((!oldVal.equals(newGroupType)) && group.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            group.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the GroupGroupType.
     *
     * @return the String.
     */
    public String getGroupGroupType() {
        if ((((group == null) || (group.getGroupType() == null)) || (group.getGroupType()
                .getValue() == null))) {
            return "";
        }
        return group.getGroupType().getValue();
    }

    /**
     * Setter for the ParentGroupGroupname.
     *
     * @param newGroupname the String.
     */
    public void setParentGroupGroupname(String newGroupname) {
        if (((parentGroup != null) && (parentGroup.getGroupname() == null))) {
            Name groupname = new Name();
            parentGroup.setGroupname(groupname);
        }
        String oldVal = parentGroup.getGroupname().getValue();
        parentGroup.getGroupname().setValue(newGroupname);
        this.updateProperty(PROPERTY_PARENTGROUP_GROUPNAME, oldVal, newGroupname);
        if (((!oldVal.equals(newGroupname)) && parentGroup.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            parentGroup.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ParentGroupGroupname.
     *
     * @return the String.
     */
    public String getParentGroupGroupname() {
        if ((((parentGroup == null) || (parentGroup.getGroupname() == null)) || (parentGroup
                .getGroupname().getValue() == null))) {
            return "";
        }
        return parentGroup.getGroupname().getValue();
    }
}
