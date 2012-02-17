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
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

public class AuthorizationRoleUserPickerContentProviderHandlerImpl implements
        AuthorizationRoleUserPickerContentProviderHandler {

    private Map<String, AuthorizationUser[]> authorizationUsers;

    private AuthorizationRole currentRole;

    @Override
    public Map<String, AuthorizationUser[]> loadAllAuthorizationUser(AuthorizationRoleEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationUsers = loadAllAuthorizationUsers(viewModel);
        }
        return authorizationUsers;
    }

    private boolean needsRefresh(AuthorizationRoleEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentRole != viewModel.getRole();
            currentRole = viewModel.getRole();
        }
        return result;
    }

    private Map<String, AuthorizationUser[]> loadAllAuthorizationUsers(AuthorizationRoleEditViewModel viewModel) {
        final HashMap<String, AuthorizationUser[]> result = new HashMap<String, AuthorizationUser[]>();
        try {
            final SearchAuthorizationDelegate searchAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            final AuthorizationUserListMsg response = searchAuthorization
                    .searchAuthorizationUser(new AuthorizationSearchMsg());
            result.put(" ", response.getAuthorizationUserList().toArray(new AuthorizationUser[0]));
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }

        return result;
    }

}
