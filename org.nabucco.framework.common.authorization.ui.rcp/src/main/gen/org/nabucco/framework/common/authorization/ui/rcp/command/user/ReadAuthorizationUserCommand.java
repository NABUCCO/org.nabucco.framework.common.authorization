/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReadAuthorizationUserCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class ReadAuthorizationUserCommand implements NabuccoCommand {

    private ReadUserHandler readUserHandler = NabuccoInjector.getInstance(
            ReadAuthorizationUserCommand.class).inject(ReadUserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.ReadAuthorizationUserCommand";

    /** Constructs a new ReadAuthorizationUserCommand instance. */
    public ReadAuthorizationUserCommand() {
        super();
    }

    @Override
    public void run() {
        readUserHandler.readUser();
    }

    @Override
    public String getId() {
        return ReadAuthorizationUserCommand.ID;
    }
}
