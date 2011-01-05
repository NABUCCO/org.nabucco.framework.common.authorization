/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication.produce;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;

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
     * @throws ProduceException
     */
    public AuthorizationGroupMsg produceAuthorizationGroup(EmptyServiceMessage rq)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationGroup(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationGroup.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationGroupMsg.
     * @throws ProduceException
     */
    public AuthorizationGroupMsg produceAuthorizationGroup(EmptyServiceMessage rq, Subject subject)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationGroup(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationUser.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationUserMsg.
     * @throws ProduceException
     */
    public AuthorizationUserMsg produceAuthorizationUser(EmptyServiceMessage rq)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationUser(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationUser.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationUserMsg.
     * @throws ProduceException
     */
    public AuthorizationUserMsg produceAuthorizationUser(EmptyServiceMessage rq, Subject subject)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationUser(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationRole.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationRoleMsg.
     * @throws ProduceException
     */
    public AuthorizationRoleMsg produceAuthorizationRole(EmptyServiceMessage rq)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationRole(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationRole.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationRoleMsg.
     * @throws ProduceException
     */
    public AuthorizationRoleMsg produceAuthorizationRole(EmptyServiceMessage rq, Subject subject)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationRole(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationPermission.
     *
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationPermissionMsg.
     * @throws ProduceException
     */
    public AuthorizationPermissionMsg produceAuthorizationPermission(EmptyServiceMessage rq)
            throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationPermission(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * ProduceAuthorizationPermission.
     *
     * @param subject the Subject.
     * @param rq the EmptyServiceMessage.
     * @return the AuthorizationPermissionMsg.
     * @throws ProduceException
     */
    public AuthorizationPermissionMsg produceAuthorizationPermission(EmptyServiceMessage rq,
            Subject subject) throws ProduceException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMsg> rs;
        if ((service != null)) {
            rs = service.produceAuthorizationPermission(request);
        } else {
            throw new ProduceException(
                    "Cannot execute service operation: ProduceAuthorization.produceAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }
}
