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
package org.nabucco.framework.common.authorization.facade.service;

import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;

/**
 * AuthorizationService<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public interface AuthorizationService extends Service {

    /**
     * Missing description at method hasPermission.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionMsg>.
     * @return the ServiceResponse<AuthorizationRs>.
     * @throws AuthorizationException
     */
    ServiceResponse<AuthorizationRs> hasPermission(ServiceRequest<AuthorizationPermissionMsg> rq)
            throws AuthorizationException;

    /**
     * Missing description at method hasPermissionByName.
     *
     * @param rq the ServiceRequest<AuthorizationNameMsg>.
     * @return the ServiceResponse<AuthorizationRs>.
     * @throws AuthorizationException
     */
    ServiceResponse<AuthorizationRs> hasPermissionByName(ServiceRequest<AuthorizationNameMsg> rq)
            throws AuthorizationException;

    /**
     * Missing description at method getInformation.
     *
     * @param rq the ServiceRequest<UserRq>.
     * @return the ServiceResponse<AuthorizationInformationRs>.
     * @throws AuthorizationException
     */
    ServiceResponse<AuthorizationInformationRs> getInformation(ServiceRequest<UserRq> rq) throws AuthorizationException;
}
