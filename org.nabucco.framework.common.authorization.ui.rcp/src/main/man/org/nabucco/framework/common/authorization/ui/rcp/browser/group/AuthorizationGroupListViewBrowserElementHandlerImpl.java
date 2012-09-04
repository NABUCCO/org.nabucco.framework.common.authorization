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
package org.nabucco.framework.common.authorization.ui.rcp.browser.group;

import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.model.AuthorizationGroupListViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationGroupListViewBrowserElementHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupListViewBrowserElementHandlerImpl
        extends
        AbstractBrowserListViewHandlerImpl<AuthorizationGroup, AuthorizationGroupListViewModel, AuthorizationGroupListViewBrowserElement, AuthorizationGroupEditViewBrowserElement>
        implements AuthorizationGroupListViewBrowserElementHandler {

    @Override
    public void createChildren(AuthorizationGroupListViewModel viewModel,
            AuthorizationGroupListViewBrowserElement element) {
        fullLoadChildren(viewModel);

        for (AuthorizationGroup authorizationGroup : viewModel.getElements()) {
            element.addBrowserElement(new AuthorizationGroupEditViewBrowserElement(authorizationGroup));
        }

    }

    private void fullLoadChildren(AuthorizationGroupListViewModel viewModel) {
        try {
            ResolveAuthorizationDelegate resolveAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();
            AuthorizationGroupListMsg msg = this.createAuthorizationGroupListResolutionMsg(viewModel);

            AuthorizationGroupListMsg response = resolveAuthorization.resolveAuthorizationGroupList(msg);

            viewModel.setElements(response.getAuthorizationGroupList().toArray(new AuthorizationGroup[0]));
        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
    }

    private AuthorizationGroupListMsg createAuthorizationGroupListResolutionMsg(
            AuthorizationGroupListViewModel viewModel) {

        AuthorizationGroupListMsg result = new AuthorizationGroupListMsg();
        for (AuthorizationGroup group : viewModel.getElements()) {
            result.getAuthorizationGroupList().add(group);
        }

        return result;
    }

    @Override
    public void removeChild(BrowserElement element, AuthorizationGroupListViewBrowserElement list) {
        super.removeChildren((AuthorizationGroupEditViewBrowserElement) element, list);
    }

    @Override
    public boolean haveSameId(AuthorizationGroup authorizationGroup,
            AuthorizationGroupEditViewBrowserElement authoriationGroupEditViewBrowserElement) {
        boolean result = false;
        result = authorizationGroup.getId().equals(
                authoriationGroupEditViewBrowserElement.getViewModel().getGroup().getId());
        return result;
    }

    @Override
    public void updateViewModel(List<AuthorizationGroup> elements, AuthorizationGroupListViewModel viewModel) {
        viewModel.setElements(elements.toArray(new AuthorizationGroup[0]));
    }

}
