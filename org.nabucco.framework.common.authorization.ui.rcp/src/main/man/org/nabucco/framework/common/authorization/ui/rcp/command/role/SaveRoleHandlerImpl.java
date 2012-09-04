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
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import java.util.Set;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;

/**
 * Implements SaveRole Handler.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class SaveRoleHandlerImpl extends
        AbstractSaveCommandHandlerImpl<AuthorizationRoleEditBusinessModel, AuthorizationRoleEditViewModel> implements
        SaveRoleHandler {

    @Override
    public void saveRole() {
        run();
    }

    @Override
    public String getBusinessModelId() {
        return AuthorizationRoleEditBusinessModel.ID;
    }

    @Override
    protected void saveModel(AuthorizationRoleEditViewModel viewModel, AuthorizationRoleEditBusinessModel businessModel)
            throws ClientException {

        AuthorizationRole role = viewModel.getRole();
        Set<AuthorizationUser> userSet = viewModel.getUserSet();
        Set<AuthorizationGroup> groupSet = viewModel.getGroupSet();

        AuthorizationRoleMaintainMsg result = businessModel.save(role, groupSet, userSet);

        viewModel.setRole(result.getAuthorizationRole());
        viewModel.setDirty(false);
    }
}
