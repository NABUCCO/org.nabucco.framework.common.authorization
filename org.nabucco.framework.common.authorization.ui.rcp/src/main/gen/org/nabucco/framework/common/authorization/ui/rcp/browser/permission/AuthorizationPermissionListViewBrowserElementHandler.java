/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationPermissionListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationPermissionListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationPermissionListViewBrowserElement.
     * @param viewModel the AuthorizationPermissionListViewModel.
     */
    void createChildren(final AuthorizationPermissionListViewModel viewModel,
            final AuthorizationPermissionListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the AuthorizationPermissionListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved,
            final AuthorizationPermissionListViewBrowserElement element);
}
