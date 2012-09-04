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
package org.nabucco.framework.common.authorization.ui.rcp.browser.role;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * AuthorizationRoleEditViewBrowserElement
 * 
 * @author Undefined
 */
public class AuthorizationRoleEditViewBrowserElement extends DatatypeBrowserElement implements NabuccoInjectionReciever {

    private AuthorizationRoleEditViewModel viewModel;

    private AuthorizationRoleEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationRoleEditViewBrowserElement instance.
     * 
     * @param datatype
     *            the AuthorizationRole.
     */
    public AuthorizationRoleEditViewBrowserElement(final AuthorizationRole datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(AuthorizationRoleEditViewBrowserElement.class);
        browserHandler = instance.inject(AuthorizationRoleEditViewBrowserElementHandler.class);
        viewModel = new AuthorizationRoleEditViewModel();
        viewModel.setRole(datatype);
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
     * @return the AuthorizationRoleEditViewModel.
     */
    public AuthorizationRoleEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     * 
     * @param viewModel
     *            the AuthorizationRoleEditViewModel.
     */
    public void setViewModel(AuthorizationRoleEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
