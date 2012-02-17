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

import java.util.HashSet;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.role.AuthorizationRoleEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.view.AuthorizationRoleEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;

/**
 * Implements handler of openening edit view.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenCorrespondingRoleEditViewFromBrowserElementCommandHandlerImpl extends
        AbstractNabuccoOpenEditViewHandlerImpl<AuthorizationRoleEditViewBrowserElement, AuthorizationRoleEditViewModel>
        implements OpenCorrespondingRoleEditViewFromBrowserElementCommandHandler {

    @Override
    public void openCorrespondingRoleEditViewFromBrowserElementCommand() {
        run();
    }

    @Override
    protected String getEditViewId() {
        return AuthorizationRoleEditView.ID;
    }

    @Override
    protected void updateModel(AuthorizationRoleEditViewBrowserElement element, AuthorizationRoleEditViewModel model) {
        loadFull(element.getViewModel());
        model.setRole(element.getViewModel().getRole());
        model.setUserSet(element.getViewModel().getUserSet());
        model.setGroupSet(element.getViewModel().getGroupSet());
    }

    /**
     * @param viewModel
     */
    private void loadFull(AuthorizationRoleEditViewModel viewModel) {
        try {
            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();

            AuthorizationRoleMsg msg = this.createAuthorizationGroupResolutionMsg(viewModel);

            AuthorizationRoleMaintainMsg response = resolveAuthorization.resolveAuthorizationRole(msg);

            viewModel.setRole(response.getAuthorizationRole());
            viewModel.setGroupSet(new HashSet<AuthorizationGroup>(response.getAuthorizationGroupList()));
            viewModel.setUserSet(new HashSet<AuthorizationUser>(response.getAuthorizationUserList()));

        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationRoleMsg createAuthorizationGroupResolutionMsg(AuthorizationRoleEditViewModel viewModel) {
        AuthorizationRoleMsg result = new AuthorizationRoleMsg();
        result.setAuthorizationRole(viewModel.getRole());
        return result;
    }
}
