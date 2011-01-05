/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingPermissionListViewCommand<p/>Command for opening a list view<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingPermissionListViewCommand implements NabuccoCommand {

    private OpenCorrespondingPermissionListViewHandler openCorrespondingPermissionListViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingPermissionListViewCommand.class).inject(
                    OpenCorrespondingPermissionListViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.OpenCorrespondingPermissionListViewCommand";

    /** Constructs a new OpenCorrespondingPermissionListViewCommand instance. */
    public OpenCorrespondingPermissionListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingPermissionListViewHandler.openCorrespondingPermissionListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingPermissionListViewCommand.ID;
    }
}
