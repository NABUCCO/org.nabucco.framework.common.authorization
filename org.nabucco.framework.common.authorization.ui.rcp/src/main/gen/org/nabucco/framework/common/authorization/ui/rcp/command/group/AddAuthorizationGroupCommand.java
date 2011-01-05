/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationGroupCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationGroupCommand implements NabuccoCommand {

    private AddGroupHandler addGroupHandler = NabuccoInjector.getInstance(
            AddAuthorizationGroupCommand.class).inject(AddGroupHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationUserCommand";

    /** Constructs a new AddAuthorizationGroupCommand instance. */
    public AddAuthorizationGroupCommand() {
        super();
    }

    @Override
    public void run() {
        addGroupHandler.addGroup();
    }

    @Override
    public String getId() {
        return AddAuthorizationGroupCommand.ID;
    }
}
