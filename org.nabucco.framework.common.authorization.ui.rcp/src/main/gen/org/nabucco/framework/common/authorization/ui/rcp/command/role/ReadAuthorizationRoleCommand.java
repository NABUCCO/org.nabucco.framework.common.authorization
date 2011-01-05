/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReadAuthorizationRoleCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class ReadAuthorizationRoleCommand implements NabuccoCommand {

    private ReadRoleHandler readRoleHandler = NabuccoInjector.getInstance(
            ReadAuthorizationRoleCommand.class).inject(ReadRoleHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.ReadAuthorizationRoleCommand";

    /** Constructs a new ReadAuthorizationRoleCommand instance. */
    public ReadAuthorizationRoleCommand() {
        super();
    }

    @Override
    public void run() {
        readRoleHandler.readRole();
    }

    @Override
    public String getId() {
        return ReadAuthorizationRoleCommand.ID;
    }
}
