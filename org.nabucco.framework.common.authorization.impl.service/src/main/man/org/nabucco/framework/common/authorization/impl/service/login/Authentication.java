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

import java.io.Serializable;

import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;

/**
 * Authentication
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public interface Authentication extends Serializable {

    /**
     * Authenticates a username and password against the given implementation.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * @param tenant
     *            the tenant
     * 
     * @throws LoginException
     *             when the user cannot be authenticated
     */
    void authenticate(UserId username, Password password, Tenant tenant) throws LoginException;

    /**
     * Setter for the persistence manager.
     * 
     * @param persistenceManager
     *            the entity manager
     */
    void setPersistenceManager(PersistenceManager persistenceManager);

}
