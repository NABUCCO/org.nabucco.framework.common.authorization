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
package org.nabucco.framework.common.authorization.ui.rcp.command.role;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingRoleEditViewFromBrowserElementCommand
 * <p/>
 * Command for opening an edit view from a browser element
 * <p/>
 * 
 * @author Nicolas Moser, PRODYNA AG, 2010-05-28
 */
public class OpenCorrespondingRoleEditViewFromBrowserElementCommand implements NabuccoCommand {

    private OpenCorrespondingRoleEditViewFromBrowserElementCommandHandler openCorrespondingRoleEditViewFromBrowserElementCommandHandler = NabuccoInjector
            .getInstance(OpenCorrespondingRoleEditViewFromBrowserElementCommand.class).inject(
                    OpenCorrespondingRoleEditViewFromBrowserElementCommandHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.role.OpenCorrespondingRoleEditViewFromBrowserElementCommand";

    /** Constructs a new OpenCorrespondingRoleEditViewFromBrowserElementCommand instance. */
    public OpenCorrespondingRoleEditViewFromBrowserElementCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingRoleEditViewFromBrowserElementCommandHandler
                .openCorrespondingRoleEditViewFromBrowserElementCommand();
    }

    @Override
    public String getId() {
        return OpenCorrespondingRoleEditViewFromBrowserElementCommand.ID;
    }
}
