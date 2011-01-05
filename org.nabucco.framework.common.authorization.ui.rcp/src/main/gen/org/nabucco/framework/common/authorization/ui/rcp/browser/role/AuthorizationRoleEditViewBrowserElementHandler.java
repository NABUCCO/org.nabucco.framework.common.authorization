/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.role;

import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationRoleEditViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationRoleEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     *
     * @param authorizationRole the AuthorizationRoleEditViewModel.
     * @return the AuthorizationRoleEditViewModel.
     */
    AuthorizationRoleEditViewModel loadFull(final AuthorizationRoleEditViewModel authorizationRole);

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationRoleEditViewBrowserElement.
     * @param viewModel the AuthorizationRoleEditViewModel.
     */
    void createChildren(final AuthorizationRoleEditViewModel viewModel,
            final AuthorizationRoleEditViewBrowserElement element);
}
