/*
 * Copyright 2010 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco-source.org/nabucco-license.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import java.util.Set;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.command.NabuccoAbstractSaveCommandHandlerImpl;

/**
 * Save Command saves a AddUserModel.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class SaveUserHandlerImpl
        extends
        NabuccoAbstractSaveCommandHandlerImpl<AuthorizationUserEditBusinessModel, AuthorizationUserEditViewModel>
        implements SaveUserHandler {

    /**
     * Save a user.
     */
    @Override
    public void saveUser() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBusinessModelId() {
        return AuthorizationUserEditBusinessModel.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveModel(AuthorizationUserEditViewModel viewModel,
            AuthorizationUserEditBusinessModel businessModel) {
        AuthorizationUser user = viewModel.getUser();
        Set<AuthorizationGroup> groupSet = viewModel.getGroupSet();
        AuthorizationUserMaintainMsg result = businessModel.save(user, groupSet);
        viewModel.setUser(result.getAuthorizationUser());
    }
}