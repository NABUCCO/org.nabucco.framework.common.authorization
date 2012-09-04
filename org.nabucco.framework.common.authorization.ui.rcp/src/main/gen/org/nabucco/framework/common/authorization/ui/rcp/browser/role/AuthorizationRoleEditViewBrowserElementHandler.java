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

import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationRoleEditViewBrowserElementHandler
 * 
 * @author Undefined
 */
public interface AuthorizationRoleEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     * 
     * @param authorizationRole
     *            the AuthorizationRoleEditViewModel.
     * @return the AuthorizationRoleEditViewModel.
     */
    AuthorizationRoleEditViewModel loadFull(final AuthorizationRoleEditViewModel authorizationRole);

    /**
     * CreateChildren.
     * 
     * @param element
     *            the AuthorizationRoleEditViewBrowserElement.
     * @param viewModel
     *            the AuthorizationRoleEditViewModel.
     */
    void createChildren(final AuthorizationRoleEditViewModel viewModel,
            final AuthorizationRoleEditViewBrowserElement element);
}
