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
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * AuthorizationPermissionEditViewBrowserElement
 * 
 * @author Undefined
 */
public class AuthorizationPermissionEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private AuthorizationPermissionEditViewModel viewModel;

    private AuthorizationPermissionEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationPermissionEditViewBrowserElement instance.
     * 
     * @param datatype
     *            the AuthorizationPermission.
     */
    public AuthorizationPermissionEditViewBrowserElement(final AuthorizationPermission datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(AuthorizationPermissionEditViewBrowserElement.class);
        browserHandler = instance.inject(AuthorizationPermissionEditViewBrowserElementHandler.class);
        viewModel = new AuthorizationPermissionEditViewModel();
        viewModel.setPermission(datatype);
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
     * @return the AuthorizationPermissionEditViewModel.
     */
    public AuthorizationPermissionEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     * 
     * @param viewModel
     *            the AuthorizationPermissionEditViewModel.
     */
    public void setViewModel(AuthorizationPermissionEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
