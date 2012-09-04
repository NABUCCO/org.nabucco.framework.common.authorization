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
 * SearchAuthorizationPermissionCommand
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-03-16
 */
public class SearchAuthorizationPermissionCommand implements NabuccoCommand {

    private SearchPermissionHandler searchPermissionHandler = NabuccoInjector.getInstance(
            SearchAuthorizationPermissionCommand.class).inject(SearchPermissionHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.permission.SearchAuthorizationPermissionCommand";

    /** Constructs a new SearchAuthorizationPermissionCommand instance. */
    public SearchAuthorizationPermissionCommand() {
        super();
    }

    @Override
    public void run() {
        searchPermissionHandler.searchPermission();
    }

    @Override
    public String getId() {
        return SearchAuthorizationPermissionCommand.ID;
    }
}
