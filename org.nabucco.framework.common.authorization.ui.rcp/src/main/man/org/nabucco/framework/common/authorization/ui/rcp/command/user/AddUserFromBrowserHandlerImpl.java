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
import java.util.Set;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.browser.group.AuthorizationGroupEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AddUserFromBrowserHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AddUserFromBrowserHandlerImpl extends AddUserHandlerImpl implements
        AddUserFromBrowserHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserFromBrowser() {
        super.run();
    }

    @Override
    protected void updateModel(AuthorizationUserEditViewModel viewModel) {
        super.updateModel(viewModel);

        this.updateParent(viewModel);
    }

    /**
     * Update the parent group.
     * 
     * @param viewModel
     *            the view model
     */
    private void updateParent(AuthorizationUserEditViewModel viewModel) {
        BrowserElement element = Activator.getDefault().getModel().getBrowserModel()
                .getFirstElement();

        if (element instanceof AuthorizationGroupEditViewBrowserElement) {
            addToGroup(viewModel, (AuthorizationGroupEditViewBrowserElement) element);
        }
    }

    /**
     * Add user to group browser element.
     * 
     * @param viewModel
     *            the view model.
     * @param element
     *            the selected browser element
     */
    private void addToGroup(AuthorizationUserEditViewModel viewModel,
            AuthorizationGroupEditViewBrowserElement element) {
        AuthorizationGroup group = element.getViewModel().getGroup();

        Set<AuthorizationGroup> groupSet = new HashSet<AuthorizationGroup>();
        groupSet.add(group);

        viewModel.setGroupSet(groupSet);
    }

}
