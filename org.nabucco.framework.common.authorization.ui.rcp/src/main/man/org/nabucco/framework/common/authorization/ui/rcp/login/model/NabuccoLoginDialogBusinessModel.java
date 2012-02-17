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
package org.nabucco.framework.common.authorization.ui.rcp.login.model;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.datatype.session.authorization.SecurityContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationServiceDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.communication.login.LoginDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * NabuccoLoginDialogBusinessModel
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogBusinessModel implements BusinessModel {

    public static String ID = "org.nabucco.framework.plugin.base.dialog.login.model.NabuccoLoginDialogBusinessModel";

    /**
     * Load the user authorization information (groups, roles, permissions).
     * 
     * @param userId
     *            the user id
     * 
     * @return the authorization information
     */
    public SecurityContext login(String username, String password) {

        SecurityContext context = new SecurityContext();

        try {
            Subject subject = this.authenticate(username, password);

            AuthorizationServiceDelegate authorizationService = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getAuthorizationService();

            UserRq rq = new UserRq();
            rq.setUserId(new UserId(username));

            AuthorizationInformationRs rs = authorizationService.getInformation(rq);

            context.setSubject(subject);
            context.getGroupList().addAll(rs.getGroupList());
            context.getRoleList().addAll(rs.getRoleList());
            context.getPermissionList().addAll(rs.getPermissionList());

        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }

        return context;
    }

    /**
     * Authenticate the user against the database.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * 
     * @throws ClientException
     *             when the authentication fails
     */
    private Subject authenticate(String username, String password) throws ClientException {
        LoginDelegate loginService = AuthorizationComponentServiceDelegateFactory.getInstance().getLogin();

        LoginMsg rq = new LoginMsg();
        rq.setUsername(new UserId(username));
        rq.setPassword(new Password(password));
        LoginRs rs = loginService.login(rq);

        return rs.getSubject();
    }

}
