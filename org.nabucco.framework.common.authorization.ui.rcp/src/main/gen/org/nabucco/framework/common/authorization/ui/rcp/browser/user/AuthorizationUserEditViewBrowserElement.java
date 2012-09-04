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

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * AuthorizationUserEditViewBrowserElement
 * 
 * @author Undefined
 */
public class AuthorizationUserEditViewBrowserElement extends DatatypeBrowserElement implements NabuccoInjectionReciever {

    private AuthorizationUserEditViewModel viewModel;

    private AuthorizationUserEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationUserEditViewBrowserElement instance.
     * 
     * @param datatype
     *            the AuthorizationUser.
     */
    public AuthorizationUserEditViewBrowserElement(final AuthorizationUser datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(AuthorizationUserEditViewBrowserElement.class);
        browserHandler = instance.inject(AuthorizationUserEditViewBrowserElementHandler.class);
        viewModel = new AuthorizationUserEditViewModel();
        viewModel.setUser(datatype);
    }

    @Override
    protected void fillDatatype() {
        viewModel = browserHandler.loadFull(viewModel);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        browserHandler.createChildren(viewModel, this);
    }

    @Override
    public Map<String, Serializable> getValues() {
        return this.viewModel.getValues();
    }

    /**
     * Getter for the ViewModel.
     * 
     * @return the AuthorizationUserEditViewModel.
     */
    public AuthorizationUserEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     * 
     * @param viewModel
     *            the AuthorizationUserEditViewModel.
     */
    public void setViewModel(AuthorizationUserEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
