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

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.browser.role.AuthorizationRoleListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.browser.user.AuthorizationUserListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;

/**
 * Implements handle for authorizationGroup Browser Element.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupEditViewBrowserElementHandlerImpl implements
        AuthorizationGroupEditViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationGroupEditViewModel loadFull(AuthorizationGroupEditViewModel viewModel) {

        return viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(AuthorizationGroupEditViewModel viewModel,
            AuthorizationGroupEditViewBrowserElement element) {

        AuthorizationGroup group = viewModel.getGroup();

        if (group.getUserList().size() > 0) {

            List<AuthorizationUser> users = new ArrayList<AuthorizationUser>();
            List<AuthorizationGroupUserRelation> relations = viewModel.getGroup().getUserList();
            for (AuthorizationGroupUserRelation relation : relations) {
                users.add(relation.getUser());
            }

            element.addBrowserElement(new AuthorizationUserListViewBrowserElement(users));
        }

        if (group.getChildGroupList().size() > 0) {
            List<AuthorizationGroup> childGroups = viewModel.getGroup().getChildGroupList();
            element.addBrowserElement(new AuthorizationGroupListViewBrowserElement(childGroups));
        }

        if (group.getRoleList().size() > 0) {
            List<AuthorizationRole> roles = new ArrayList<AuthorizationRole>();
            List<AuthorizationGroupRoleRelation> relations = viewModel.getGroup().getRoleList();
            for (AuthorizationGroupRoleRelation relation : relations) {
                roles.add(relation.getRole());
            }

            element.addBrowserElement(new AuthorizationRoleListViewBrowserElement(roles));
        }

        if (group.getPermissionList().size() > 0) {
            List<AuthorizationPermission> roles = new ArrayList<AuthorizationPermission>();
            List<AuthorizationGroupPermissionRelation> relations = viewModel.getGroup().getPermissionList();
            for (AuthorizationGroupPermissionRelation relation : relations) {
                roles.add(relation.getPermission());
            }

            element.addBrowserElement(new AuthorizationPermissionListViewBrowserElement(roles));
        }
    }

}
