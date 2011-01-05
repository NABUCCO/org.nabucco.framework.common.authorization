/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * AuthorizationPermissionListViewBrowserElement
 *
 * @author Undefined
 */
public class AuthorizationPermissionListViewBrowserElement extends
        BrowserListElement<AuthorizationPermissionListViewModel> implements
        NabuccoInjectionReciever {

    private AuthorizationPermissionListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new AuthorizationPermissionListViewBrowserElement instance.
     *
     * @param datatypeList the List<AuthorizationPermission>.
     */
    public AuthorizationPermissionListViewBrowserElement(
            final List<AuthorizationPermission> datatypeList) {
        this(datatypeList.toArray(new AuthorizationPermission[datatypeList.size()]));
    }

    /**
     * Constructs a new AuthorizationPermissionListViewBrowserElement instance.
     *
     * @param datatypeArray the AuthorizationPermission[].
     */
    public AuthorizationPermissionListViewBrowserElement(
            final AuthorizationPermission[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationPermissionListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(AuthorizationPermissionListViewBrowserElementHandler.class);
        viewModel = new AuthorizationPermissionListViewModel();
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
