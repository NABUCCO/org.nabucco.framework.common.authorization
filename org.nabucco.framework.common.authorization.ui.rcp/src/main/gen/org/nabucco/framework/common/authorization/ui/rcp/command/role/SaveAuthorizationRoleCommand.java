/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveAuthorizationRoleCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SaveAuthorizationRoleCommand implements NabuccoCommand {

    private SaveRoleHandler saveRoleHandler = NabuccoInjector.getInstance(
            SaveAuthorizationRoleCommand.class).inject(SaveRoleHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.SaveAuthorizationRoleCommand";

    /** Constructs a new SaveAuthorizationRoleCommand instance. */
    public SaveAuthorizationRoleCommand() {
        super();
    }

    @Override
    public void run() {
        saveRoleHandler.saveRole();
    }

    @Override
    public String getId() {
        return SaveAuthorizationRoleCommand.ID;
    }
}
