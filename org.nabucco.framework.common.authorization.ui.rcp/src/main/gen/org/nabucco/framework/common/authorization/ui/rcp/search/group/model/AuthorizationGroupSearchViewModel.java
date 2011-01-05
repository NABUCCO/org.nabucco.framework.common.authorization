/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.group.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * AuthorizationGroupSearchViewModel<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationGroupSearchViewModel extends
        NabuccoComponentSearchViewModel<AuthorizationGroup> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.group.AuthorizationGroupSearchViewModel";

    private AuthorizationGroup group;

    public static final String PROPERTY_GROUP_GROUPNAME = "groupGroupname";

    public static final String PROPERTY_GROUP_GROUPTYPE = "groupGroupType";

    public static final String PROPERTY_GROUP_OWNER = "groupOwner";

    public static final String PROPERTY_GROUP_DESCRIPTION = "groupDescription";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new AuthorizationGroupSearchViewModel instance.
     *
     * @param viewId the String.
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

    @Override
    public String getId() {
        return AuthorizationGroupSearchViewModel.ID;
    }
}
