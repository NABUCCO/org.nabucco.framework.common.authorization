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
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * DeleteAuthorizationUserCommand<p/>Command for deletion of a AuthorizationUser<p/>
 *
 * @author Nicolas Moser, PRODYNA AG, 2011-11-16
 */
public class DeleteAuthorizationUserCommand implements NabuccoCommand {

    private DeleteAuthorizationUserHandler deleteAuthorizationUserHandler = NabuccoInjector.getInstance(
            DeleteAuthorizationUserCommand.class).inject(DeleteAuthorizationUserHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.user.DeleteAuthorizationUserCommand";

    /** Constructs a new DeleteAuthorizationUserCommand instance. */
    public DeleteAuthorizationUserCommand() {
        super();
    }

    @Override
    public void run() {
        deleteAuthorizationUserHandler.deleteAuthorizationUser();
    }

    @Override
    public String getId() {
        return DeleteAuthorizationUserCommand.ID;
    }
}
