/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * AddAuthorizationPermissionCommand
 * <p/>
 * Adds a permission
 * <p/>
 * 
 * @author Michael Krausse, PRODYNA AG, 2010-03-30
 */
public class AddAuthorizationPermissionCommand implements NabuccoCommand {

    private AddPermissionHandler addPermissionHandler = NabuccoInjector.getInstance(
            AddAuthorizationPermissionCommand.class).inject(AddPermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.AddAuthorizationUserCommand";

    /** Constructs a new AddAuthorizationPermissionCommand instance. */
    public AddAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        addPermissionHandler.addPermission();
    }

    @Override
    public String getId() {
        return AddAuthorizationPermissionCommand.ID;
    }
}
