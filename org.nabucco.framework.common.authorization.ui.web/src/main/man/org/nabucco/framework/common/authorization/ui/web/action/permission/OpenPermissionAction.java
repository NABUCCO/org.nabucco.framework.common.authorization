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
package org.nabucco.framework.common.authorization.ui.web.action.permission;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.client.action.ActionException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.ui.web.action.handler.OpenEditorActionHandler;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.resolve.ResolveAuthorizationDelegate;

/**
 * OpenPermissionAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenPermissionAction extends OpenEditorActionHandler<AuthorizationPermission> {

    private static final String EDITOR_ID = "AuthorizationPermissionEditor";

    @Override
    protected String getEditorId(WebActionParameter arg0) throws ClientException {
        return EDITOR_ID;
    }

    @Override
    protected AuthorizationPermission loadDatatype(Long id, WebActionParameter parameter) throws ClientException {
        if (id == null) {
            return null;
        }

        ResolveAuthorizationDelegate resolveService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getResolveAuthorization();

        AuthorizationPermissionMsg rq = new AuthorizationPermissionMsg();
        AuthorizationPermission permission = new AuthorizationPermission();
        permission.setId(id);

        rq.setAuthorizationPermission(permission);

        try {
            AuthorizationPermissionMaintainMsg rs = resolveService.resolveAuthorizationPermission(rq, parameter.getSession());
            return rs.getAuthorizationPermission();
        } catch (ResolveException e) {
            throw new ActionException("Error resolving Authorization Permission.", e);
        }
    }

}
