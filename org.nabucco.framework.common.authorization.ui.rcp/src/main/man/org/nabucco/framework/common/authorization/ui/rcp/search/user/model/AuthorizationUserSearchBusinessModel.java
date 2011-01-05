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
package org.nabucco.framework.common.authorization.ui.rcp.search.user.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.user.AuthorizationUserListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * AuthorizationUserSearchBusinessModel
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.user.model.AuthorizationUserSearchBusinessModel";

    public List<AuthorizationUser> findAuthorizationUserByGroup(AuthorizationGroup group) {
        return search(createSearchMsg(group));
    }

    @Override
    public AuthorizationUserListViewBrowserElement search(NabuccoComponentSearchParameter model) {

        if (model instanceof AuthorizationUserSearchViewModel) {
            AuthorizationUserSearchViewModel authorizationUserSearchViewModel = (AuthorizationUserSearchViewModel) model;
            AuthorizationSearchMsg request = this.createSearchMsg(authorizationUserSearchViewModel);

            List<AuthorizationUser> response = this.search(request);

            if (response.size() > 0) {
                AuthorizationUser[] elements = response.toArray(new AuthorizationUser[0]);
                return new AuthorizationUserListViewBrowserElement(elements);
            }
        }

        return null;
    }

    private List<AuthorizationUser> search(AuthorizationSearchMsg request) {
        try {
            SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();

            AuthorizationUserListMsg response = searchDelegate.searchAuthorizationUser(request);

            return response.getAuthorizationUserList();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationGroup group) {
        AuthorizationSearchMsg result = new AuthorizationSearchMsg();
        AuthorizationSearchParameter param = new AuthorizationSearchParameter();
        param.setType(AuthorizationType.GROUP);
        param.setId(group.getId());
        result.getParameterList().add(param);
        return result;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationUserSearchViewModel model) {
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(getNameFromModel(model));
        msg.setOwner(getOwnerFromModel(model));
        msg.setCodeType(getCodeTypeFromModel(model));
        msg.setDescription(getDescriptionFromModel(model));
        return msg;
    }

    private Name getNameFromModel(AuthorizationUserSearchViewModel searchViewModel) {
        String username = searchViewModel.getUserUsername();
        if (username == null || username.isEmpty()) {
            return null;
        }
        return new Name(username);
    }

    private CodeType getCodeTypeFromModel(AuthorizationUserSearchViewModel searchViewModel) {
        String codeType = searchViewModel.getUserUserType();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return new CodeType(codeType);
    }

    private Owner getOwnerFromModel(AuthorizationUserSearchViewModel searchViewModel) {
        String owner = searchViewModel.getUserOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(AuthorizationUserSearchViewModel searchViewModel) {
        String description = searchViewModel.getUserDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }
}
