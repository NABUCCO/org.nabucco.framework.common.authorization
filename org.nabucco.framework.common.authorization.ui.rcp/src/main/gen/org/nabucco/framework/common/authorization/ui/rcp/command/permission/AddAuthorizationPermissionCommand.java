/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationPermissionCommand<p/>Adds a permission<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationPermissionCommand implements NabuccoCommand {

    private AddPermissionHandler addPermissionHandler = NabuccoInjector.getInstance(
            AddAuthorizationPermissionCommand.class).inject(AddPermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationUserCommand";

    /** Constructs a new AddAuthorizationPermissionCommand instance. */
    public AddAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        addPermissionHandler.addPermission();
    }

    @Override
    public String getId() {
        return AddAuthorizationPermissionCommand.ID;
    }
}
