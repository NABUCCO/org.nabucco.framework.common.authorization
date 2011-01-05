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
package org.nabucco.framework.common.authorization.ui.rcp.login.model;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.common.authorization.facade.datatype.login.AuthenticationType;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
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
     * Logs a user into the system.
     * 
     * @param userName
     *            the username
     * @param password
     *            the password
     * 
     * @return logged in subject OR null if login failed
     * @throws LoginException
     */
    public Subject login(final String userName, final String password) {

        try {
            LoginDelegate loginService = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getLogin();

            LoginMsg request = this.createLoginMsg(userName, password);
            LoginRs response = loginService.login(request);

            return response.getSubject();

        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }

        return null;
    }

    /**
     * Creates the login request message.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * 
     * @return the login request message
     */
    private LoginMsg createLoginMsg(String username, String password) {
        LoginMsg msg = new LoginMsg();
        msg.setUsername(new UserId(username));
        msg.setPassword(new Password(password));
        msg.setLoginType(AuthenticationType.DATABASE);
        return msg;
    }
}
