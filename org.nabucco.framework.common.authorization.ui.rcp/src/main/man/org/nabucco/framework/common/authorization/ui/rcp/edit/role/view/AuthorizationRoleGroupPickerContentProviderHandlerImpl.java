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

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * AuthorizationRoleGroupPickerContentProviderHandler
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationRoleGroupPickerContentProviderHandlerImpl implements
        AuthorizationRoleGroupPickerContentProviderHandler {

    private Map<String, AuthorizationGroup[]> authorizationGroups;

    private AuthorizationRole currentRole;

    @Override
    public Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(AuthorizationRoleEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationGroups = loadAllAuthorizationGroups(viewModel);
        }
        return authorizationGroups;
    }

    private boolean needsRefresh(AuthorizationRoleEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentRole != viewModel.getRole();
            currentRole = viewModel.getRole();
        }
        return result;
    }

    private Map<String, AuthorizationGroup[]> loadAllAuthorizationGroups(AuthorizationRoleEditViewModel viewModel) {
        HashMap<String, AuthorizationGroup[]> values = new HashMap<String, AuthorizationGroup[]>();

        try {
            SearchAuthorizationDelegate searchComponent = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getSearchAuthorization();
            AuthorizationGroupListMsg result = searchComponent.searchAuthorizationGroup(new AuthorizationSearchMsg());
            values.put(" ", result.getAuthorizationGroupList().toArray(new AuthorizationGroup[0]));
        } catch (NabuccoException e) {
            Activator.getDefault().logError(e);
        }
        return values;
    }
}
