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
package org.nabucco.framework.common.authorization.ui.rcp.browser.user;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.model.AuthorizationUserListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * AuthorizationUserListViewBrowserElement
 * 
 * @author Undefined
 */
public class AuthorizationUserListViewBrowserElement extends BrowserListElement<AuthorizationUserListViewModel>
        implements NabuccoInjectionReciever {

    private AuthorizationUserListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new AuthorizationUserListViewBrowserElement instance.
     * 
     * @param datatypeList
     *            the List<AuthorizationUser>.
     */
    public AuthorizationUserListViewBrowserElement(final List<AuthorizationUser> datatypeList) {
        this(datatypeList.toArray(new AuthorizationUser[datatypeList.size()]));
    }

    /**
     * Constructs a new AuthorizationUserListViewBrowserElement instance.
     * 
     * @param datatypeArray
     *            the AuthorizationUser[].
     */
    public AuthorizationUserListViewBrowserElement(final AuthorizationUser[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(AuthorizationUserListViewBrowserElement.class);
        listViewBrowserElementHandler = instance.inject(AuthorizationUserListViewBrowserElementHandler.class);
        viewModel = new AuthorizationUserListViewModel();
        viewModel.setElements(datatypeArray);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        listViewBrowserElementHandler.createChildren(viewModel, this);
    }

    @Override
    public void removeBrowserElement(final BrowserElement element) {
        super.removeBrowserElement(element);
        listViewBrowserElementHandler.removeChild(element, this);
    }
}
