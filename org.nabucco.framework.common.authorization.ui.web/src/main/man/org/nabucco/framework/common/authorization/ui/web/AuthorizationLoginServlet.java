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
package org.nabucco.framework.common.authorization.ui.web;

import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.User;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.datatype.ui.login.LoginRequest;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.security.InvalidLoginException;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.ui.web.servlet.login.LoginServlet;
import org.nabucco.framework.base.ui.web.session.NabuccoWebSession;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationServiceDelegate;
import org.nabucco.framework.common.authorization.ui.web.communication.login.LoginDelegate;

/**
 * AuthorizationLoginServlet
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationLoginServlet extends LoginServlet {

    private static final long serialVersionUID = 1L;

    /** Logger */
    private NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(AuthorizationLoginServlet.class);

    @Override
    protected void login(LoginRequest request, NabuccoWebSession session) throws InvalidLoginException, ClientException {
        this.validateRequest(request);

        this.authenticate(request.getUsername(), request.getPassword(), request.getTenant(), session);

        this.loadInformation(session);
    }

    /**
     * Authenticate the user against the database.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * @param tenant
     *            the tenant
     * @param session
     *            the current web session
     * 
     * @throws ClientException
     *             when an unexpected exception occurs
     * @throws InvalidLoginException
     *             when the user is not authorized
     */
    private void authenticate(UserId username, Password password, Tenant tenant, NabuccoWebSession session)
            throws ClientException, InvalidLoginException {

        if (tenant == null) {
            tenant = new Tenant();
        }

        try {
            LoginMsg rq = new LoginMsg();
            rq.setUsername(username);
            rq.setPassword(password);
            rq.setTenant(tenant);

            LoginDelegate loginService = AuthorizationComponentServiceDelegateFactory.getInstance().getLogin();
            LoginRs rs = loginService.login(rq, session);

            if (rs == null || rs.getSubject() == null || rs.getSubject().getUser() == null) {
                throw new InvalidLoginException("Login of user '" + username + "' [" + tenant + "] failed!");
            }

            session.getSecurityContext().setSubject(rs.getSubject());

        } catch (ClientException ce) {
            logger.error(ce, "Error authenticating user '", username, "' [", tenant, "].");
            throw ce;
        } catch (InvalidLoginException ile) {
            logger.error(ile, "Error authenticating user '", username, "' [", tenant, "].");
            throw ile;
        } catch (LoginException le) {
            logger.error(le, "User '", username, "' [", tenant, "] is not authorized.");
            throw new InvalidLoginException(le);
        } catch (Exception e) {
            logger.error(e, "Error authenticating user '", username, "' [", tenant, "].");
            throw new ClientException(e);
        }
    }

    /**
     * Load the authorization information for the given user.
     * 
     * @param user
     *            the user to load the information for
     * @param session
     *            the web session
     * 
     * @throws ClientException
     *             when the information cannot be retrieved
     */
    private void loadInformation(NabuccoWebSession session) throws ClientException {
        if (!session.getSecurityContext().isAuthenticated()) {
            logger.error("Cannot retrieve authorization information after invalid login! SecurityContext is not authenticated.");
            throw new ClientException("Login was not successful.");
        }

        Subject subject = session.getSecurityContext().getSubject();
        User user = subject.getUser();

        if (user.getUsername() == null || user.getUsername().getValue() == null) {
            throw new ClientException("Cannot retrieve authorization information from user 'null'.");
        }

        String username = user.getUsername().getValue();

        try {
            AuthorizationServiceDelegate authorizationService = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getAuthorizationService();

            UserRq rq = new UserRq();
            rq.setUserId(new UserId(username));
            AuthorizationInformationRs rs = authorizationService.getInformation(rq, session);

            session.getSecurityContext().getGroupList().addAll(rs.getGroupList());
            session.getSecurityContext().getRoleList().addAll(rs.getRoleList());
            session.getSecurityContext().getPermissionList().addAll(rs.getPermissionList());

        } catch (ClientException ce) {
            logger.error(ce, "Error retrieving authorization information for user '", username, "'.");
            throw ce;
        } catch (Exception e) {
            logger.error(e, "Error retrieving authorization information for user '", username, "'.");
            throw new ClientException("Error retrieving authorization information for user '" + username + "'.", e);
        }
    }

    /**
     * Validate the login request.
     * 
     * @param request
     *            the JSON request
     * 
     * @throws InvalidLoginException
     *             when the request parameter is not valid
     */
    private void validateRequest(LoginRequest request) throws InvalidLoginException {
        if (request == null) {
            throw new InvalidLoginException("Login Data is not valid.");
        }

        if (request.getUsername() == null || request.getUsername().getValue() == null) {
            throw new InvalidLoginException("User 'null' is not valid.");
        }

        if (request.getPassword() == null || request.getPassword().getValue() == null) {
            throw new InvalidLoginException("Password 'null' is not valid.");
        }

        if (request.getTenant() == null || request.getTenant().getValue() == null) {
            throw new InvalidLoginException("Tenant 'null' is not valid.");
        }
    }

}
