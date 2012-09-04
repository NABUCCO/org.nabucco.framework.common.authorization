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

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.impl.service.util.EncryptionUtility;

/**
 * LoginServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class LoginServiceHandlerImpl extends LoginServiceHandler {

    private static final long serialVersionUID = 1L;

    private LoginMsg request;

    /** Logger */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(LoginServiceHandlerImpl.class);
    
    @Override
    public LoginRs login(LoginMsg msg) throws LoginException {

        this.request = msg;

        this.validate();

        Subject subject = this.login();

        super.getLogger().info("Login successful: " + msg.getUsername().getValue());

        LoginRs response = new LoginRs();
        response.setSubject(subject);

        return response;
    }

    /**
     * Validates the request message.
     * 
     * @throws LoginException
     *             if username or password are incorrect.
     */
    private void validate() throws LoginException {
        if (this.request.getUsername() == null || this.request.getUsername().getValue() == null) {
            super.getLogger().debug("Username is not valid 'null'.");
            throw new LoginException("Login failed. Username or password incorrect.");
        }

        if (this.request.getPassword() == null || this.request.getPassword().getValue() == null) {
            super.getLogger().debug("Password is not valid 'null'.");
            throw new LoginException("Login failed. Username or password incorrect.");
        }
    }

    /**
     * Authenticates the user and creates an authorization token.
     * 
     * @return the authentication subject
     * 
     * @throws LoginException
     */
    private Subject login() throws LoginException {

        UserId username = this.request.getUsername();
        Password password = this.request.getPassword();
        Tenant tenant = this.request.getTenant();

        Authentication authentication = AuthenticationFactory.getAuthentication(tenant);
        authentication.setPersistenceManager(super.getPersistenceManager());
        authentication.authenticate(username, password, tenant);

        AuthorizationUser user = this.loadUser();

        Subject subject = new Subject();
        subject.setOwner(NabuccoInstance.getInstance().getOwner());

        if (user.getTenant() != null) {
            subject.setTenant(user.getTenant());
        } else {
            subject.setTenant(new Tenant());
        }

        subject.setUserId(username.getValue());
        subject.setUser(user);
        subject.setLoginTime(NabuccoSystem.getCurrentTimestamp());

        EncryptionUtility.generateSecurityToken(subject);

        return subject;
    }

    /**
     * Loads the user from the database.
     * 
     * @return the loaded user
     * 
     * @throws LoginException
     *             when the user does not exist
     */
    private AuthorizationUser loadUser() throws LoginException {

        UserId userId = this.request.getUsername();

        if (this.request.getTenant() == null) {
            this.request.setTenant(new Tenant());
            logger.warning("No Tenant defined, reset to default Tenant '", this.request.getTenant(), "'.");
        }

        String tenant = this.request.getTenant().getValue();

        try {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select u from AuthorizationUser u");
            queryString.append(" where u.username = :username");
            queryString.append(" and lower(u.tenant.value) = lower(:tenant)");

            NabuccoQuery<AuthorizationUser> query = super.getPersistenceManager().createQuery(queryString.toString());
            query.setParameter(AuthorizationUser.USERNAME, userId);
            query.setParameter(AuthorizationUser.TENANT, tenant);

            AuthorizationUser user = query.getSingleResult();
            return user;
        } catch (Exception e) {
            super.getLogger().error(e, "Error authenticating '" + userId + "' against NABUCCO Authorization.");
            throw new LoginException("Login failed. Cannot login user with username '" + userId + "'.");
        }
    }

}
