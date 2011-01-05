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
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * AuthorizationPermissionGroupPickerContentProviderHandler.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationPermissionRolePickerContentProviderHandlerImpl implements
        AuthorizationPermissionRolePickerContentProviderHandler {

    private Map<String, AuthorizationRole[]> authorizationRoles;

    private AuthorizationPermission currentPermission;

    @Override
    public Map<String, AuthorizationRole[]> loadAllAuthorizationRole(
            AuthorizationPermissionEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationRoles = loadAllAuthorizationRoles(viewModel);
        }
        return authorizationRoles;
    }

    private boolean needsRefresh(AuthorizationPermissionEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentPermission != viewModel.getPermission();
            currentPermission = viewModel.getPermission();
        }
        return result;
    }

    private Map<String, AuthorizationRole[]> loadAllAuthorizationRoles(
            AuthorizationPermissionEditViewModel viewModel) {
        HashMap<String, AuthorizationRole[]> values = new HashMap<String, AuthorizationRole[]>();
        try {
            SearchAuthorizationDelegate searchComponent = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            AuthorizationRoleListMsg result = searchComponent
                    .searchAuthorizationRole((new AuthorizationSearchMsg()));
            values.put(" ", result.getAuthorizationRoleList().toArray(new AuthorizationRole[0]));
        } catch (NabuccoException e) {
            Activator.getDefault().logError(e);
        }
        return values;
    }
}
