/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingPermissionEditViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingPermissionEditViewCommand implements NabuccoCommand {

    private OpenCorrespondingPermissionEditViewHandler openCorrespondingPermissionEditViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingPermissionEditViewCommand.class).inject(
                    OpenCorrespondingPermissionEditViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.OpenCorrespondingPermissionEditViewCommand";

    /** Constructs a new OpenCorrespondingPermissionEditViewCommand instance. */
    public OpenCorrespondingPermissionEditViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingPermissionEditViewHandler.openCorrespondingPermissionEditView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingPermissionEditViewCommand.ID;
    }
}
