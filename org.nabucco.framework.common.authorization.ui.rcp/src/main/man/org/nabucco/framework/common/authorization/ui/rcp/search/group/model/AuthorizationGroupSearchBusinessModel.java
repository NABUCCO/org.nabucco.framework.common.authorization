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
package org.nabucco.framework.common.authorization.ui.rcp.search.group.model;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
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

    public static final String ID = "org.nabucco.framework.common.authorization.ui.rcp.search.group.model.AuthorizationGroupSearchBusinessModel";

    public List<AuthorizationGroup> findAuthorizationGroupByGroup(AuthorizationGroup group) {
        final AuthorizationSearchMsg msg = this.createSearchMessage(group);
        return search(msg);
    }

    private List<AuthorizationGroup> search(AuthorizationSearchMsg rq) {
        List<AuthorizationGroup> result = null;
        try {
            final SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getSearchAuthorization();

            final AuthorizationGroupListMsg response = searchDelegate.searchAuthorizationGroup(rq);

            result = response.getAuthorizationGroupList();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthorizationGroupListViewBrowserElement search(
            NabuccoComponentSearchParameter searchParameter) {

        AuthorizationGroupListViewBrowserElement searchResult = null;

        if (searchParameter instanceof AuthorizationGroupSearchViewModel) {
            try {
                final AuthorizationGroupSearchViewModel searchViewModel = (AuthorizationGroupSearchViewModel) searchParameter;

                final SearchAuthorizationDelegate searchDelegate = AuthorizationComponentServiceDelegateFactory
                        .getInstance().getSearchAuthorization();

                final AuthorizationSearchMsg rq = this.createSearchMessage(searchViewModel);

                final AuthorizationGroupListMsg response = searchDelegate
                        .searchAuthorizationGroup(rq);

                if (response.getAuthorizationGroupList().size() > 0) {
                    searchResult = new AuthorizationGroupListViewBrowserElement(response
                            .getAuthorizationGroupList().toArray(new AuthorizationGroup[0]));
                }
            } catch (final ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
        return searchResult;
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
        final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        msg.setName(group.getGroupname());
        msg.setCodeType(group.getGroupType());
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
    private AuthorizationSearchMsg createSearchMessage(
            AuthorizationGroupSearchViewModel searchViewModel) {

        final AuthorizationSearchMsg msg = new AuthorizationSearchMsg();

        final Name name = this.getNameFromModel(searchViewModel);
        final Owner owner = this.getOwnerFromModel(searchViewModel);
        final Description description = this.getDescriptionFromModel(searchViewModel);
        final CodeType codeType = this.getCodeTypeFromModel(searchViewModel);

        msg.setName(name);
        msg.setOwner(owner);
        msg.setDescription(description);
        msg.setCodeType(codeType);

        return msg;
    }

    private Name getNameFromModel(AuthorizationGroupSearchViewModel searchViewModel) {
        final String name = searchViewModel.getGroupGroupname();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private CodeType getCodeTypeFromModel(AuthorizationGroupSearchViewModel searchViewModel) {
        final String codeType = searchViewModel.getGroupGroupType();
        if (codeType == null || codeType.isEmpty()) {
            return null;
        }
        return new CodeType(codeType);
    }

    private Owner getOwnerFromModel(AuthorizationGroupSearchViewModel searchViewModel) {
        final String owner = searchViewModel.getGroupOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(AuthorizationGroupSearchViewModel searchViewModel) {
        final String description = searchViewModel.getGroupDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
