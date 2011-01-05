/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationRoleFromBrowserCommand<p/>Add an authorizationrole to authorizationRole from browserEntry.<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-05-29
 */
public class AddAuthorizationRoleFromBrowserCommand implements NabuccoCommand {

    private AddRoleFromBrowserHandler addRoleFromBrowserHandler = NabuccoInjector.getInstance(
            AddAuthorizationRoleFromBrowserCommand.class).inject(AddRoleFromBrowserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.AddAuthorizationRoleFromBrowserCommand";

    /** Constructs a new AddAuthorizationRoleFromBrowserCommand instance. */
    public AddAuthorizationRoleFromBrowserCommand() {
        super();
    }

    @Override
    public void run() {
        addRoleFromBrowserHandler.addRoleFromBrowser();
    }

    @Override
    public String getId() {
        return AddAuthorizationRoleFromBrowserCommand.ID;
    }
}
