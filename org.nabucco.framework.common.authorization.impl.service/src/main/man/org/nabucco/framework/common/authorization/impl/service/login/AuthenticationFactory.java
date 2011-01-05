/*
 * Copyright 2010 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco-source.org/nabucco-license.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.impl.service.login;

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.common.authorization.facade.datatype.login.AuthenticationType;
import org.nabucco.framework.common.authorization.impl.service.login.db.DatabaseAuthenticationFactory;
import org.nabucco.framework.common.authorization.impl.service.login.ldap.LdapAuthenticationFactory;

/**
 * AuthenticationFactory
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public abstract class AuthenticationFactory {

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            AuthenticationFactory.class);

    /**
     * Returns the configured authentication mechanism.
     * 
     * @return the authentication mechanism.
     */
    protected abstract Authentication getAuthentication();

    /**
     * Retrieves the configured authentication mechanism.
     * 
     * @return the authentication
     */
    public static Authentication getAuthentication(AuthenticationType type) {

        logger.info("Authentication by " + type, ".");

        if (type == null) {
            return NoAuthentication.getInstance();
        }
        
        Authentication authentication;

        switch (type) {

        case DATABASE:
            authentication = DatabaseAuthenticationFactory.getInstance().getAuthentication();
            break;

        case LDAP:
            authentication = LdapAuthenticationFactory.getInstance().getAuthentication();
            break;

        case NONE:
            authentication = NoAuthentication.getInstance();
            break;

        default:
            authentication = LdapAuthenticationFactory.getInstance().getAuthentication();
            break;

        }

        return authentication;
    }
}
