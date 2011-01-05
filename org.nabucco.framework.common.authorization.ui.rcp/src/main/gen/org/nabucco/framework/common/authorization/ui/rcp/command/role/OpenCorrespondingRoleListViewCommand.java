/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingRoleListViewCommand<p/>Command for opening a list view<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingRoleListViewCommand implements NabuccoCommand {

    private OpenCorrespondingRoleListViewHandler openCorrespondingRoleListViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingRoleListViewCommand.class).inject(
                    OpenCorrespondingRoleListViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.OpenCorrespondingRoleListViewCommand";

    /** Constructs a new OpenCorrespondingRoleListViewCommand instance. */
    public OpenCorrespondingRoleListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingRoleListViewHandler.openCorrespondingRoleListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingRoleListViewCommand.ID;
    }
}
