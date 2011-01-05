/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
public class AuthorizationUserEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private AuthorizationUserEditViewModel viewModel;

    private AuthorizationUserEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new AuthorizationUserEditViewBrowserElement instance.
     *
     * @param datatype the AuthorizationUser.
     */
    public AuthorizationUserEditViewBrowserElement(final AuthorizationUser datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationUserEditViewBrowserElement.class);
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
     * @param viewModel the AuthorizationUserEditViewModel.
     */
    public void setViewModel(AuthorizationUserEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
