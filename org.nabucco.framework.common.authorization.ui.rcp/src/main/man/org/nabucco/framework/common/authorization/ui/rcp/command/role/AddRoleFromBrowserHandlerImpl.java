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
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import java.util.HashSet;
import java.util.Set;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.browser.group.AuthorizationGroupEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.browser.user.AuthorizationUserEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AddRoleFromBrowserHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AddRoleFromBrowserHandlerImpl extends AddRoleHandlerImpl implements
        AddRoleFromBrowserHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoleFromBrowser() {
        super.run();
    }

    @Override
    protected void updateModel(AuthorizationRoleEditViewModel viewModel) {
        super.updateModel(viewModel);

        this.updateParent(viewModel);
    }

    /**
     * Update the parent group.
     * 
     * @param viewModel
     *            the view model
     */
    private void updateParent(AuthorizationRoleEditViewModel viewModel) {
        BrowserElement element = Activator.getDefault().getModel().getBrowserModel()
                .getFirstElement();

        if (element instanceof AuthorizationGroupEditViewBrowserElement) {
            addToGroup(viewModel, (AuthorizationGroupEditViewBrowserElement) element);
        } else if (element instanceof AuthorizationUserEditViewBrowserElement) {
            addToUser(viewModel, (AuthorizationUserEditViewBrowserElement) element);
        }
    }

    /**
     * Add role to group browser element.
     * 
     * @param viewModel
     *            the view model.
     * @param element
     *            the selected browser element
     */
    private void addToGroup(AuthorizationRoleEditViewModel viewModel,
            AuthorizationGroupEditViewBrowserElement element) {
        AuthorizationGroup group = element.getViewModel().getGroup();

        Set<AuthorizationGroup> groupSet = new HashSet<AuthorizationGroup>();
        groupSet.add(group);

        viewModel.setGroupSet(groupSet);
    }

    /**
     * Add role to user browser element.
     * 
     * @param viewModel
     *            the view model.
     * @param element
     *            the selected browser element
     */
    private void addToUser(AuthorizationRoleEditViewModel viewModel,
            AuthorizationUserEditViewBrowserElement element) {
        AuthorizationUser user = element.getViewModel().getUser();

        Set<AuthorizationUser> userSet = new HashSet<AuthorizationUser>();
        userSet.add(user);

        viewModel.setUserSet(userSet);
    }

}
