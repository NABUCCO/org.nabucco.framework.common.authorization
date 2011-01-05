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
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model;

import java.util.List;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.logging.Loggable;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * Edit Permission Model.
 * 
 * @author Michael Krausse, PRODYNA AG 1
 */
public class AuthorizationPermissionEditBusinessModel implements BusinessModel, Loggable {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditBusinessModel";

    /**
     * Save a user and updates user with data from server.
     * 
     * @param authorizationPermission
     *            should be saved
     * @param roleList
     *            the parent roles
     * @param userList
     *            the parent users
     * @param groupList
     *            the parent groups
     */
    public AuthorizationPermissionMaintainMsg save(AuthorizationPermission authorizationPermission,
            List<AuthorizationGroup> groupList, List<AuthorizationUser> userList,
            List<AuthorizationRole> roleList) {

        MaintainAuthorizationDelegate maintainModel;
        try {
            maintainModel = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getMaintainAuthorization();

            final AuthorizationPermissionMaintainMsg message = this
                    .createAuthorizationPermissionMaintainMsg(authorizationPermission);

            message.getAuthorizationGroupList().addAll(groupList);
            message.getAuthorizationUserList().addAll(userList);
            message.getAuthorizationRoleList().addAll(roleList);

            return maintainModel.maintainAuthorizationPermission(message);
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    private AuthorizationPermissionMaintainMsg createAuthorizationPermissionMaintainMsg(
            final AuthorizationPermission authorizationPermission) {

        final AuthorizationPermissionMaintainMsg message = new AuthorizationPermissionMaintainMsg();

        message.setAuthorizationPermission(authorizationPermission);

        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return AuthorizationPermissionEditBusinessModel.ID;
    }
}
