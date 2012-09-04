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
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
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
 * 
 * @author Michael Krau�e, PRODYNA AG
 */
public class AuthorizationPermissionSearchBusinessModel implements NabuccoComponentSearchModel {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.role.model.AuthorizationPermissionSearchBusinessModel";

    /**
     * Find permissions attached to groups.
     * 
     * @param authorizationGroup
     *            the authorization group
     * 
     * @return the list of permissions
     */
    public List<AuthorizationPermission> findAuthorizationPermissionByGroup(AuthorizationGroup authorizationGroup) {
        AuthorizationSearchMsg rq = this.createSearchMsg(authorizationGroup);
        AuthorizationPermissionListMsg rs = this.search(rq);
        return rs.getAuthorizationPermissionList();
    }

    @Override
    public AuthorizationPermissionListViewBrowserElement search(NabuccoComponentSearchParameter parameter) {

        if (parameter instanceof AuthorizationPermissionSearchViewModel) {
            AuthorizationPermissionSearchViewModel viewModel = (AuthorizationPermissionSearchViewModel) parameter;
            AuthorizationSearchMsg rq = this.createSearchMsg(viewModel);

            AuthorizationPermissionListMsg rs = this.search(rq);

            NabuccoList<AuthorizationPermission> permissionList = rs.getAuthorizationPermissionList();

            if (!permissionList.isEmpty()) {
                return new AuthorizationPermissionListViewBrowserElement(
                        permissionList.toArray(new AuthorizationPermission[permissionList.size()]));
            }

        }
        return null;
    }

    /**
     * Execute the search service.
     * 
     * @param rq
     *            the request parameter
     * 
     * @return the response paramter
     */
    private AuthorizationPermissionListMsg search(AuthorizationSearchMsg rq) {

        try {
            SearchAuthorizationDelegate searchService = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getSearchAuthorization();
            return searchService.searchAuthorizationPermission(rq);

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

    private AuthorizationSearchMsg createSearchMsg(AuthorizationPermissionSearchViewModel model) {
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(getName(model));
        msg.setOwner(getOwner(model));
        msg.setDescription(getDescription(model));
        return msg;
    }

    private Name getName(AuthorizationPermissionSearchViewModel searchViewModel) {
        String name = searchViewModel.getPermissionPermissionname();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private Owner getOwner(AuthorizationPermissionSearchViewModel searchViewModel) {
        String owner = searchViewModel.getOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescription(AuthorizationPermissionSearchViewModel searchViewModel) {
        String description = searchViewModel.getPermissionDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
