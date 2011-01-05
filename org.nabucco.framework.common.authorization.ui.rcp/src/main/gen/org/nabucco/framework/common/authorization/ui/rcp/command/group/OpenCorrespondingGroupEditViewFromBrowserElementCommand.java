/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingGroupEditViewFromBrowserElementCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingGroupEditViewFromBrowserElementCommand implements NabuccoCommand {

    private OpenCorrespondingGroupEditViewFromBrowserElementCommandHandler openCorrespondingGroupEditViewFromBrowserElementCommandHandler = NabuccoInjector
            .getInstance(OpenCorrespondingGroupEditViewFromBrowserElementCommand.class).inject(
                    OpenCorrespondingGroupEditViewFromBrowserElementCommandHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.group.OpenCorrespondingGroupEditViewFromBrowserElementCommand";

    /** Constructs a new OpenCorrespondingGroupEditViewFromBrowserElementCommand instance. */
    public OpenCorrespondingGroupEditViewFromBrowserElementCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingGroupEditViewFromBrowserElementCommandHandler
                .openCorrespondingGroupEditViewFromBrowserElementCommand();
    }

    @Override
    public String getId() {
        return OpenCorrespondingGroupEditViewFromBrowserElementCommand.ID;
    }
}
