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
package org.nabucco.framework.common.authorization.ui.rcp.browser.group;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * AuthorizationGroupEditViewBrowserElement
 * 
 * @author Undefined
 */
public class AuthorizationGroupEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private AuthorizationGroupEditViewModel viewModel;

    private AuthorizationGroupEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationGroupEditViewBrowserElement instance.
     * 
     * @param datatype
     *            the AuthorizationGroup.
     */
    public AuthorizationGroupEditViewBrowserElement(final AuthorizationGroup datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(AuthorizationGroupEditViewBrowserElement.class);
        browserHandler = instance.inject(AuthorizationGroupEditViewBrowserElementHandler.class);
        viewModel = new AuthorizationGroupEditViewModel();
        viewModel.setGroup(datatype);
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
     * @return the AuthorizationGroupEditViewModel.
     */
    public AuthorizationGroupEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     * 
     * @param viewModel
     *            the AuthorizationGroupEditViewModel.
     */
    public void setViewModel(AuthorizationGroupEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
