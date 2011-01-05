/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationRoleCommand<p/>Creates a new role.<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationRoleCommand implements NabuccoCommand {

    private AddRoleHandler addRoleHandler = NabuccoInjector.getInstance(
            AddAuthorizationRoleCommand.class).inject(AddRoleHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.AddAuthorizationUserCommand";

    /** Constructs a new AddAuthorizationRoleCommand instance. */
    public AddAuthorizationRoleCommand() {
        super();
    }

    @Override
    public void run() {
        addRoleHandler.addRole();
    }

    @Override
    public String getId() {
        return AddAuthorizationRoleCommand.ID;
    }
}
