/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
     * @param datatype the AuthorizationGroup.
     */
    public AuthorizationGroupEditViewBrowserElement(final AuthorizationGroup datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationGroupEditViewBrowserElement.class);
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
     * @param viewModel the AuthorizationGroupEditViewModel.
     */
    public void setViewModel(AuthorizationGroupEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
