/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.group;

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.model.AuthorizationGroupListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * AuthorizationGroupListViewBrowserElement
 *
 * @author Undefined
 */
public class AuthorizationGroupListViewBrowserElement extends
        BrowserListElement<AuthorizationGroupListViewModel> implements NabuccoInjectionReciever {

    private AuthorizationGroupListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new AuthorizationGroupListViewBrowserElement instance.
     *
     * @param datatypeList the List<AuthorizationGroup>.
     */
    public AuthorizationGroupListViewBrowserElement(final List<AuthorizationGroup> datatypeList) {
        this(datatypeList.toArray(new AuthorizationGroup[datatypeList.size()]));
    }

    /**
     * Constructs a new AuthorizationGroupListViewBrowserElement instance.
     *
     * @param datatypeArray the AuthorizationGroup[].
     */
    public AuthorizationGroupListViewBrowserElement(final AuthorizationGroup[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector
                .getInstance(AuthorizationGroupListViewBrowserElement.class);
        listViewBrowserElementHandler = instance
                .inject(AuthorizationGroupListViewBrowserElementHandler.class);
        viewModel = new AuthorizationGroupListViewModel();
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
