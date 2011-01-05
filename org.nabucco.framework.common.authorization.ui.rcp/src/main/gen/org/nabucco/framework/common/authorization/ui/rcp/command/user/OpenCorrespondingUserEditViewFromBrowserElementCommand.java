/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingUserEditViewFromBrowserElementCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingUserEditViewFromBrowserElementCommand implements NabuccoCommand {

    private OpenCorrespondingUserEditViewFromBrowserElementCommandHandler openCorrespondingUserEditViewFromBrowserElementCommandHandler = NabuccoInjector
            .getInstance(OpenCorrespondingUserEditViewFromBrowserElementCommand.class).inject(
                    OpenCorrespondingUserEditViewFromBrowserElementCommandHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.OpenCorrespondingUserEditViewFromBrowserElementCommand";

    /** Constructs a new OpenCorrespondingUserEditViewFromBrowserElementCommand instance. */
    public OpenCorrespondingUserEditViewFromBrowserElementCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingUserEditViewFromBrowserElementCommandHandler
                .openCorrespondingUserEditViewFromBrowserElementCommand();
    }

    @Override
    public String getId() {
        return OpenCorrespondingUserEditViewFromBrowserElementCommand.ID;
    }
}
