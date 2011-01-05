/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.user;

import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationUserEditViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationUserEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     *
     * @param authorizationUser the AuthorizationUserEditViewModel.
     * @return the AuthorizationUserEditViewModel.
     */
    AuthorizationUserEditViewModel loadFull(final AuthorizationUserEditViewModel authorizationUser);

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationUserEditViewBrowserElement.
     * @param viewModel the AuthorizationUserEditViewModel.
     */
    void createChildren(final AuthorizationUserEditViewModel viewModel,
            final AuthorizationUserEditViewBrowserElement element);
}
