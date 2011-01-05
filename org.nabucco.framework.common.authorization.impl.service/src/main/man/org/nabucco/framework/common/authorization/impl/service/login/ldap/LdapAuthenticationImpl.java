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
package org.nabucco.framework.common.authorization.impl.service.login.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.persistence.EntityManager;

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;

/**
 * LdapService
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class LdapAuthenticationImpl implements LdapAuthentication {

    private static final long serialVersionUID = 1L;

    /** The ldap.properties file */
    private static final String LDAP_PROPERTIES = "/env/ldap.properties";

    /** The active flag property */
    private static final String PROPERTY_ACTIVE = "ldap.active";

    /** The base DN property name */
    private static final String PROPERTY_BASE_DN = "ldap.basedn";

    /** The base DN property return attributes */
    private static final String PROPERTY_RETURN = "ldap.return";

    /** The default base DN */
    private static final String DEFAULT_BASE_DN = "";

    /** The default return attributes */
    private static final String DEFAULT_RETURN = "";

    /** The object filter property name */
    private static final String PROPERTY_OBJECT_FILTER = "ldap.objectfilter";

    /** The default object filter value. */
    private static final String DEFAULT_OBJECT_FILTER = "";

    /** Regex for splitting the return attributes */
    private static final String RETURN_SPLIT_REGEX = "/";

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            LdapAuthentication.class);

    @Override
    public void authenticate(UserId username, Password password) throws LoginException {

        try {
            final Properties ldapProperties = this.loadProperties();

            if (this.isActive(ldapProperties)) {
                final LdapContext context = this.createContext(ldapProperties);

                final String userDn = this.distinguishName(username.getValue(), context,
                        ldapProperties);

                context.addToEnvironment(Context.SECURITY_PRINCIPAL, userDn);
                context.addToEnvironment(Context.SECURITY_CREDENTIALS, password.getValue());

                // Authentication
                context.lookup(userDn);
            } else {
                logger.info("LDAP Authentication is deactivated.");
            }

        } catch (final Exception e) {
            logger.warning(e, "Error authenticating '" + username.getValue() + "' against LDAP.");
            throw new LoginException("Login failed. Cannot login user with username '"
                    + username + "'.");
        }
    }

    /**
     * Checks whether the LDAP authentication is activated or not.
     * 
     * @param properties
     *            the LDAP properties to validate
     * 
     * @return <b>true</b> if the LDAP is activated, <b>falsse</b> if not
     */
    private boolean isActive(Properties properties) {
        if (properties == null) {
            return false;
        }
        return Boolean.valueOf(properties.getProperty(PROPERTY_ACTIVE));
    }

    /**
     * Loads the LDAP properties from ldap.properties file.
     * 
     * @return the LDAP properties
     * 
     * @throws IOException
     *             if the ldap.properties file cannot be found
     */
    private Properties loadProperties() throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(LDAP_PROPERTIES);

        if (inputStream == null) {
            logger.warning("Cannot load file '" + LDAP_PROPERTIES + "'.");
            return null;
        }

        final Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    /**
     * Create context for LDAP connection depending on the ldap.properties file.
     * 
     * @param properties
     *            the LDAP connection properties
     * 
     * @throws NamingException
     *             if the LDAP authentification failed
     */
    private LdapContext createContext(Properties properties) throws NamingException {
        return new InitialLdapContext(properties, null);
    }

    /**
     * Distinguishes the name from the LDAP.
     * 
     * @param username
     *            the username
     * @param context
     *            the LDAP directory context
     * @param ldapProperties
     *            the LDAP properties
     * 
     * @return the distuinguished name
     * 
     * @throws NamingException
     */
    private String distinguishName(String username, LdapContext context, Properties ldapProperties)
            throws NamingException {

        final String filter = this.getObjectFilter(ldapProperties);
        final String baseDn = this.getBaseDn(ldapProperties);
        final String[] returnAttributes = this.getReturn(ldapProperties);

        final SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(returnAttributes);

        final NamingEnumeration<SearchResult> resultList = context.search(baseDn, filter,
                new String[] { username }, searchControls);

        if (resultList.hasMore()) {
            final SearchResult result = resultList.next();
            return result.getNameInNamespace();
        }

        return null;
    }

    /**
     * Extracts the return attribute from the LDAP properties.
     * 
     * @param properties
     *            the LDAP properties
     * 
     * @return the return attribute
     */
    private String[] getReturn(Properties properties) {
        String returnAttribute = properties.getProperty(PROPERTY_RETURN, DEFAULT_RETURN);

        if (returnAttribute == null || returnAttribute.isEmpty()) {
            return new String[0];
        }

        return returnAttribute.split(RETURN_SPLIT_REGEX);
    }

    /**
     * Extracts the base distinguished name from the LDAP properties.
     * 
     * @param properties
     *            the LDAP properties
     * 
     * @return the Base DN
     */
    private String getBaseDn(Properties properties) {
        return properties.getProperty(PROPERTY_BASE_DN, DEFAULT_BASE_DN);
    }

    /**
     * Extracts the object filter from the LDAP properties.
     * 
     * @param properties
     *            the LDAP properties
     * 
     * @return the Base DN
     */
    private String getObjectFilter(Properties properties) {
        return properties.getProperty(PROPERTY_OBJECT_FILTER, DEFAULT_OBJECT_FILTER);
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        // No entity manager necessary!
    }

}
