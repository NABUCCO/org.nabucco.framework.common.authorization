/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SearchAuthorizationUserCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SearchAuthorizationUserCommand implements NabuccoCommand {

    private SearchUserHandler searchUserHandler = NabuccoInjector.getInstance(
            SearchAuthorizationUserCommand.class).inject(SearchUserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.SearchAuthorizationUserCommand";

    /** Constructs a new SearchAuthorizationUserCommand instance. */
    public SearchAuthorizationUserCommand() {
        super();
    }

    @Override
    public void run() {
        searchUserHandler.searchUser();
    }

    @Override
    public String getId() {
        return SearchAuthorizationUserCommand.ID;
    }
}
