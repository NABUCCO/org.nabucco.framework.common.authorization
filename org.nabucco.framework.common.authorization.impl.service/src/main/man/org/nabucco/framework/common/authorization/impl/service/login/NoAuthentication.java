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
package org.nabucco.framework.common.authorization.impl.service.login;

import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;

/**
 * NoAuthentication
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class NoAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance.
     */
    private static final NoAuthentication instance = new NoAuthentication();

    /**
     * Private constructor.
     */
    private NoAuthentication() {
    }

    /**
     * Singleton access.
     * 
     * @return the NoAuthentication instance.
     */
    public static NoAuthentication getInstance() {
        return instance;
    }

    @Override
    public void authenticate(UserId username, Password password, Tenant tenant) throws LoginException {
        // No authentication.
    }

    @Override
    public void setPersistenceManager(PersistenceManager persistenceManager) {
        // No persistence manager necessary!
    }

}
