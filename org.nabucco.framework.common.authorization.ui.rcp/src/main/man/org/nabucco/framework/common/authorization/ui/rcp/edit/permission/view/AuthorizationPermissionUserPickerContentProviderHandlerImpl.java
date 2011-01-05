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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * AuthorizationPermissionUserPickerContentProviderHandler.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationPermissionUserPickerContentProviderHandlerImpl implements
        AuthorizationPermissionUserPickerContentProviderHandler {

    private Map<String, AuthorizationUser[]> authorizationUsers;

    private AuthorizationPermission currentPermission;

    @Override
    public Map<String, AuthorizationUser[]> loadAllAuthorizationUser(
            AuthorizationPermissionEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationUsers = loadAllAuthorizationUsers(viewModel);
        }
        return authorizationUsers;
    }

    private boolean needsRefresh(AuthorizationPermissionEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentPermission != viewModel.getPermission();
            currentPermission = viewModel.getPermission();
        }
        return result;
    }

    private Map<String, AuthorizationUser[]> loadAllAuthorizationUsers(
            AuthorizationPermissionEditViewModel viewModel) {
        HashMap<String, AuthorizationUser[]> values = new HashMap<String, AuthorizationUser[]>();
        try {
            SearchAuthorizationDelegate searchComponent = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            AuthorizationUserListMsg result = searchComponent
                    .searchAuthorizationUser(new AuthorizationSearchMsg());
            values.put(" ", result.getAuthorizationUserList().toArray(new AuthorizationUser[0]));
        } catch (NabuccoException e) {
            Activator.getDefault().logError(e);
        }
        return values;
    }

}
