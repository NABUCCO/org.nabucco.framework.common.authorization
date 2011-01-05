/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.user;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.model.AuthorizationUserListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * AuthorizationUserListViewBrowserElement
 *
 * @author Undefined
 */
public class AuthorizationUserListViewBrowserElement extends
        BrowserListElement<AuthorizationUserListViewModel> implements NabuccoInjectionReciever {

    private AuthorizationUserListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new AuthorizationUserListViewBrowserElement instance.
     *
     * @param datatypeList the List<AuthorizationUser>.
     */
    public AuthorizationUserListViewBrowserElement(final List<AuthorizationUser> datatypeList) {
        this(datatypeList.toArray(new AuthorizationUser[datatypeList.size()]));
    }

    /**
     * Constructs a new AuthorizationUserListViewBrowserElement instance.
     *
     * @param datatypeArray the AuthorizationUser[].
     */
    public AuthorizationUserListViewBrowserElement(final AuthorizationUser[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationUserListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(AuthorizationUserListViewBrowserElementHandler.class);
        viewModel = new AuthorizationUserListViewModel();
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
