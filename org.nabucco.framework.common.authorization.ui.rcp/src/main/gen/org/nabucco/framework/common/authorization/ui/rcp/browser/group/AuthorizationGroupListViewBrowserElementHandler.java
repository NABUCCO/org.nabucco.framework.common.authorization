/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.group;

import org.nabucco.framework.common.authorization.ui.rcp.list.group.model.AuthorizationGroupListViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationGroupListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationGroupListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationGroupListViewBrowserElement.
     * @param viewModel the AuthorizationGroupListViewModel.
     */
    void createChildren(final AuthorizationGroupListViewModel viewModel,
            final AuthorizationGroupListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the AuthorizationGroupListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved,
            final AuthorizationGroupListViewBrowserElement element);
}
