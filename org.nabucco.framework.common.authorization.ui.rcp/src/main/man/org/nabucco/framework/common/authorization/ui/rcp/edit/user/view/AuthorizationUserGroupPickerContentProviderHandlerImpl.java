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
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.view;

import java.util.HashMap;
import java.util.Map;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * AuthorizationUserGroupPickerContentProviderHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserGroupPickerContentProviderHandlerImpl implements
        AuthorizationUserGroupPickerContentProviderHandler {

    private Map<String, AuthorizationGroup[]> authorizationGroups;

    private AuthorizationUser currentUser;

    @Override
    public Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(
            AuthorizationUserEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationGroups = loadAllAuthorizationGroups(viewModel);
        }
        return authorizationGroups;
    }

    public Map<String, AuthorizationGroup[]> loadAllAuthorizationGroups(
            AuthorizationUserEditViewModel viewModel) {
        final Map<String, AuthorizationGroup[]> result = new HashMap<String, AuthorizationGroup[]>();

        try {
            final SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();

            final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
            final AuthorizationGroupListMsg rs = searchDelegate.searchAuthorizationGroup(msg);

            result.put(" ", rs.getAuthorizationGroupList().toArray(new AuthorizationGroup[0]));

        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }

        return result;
    }

    private boolean needsRefresh(final AuthorizationUserEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentUser != viewModel.getUser();
            currentUser = viewModel.getUser();
        }
        return result;
    }

}
