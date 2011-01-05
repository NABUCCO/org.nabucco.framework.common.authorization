/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
     * Missing description at method searchAuthorizationGroup.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationGroupListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationGroupListMsg> searchAuthorizationGroup(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException;

    /**
     * Missing description at method searchAuthorizationUser.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationUserListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationUserListMsg> searchAuthorizationUser(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException;

    /**
     * Missing description at method searchAuthorizationRole.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationRoleListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationRoleListMsg> searchAuthorizationRole(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException;

    /**
     * Missing description at method searchAuthorizationPermission.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationPermissionListMsg>.
     * @throws SearchException
     */
    ServiceResponse<AuthorizationPermissionListMsg> searchAuthorizationPermission(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException;
}
