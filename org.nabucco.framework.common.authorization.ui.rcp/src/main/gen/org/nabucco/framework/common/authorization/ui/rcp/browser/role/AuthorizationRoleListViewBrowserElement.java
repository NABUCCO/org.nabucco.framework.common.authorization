/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.role;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.ui.rcp.list.role.model.AuthorizationRoleListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * AuthorizationRoleListViewBrowserElement
 *
 * @author Undefined
 */
public class AuthorizationRoleListViewBrowserElement extends
        BrowserListElement<AuthorizationRoleListViewModel> implements NabuccoInjectionReciever {

    private AuthorizationRoleListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new AuthorizationRoleListViewBrowserElement instance.
     *
     * @param datatypeList the List<AuthorizationRole>.
     */
    public AuthorizationRoleListViewBrowserElement(final List<AuthorizationRole> datatypeList) {
        this(datatypeList.toArray(new AuthorizationRole[datatypeList.size()]));
    }

    /**
     * Constructs a new AuthorizationRoleListViewBrowserElement instance.
     *
     * @param datatypeArray the AuthorizationRole[].
     */
    public AuthorizationRoleListViewBrowserElement(final AuthorizationRole[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationRoleListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(AuthorizationRoleListViewBrowserElementHandler.class);
        viewModel = new AuthorizationRoleListViewModel();
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
