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

import org.nabucco.common.extension.ExtensionException;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.extension.ExtensionPointType;
import org.nabucco.framework.base.facade.datatype.extension.ExtensionResolver;
import org.nabucco.framework.base.facade.datatype.extension.schema.authorization.authentication.AuthenticationExtension;
import org.nabucco.framework.base.facade.datatype.extension.schema.authorization.authentication.AuthenticationExtensionType;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.common.authorization.impl.service.login.db.DatabaseAuthenticationFactory;
import org.nabucco.framework.common.authorization.impl.service.login.ldap.LdapAuthenticationFactory;

/**
 * AuthenticationFactory
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public abstract class AuthenticationFactory {

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(AuthenticationFactory.class);

    /**
     * Returns the configured authentication mechanism.
     * 
     * @return the authentication mechanism.
     */
    protected abstract Authentication getAuthentication();

    /**
     * Retrieves the configured authentication mechanism.
     * 
     * @param tenant
     *            the tenant to resolve the authentication for
     * 
     * @return the authentication
     */
    public static Authentication getAuthentication(Tenant tenant) {

        AuthenticationExtensionType type = loadAuthenticationType(tenant);

        if (type == null) {
            logger.warning("NABUCCO Authentication is DISABLED!!!");
            return NoAuthentication.getInstance();
        }

        logger.info("Authentication is accomplished against '", type, "'.");

        Authentication authentication;

        switch (type) {

        case DATABASE:
            authentication = DatabaseAuthenticationFactory.getInstance().getAuthentication();
            break;

        case LDAP:
            authentication = LdapAuthenticationFactory.getInstance().getAuthentication();
            break;

        default:
            logger.warning("Configured Authentication '", type, "' is not supported. Disabling Authentication!!!");
            
            authentication = NoAuthentication.getInstance();
            break;

        }

        return authentication;
    }

    /**
     * Resolve the authentication type of this login.
     * 
     * @param tenant
     *            the tenant to resolve the authentication for
     * 
     * @return the configured authentication type
     */
    private static AuthenticationExtensionType loadAuthenticationType(Tenant tenant) {

        try {
            AuthenticationExtension extension = (AuthenticationExtension) NabuccoSystem.getExtensionResolver()
                    .resolveExtension(ExtensionPointType.ORG_NABUCCO_FRAMEWORK_AUTHORIZATION_AUTHENTICATION,
                            ExtensionResolver.DEFAULT_EXTENSION, tenant);

            return extension.getAuthenticationType();

        } catch (ExtensionException e) {
            logger.error(e, "Cannot resolve authentication extension.");
        }

        return null;
    }
}
