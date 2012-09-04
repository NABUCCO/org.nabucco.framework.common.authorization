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
package org.nabucco.framework.common.authorization.ui.web.action.group;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.client.action.ActionException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.ui.web.action.handler.OpenEditorActionHandler;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.produce.ProduceAuthorizationDelegate;

/**
 * CreateNewGroupAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class CreateNewGroupAction extends OpenEditorActionHandler<AuthorizationGroup> {

    private static final String EDITOR_ID = "AuthorizationGroupEditor";

    @Override
    protected String getEditorId(WebActionParameter parameter, AuthorizationGroup datatype) throws ClientException {
        return EDITOR_ID;
    }

    @Override
    protected AuthorizationGroup loadDatatype(Long id, WebActionParameter parameter) throws ClientException {

        ProduceAuthorizationDelegate produceService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getProduceAuthorization();

        EmptyServiceMessage rq = new EmptyServiceMessage();

        try {
            AuthorizationGroupMsg rs = produceService.produceAuthorizationGroup(rq, parameter.getSession());
            return rs.getAuthorizationGroup();
        } catch (ProduceException e) {
            throw new ActionException("Error producing new Authorization Group.", e);
        }
    }

}
