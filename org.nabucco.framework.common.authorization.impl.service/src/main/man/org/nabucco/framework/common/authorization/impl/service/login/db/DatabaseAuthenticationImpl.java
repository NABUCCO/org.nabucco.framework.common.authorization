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
package org.nabucco.framework.common.authorization.impl.service.login.db;

import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.impl.service.util.EncryptionUtility;

/**
 * DatabaseAuthenticationImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class DatabaseAuthenticationImpl implements DatabaseAuthentication {

    private static final long serialVersionUID = 1L;

    /** The persistence manager */
    private PersistenceManager persistenceManager;

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(DatabaseAuthentication.class);

    @Override
    public void authenticate(UserId username, Password password, Tenant tenant) throws LoginException {

        String encrypted = EncryptionUtility.encrypt(password.getValue());

        if (!encrypted.equals(this.loadPassword(username))) {
            logger.warning("Password is not correct for user '", username.getValue(), "'.");
            throw new LoginException("Login failed. Username or password incorrect.");
        }

    }

    /**
     * Loads the user password from database.
     * 
     * @return the persistent and encrypted password
     * 
     * @throws LoginException
     */
    private String loadPassword(UserId userId) throws LoginException {

        try {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select p.password from AuthorizationUser u");
            queryString.append(" inner join u.password p");
            queryString.append(" where u.username = :username");

            NabuccoQuery<Password> query = this.persistenceManager.createQuery(queryString.toString());
            query.setParameter("username", userId);
            return query.getSingleResult().getValue();

        } catch (Exception e) {
            logger.warning(e, "Cannot authenticate '" + userId + "'. User is not persistent.");
            throw new LoginException("Login failed. Cannot login user with username '" + userId + "'.");
        }
    }

    @Override
    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

}
