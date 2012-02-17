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
package org.nabucco.framework.common.authorization.ui.rcp.search.user.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
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

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.user.model.AuthorizationUserSearchBusinessModel";

    /**
     * Find all users for a given group.
     * 
     * @param group
     *            the parent group
     * 
     * @return the user list
     */
    public List<AuthorizationUser> findAuthorizationUserByGroup(AuthorizationGroup group) {
        try {
            SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getSearchAuthorization();

            AuthorizationSearchMsg rq = this.createSearchMsg(group);
            AuthorizationUserListMsg rs = searchDelegate.searchAuthorizationUser(rq);

            return rs.getAuthorizationUserList();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    @Override
    public AuthorizationUserListViewBrowserElement search(NabuccoComponentSearchParameter parameter) {

        if (parameter instanceof AuthorizationUserSearchViewModel) {
            AuthorizationUserSearchViewModel viewModel = (AuthorizationUserSearchViewModel) parameter;

            try {
                SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory.getInstance()
                        .getSearchAuthorization();

                AuthorizationSearchMsg rq = this.createSearchMsg(viewModel);
                AuthorizationUserListMsg rs = searchDelegate.searchAuthorizationUser(rq);
                NabuccoList<AuthorizationUser> userList = rs.getAuthorizationUserList();

                if (userList.size() > 0) {
                    return new AuthorizationUserListViewBrowserElement(userList.toArray(new AuthorizationUser[userList
                            .size()]));
                }

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
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
        msg.setName(getName(model));
        msg.setOwner(getOwner(model));
        msg.setDescription(getDescription(model));
        return msg;
    }

    private Name getName(AuthorizationUserSearchViewModel searchViewModel) {
        String username = searchViewModel.getUserUsername();
        if (username == null || username.isEmpty()) {
            return null;
        }
        return new Name(username);
    }

    private Owner getOwner(AuthorizationUserSearchViewModel searchViewModel) {
        String owner = searchViewModel.getOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescription(AuthorizationUserSearchViewModel searchViewModel) {
        String description = searchViewModel.getUserDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }
}
