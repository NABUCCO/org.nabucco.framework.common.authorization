/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SearchAuthorizationPermissionCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SearchAuthorizationPermissionCommand implements NabuccoCommand {

    private SearchPermissionHandler searchPermissionHandler = NabuccoInjector.getInstance(
            SearchAuthorizationPermissionCommand.class).inject(SearchPermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.SearchAuthorizationPermissionCommand";

    /** Constructs a new SearchAuthorizationPermissionCommand instance. */
    public SearchAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        searchPermissionHandler.searchPermission();
    }

    @Override
    public String getId() {
        return SearchAuthorizationPermissionCommand.ID;
    }
}
