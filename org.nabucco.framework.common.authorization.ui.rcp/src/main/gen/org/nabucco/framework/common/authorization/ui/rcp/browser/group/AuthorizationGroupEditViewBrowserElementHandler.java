/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.browser.group;

import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * AuthorizationGroupEditViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface AuthorizationGroupEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     *
     * @param authorizationGroup the AuthorizationGroupEditViewModel.
     * @return the AuthorizationGroupEditViewModel.
     */
    AuthorizationGroupEditViewModel loadFull(
            final AuthorizationGroupEditViewModel authorizationGroup);

    /**
     * CreateChildren.
     *
     * @param element the AuthorizationGroupEditViewBrowserElement.
     * @param viewModel the AuthorizationGroupEditViewModel.
     */
    void createChildren(final AuthorizationGroupEditViewModel viewModel,
            final AuthorizationGroupEditViewBrowserElement element);
}
