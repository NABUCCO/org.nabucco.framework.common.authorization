/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.user;

import org.nabucco.framework.common.authorization.ui.rcp.list.user.model.AuthorizationUserListViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationUserListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationUserListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationUserListViewBrowserElement.
     * @param viewModel the AuthorizationUserListViewModel.
     */
    void createChildren(final AuthorizationUserListViewModel viewModel,
            final AuthorizationUserListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the AuthorizationUserListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved,
            final AuthorizationUserListViewBrowserElement element);
}
