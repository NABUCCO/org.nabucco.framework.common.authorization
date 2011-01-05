/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.role.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * AuthorizationRoleSearchViewModel<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationRoleSearchViewModel extends
        NabuccoComponentSearchViewModel<AuthorizationRole> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.role.AuthorizationRoleSearchViewModel";

    private AuthorizationRole role;

    public static final String PROPERTY_ROLE_ROLENAME = "roleRolename";

    public static final String PROPERTY_ROLE_ROLETYPE = "roleRoleType";

    public static final String PROPERTY_ROLE_OWNER = "roleOwner";

    public static final String PROPERTY_ROLE_DESCRIPTION = "roleDescription";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new AuthorizationRoleSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public AuthorizationRoleSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.role = new AuthorizationRole();
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
     * Getter for the Role.
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getRole() {
        return this.role;
    }

    /**
     * Setter for the RoleRolename.
     *
     * @param newRolename the String.
     */
    public void setRoleRolename(String newRolename) {
        if (((role != null) && (role.getRolename() == null))) {
            Name rolename = new Name();
            role.setRolename(rolename);
        }
        String oldVal = role.getRolename().getValue();
        role.getRolename().setValue(newRolename);
        this.updateProperty(PROPERTY_ROLE_ROLENAME, oldVal, newRolename);
        if (((!oldVal.equals(newRolename)) && role.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
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
     * Setter for the RoleRoleType.
     *
     * @param newRoleType the String.
     */
    public void setRoleRoleType(String newRoleType) {
        if (((role != null) && (role.getRoleType() == null))) {
            CodeType roleType = new CodeType();
            role.setRoleType(roleType);
        }
        String oldVal = role.getRoleType().getValue();
        role.getRoleType().setValue(newRoleType);
        this.updateProperty(PROPERTY_ROLE_ROLETYPE, oldVal, newRoleType);
        if (((!oldVal.equals(newRoleType)) && role.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            role.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the RoleRoleType.
     *
     * @return the String.
     */
    public String getRoleRoleType() {
        if ((((role == null) || (role.getRoleType() == null)) || (role.getRoleType().getValue() == null))) {
            return "";
        }
        return role.getRoleType().getValue();
    }

    /**
     * Setter for the RoleOwner.
     *
     * @param newOwner the String.
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
     * Setter for the RoleDescription.
     *
     * @param newDescription the String.
     */
    public void setRoleDescription(String newDescription) {
        if (((role != null) && (role.getDescription() == null))) {
            Description description = new Description();
            role.setDescription(description);
        }
        String oldVal = role.getDescription().getValue();
        role.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_ROLE_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && role.getDatatypeState().equals(
                DatatypeState.PERSISTENT))) {
            role.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the RoleDescription.
     *
     * @return the String.
     */
    public String getRoleDescription() {
        if ((((role == null) || (role.getDescription() == null)) || (role.getDescription()
                .getValue() == null))) {
            return "";
        }
        return role.getDescription().getValue();
    }

    @Override
    public String getId() {
        return AuthorizationRoleSearchViewModel.ID;
    }
}
