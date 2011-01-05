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
package org.nabucco.framework.common.authorization.ui.rcp.browser.user;

import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.model.AuthorizationUserListViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * Implements handler for browser list element.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserListViewBrowserElementHandlerImpl
        extends
        AbstractBrowserListViewHandlerImpl<AuthorizationUser, AuthorizationUserListViewModel, AuthorizationUserListViewBrowserElement, AuthorizationUserEditViewBrowserElement>
        implements AuthorizationUserListViewBrowserElementHandler {

    @Override
    public void createChildren(AuthorizationUserListViewModel viewModel,
            AuthorizationUserListViewBrowserElement element) {
        fullLoadChildren(viewModel);
        for (AuthorizationUser user : viewModel.getElements()) {
            element.addBrowserElement(new AuthorizationUserEditViewBrowserElement(user));
        }

    }

    private void fullLoadChildren(AuthorizationUserListViewModel viewModel) {
        try {
            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();
            AuthorizationUserListMsg response = resolveAuthorization
                    .resolveAuthorizationUserList(createAuthorizationGroupListMsg(viewModel));
            viewModel.setElements(response.getAuthorizationUserList().toArray(
                    new AuthorizationUser[0]));
        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationUserListMsg createAuthorizationGroupListMsg(
            AuthorizationUserListViewModel viewModel) {

        AuthorizationUserListMsg result = new AuthorizationUserListMsg();
        for (AuthorizationUser user : viewModel.getElements()) {
            result.getAuthorizationUserList().add(user);
        }
        return result;
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved,
            AuthorizationUserListViewBrowserElement element) {
        super.removeChildren((AuthorizationUserEditViewBrowserElement) toBeRemoved, element);
    }

    @Override
    public boolean haveSameId(AuthorizationUser authorizationUser,
            AuthorizationUserEditViewBrowserElement authorizationUserEditViewBrowserElement) {
        boolean result = false;
        result = authorizationUser.getId().equals(
                authorizationUserEditViewBrowserElement.getViewModel().getUser().getId());
        return result;
    }

    @Override
    public void updateViewModel(List<AuthorizationUser> elements,
            AuthorizationUserListViewModel viewModel) {
        viewModel.setElements(elements.toArray(new AuthorizationUser[0]));
    }
}
