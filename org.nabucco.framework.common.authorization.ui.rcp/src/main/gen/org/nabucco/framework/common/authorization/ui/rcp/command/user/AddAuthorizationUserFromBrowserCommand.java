/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationUserFromBrowserCommand<p/>Add an authorizationuser to authorizationUser from browserEntry.<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-05-29
 */
public class AddAuthorizationUserFromBrowserCommand implements NabuccoCommand {

    private AddUserFromBrowserHandler addUserFromBrowserHandler = NabuccoInjector.getInstance(
            AddAuthorizationUserFromBrowserCommand.class).inject(AddUserFromBrowserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationUserFromBrowserCommand";

    /** Constructs a new AddAuthorizationUserFromBrowserCommand instance. */
    public AddAuthorizationUserFromBrowserCommand() {
        super();
    }

    @Override
    public void run() {
        addUserFromBrowserHandler.addUserFromBrowser();
    }

    @Override
    public String getId() {
        return AddAuthorizationUserFromBrowserCommand.ID;
    }
}
