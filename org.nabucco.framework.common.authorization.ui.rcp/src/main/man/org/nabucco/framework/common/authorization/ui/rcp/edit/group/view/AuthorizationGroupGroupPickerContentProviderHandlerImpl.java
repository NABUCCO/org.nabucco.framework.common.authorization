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
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * AuthorizationGroupGroupPickerContentProviderHandlerImpl
 * 
 * @author Michael Krau�e, PRODYNA AG
 */
public class AuthorizationGroupGroupPickerContentProviderHandlerImpl implements
        AuthorizationGroupGroupPickerContentProviderHandler {

    private Map<String, AuthorizationGroup[]> authorizationGroups;

    private AuthorizationGroup currentAuthorizationGroup = null;

    @Override
    public Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(AuthorizationGroupEditViewModel viewModel) {
        if (needsRefresh(viewModel)) {
            authorizationGroups = loadAvailableAuthorizationGroups(viewModel);
        }
        return authorizationGroups;
    }

    /**
     * @param viewModel
     * @return
     */
    private boolean needsRefresh(final AuthorizationGroupEditViewModel viewModel) {
        boolean result = false;
        if (viewModel != null) {
            result = currentAuthorizationGroup != viewModel.getGroup();
            currentAuthorizationGroup = viewModel.getGroup();
        }
        return result;
    }

    /**
     * @param viewModel
     * @return
     */
    private Map<String, AuthorizationGroup[]> loadAvailableAuthorizationGroups(AuthorizationGroupEditViewModel viewModel) {
        final Map<String, AuthorizationGroup[]> result = new HashMap<String, AuthorizationGroup[]>();
        try {
            final SearchAuthorizationDelegate searchComponent = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            final AuthorizationGroupListMsg response = searchComponent
                    .searchAuthorizationGroup(new AuthorizationSearchMsg());
            final List<AuthorizationGroup> groups = new LinkedList<AuthorizationGroup>();
            for (final AuthorizationGroup group : response.getAuthorizationGroupList()) {
                if (!group.equals(viewModel.getGroup())) {
                    groups.add(group);
                }
            }
            result.put(" ", groups.toArray(new AuthorizationGroup[0]));
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }

        return result;
    }

}
