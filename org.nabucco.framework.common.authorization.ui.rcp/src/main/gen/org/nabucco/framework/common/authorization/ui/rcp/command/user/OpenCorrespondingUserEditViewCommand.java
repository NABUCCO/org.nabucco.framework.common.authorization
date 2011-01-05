/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingUserEditViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingUserEditViewCommand implements NabuccoCommand {

    private OpenCorrespondingUserEditViewHandler openCorrespondingUserEditViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingUserEditViewCommand.class).inject(
                    OpenCorrespondingUserEditViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.OpenCorrespondingUserCommand";

    /** Constructs a new OpenCorrespondingUserEditViewCommand instance. */
    public OpenCorrespondingUserEditViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingUserEditViewHandler.openCorrespondingUserEditView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingUserEditViewCommand.ID;
    }
}
