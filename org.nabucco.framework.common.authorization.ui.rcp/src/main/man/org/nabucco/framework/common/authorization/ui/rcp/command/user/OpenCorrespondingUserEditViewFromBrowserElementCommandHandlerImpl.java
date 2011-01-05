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

import java.util.HashSet;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.user.AuthorizationUserEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.view.AuthorizationUserEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;

/**
 * Implements handler of openening edit view.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class OpenCorrespondingUserEditViewFromBrowserElementCommandHandlerImpl
        extends
        AbstractNabuccoOpenEditViewHandlerImpl<AuthorizationUserEditViewBrowserElement, AuthorizationUserEditViewModel>
        implements OpenCorrespondingUserEditViewFromBrowserElementCommandHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCorrespondingUserEditViewFromBrowserElementCommand() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditViewId() {
        return AuthorizationUserEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(AuthorizationUserEditViewBrowserElement browserElement,
            AuthorizationUserEditViewModel model) {
        loadFull(browserElement.getViewModel());
        model.setUser(browserElement.getViewModel().getUser());
        model.setGroupSet(browserElement.getViewModel().getGroupSet());
    }

    /**
     * @param viewModel
     */
    private void loadFull(AuthorizationUserEditViewModel viewModel) {
        try {
            final ResolveAuthorizationDelegate resolveService = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();
            final AuthorizationUserMaintainMsg response = resolveService
                    .resolveAuthorizationUser(createAuthorizationUserMsg(viewModel.getUser()));
            viewModel.setGroupSet(new HashSet<AuthorizationGroup>(response
                    .getAuthorizationGroupList()));
            viewModel.setUser(response.getAuthorizationUser());
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }

    }

    private AuthorizationUserMsg createAuthorizationUserMsg(AuthorizationUser user) {
        final AuthorizationUserMsg result = new AuthorizationUserMsg();
        result.setAuthorizationUser(user);
        return result;
    }
}
