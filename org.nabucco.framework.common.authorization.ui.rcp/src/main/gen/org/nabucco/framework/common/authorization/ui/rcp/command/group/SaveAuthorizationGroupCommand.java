/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveAuthorizationGroupCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SaveAuthorizationGroupCommand implements NabuccoCommand {

    private SaveGroupHandler saveGroupHandler = NabuccoInjector.getInstance(
            SaveAuthorizationGroupCommand.class).inject(SaveGroupHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.group.SaveAuthorizationGroupCommand";

    /** Constructs a new SaveAuthorizationGroupCommand instance. */
    public SaveAuthorizationGroupCommand() {
        super();
    }

    @Override
    public void run() {
        saveGroupHandler.saveGroup();
    }

    @Override
    public String getId() {
        return SaveAuthorizationGroupCommand.ID;
    }
}
