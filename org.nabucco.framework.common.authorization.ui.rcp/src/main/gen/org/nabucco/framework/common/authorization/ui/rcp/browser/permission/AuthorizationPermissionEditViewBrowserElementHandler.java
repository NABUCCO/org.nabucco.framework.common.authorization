/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.permission;

import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationPermissionEditViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationPermissionEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     *
     * @param authorizationPermission the AuthorizationPermissionEditViewModel.
     * @return the AuthorizationPermissionEditViewModel.
     */
    AuthorizationPermissionEditViewModel loadFull(
            final AuthorizationPermissionEditViewModel authorizationPermission);

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationPermissionEditViewBrowserElement.
     * @param viewModel the AuthorizationPermissionEditViewModel.
     */
    void createChildren(final AuthorizationPermissionEditViewModel viewModel,
            final AuthorizationPermissionEditViewBrowserElement element);
}
