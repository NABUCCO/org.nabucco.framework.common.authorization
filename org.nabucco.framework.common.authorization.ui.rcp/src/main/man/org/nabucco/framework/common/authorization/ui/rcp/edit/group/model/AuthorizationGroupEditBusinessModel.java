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
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.model;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.maintain.MaintainAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * AuthorizationGroupEditBusinessModel
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupEditBusinessModel implements BusinessModel {

    public static String ID = "org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditBusinessModel";

    /**
     * Save group.
     * 
     * @param authorizationGroup
     *            should be saved
     * @param parentAuthorizationGroup
     *            should be saved
     */
    public AuthorizationGroupMaintainMsg save(final AuthorizationGroup authorizationGroup,
            final AuthorizationGroup parentAuthorizationGroup) {
        MaintainAuthorizationDelegate maintainModel;
        try {
            maintainModel = AuthorizationComponentServiceDelegateFactory.getInstance()
                    .getMaintainAuthorization();

            final AuthorizationGroupMaintainMsg message = createAuthorizationGroupMaintainMsg(
                    authorizationGroup, parentAuthorizationGroup);

            return maintainModel.maintainAuthorizationGroup(message);
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    private AuthorizationGroupMaintainMsg createAuthorizationGroupMaintainMsg(
            final AuthorizationGroup authorizationGroup,
            final AuthorizationGroup parentAuthorizationGroup) {

        final AuthorizationGroupMaintainMsg message = new AuthorizationGroupMaintainMsg();

        message.setAuthorizationGroup(authorizationGroup);
        message.setParentAuthorizationGroup(parentAuthorizationGroup);
        return message;
    }
}
