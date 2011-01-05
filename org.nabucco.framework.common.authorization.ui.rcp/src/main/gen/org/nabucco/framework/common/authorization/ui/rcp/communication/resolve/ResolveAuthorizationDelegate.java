/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication.resolve;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
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
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

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
     * @throws ClientException
     */
    public AuthorizationGroupMaintainMsg resolveAuthorizationGroup(AuthorizationGroupMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationGroupMsg> request = new ServiceRequest<AuthorizationGroupMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationGroup(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationGroup", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroup");
    }

    /**
     * ResolveAuthorizationGroupList.
     *
     * @param rq the AuthorizationGroupListMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ClientException
     */
    public AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationGroupListMsg> request = new ServiceRequest<AuthorizationGroupListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationGroupList(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationGroupList", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationGroupList");
    }

    /**
     * ResolveAuthorizationPermission.
     *
     * @param rq the AuthorizationPermissionMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMaintainMsg resolveAuthorizationPermission(
            AuthorizationPermissionMsg rq) throws ClientException {
        ServiceRequest<AuthorizationPermissionMsg> request = new ServiceRequest<AuthorizationPermissionMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationPermission(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationPermission", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermission");
    }

    /**
     * ResolveAuthorizationPermissionList.
     *
     * @param rq the AuthorizationPermissionListMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionListMsg resolveAuthorizationPermissionList(
            AuthorizationPermissionListMsg rq) throws ClientException {
        ServiceRequest<AuthorizationPermissionListMsg> request = new ServiceRequest<AuthorizationPermissionListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationPermissionList(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationPermissionList",
                                " Time: ", String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationPermissionList");
    }

    /**
     * ResolveAuthorizationRole.
     *
     * @param rq the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMaintainMsg resolveAuthorizationRole(AuthorizationRoleMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationRoleMsg> request = new ServiceRequest<AuthorizationRoleMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationRole(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationRole", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRole");
    }

    /**
     * ResolveAuthorizationRoleList.
     *
     * @param rq the AuthorizationRoleListMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ClientException
     */
    public AuthorizationRoleListMsg resolveAuthorizationRoleList(AuthorizationRoleListMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationRoleListMsg> request = new ServiceRequest<AuthorizationRoleListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationRoleList(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationRoleList", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationRoleList");
    }

    /**
     * ResolveAuthorizationUser.
     *
     * @param rq the AuthorizationUserMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationUserMaintainMsg resolveAuthorizationUser(AuthorizationUserMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationUserMsg> request = new ServiceRequest<AuthorizationUserMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationUser(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationUser", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUser");
    }

    /**
     * ResolveAuthorizationUserList.
     *
     * @param rq the AuthorizationUserListMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ClientException
     */
    public AuthorizationUserListMsg resolveAuthorizationUserList(AuthorizationUserListMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationUserListMsg> request = new ServiceRequest<AuthorizationUserListMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.resolveAuthorizationUserList(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(ResolveAuthorizationDelegate.class, "Service: ",
                                "ResolveAuthorization.resolveAuthorizationUserList", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: ResolveAuthorization.resolveAuthorizationUserList");
    }
}
