/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveAuthorizationUserCommand<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SaveAuthorizationUserCommand implements NabuccoCommand {

    private SaveUserHandler saveUserHandler = NabuccoInjector.getInstance(
            SaveAuthorizationUserCommand.class).inject(SaveUserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.SaveAuthorizationUserCommand";

    /** Constructs a new SaveAuthorizationUserCommand instance. */
    public SaveAuthorizationUserCommand() {
        super();
    }

    @Override
    public void run() {
        saveUserHandler.saveUser();
    }

    @Override
    public String getId() {
        return SaveAuthorizationUserCommand.ID;
    }
}
