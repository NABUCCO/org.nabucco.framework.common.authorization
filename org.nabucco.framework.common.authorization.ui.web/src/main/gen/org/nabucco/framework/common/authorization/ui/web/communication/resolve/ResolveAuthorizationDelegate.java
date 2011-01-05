/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication.resolve;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;

/**
 * ResolveAuthorizationDelegate<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-05-06
 */
public class ResolveAuthorizationDelegate extends ServiceDelegateSupport {

    private ResolveAuthorization service;

    /**
     * Constructs a new ResolveAuthorizationDelegate instance.
     *
     * @param service the ResolveAuthorization.
     */
    public ResolveAuthorizationDelegate(ResolveAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * ResolveAuthorizationGroup.
     *
     * @param rq the AuthorizationGroupMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationGroupMsg> request = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationGroup(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationGroup.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationGroupMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationGroupMsg> request = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationGroup(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationGroupList.
     *
     * @param rq the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationGroupListMsg> request = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationGroupList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroupList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationGroupList.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ResolveException
     */
    public AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationGroupListMsg> request = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationGroupList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroupList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationPermission.
     *
     * @param rq the AuthorizationPermissionMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(
            AuthorizationPermissionMsg rq) throws ResolveException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationPermission(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationPermission.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationPermissionMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(
            AuthorizationPermissionMsg rq, Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationPermission(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationPermissionList.
     *
     * @param rq the AuthorizationPermissionListMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionListMsg resolveAuthorizationPermissionList(
            AuthorizationPermissionListMsg rq) throws ResolveException {
        ServiceRequest<AuthorizationPermissionListMsg> request = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationPermissionList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermissionList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationPermissionList.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationPermissionListMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ResolveException
     */
    public AuthorizationPermissionListMsg resolveAuthorizationPermissionList(
            AuthorizationPermissionListMsg rq, Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationPermissionListMsg> request = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationPermissionList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermissionList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationRole.
     *
     * @param rq the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationRoleMsg> request = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationRole(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationRole.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationRoleMsg> request = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationRole(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationRoleList.
     *
     * @param rq the AuthorizationRoleListMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationRoleListMsg> request = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationRoleList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRoleList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationRoleList.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationRoleListMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ResolveException
     */
    public AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationRoleListMsg> request = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationRoleList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRoleList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationUser.
     *
     * @param rq the AuthorizationUserMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationUserMsg> request = new ServiceRequest<AuthorizationUserMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationUser(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationUser.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationUserMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ResolveException
     */
    public AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationUserMsg> request = new ServiceRequest<AuthorizationUserMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationUser(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationUserList.
     *
     * @param rq the AuthorizationUserListMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ResolveException
     */
    public AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg rq)
            throws ResolveException {
        ServiceRequest<AuthorizationUserListMsg> request = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationUserList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUserList");
        }
        return rs.getResponseMessage();
    }

    /**
     * ResolveAuthorizationUserList.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationUserListMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ResolveException
     */
    public AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg rq,
            Subject subject) throws ResolveException {
        ServiceRequest<AuthorizationUserListMsg> request = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            rs = service.resolveAuthorizationUserList(request);
        } else {
            throw new ResolveException(
                    "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUserList");
        }
        return rs.getResponseMessage();
    }
}
