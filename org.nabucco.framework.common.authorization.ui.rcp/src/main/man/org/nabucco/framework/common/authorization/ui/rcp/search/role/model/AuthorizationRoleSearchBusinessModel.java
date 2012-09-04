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

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.role.model.AuthorizationRoleSearchBusinessModel";

    /**
     * Search all roles attached to a given group.
     * 
     * @param group
     *            the group holding the roles
     * 
     * @return the roles
     */
    public List<AuthorizationRole> findAuthorizationRoleByGroup(AuthorizationGroup group) {
        try {
            AuthorizationSearchMsg rq = this.createSearchMsg(group);
            SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getSearchAuthorization();
            AuthorizationRoleListMsg rs = searchDelegate.searchAuthorizationRole(rq);
            return rs.getAuthorizationRoleList();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    @Override
    public AuthorizationRoleListViewBrowserElement search(NabuccoComponentSearchParameter parameter) {

        if (parameter instanceof AuthorizationRoleSearchViewModel) {
            AuthorizationRoleSearchViewModel viewModel = (AuthorizationRoleSearchViewModel) parameter;

            AuthorizationSearchMsg rq = this.createSearchMsg(viewModel);

            try {
                SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory.getInstance()
                        .getSearchAuthorization();
                AuthorizationRoleListMsg rs = searchDelegate.searchAuthorizationRole(rq);

                List<AuthorizationRole> roleList = rs.getAuthorizationRoleList();

                if (roleList.size() > 0) {
                    return new AuthorizationRoleListViewBrowserElement(roleList.toArray(new AuthorizationRole[roleList
                            .size()]));
                }

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }

        return null;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationGroup group) {
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        AuthorizationSearchParameter param = new AuthorizationSearchParameter();
        param.setType(AuthorizationType.GROUP);
        param.setId(group.getId());
        msg.getParameterList().add(param);
        return msg;
    }

    private AuthorizationSearchMsg createSearchMsg(AuthorizationRoleSearchViewModel model) {
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(getName(model));
        msg.setCodeType(getCodeType(model));
        msg.setOwner(getOwner(model));
        msg.setDescription(getDescription(model));
        return msg;
    }

    private Name getName(AuthorizationRoleSearchViewModel searchViewModel) {
        String rolename = searchViewModel.getRoleRolename();
        if (rolename == null || rolename.isEmpty()) {
            return null;
        }
        return new Name(rolename);
    }

    private CodeType getCodeType(AuthorizationRoleSearchViewModel searchViewModel) {
        String codeType = searchViewModel.getOwner();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return new CodeType(codeType);
    }

    private Owner getOwner(AuthorizationRoleSearchViewModel searchViewModel) {
        String owner = searchViewModel.getRoleOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescription(AuthorizationRoleSearchViewModel searchViewModel) {
        String description = searchViewModel.getRoleDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
