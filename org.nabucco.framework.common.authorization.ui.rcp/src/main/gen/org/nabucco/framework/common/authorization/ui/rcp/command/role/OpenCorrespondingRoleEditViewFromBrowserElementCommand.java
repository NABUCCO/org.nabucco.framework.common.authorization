/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingRoleEditViewFromBrowserElementCommand<p/>Command for opening an edit view from a browser element<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-05-28
 */
public class OpenCorrespondingRoleEditViewFromBrowserElementCommand implements NabuccoCommand {

    private OpenCorrespondingRoleEditViewFromBrowserElementCommandHandler openCorrespondingRoleEditViewFromBrowserElementCommandHandler = NabuccoInjector
            .getInstance(OpenCorrespondingRoleEditViewFromBrowserElementCommand.class).inject(
                    OpenCorrespondingRoleEditViewFromBrowserElementCommandHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.OpenCorrespondingRoleEditViewFromBrowserElementCommand";

    /** Constructs a new OpenCorrespondingRoleEditViewFromBrowserElementCommand instance. */
    public OpenCorrespondingRoleEditViewFromBrowserElementCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingRoleEditViewFromBrowserElementCommandHandler
                .openCorrespondingRoleEditViewFromBrowserElementCommand();
    }

    @Override
    public String getId() {
        return OpenCorrespondingRoleEditViewFromBrowserElementCommand.ID;
    }
}
