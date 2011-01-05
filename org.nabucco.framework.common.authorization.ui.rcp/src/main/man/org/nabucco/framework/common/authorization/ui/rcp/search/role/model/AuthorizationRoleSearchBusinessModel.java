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
package org.nabucco.framework.common.authorization.ui.rcp.search.role.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.role.AuthorizationRoleListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * This does the search.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationRoleSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.role.model.AuthorizationRoleSearchBusinessModel";

    public List<AuthorizationRole> findAuthorizationRoleByGroup(
            final AuthorizationGroup authorizationGroup) {
        return search(createSearchMsg(authorizationGroup));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationRoleListViewBrowserElement search(
            NabuccoComponentSearchParameter searchViewModel) {
        AuthorizationRoleListViewBrowserElement result = null;
        if (searchViewModel instanceof AuthorizationRoleSearchViewModel) {
            final AuthorizationRoleSearchViewModel AuthorizationRoleSearchViewModel = (AuthorizationRoleSearchViewModel) searchViewModel;
            final AuthorizationSearchMsg rq = createSearchMsg(AuthorizationRoleSearchViewModel);
            final List<AuthorizationRole> response = search(rq);
            if (response.size() > 0) {
                result = new AuthorizationRoleListViewBrowserElement(search(rq).toArray(
                        new AuthorizationRole[0]));
            }

        }
        return result;
    }

    private List<AuthorizationRole> search(final AuthorizationSearchMsg rq) {
        try {
            final SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            final AuthorizationRoleListMsg response = searchDelegate.searchAuthorizationRole(rq);
            return response.getAuthorizationRoleList();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationGroup group) {
        final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        final AuthorizationSearchParameter param = new AuthorizationSearchParameter();
        param.setType(AuthorizationType.GROUP);
        param.setId(group.getId());
        msg.getParameterList().add(param);
        return msg;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationRoleSearchViewModel model) {
        final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(getNameFromModel(model));
        msg.setCodeType(getCodeTypeFromModel(model));
        msg.setOwner(getOwnerFromModel(model));
        msg.setDescription(getDescriptionFromModel(model));
        return msg;
    }

    private Name getNameFromModel(AuthorizationRoleSearchViewModel searchViewModel) {
        final String rolename = searchViewModel.getRoleRolename();
        if (rolename == null || rolename.isEmpty()) {
            return null;
        }
        return new Name(rolename);
    }

    private CodeType getCodeTypeFromModel(AuthorizationRoleSearchViewModel searchViewModel) {
        final String codeType = searchViewModel.getRoleRoleType();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return new CodeType(codeType);
    }

    private Owner getOwnerFromModel(AuthorizationRoleSearchViewModel searchViewModel) {
        final String owner = searchViewModel.getRoleOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(AuthorizationRoleSearchViewModel searchViewModel) {
        final String description = searchViewModel.getRoleDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
