/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
public class AuthorizationRoleEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private AuthorizationRoleEditViewModel viewModel;

    private AuthorizationRoleEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationRoleEditViewBrowserElement instance.
     *
     * @param datatype the AuthorizationRole.
     */
    public AuthorizationRoleEditViewBrowserElement(final AuthorizationRole datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationRoleEditViewBrowserElement.class);
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
     * @param viewModel the AuthorizationRoleEditViewModel.
     */
    public void setViewModel(AuthorizationRoleEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
