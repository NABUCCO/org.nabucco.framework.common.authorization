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
package org.nabucco.framework.common.authorization.ui.web.action.role;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.client.action.ActionException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.ui.web.action.handler.OpenEditorActionHandler;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.resolve.ResolveAuthorizationDelegate;

/**
 * OpenRoleAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenRoleAction extends OpenEditorActionHandler<AuthorizationRole> {

    private static final String EDITOR_ID = "AuthorizationRoleEditor";

    @Override
    protected String getEditorId(WebActionParameter parameter) throws ClientException {
        return EDITOR_ID;
    }

    @Override
    protected AuthorizationRole loadDatatype(Long id, WebActionParameter parameter) throws ClientException {

        if (id == null) {
            return null;
        }

        ResolveAuthorizationDelegate resolveService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getResolveAuthorization();

        AuthorizationRoleMsg rq = new AuthorizationRoleMsg();
        AuthorizationRole role = new AuthorizationRole();
        role.setId(id);

        rq.setAuthorizationRole(role);

        try {
            AuthorizationRoleMaintainMsg rs = resolveService.resolveAuthorizationRole(rq, parameter.getSession());
            return rs.getAuthorizationRole();
        } catch (ResolveException e) {
            throw new ActionException("Error resolving Authorization Role.", e);
        }
    }

}
