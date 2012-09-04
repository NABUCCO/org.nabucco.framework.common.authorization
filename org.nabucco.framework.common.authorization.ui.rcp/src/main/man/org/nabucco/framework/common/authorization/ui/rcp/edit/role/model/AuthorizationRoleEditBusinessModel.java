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
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.model;

import java.util.Set;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * AuthorizationRoleEditBusinessModel
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationRoleEditBusinessModel implements BusinessModel {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditBusinessModel";

    /**
     * Save a Role
     * 
     * @param authorizationRole
     *            should be saved
     * @param groupList
     *            relating authorization groups to save
     * @param userList
     *            relating authorization users to save
     * 
     * @throws ClientException
     *             when save fails
     */
    public AuthorizationRoleMaintainMsg save(AuthorizationRole authorizationRole, Set<AuthorizationGroup> groupList,
            Set<AuthorizationUser> userList) throws ClientException {

        AuthorizationRoleMaintainMsg message = new AuthorizationRoleMaintainMsg();
        message.setAuthorizationRole(authorizationRole);
        if (groupList != null) {
            message.getAuthorizationGroupList().addAll(groupList);
        }
        if (userList != null) {
            message.getAuthorizationUserList().addAll(userList);
        }

        MaintainAuthorizationDelegate maintainAuthorization = AuthorizationComponentServiceDelegateFactory
                .getInstance().getMaintainAuthorization();

        return maintainAuthorization.maintainAuthorizationRole(message);
    }

}
