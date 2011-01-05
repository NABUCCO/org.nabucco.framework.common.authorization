/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.service;

import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
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
}
