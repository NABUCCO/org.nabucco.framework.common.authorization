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
package org.nabucco.framework.common.authorization.ui.rcp.browser.role;

import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.list.role.model.AuthorizationRoleListViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationUserListViewBrowserElementHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationRoleListViewBrowserElementHandlerImpl
        extends
        AbstractBrowserListViewHandlerImpl<AuthorizationRole, AuthorizationRoleListViewModel, AuthorizationRoleListViewBrowserElement, AuthorizationRoleEditViewBrowserElement>
        implements AuthorizationRoleListViewBrowserElementHandler {

    @Override
    public void createChildren(AuthorizationRoleListViewModel viewModel, AuthorizationRoleListViewBrowserElement element) {
        fullLoadChildren(viewModel);
        for (AuthorizationRole authorizationGroup : viewModel.getElements()) {
            element.addBrowserElement(new AuthorizationRoleEditViewBrowserElement(authorizationGroup));
        }
    }

    private void fullLoadChildren(AuthorizationRoleListViewModel viewModel) {
        try {
            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();
            AuthorizationRoleListMsg msg = this.createAuthorizationRoleListResolutionMsg(viewModel);

            AuthorizationRoleListMsg response = resolveAuthorization.resolveAuthorizationRoleList(msg);

            viewModel.setElements(response.getAuthorizationRoleList().toArray(new AuthorizationRole[0]));
        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationRoleListMsg createAuthorizationRoleListResolutionMsg(AuthorizationRoleListViewModel viewModel) {

        AuthorizationRoleListMsg result = new AuthorizationRoleListMsg();
        for (AuthorizationRole role : viewModel.getElements()) {
            result.getAuthorizationRoleList().add(role);
        }

        return result;
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved, AuthorizationRoleListViewBrowserElement element) {
        super.removeChildren((AuthorizationRoleEditViewBrowserElement) toBeRemoved, element);

    }

    @Override
    public boolean haveSameId(AuthorizationRole authorizationRole,
            AuthorizationRoleEditViewBrowserElement authorizationRoleBrowserElement) {
        boolean result = false;
        result = authorizationRole.getId().equals(authorizationRoleBrowserElement.getViewModel().getRole().getId());
        return result;
    }

    @Override
    public void updateViewModel(List<AuthorizationRole> elements, AuthorizationRoleListViewModel viewModel) {
        viewModel.setElements(elements.toArray(new AuthorizationRole[0]));
    }
}
