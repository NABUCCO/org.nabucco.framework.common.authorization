/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingUserListViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingUserListViewCommand implements NabuccoCommand {

    private OpenCorrespondingUserListViewHandler openCorrespondingUserListViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingUserListViewCommand.class).inject(
                    OpenCorrespondingUserListViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.OpenCorrespondingUserListViewCommand";

    /** Constructs a new OpenCorrespondingUserListViewCommand instance. */
    public OpenCorrespondingUserListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingUserListViewHandler.openCorrespondingUserListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingUserListViewCommand.ID;
    }
}
