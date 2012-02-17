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

import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.group.AuthorizationGroupEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.view.AuthorizationGroupEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;

/**
 * OpenCorrespondingGroupEditViewFromBrowserElementCommandHandler.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class OpenCorrespondingGroupEditViewFromBrowserElementCommandHandlerImpl
        extends
        AbstractNabuccoOpenEditViewHandlerImpl<AuthorizationGroupEditViewBrowserElement, AuthorizationGroupEditViewModel>
        implements OpenCorrespondingGroupEditViewFromBrowserElementCommandHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCorrespondingGroupEditViewFromBrowserElementCommand() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditViewId() {
        return AuthorizationGroupEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(AuthorizationGroupEditViewBrowserElement browserElement,
            AuthorizationGroupEditViewModel model) {
        loadFull(browserElement.getViewModel());
        model.setGroup(browserElement.getViewModel().getGroup());
        model.setParentGroup(browserElement.getViewModel().getParentGroup());
    }

    /**
     * @param viewModel
     */
    private void loadFull(AuthorizationGroupEditViewModel viewModel) {
        try {

            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();

            AuthorizationGroupMsg msg = this.createAuthorizationGroupResolutionMsg(viewModel);
            AuthorizationGroupMaintainMsg response = resolveAuthorization.resolveAuthorizationGroup(msg);

            viewModel.setGroup(response.getAuthorizationGroup());
            viewModel.setParentGroup(response.getParentAuthorizationGroup());
        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationGroupMsg createAuthorizationGroupResolutionMsg(
            AuthorizationGroupEditViewModel authorizationGroupViewModel) {

        AuthorizationGroupMsg result = new AuthorizationGroupMsg();
        result.setAuthorizationGroup(authorizationGroupViewModel.getGroup());
        return result;
    }
}
