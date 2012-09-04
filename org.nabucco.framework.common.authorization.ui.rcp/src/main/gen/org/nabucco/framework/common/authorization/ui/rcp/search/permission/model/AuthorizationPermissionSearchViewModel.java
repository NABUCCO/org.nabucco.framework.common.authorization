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
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.model;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * AuthorizationPermissionSearchViewModel
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationPermissionSearchViewModel extends NabuccoComponentSearchViewModel<AuthorizationPermission>
        implements NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.permission.AuthorizationPermissionSearchViewModel";

    private AuthorizationPermission permission;

    public static final String PROPERTY_PERMISSION_PERMISSIONNAME = "permissionPermissionname";

    public static final String PROPERTY_PERMISSION_OWNER = "permissionOwner";

    public static final String PROPERTY_PERMISSION_DESCRIPTION = "permissionDescription";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new AuthorizationPermissionSearchViewModel instance.
     * 
     * @param viewId
     *            the String.
     */
    public AuthorizationPermissionSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.permission = new AuthorizationPermission();
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
     * Getter for the Permission.
     * 
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getPermission() {
        return this.permission;
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

    @Override
    public String getId() {
        return AuthorizationPermissionSearchViewModel.ID;
    }
}
