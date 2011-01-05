/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationPermissionFromBrowserCommand<p/>Add an authorizationpermission to authorizationPermission from browserEntry.<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-05-29
 */
public class AddAuthorizationPermissionFromBrowserCommand implements NabuccoCommand {

    private AddPermissionFromBrowserHandler addPermissionFromBrowserHandler = NabuccoInjector
            .getInstance(AddAuthorizationPermissionFromBrowserCommand.class).inject(
                    AddPermissionFromBrowserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationPermissionFromBrowserCommand";

    /** Constructs a new AddAuthorizationPermissionFromBrowserCommand instance. */
    public AddAuthorizationPermissionFromBrowserCommand() {
        super();
    }

    @Override
    public void run() {
        addPermissionFromBrowserHandler.addPermissionFromBrowser();
    }

    @Override
    public String getId() {
        return AddAuthorizationPermissionFromBrowserCommand.ID;
    }
}
