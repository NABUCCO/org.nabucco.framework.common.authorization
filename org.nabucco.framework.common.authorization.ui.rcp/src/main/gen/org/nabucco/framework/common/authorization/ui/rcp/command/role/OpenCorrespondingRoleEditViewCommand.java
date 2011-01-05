/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingRoleEditViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingRoleEditViewCommand implements NabuccoCommand {

    private OpenCorrespondingRoleEditViewHandler openCorrespondingRoleEditViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingRoleEditViewCommand.class).inject(
                    OpenCorrespondingRoleEditViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.OpenCorrespondingRoleEditViewCommand";

    /** Constructs a new OpenCorrespondingRoleEditViewCommand instance. */
    public OpenCorrespondingRoleEditViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingRoleEditViewHandler.openCorrespondingRoleEditView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingRoleEditViewCommand.ID;
    }
}
