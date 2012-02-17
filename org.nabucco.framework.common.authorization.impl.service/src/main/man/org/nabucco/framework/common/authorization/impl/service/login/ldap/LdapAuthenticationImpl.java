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
package org.nabucco.framework.common.authorization.impl.service.login.ldap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.nabucco.common.extension.ExtensionException;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.extension.ExtensionPointType;
import org.nabucco.framework.base.facade.datatype.extension.ExtensionResolver;
import org.nabucco.framework.base.facade.datatype.extension.NabuccoExtensionComposite;
import org.nabucco.framework.base.facade.datatype.extension.property.PropertyLoader;
import org.nabucco.framework.base.facade.datatype.extension.property.StringProperty;
import org.nabucco.framework.base.facade.datatype.extension.schema.authorization.authentication.LdapAuthenticationExtension;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;

/**
 * LdapAuthenticationImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class LdapAuthenticationImpl implements LdapAuthentication {

    private static final long serialVersionUID = 1L;

    /** The default base DN */
    private static final String DEFAULT_BASE_DN = "";

    /** The default object filter value. */
    private static final String DEFAULT_OBJECT_FILTER = "";

    /** The logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(LdapAuthentication.class);

    @Override
    public void authenticate(UserId username, Password password, Tenant tenant) throws LoginException {

        try {
            LdapAuthenticationExtension ldapConfiguration = this.loadConfiguration(tenant);

            LdapContext context = this.createContext(ldapConfiguration);

            String userDn = this.distinguishName(username.getValue(), context, ldapConfiguration);

            context.addToEnvironment(Context.SECURITY_PRINCIPAL, userDn);
            context.addToEnvironment(Context.SECURITY_CREDENTIALS, password.getValue());

            try {
                // Authentication
                context.lookup(userDn);
            } catch (NamingException ne) {
                logger.warning("Cannot authenticate '", username.getValue(), "' against LDAP.");
                throw new LoginException("Login failed. Cannot login user with username '" + username + "'.", ne);
            }

        } catch (ExtensionException ee) {
            logger.error(ee, "Error resolving LDAP Authentication Extension.");
            throw new LoginException("Error resolving LDAP Authentication Extension.", ee);
        } catch (NamingException ne) {
            logger.error(ne, "Cannot establish LDAP connection.");
            throw new LoginException("Cannot establish LDAP connection.", ne);
        } catch (Exception e) {
            logger.error(e, "Error authenticating '" + username.getValue() + "' against LDAP.");
            throw new LoginException("Login failed. Cannot login user with username '" + username + "'.");
        }
    }

    /**
     * Loads the LDAP authentication configuration from extension point
     * <tt>org.nabucco.framework.authorization.authentication</tt>.
     * 
     * @param tenant
     *            the users tenant
     * 
     * @return the LDAP authentication configuration
     * 
     * @throws IOException
     *             if the ldap.properties file cannot be found
     */
    private LdapAuthenticationExtension loadConfiguration(Tenant tenant) throws ExtensionException {
        ExtensionResolver resolver = NabuccoSystem.getExtensionResolver();
        ExtensionPointType extensionPoint = ExtensionPointType.ORG_NABUCCO_FRAMEWORK_AUTHORIZATION_AUTHENTICATION;
        String extensionName = ExtensionResolver.DEFAULT_EXTENSION;

        NabuccoExtensionComposite extension = resolver.resolveExtension(extensionPoint, extensionName, tenant);

        return (LdapAuthenticationExtension) extension;
    }

    /**
     * Create context for LDAP connection depending on the ldap.properties file.
     * 
     * @param extension
     *            the LDAP connection properties
     * 
     * @throws NamingException
     *             if the LDAP authentification failed
     */
    private LdapContext createContext(LdapAuthenticationExtension configuration) throws NamingException {

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, this.getFactoryName(configuration));
        properties.put(Context.PROVIDER_URL, this.getUrl(configuration));

        properties.put(Context.SECURITY_AUTHENTICATION, PropertyLoader.loadProperty(configuration.getSecurityType()));
        properties.put(Context.SECURITY_PROTOCOL, PropertyLoader.loadProperty(configuration.getSecurityProtocol()));
        properties.put(Context.SECURITY_PRINCIPAL, PropertyLoader.loadProperty(configuration.getSecurityPrincipal()));
        properties.put(Context.SECURITY_CREDENTIALS,
                PropertyLoader.loadProperty(configuration.getSecurityCredentials()));

        return new InitialLdapContext(properties, null);
    }

    /**
     * Resolve the LDAP factory name.
     * 
     * @param configuration
     *            the LDAP authentication configuration
     * 
     * @return the factory class name
     */
    private String getFactoryName(LdapAuthenticationExtension configuration) {
        LdapFactoryType type = PropertyLoader.loadProperty(LdapFactoryType.class, configuration.getFactory());
        if (type != null) {
            return type.getImplName();
        }
        return LdapFactoryType.SUN.getImplName();
    }

    /**
     * Resolve the LDAP url.
     * 
     * @param configuration
     *            the LDAP authentication configuration
     * 
     * @return the directory url
     */
    private String getUrl(LdapAuthenticationExtension configuration) {
        String url = PropertyLoader.loadProperty(configuration.getUrl());
        if (url == null || url.isEmpty()) {
            logger.warning("No valid LDAP URL configured 'null'.");
        } else {
            logger.info("Authenticating against LDAP '", url, "'.");
        }
        return url;
    }

    /**
     * Distinguishes the name from the LDAP.
     * 
     * @param username
     *            the username
     * @param context
     *            the LDAP directory context
     * @param configuration
     *            the LDAP configuration
     * 
     * @return the distuinguished name
     * 
     * @throws NamingException
     */
    private String distinguishName(String username, LdapContext context, LdapAuthenticationExtension configuration)
            throws NamingException {

        String filter = this.getObjectFilter(configuration);
        String baseDn = this.getBaseDn(configuration);
        String[] returnAttributes = this.getReturnAttributes(configuration);

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(returnAttributes);

        NamingEnumeration<SearchResult> resultList = context.search(baseDn, filter, new String[] { username },
                searchControls);

        if (resultList.hasMore()) {
            SearchResult result = resultList.next();
            return result.getNameInNamespace();
        }

        return null;
    }

    /**
     * Extracts the base distinguished name from the LDAP configuration.
     * 
     * @param configuration
     *            the LDAP configuration
     * 
     * @return the Base DN
     */
    private String getBaseDn(LdapAuthenticationExtension configuration) {
        String baseDn = PropertyLoader.loadProperty(configuration.getBaseDn());
        if (baseDn == null) {
            return DEFAULT_BASE_DN;
        }
        return baseDn;
    }

    /**
     * Extracts the object filter from the LDAP configuration.
     * 
     * @param configuration
     *            the LDAP configuration
     * 
     * @return the object filter
     */
    private String getObjectFilter(LdapAuthenticationExtension configuration) {
        String objectFilter = PropertyLoader.loadProperty(configuration.getObjectFilter());
        if (objectFilter == null) {
            return DEFAULT_OBJECT_FILTER;
        }
        return objectFilter;
    }

    /**
     * Extracts the return attributes from the LDAP configuration.
     * 
     * @param configuration
     *            the LDAP configuration
     * 
     * @return the return attributes
     */
    private String[] getReturnAttributes(LdapAuthenticationExtension configuration) {
        List<String> returnAttributes = new ArrayList<String>();
        for (StringProperty attribute : configuration.getReturnAttributes()) {
            String returnAttribute = PropertyLoader.loadProperty(attribute);
            returnAttributes.add(returnAttribute);
        }
        return returnAttributes.toArray(new String[returnAttributes.size()]);
    }

    @Override
    public void setPersistenceManager(PersistenceManager persistenceManager) {
        // No persistence manager necessary!
    }

}
