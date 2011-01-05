/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveAuthorizationPermissionCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SaveAuthorizationPermissionCommand implements NabuccoCommand {

    private SavePermissionHandler savePermissionHandler = NabuccoInjector.getInstance(
            SaveAuthorizationPermissionCommand.class).inject(SavePermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.SaveAuthorizationPermissionCommand";

    /** Constructs a new SaveAuthorizationPermissionCommand instance. */
    public SaveAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        savePermissionHandler.savePermission();
    }

    @Override
    public String getId() {
        return SaveAuthorizationPermissionCommand.ID;
    }
}
