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

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;
import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.browser.role.AuthorizationRoleListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;

/**
 * AuthorizationUserEditViewBrowserElementHandlerImpl.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserEditViewBrowserElementHandlerImpl implements
        AuthorizationUserEditViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationUserEditViewModel loadFull(final AuthorizationUserEditViewModel viewModel) {
        return viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(AuthorizationUserEditViewModel viewModel,
            AuthorizationUserEditViewBrowserElement element) {
        AuthorizationUser user = viewModel.getUser();
        if (user.getRoleList().size() > 0) {

            List<AuthorizationRole> roles = new ArrayList<AuthorizationRole>();
            List<AuthorizationUserRoleRelation> relations = viewModel.getUser().getRoleList();
            for (AuthorizationUserRoleRelation relation : relations) {
                roles.add(relation.getRole());
            }

            element.addBrowserElement(new AuthorizationRoleListViewBrowserElement(roles));
        }
        if (user.getPermissionList().size() > 0) {

            List<AuthorizationPermission> roles = new ArrayList<AuthorizationPermission>();
            List<AuthorizationUserPermissionRelation> relations = viewModel.getUser()
                    .getPermissionList();
            for (AuthorizationUserPermissionRelation relation : relations) {
                roles.add(relation.getPermission());
            }

            element.addBrowserElement(new AuthorizationPermissionListViewBrowserElement(roles));
        }
    }

}
