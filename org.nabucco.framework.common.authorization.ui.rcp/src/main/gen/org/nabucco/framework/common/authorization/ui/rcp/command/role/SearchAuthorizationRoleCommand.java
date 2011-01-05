/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SearchAuthorizationRoleCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SearchAuthorizationRoleCommand implements NabuccoCommand {

    private SearchRoleHandler searchRoleHandler = NabuccoInjector.getInstance(
            SearchAuthorizationRoleCommand.class).inject(SearchRoleHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.SearchAuthorizationRoleCommand";

    /** Constructs a new SearchAuthorizationRoleCommand instance. */
    public SearchAuthorizationRoleCommand() {
        super();
    }

    @Override
    public void run() {
        searchRoleHandler.searchRole();
    }

    @Override
    public String getId() {
        return SearchAuthorizationRoleCommand.ID;
    }
}
