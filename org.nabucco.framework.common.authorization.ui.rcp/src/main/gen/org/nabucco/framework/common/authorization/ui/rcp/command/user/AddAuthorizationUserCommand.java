/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationUserCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationUserCommand implements NabuccoCommand {

    private AddUserHandler addUserHandler = NabuccoInjector.getInstance(
            AddAuthorizationUserCommand.class).inject(AddUserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationUserCommand";

    /** Constructs a new AddAuthorizationUserCommand instance. */
    public AddAuthorizationUserCommand() {
        super();
    }

    @Override
    public void run() {
        addUserHandler.addUser();
    }

    @Override
    public String getId() {
        return AddAuthorizationUserCommand.ID;
    }
}
