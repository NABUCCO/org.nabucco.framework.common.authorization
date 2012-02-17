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
package org.nabucco.framework.common.authorization.ui.rcp.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;
import org.nabucco.framework.plugin.base.registry.ApplicationElement;
import org.nabucco.framework.plugin.base.registry.NabuccoRegistry;
import org.nabucco.framework.plugin.base.registry.callback.PermissionCallback;

/**
 * AuthorizationSourceProvider
 * <p/>
 * Source provider for commands in rcp file menu that must be disabled depending on user's
 * permissions.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationSourceProvider extends AbstractSourceProvider {

    private final static String PERMISSION_VARIABLE = "org.nabucco.framework.common.authorization.permission";

    /**
     * This method should be overriden for custom variable names.
     * 
     * @return the variable name
     */
    protected String getVariableName() {
        return PERMISSION_VARIABLE;
    }

    @Override
    public final void dispose() {
    }

    @Override
    public final String[] getProvidedSourceNames() {
        return new String[] { this.getVariableName() };
    }

    @Override
    public final Map<String, Boolean> getCurrentState() {
        Map<String, Boolean> map = new HashMap<String, Boolean>(1);
        boolean value = this.hasPermission();
        map.put(this.getVariableName(), value);
        return map;
    }

    /**
     * Callbacks the application for the given permissions.
     * 
     * @param element
     *            the element to check
     * 
     * @return <b>true</b> if the permission is granted, <b>false</b> if not
     */
    private boolean hasPermission() {
        boolean result = true;
        ApplicationElement application = NabuccoRegistry.getInstance().getApplication();

        List<PermissionCallback> callbacks = application.getRegistryCallbacks(PermissionCallback.class);

        for (PermissionCallback callback : callbacks) {
            result = result && callback.onSearchViewContribution(this.getVariableName());
        }

        super.fireSourceChanged(ISources.WORKBENCH, this.getVariableName(), result);

        return result;
    }
}
