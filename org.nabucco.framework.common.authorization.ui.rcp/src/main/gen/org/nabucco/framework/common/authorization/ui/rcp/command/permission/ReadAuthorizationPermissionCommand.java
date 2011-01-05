/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * ReadAuthorizationPermissionCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class ReadAuthorizationPermissionCommand implements NabuccoCommand {

    private ReadPermissionHandler readPermissionHandler = NabuccoInjector.getInstance(
            ReadAuthorizationPermissionCommand.class).inject(ReadPermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.ReadAuthorizationPermissionCommand";

    /** Constructs a new ReadAuthorizationPermissionCommand instance. */
    public ReadAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        readPermissionHandler.readPermission();
    }

    @Override
    public String getId() {
        return ReadAuthorizationPermissionCommand.ID;
    }
}
