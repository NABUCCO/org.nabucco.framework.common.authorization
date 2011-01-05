/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

/**
 * ProduceAuthorizationDelegate<p/>Authorization produce service<p/>
 *
 * @version 1.0
 * @author Stefanie Feld, PRODYNA AG, 2010-03-29
 */
public class ProduceAuthorizationDelegate extends ServiceDelegateSupport {

    private ProduceAuthorization service;

    /**
     * Constructs a new ProduceAuthorizationDelegate instance.
     *
     * @param service the ProduceAuthorization.
     */
    public ProduceAuthorizationDelegate(ProduceAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * ProduceAuthorizationGroup.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationGroupMsg.
     * @throws ClientException
     */
    public AuthorizationGroupMsg produceAuthorizationGroup(EmptyServiceMessage rq)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceAuthorizationGroup(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceAuthorizationDelegate.class, "Service: ",
                                "ProduceAuthorization.produceAuthorizationGroup", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceAuthorization.produceAuthorizationGroup");
    }

    /**
     * ProduceAuthorizationUser.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationUserMsg.
     * @throws ClientException
     */
    public AuthorizationUserMsg produceAuthorizationUser(EmptyServiceMessage rq)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceAuthorizationUser(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceAuthorizationDelegate.class, "Service: ",
                                "ProduceAuthorization.produceAuthorizationUser", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceAuthorization.produceAuthorizationUser");
    }

    /**
     * ProduceAuthorizationRole.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationRoleMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMsg produceAuthorizationRole(EmptyServiceMessage rq)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceAuthorizationRole(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceAuthorizationDelegate.class, "Service: ",
                                "ProduceAuthorization.produceAuthorizationRole", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceAuthorization.produceAuthorizationRole");
    }

    /**
     * ProduceAuthorizationPermission.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationPermissionMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMsg produceAuthorizationPermission(EmptyServiceMessage rq)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.produceAuthorizationPermission(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ProduceAuthorizationDelegate.class, "Service: ",
                                "ProduceAuthorization.produceAuthorizationPermission", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ProduceAuthorization.produceAuthorizationPermission");
    }
}
