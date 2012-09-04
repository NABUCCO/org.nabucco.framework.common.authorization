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
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import java.util.HashSet;
import java.util.Set;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionEditViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view.AuthorizationPermissionEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;

/**
 * Implements handler of openening edit view.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenCorrespondingPermissionEditViewFromBrowserElementCommandHandlerImpl
        extends
        AbstractNabuccoOpenEditViewHandlerImpl<AuthorizationPermissionEditViewBrowserElement, AuthorizationPermissionEditViewModel>
        implements OpenCorrespondingPermissionEditViewFromBrowserElementCommandHandler {

    @Override
    public void openCorrespondingPermissionEditViewFromBrowserElementCommand() {
        run();
    }

    @Override
    protected String getEditViewId() {
        return AuthorizationPermissionEditView.ID;
    }

    @Override
    protected void updateModel(AuthorizationPermissionEditViewBrowserElement browserElement,
            AuthorizationPermissionEditViewModel model) {
        loadFull(browserElement.getViewModel());
        model.setPermission(browserElement.getViewModel().getPermission());
        model.setGroupSet(browserElement.getViewModel().getGroupSet());
        model.setUserSet(browserElement.getViewModel().getUserSet());
        model.setRoleSet(browserElement.getViewModel().getRoleSet());
    }

    private void loadFull(AuthorizationPermissionEditViewModel model) {
        try {
            final ResolveAuthorizationDelegate resolveService = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();

            final AuthorizationPermissionMsg msg = this.createAuthorizationPermissionMsg(model.getPermission());

            final AuthorizationPermissionMaintainMsg response = resolveService.resolveAuthorizationPermission(msg);

            model.setPermission(response.getAuthorizationPermission());

            if (!response.getAuthorizationGroupList().isEmpty()) {
                final Set<AuthorizationGroup> groupSet = new HashSet<AuthorizationGroup>(
                        response.getAuthorizationGroupList());
                model.setGroupSet(groupSet);
            }
            if (!response.getAuthorizationUserList().isEmpty()) {
                final Set<AuthorizationUser> userSet = new HashSet<AuthorizationUser>(
                        response.getAuthorizationUserList());

                model.setUserSet(userSet);
            }
            if (!response.getAuthorizationRoleList().isEmpty()) {
                final Set<AuthorizationRole> roleSet = new HashSet<AuthorizationRole>(
                        response.getAuthorizationRoleList());

                model.setRoleSet(roleSet);
            }

        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
    }

    /**
     * Creates the message for resolving AuthorizationPermission
     * 
     * @param permission
     *            the permission to add into the message
     * 
     * @return the created message
     */
    private AuthorizationPermissionMsg createAuthorizationPermissionMsg(AuthorizationPermission permission) {
        final AuthorizationPermissionMsg result = new AuthorizationPermissionMsg();
        result.setAuthorizationPermission(permission);
        return result;
    }
}
