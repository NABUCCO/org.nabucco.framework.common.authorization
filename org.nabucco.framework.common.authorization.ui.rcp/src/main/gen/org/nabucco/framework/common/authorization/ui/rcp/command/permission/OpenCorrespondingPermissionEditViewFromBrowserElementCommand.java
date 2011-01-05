/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingPermissionEditViewFromBrowserElementCommand<p/>Command for opening an edit view from a browser element<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-05-28
 */
public class OpenCorrespondingPermissionEditViewFromBrowserElementCommand implements NabuccoCommand {

    private OpenCorrespondingPermissionEditViewFromBrowserElementCommandHandler openCorrespondingPermissionEditViewFromBrowserElementCommandHandler = NabuccoInjector
            .getInstance(OpenCorrespondingPermissionEditViewFromBrowserElementCommand.class)
            .inject(OpenCorrespondingPermissionEditViewFromBrowserElementCommandHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.OpenCorrespondingPermissionEditViewFromBrowserElementCommand";

    /** Constructs a new OpenCorrespondingPermissionEditViewFromBrowserElementCommand instance. */
    public OpenCorrespondingPermissionEditViewFromBrowserElementCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingPermissionEditViewFromBrowserElementCommandHandler
                .openCorrespondingPermissionEditViewFromBrowserElementCommand();
    }

    @Override
    public String getId() {
        return OpenCorrespondingPermissionEditViewFromBrowserElementCommand.ID;
    }
}
