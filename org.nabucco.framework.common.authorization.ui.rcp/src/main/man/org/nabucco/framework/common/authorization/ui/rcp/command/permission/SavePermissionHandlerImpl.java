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
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;

/**
 * Save Command saves a permission.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class SavePermissionHandlerImpl extends
        AbstractSaveCommandHandlerImpl<AuthorizationPermissionEditBusinessModel, AuthorizationPermissionEditViewModel>
        implements SavePermissionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void savePermission() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBusinessModelId() {
        return AuthorizationPermissionEditBusinessModel.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveModel(AuthorizationPermissionEditViewModel viewModel,
            AuthorizationPermissionEditBusinessModel businessModel) throws ClientException {

        AuthorizationPermission permission = viewModel.getPermission();
        List<AuthorizationGroup> groupList = new ArrayList<AuthorizationGroup>();
        List<AuthorizationRole> roleList = new ArrayList<AuthorizationRole>();
        List<AuthorizationUser> userList = new ArrayList<AuthorizationUser>();

        if (viewModel.getGroupSet() != null) {
            groupList.addAll(viewModel.getGroupSet());
        }
        if (viewModel.getRoleSet() != null) {
            roleList.addAll(viewModel.getRoleSet());
        }
        if (viewModel.getUserSet() != null) {
            userList.addAll(viewModel.getUserSet());
        }

        AuthorizationPermissionMaintainMsg response = businessModel.save(permission, groupList, userList, roleList);

        viewModel.setPermission(response.getAuthorizationPermission());
        viewModel.setDirty(false);
    }
}
