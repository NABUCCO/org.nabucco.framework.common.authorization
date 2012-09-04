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
package org.nabucco.framework.common.authorization.ui.rcp.search.group.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.ui.rcp.browser.group.AuthorizationGroupListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.search.SearchAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * SearchModel for search an authorizationGroup.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupSearchBusinessModel implements NabuccoComponentSearchModel {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.group.model.AuthorizationGroupSearchBusinessModel";

    /**
     * Find child groups of a given group.
     * 
     * @param group
     *            the parent group
     * 
     * @return the child groups
     */
    public List<AuthorizationGroup> findAuthorizationGroupByGroup(AuthorizationGroup group) {
        AuthorizationSearchMsg rq = this.createSearchMessage(group);
        try {
            SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getSearchAuthorization();

            AuthorizationGroupListMsg rs = searchDelegate.searchAuthorizationGroup(rq);

            return rs.getAuthorizationGroupList();

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

    @Override
    public AuthorizationGroupListViewBrowserElement search(NabuccoComponentSearchParameter searchParameter) {

        if (searchParameter instanceof AuthorizationGroupSearchViewModel) {
            try {
                AuthorizationGroupSearchViewModel searchViewModel = (AuthorizationGroupSearchViewModel) searchParameter;

                SearchAuthorizationDelegate searchService = AuthorizationComponentServiceDelegateFactory.getInstance()
                        .getSearchAuthorization();

                AuthorizationSearchMsg rq = this.createSearchMessage(searchViewModel);

                AuthorizationGroupListMsg response = searchService.searchAuthorizationGroup(rq);

                if (response.getAuthorizationGroupList().size() > 0) {
                    return new AuthorizationGroupListViewBrowserElement(response.getAuthorizationGroupList().toArray(
                            new AuthorizationGroup[0]));
                }

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
        return null;
    }

    /**
     * Creaets the search request message.
     * 
     * @param group
     *            the authorization group containing the search parameters
     * 
     * @return the search message
     */
    private AuthorizationSearchMsg createSearchMessage(AuthorizationGroup group) {
        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(group.getGroupname());
        msg.setOwner(group.getOwner());
        msg.setDescription(group.getDescription());
        return msg;
    }

    /**
     * Creates the search request message.
     * 
     * @param searchViewModel
     *            the search model containing the search parameters
     * 
     * @return the search message
     */
    private AuthorizationSearchMsg createSearchMessage(AuthorizationGroupSearchViewModel searchViewModel) {

        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();

        Name name = this.getName(searchViewModel);
        Owner owner = this.getOwner(searchViewModel);
        Description description = this.getDescription(searchViewModel);

        msg.setName(name);
        msg.setOwner(owner);
        msg.setDescription(description);

        return msg;
    }

    private Name getName(AuthorizationGroupSearchViewModel searchViewModel) {
        String name = searchViewModel.getGroupGroupname();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private Owner getOwner(AuthorizationGroupSearchViewModel searchViewModel) {
        String owner = searchViewModel.getOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescription(AuthorizationGroupSearchViewModel searchViewModel) {
        String description = searchViewModel.getGroupDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
