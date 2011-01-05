/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

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
     * @throws ClientException
     */
    public AuthorizationRs hasPermission(AuthorizationPermissionMsg rq) throws ClientException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.hasPermission(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(AuthorizationServiceDelegate.class, "Service: ",
                                "AuthorizationService.hasPermission", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: AuthorizationService.hasPermission");
    }

    /**
     * HasPermissionByName.
     *
     * @param rq the AuthorizationNameMsg.
     * @return the AuthorizationRs.
     * @throws ClientException
     */
    public AuthorizationRs hasPermissionByName(AuthorizationNameMsg rq) throws ClientException {
        ServiceRequest<AuthorizationNameMsg> request = new ServiceRequest<AuthorizationNameMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRs> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.hasPermissionByName(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(AuthorizationServiceDelegate.class, "Service: ",
                                "AuthorizationService.hasPermissionByName", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: AuthorizationService.hasPermissionByName");
    }
}
