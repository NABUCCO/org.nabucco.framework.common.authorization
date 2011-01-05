/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;

/**
 * AuthorizationServiceDelegate<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class AuthorizationServiceDelegate extends ServiceDelegateSupport {

    private AuthorizationService service;

    /**
     * Constructs a new AuthorizationServiceDelegate instance.
     *
     * @param service the AuthorizationService.
     */
    public AuthorizationServiceDelegate(AuthorizationService service) {
        super();
        this.service = service;
    }

    /**
     * HasPermission.
     *
     * @param rq the AuthorizationPermissionMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermission(AuthorizationPermissionMsg rq)
            throws AuthorizationException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            rs = service.hasPermission(request);
        } else {
            throw new AuthorizationException(
                    "Cannot execute service operation: AuthorizationService.hasPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * HasPermission.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationPermissionMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermission(AuthorizationPermissionMsg rq, Subject subject)
            throws AuthorizationException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            rs = service.hasPermission(request);
        } else {
            throw new AuthorizationException(
                    "Cannot execute service operation: AuthorizationService.hasPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * HasPermissionByName.
     *
     * @param rq the AuthorizationNameMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermissionByName(AuthorizationNameMsg rq)
            throws AuthorizationException {
        ServiceRequest<AuthorizationNameMsg> request = new ServiceRequest<AuthorizationNameMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            rs = service.hasPermissionByName(request);
        } else {
            throw new AuthorizationException(
                    "Cannot execute service operation: AuthorizationService.hasPermissionByName");
        }
        return rs.getResponseMessage();
    }

    /**
     * HasPermissionByName.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationNameMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    public AuthorizationRs hasPermissionByName(AuthorizationNameMsg rq, Subject subject)
            throws AuthorizationException {
        ServiceRequest<AuthorizationNameMsg> request = new ServiceRequest<AuthorizationNameMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            rs = service.hasPermissionByName(request);
        } else {
            throw new AuthorizationException(
                    "Cannot execute service operation: AuthorizationService.hasPermissionByName");
        }
        return rs.getResponseMessage();
    }
}
