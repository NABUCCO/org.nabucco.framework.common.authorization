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
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.model;

import java.util.Set;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * AuthorizationUserEditBusinessModel
 * 
 * @author Michael Krausse, PRODYNA AG
 * 
 */
public class AuthorizationUserEditBusinessModel implements BusinessModel, Loggable {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditBusinessModel";

    /**
     * Save a user and updates user with data from server.
     * 
     * @param user
     *            should be saved.
     * @param groupSet
     *            should be saved
     * 
     * @throws ClientException
     *             when the save fails
     */
    public AuthorizationUserMaintainMsg save(AuthorizationUser user, Set<AuthorizationGroup> groupSet)
            throws ClientException {

        AuthorizationUserMaintainMsg message = new AuthorizationUserMaintainMsg();
        message.setAuthorizationUser(user);
        if (groupSet != null) {
            message.getAuthorizationGroupList().addAll(groupSet);
        }

        MaintainAuthorizationDelegate maintainAuthorization = AuthorizationComponentServiceDelegateFactory
                .getInstance().getMaintainAuthorization();

        return maintainAuthorization.maintainAuthorizationUser(message);
    }

    @Override
    public String getID() {
        return "adduserModel";
    }
}
