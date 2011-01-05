/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationGroupFromBrowserCommand<p/>Add an authorizationgroup from a parent browserEntry.<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationGroupFromBrowserCommand implements NabuccoCommand {

    private AddGroupFromBrowserHandler addGroupFromBrowserHandler = NabuccoInjector.getInstance(
            AddAuthorizationGroupFromBrowserCommand.class).inject(AddGroupFromBrowserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationGroupFromBrowserCommand";

    /** Constructs a new AddAuthorizationGroupFromBrowserCommand instance. */
    public AddAuthorizationGroupFromBrowserCommand() {
        super();
    }

    @Override
    public void run() {
        addGroupFromBrowserHandler.addGroupFromBrowser();
    }

    @Override
    public String getId() {
        return AddAuthorizationGroupFromBrowserCommand.ID;
    }
}
