/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.common.authorization.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorization<p/>Authorization search service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public interface SearchAuthorization extends Service {

    /**
     * Search for a list of authorization group in the database.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationGroupListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationGroupListMsg> searchAuthorizationGroup(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException;

    /**
     * Search for a list of authorization users in the database.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationUserListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationUserListMsg> searchAuthorizationUser(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException;

    /**
     * Search for a list of authorization roles in the database.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationRoleListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationRoleListMsg> searchAuthorizationRole(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException;

    /**
     * Search for a list of authorization permissions in the database.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationPermissionListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationPermissionListMsg> searchAuthorizationPermission(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException;
}
