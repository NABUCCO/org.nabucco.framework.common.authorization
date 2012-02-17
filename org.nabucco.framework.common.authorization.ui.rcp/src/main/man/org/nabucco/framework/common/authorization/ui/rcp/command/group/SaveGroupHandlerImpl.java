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
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;

/**
 * Implements saveGroup handler.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class SaveGroupHandlerImpl extends
        AbstractSaveCommandHandlerImpl<AuthorizationGroupEditBusinessModel, AuthorizationGroupEditViewModel> implements
        SaveGroupHandler {

    @Override
    public void saveGroup() {
        run();
    }

    @Override
    public String getBusinessModelId() {
        return AuthorizationGroupEditBusinessModel.ID;
    }

    @Override
    protected void saveModel(AuthorizationGroupEditViewModel viewModel,
            AuthorizationGroupEditBusinessModel businessModel) throws ClientException {
        AuthorizationGroup group = viewModel.getGroup();
        AuthorizationGroup parentGroup = viewModel.getParentGroup();
        AuthorizationGroupMaintainMsg result = businessModel.save(group, parentGroup);
        viewModel.setGroup(result.getAuthorizationGroup());
        viewModel.setDirty(false);
    }
}
