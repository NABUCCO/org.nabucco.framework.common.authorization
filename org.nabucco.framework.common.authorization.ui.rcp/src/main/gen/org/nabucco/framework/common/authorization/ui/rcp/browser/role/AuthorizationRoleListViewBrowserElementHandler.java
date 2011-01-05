/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.role;

import org.nabucco.framework.common.authorization.ui.rcp.list.role.model.AuthorizationRoleListViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * AuthorizationRoleListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationRoleListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationRoleListViewBrowserElement.
     * @param viewModel the AuthorizationRoleListViewModel.
     */
    void createChildren(final AuthorizationRoleListViewModel viewModel,
            final AuthorizationRoleListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the AuthorizationRoleListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved,
            final AuthorizationRoleListViewBrowserElement element);
}
