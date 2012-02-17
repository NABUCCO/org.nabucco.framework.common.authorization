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
package org.nabucco.framework.common.authorization.facade.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;

/**
 * ProduceAuthorization<p/>Authorization produce service<p/>
 *
 * @version 1.0
 * @author Stefanie Feld, PRODYNA AG, 2010-03-29
 */
public interface ProduceAuthorization extends Service {

    /**
     * Creates a new AuthorizationGroup.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<AuthorizationGroupMsg>.
     * @throws ProduceException
     */
    ServiceResponse<AuthorizationGroupMsg> produceAuthorizationGroup(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Creates a new AuthorizationUser.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<AuthorizationUserMsg>.
     * @throws ProduceException
     */
    ServiceResponse<AuthorizationUserMsg> produceAuthorizationUser(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Creates a new AuthorizationRole.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<AuthorizationRoleMsg>.
     * @throws ProduceException
     */
    ServiceResponse<AuthorizationRoleMsg> produceAuthorizationRole(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;

    /**
     * Creates a new AuthorizationPermission.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<AuthorizationPermissionMsg>.
     * @throws ProduceException
     */
    ServiceResponse<AuthorizationPermissionMsg> produceAuthorizationPermission(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException;
}
