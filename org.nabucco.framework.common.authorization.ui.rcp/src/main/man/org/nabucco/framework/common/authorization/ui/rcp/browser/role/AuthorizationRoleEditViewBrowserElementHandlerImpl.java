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

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;

/**
 * AuthorizationUserEditViewBrowserElementHandlerImpl.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationRoleEditViewBrowserElementHandlerImpl implements
        AuthorizationRoleEditViewBrowserElementHandler {

    @Override
    public AuthorizationRoleEditViewModel loadFull(AuthorizationRoleEditViewModel viewModel) {
        return viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(AuthorizationRoleEditViewModel viewModel, AuthorizationRoleEditViewBrowserElement element) {
        AuthorizationRole role = viewModel.getRole();
        if (role.getPermissionList().size() > 0) {
            List<AuthorizationPermission> roles = new ArrayList<AuthorizationPermission>();
            List<AuthorizationRolePermissionRelation> relations = viewModel.getRole().getPermissionList();
            for (AuthorizationRolePermissionRelation relation : relations) {
                roles.add(relation.getPermission());
            }

            element.addBrowserElement(new AuthorizationPermissionListViewBrowserElement(roles));
        }
    }

}
