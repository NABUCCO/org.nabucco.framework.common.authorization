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
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationPermissionEditViewBrowserElementHandler
 * 
 * @author Undefined
 */
public interface AuthorizationPermissionEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     * 
     * @param authorizationPermission
     *            the AuthorizationPermissionEditViewModel.
     * @return the AuthorizationPermissionEditViewModel.
     */
    AuthorizationPermissionEditViewModel loadFull(final AuthorizationPermissionEditViewModel authorizationPermission);

    /**
     * CreateChildren.
     * 
     * @param element
     *            the AuthorizationPermissionEditViewBrowserElement.
     * @param viewModel
     *            the AuthorizationPermissionEditViewModel.
     */
    void createChildren(final AuthorizationPermissionEditViewModel viewModel,
            final AuthorizationPermissionEditViewBrowserElement element);
}
