/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;

/**
 * MaintainAuthorizationDelegate<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class MaintainAuthorizationDelegate extends ServiceDelegateSupport {

    private MaintainAuthorization service;

    /**
     * Constructs a new MaintainAuthorizationDelegate instance.
     *
     * @param service the MaintainAuthorization.
     */
    public MaintainAuthorizationDelegate(MaintainAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * MaintainAuthorizationGroup.
     *
     * @param rq the AuthorizationGroupMaintainMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationGroupMaintainMsg maintainAuthorizationGroup(AuthorizationGroupMaintainMsg rq)
            throws MaintainException {
        ServiceRequest<AuthorizationGroupMaintainMsg> request = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationGroup(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationGroup.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationGroupMaintainMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationGroupMaintainMsg maintainAuthorizationGroup(
            AuthorizationGroupMaintainMsg rq, Subject subject) throws MaintainException {
        ServiceRequest<AuthorizationGroupMaintainMsg> request = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationGroup(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationUser.
     *
     * @param rq the AuthorizationUserMaintainMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationUserMaintainMsg maintainAuthorizationUser(AuthorizationUserMaintainMsg rq)
            throws MaintainException {
        ServiceRequest<AuthorizationUserMaintainMsg> request = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationUser(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationUser.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationUserMaintainMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationUserMaintainMsg maintainAuthorizationUser(AuthorizationUserMaintainMsg rq,
            Subject subject) throws MaintainException {
        ServiceRequest<AuthorizationUserMaintainMsg> request = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationUser(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationRole.
     *
     * @param rq the AuthorizationRoleMaintainMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationRoleMaintainMsg maintainAuthorizationRole(AuthorizationRoleMaintainMsg rq)
            throws MaintainException {
        ServiceRequest<AuthorizationRoleMaintainMsg> request = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationRole(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationRole.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationRoleMaintainMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationRoleMaintainMsg maintainAuthorizationRole(AuthorizationRoleMaintainMsg rq,
            Subject subject) throws MaintainException {
        ServiceRequest<AuthorizationRoleMaintainMsg> request = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationRole(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationPermission.
     *
     * @param rq the AuthorizationPermissionMaintainMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationPermissionMaintainMsg maintainAuthorizationPermission(
            AuthorizationPermissionMaintainMsg rq) throws MaintainException {
        ServiceRequest<AuthorizationPermissionMaintainMsg> request = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationPermission(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * MaintainAuthorizationPermission.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationPermissionMaintainMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws MaintainException
     */
    public AuthorizationPermissionMaintainMsg maintainAuthorizationPermission(
            AuthorizationPermissionMaintainMsg rq, Subject subject) throws MaintainException {
        ServiceRequest<AuthorizationPermissionMaintainMsg> request = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            rs = service.maintainAuthorizationPermission(request);
        } else {
            throw new MaintainException(
                    "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }
}
