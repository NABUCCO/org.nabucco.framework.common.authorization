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
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationPermissionListViewBrowserElementHandlerImpl.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationPermissionListViewBrowserElementHandlerImpl
        extends
        AbstractBrowserListViewHandlerImpl<AuthorizationPermission, AuthorizationPermissionListViewModel, AuthorizationPermissionListViewBrowserElement, AuthorizationPermissionEditViewBrowserElement>
        implements AuthorizationPermissionListViewBrowserElementHandler {

    @Override
    public void createChildren(AuthorizationPermissionListViewModel viewModel,
            AuthorizationPermissionListViewBrowserElement element) {
        fullLoadChildren(viewModel);
        for (AuthorizationPermission permission : viewModel.getElements()) {
            element.addBrowserElement(new AuthorizationPermissionEditViewBrowserElement(permission));
        }

    }

    private void fullLoadChildren(AuthorizationPermissionListViewModel viewModel) {
        try {
            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();
            AuthorizationPermissionListMsg msg = this
                    .createAuthorizationPermissionListResolutionMsg(viewModel);

            AuthorizationPermissionListMsg response = resolveAuthorization
                    .resolveAuthorizationPermissionList(msg);

            viewModel.setElements(response.getAuthorizationPermissionList().toArray(
                    new AuthorizationPermission[0]));
        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationPermissionListMsg createAuthorizationPermissionListResolutionMsg(
            AuthorizationPermissionListViewModel viewModel) {
        AuthorizationPermissionListMsg result = new AuthorizationPermissionListMsg();
        for (AuthorizationPermission permission : viewModel.getElements()) {
            result.getAuthorizationPermissionList().add(permission);
        }
        return result;
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved,
            AuthorizationPermissionListViewBrowserElement element) {
        super.removeChildren((AuthorizationPermissionEditViewBrowserElement) toBeRemoved, element);
    }

    @Override
    public boolean haveSameId(AuthorizationPermission authorizationPermission,
            AuthorizationPermissionEditViewBrowserElement authorizationPermissionBrowserElement) {
        boolean result = false;
        result = authorizationPermission.getId().equals(
                authorizationPermissionBrowserElement.getViewModel().getPermission().getId());
        return result;
    }

    @Override
    public void updateViewModel(List<AuthorizationPermission> elements,
            AuthorizationPermissionListViewModel viewModel) {
        viewModel.setElements(elements.toArray(new AuthorizationPermission[0]));
    }
}
