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
package org.nabucco.framework.common.authorization.ui.rcp.resolve.group.model;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.resolve.ResolveAuthorizationDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * AuthorizationGroupResolveBusinessModel
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationGroupResolveBusinessModel implements BusinessModel {

    public final static String ID = "org.nabucco.framework.common.authorization.ui.rcp.resolve.group.model.AuthorizationGroupResolveBusinessModel";

    /**
     * Resolves (eager loads) an authorization group
     * 
     * @param authorizationGroup
     *            should be eager loaded.
     * @return eager loaded authorization group
     */
    public AuthorizationGroup resolve(final AuthorizationGroup authorizationGroup) {
        AuthorizationGroup result = null;

        try {
            final ResolveAuthorizationDelegate resolveDelegate = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getResolveAuthorization();

            final AuthorizationGroupMsg msg = createAuthorizationGroupMsg(authorizationGroup);
            final AuthorizationGroupMaintainMsg response = resolveDelegate
                    .resolveAuthorizationGroup(msg);

            result = response.getAuthorizationGroup();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }

        return result;
    }

    private AuthorizationGroupMsg createAuthorizationGroupMsg(
            final AuthorizationGroup authorizationGroup) {

        final AuthorizationGroupMsg result = new AuthorizationGroupMsg();
        result.setAuthorizationGroup(authorizationGroup);
        return result;
    }

}
