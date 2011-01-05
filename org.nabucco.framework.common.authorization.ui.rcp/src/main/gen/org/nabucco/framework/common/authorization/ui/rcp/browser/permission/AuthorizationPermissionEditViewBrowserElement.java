/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
     * @param datatype the AuthorizationPermission.
     */
    public AuthorizationPermissionEditViewBrowserElement(final AuthorizationPermission datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationPermissionEditViewBrowserElement.class);
        browserHandler = instance
                .inject(AuthorizationPermissionEditViewBrowserElementHandler.class);
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
     * @param viewModel the AuthorizationPermissionEditViewModel.
     */
    public void setViewModel(AuthorizationPermissionEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
