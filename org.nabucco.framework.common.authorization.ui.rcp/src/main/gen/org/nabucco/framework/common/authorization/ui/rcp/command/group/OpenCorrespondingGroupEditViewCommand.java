/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingGroupEditViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingGroupEditViewCommand implements NabuccoCommand {

    private OpenCorrespondingGroupEditViewHandler openCorrespondingGroupEditViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingGroupEditViewCommand.class).inject(
                    OpenCorrespondingGroupEditViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.group.OpenCorrespondingGroupEditViewCommand";

    /** Constructs a new OpenCorrespondingGroupEditViewCommand instance. */
    public OpenCorrespondingGroupEditViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingGroupEditViewHandler.openCorrespondingGroupEditView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingGroupEditViewCommand.ID;
    }
}
