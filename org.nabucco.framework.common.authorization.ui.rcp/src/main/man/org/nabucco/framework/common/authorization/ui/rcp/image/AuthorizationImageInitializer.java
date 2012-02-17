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
package org.nabucco.framework.common.authorization.ui.rcp.image;

import java.net.URL;

import org.nabucco.framework.plugin.base.layout.ImageProvider;

/**
 * AuthorizationImageInitializer
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
@Deprecated
public class AuthorizationImageInitializer {

    private static AuthorizationImageInitializer instance;

    private AuthorizationImageInitializer() {
    }

    /**
     * Singleton constructor.
     * 
     * @return The singleton instance of AuthorizationImageInitializer
     */
    public static AuthorizationImageInitializer getInstance() {
        if (instance == null) {
            instance = new AuthorizationImageInitializer();
        }
        return instance;
    }

    /**
     * Registers all component images in
     * {@link org.nabucco.framework.plugin.base.layout.ImageProvider}.
     * <p>
     * The component images intended to be registered must be declared in
     * {@link AuthorizationImageRegistry}.
     */
    public void initializeComponentImages() {
        for (AuthorizationImageRegistry imageConfig : AuthorizationImageRegistry.values()) {
            final String resourcePath = imageConfig.getResourcePath();
            final URL resource = getClass().getResource(resourcePath);

            if (resource != null) {
                final String id = imageConfig.getId();
                ImageProvider.registerResourceAsImage(id, resource);
            }
        }
    }
}
