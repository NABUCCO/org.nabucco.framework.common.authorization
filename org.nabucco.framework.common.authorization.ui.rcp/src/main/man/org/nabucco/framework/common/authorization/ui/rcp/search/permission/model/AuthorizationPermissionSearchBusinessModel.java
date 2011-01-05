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
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * AuthorizationPermissionSearchBusinessModel
 * <p/>
 * This does the search.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationPermissionSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.role.model.AuthorizationPermissionSearchBusinessModel";

    public List<AuthorizationPermission> findAuthorizationPermissionByGroup(
            final AuthorizationGroup authorizationGroup) {
        return search(createSearchMsg(authorizationGroup));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationPermissionListViewBrowserElement search(
            NabuccoComponentSearchParameter searchViewModel) {

        AuthorizationPermissionListViewBrowserElement result = null;

        if (searchViewModel instanceof AuthorizationPermissionSearchViewModel) {
            final AuthorizationPermissionSearchViewModel AuthorizationPermissionSearchViewModel = (AuthorizationPermissionSearchViewModel) searchViewModel;
            final AuthorizationSearchMsg rq = createSearchMsg(AuthorizationPermissionSearchViewModel);
            final List<AuthorizationPermission> response = search(rq);
            if (response.size() > 0) {
                result = new AuthorizationPermissionListViewBrowserElement(search(rq).toArray(
                        new AuthorizationPermission[0]));
            }

        }
        return result;
    }

    private List<AuthorizationPermission> search(final AuthorizationSearchMsg rq) {
        try {
            final SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();
            final AuthorizationPermissionListMsg response = searchDelegate
                    .searchAuthorizationPermission(rq);
            return response.getAuthorizationPermissionList();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationGroup group) {
        final AuthorizationSearchMsg result = new AuthorizationSearchMsg();
        final AuthorizationSearchParameter param = new AuthorizationSearchParameter();
        param.setType(AuthorizationType.GROUP);
        param.setId(group.getId());
        result.getParameterList().add(param);
        return result;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationPermissionSearchViewModel model) {
        final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(getNameFromModel(model));
        msg.setCodeType(getCodeTypeFromModel(model));
        msg.setOwner(getOwnerFromModel(model));
        msg.setDescription(getDescriptionFromModel(model));
        return msg;
    }

    private Name getNameFromModel(AuthorizationPermissionSearchViewModel searchViewModel) {
        final String name = searchViewModel.getPermissionPermissionname();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private CodeType getCodeTypeFromModel(AuthorizationPermissionSearchViewModel searchViewModel) {
        final String codeType = searchViewModel.getPermissionPermissionType();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return new CodeType(codeType);
    }

    private Owner getOwnerFromModel(AuthorizationPermissionSearchViewModel searchViewModel) {
        final String owner = searchViewModel.getPermissionOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(
            AuthorizationPermissionSearchViewModel searchViewModel) {
        final String description = searchViewModel.getPermissionDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
