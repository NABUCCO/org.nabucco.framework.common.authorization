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

import javax.persistence.Query;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.login.AuthenticationType;
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
        AuthenticationType loginType = this.request.getLoginType();

        Authentication authentication = AuthenticationFactory.getAuthentication(loginType);
        authentication.setEntityManager(super.getEntityManager());
        authentication.authenticate(username, password);

        AuthorizationUser user = this.loadUser();

        Subject subject = new Subject();
        subject.setUserId(username.getValue());
        subject.setUser(user);

        EncryptionUtility.generateSecurityToken(subject);

        return subject;
    }

    /**
     * Loads the user from the database.
     * 
     * @return the loaded user
     * 
     * @throws LoginException
     */
    private AuthorizationUser loadUser() throws LoginException {

        UserId userId = this.request.getUsername();

        try {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select u from AuthorizationUser u");
            queryString.append(" where u.username = :username");

            Query query = super.getEntityManager().createQuery(queryString.toString());
            query.setParameter("username", userId);
            AuthorizationUser user = (AuthorizationUser) query.getSingleResult();
            return user;
        } catch (Exception e) {
            super.getLogger().error(e,
                    "Error authenticating '" + userId + "' against NABUCCO Authorization.");
            throw new LoginException("Login failed. Cannot login user with username '"
                    + userId + "'.");
        }
    }

}
