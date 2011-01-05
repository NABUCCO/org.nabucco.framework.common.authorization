/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

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
     * @throws ClientException
     */
    public AuthorizationGroupMaintainMsg maintainAuthorizationGroup(AuthorizationGroupMaintainMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationGroupMaintainMsg> request = new ServiceRequest<AuthorizationGroupMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainAuthorizationGroup(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainAuthorizationDelegate.class, "Service: ",
                                "MaintainAuthorization.maintainAuthorizationGroup", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationGroup");
    }

    /**
     * MaintainAuthorizationUser.
     *
     * @param rq the AuthorizationUserMaintainMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationUserMaintainMsg maintainAuthorizationUser(AuthorizationUserMaintainMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationUserMaintainMsg> request = new ServiceRequest<AuthorizationUserMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainAuthorizationUser(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainAuthorizationDelegate.class, "Service: ",
                                "MaintainAuthorization.maintainAuthorizationUser", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationUser");
    }

    /**
     * MaintainAuthorizationRole.
     *
     * @param rq the AuthorizationRoleMaintainMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationRoleMaintainMsg maintainAuthorizationRole(AuthorizationRoleMaintainMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationRoleMaintainMsg> request = new ServiceRequest<AuthorizationRoleMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainAuthorizationRole(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainAuthorizationDelegate.class, "Service: ",
                                "MaintainAuthorization.maintainAuthorizationRole", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationRole");
    }

    /**
     * MaintainAuthorizationPermission.
     *
     * @param rq the AuthorizationPermissionMaintainMsg.
     * @return the AuthorizationPermissionMaintainMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionMaintainMsg maintainAuthorizationPermission(
            AuthorizationPermissionMaintainMsg rq) throws ClientException {
        ServiceRequest<AuthorizationPermissionMaintainMsg> request = new ServiceRequest<AuthorizationPermissionMaintainMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.maintainAuthorizationPermission(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(MaintainAuthorizationDelegate.class, "Service: ",
                                "MaintainAuthorization.maintainAuthorizationPermission", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: MaintainAuthorization.maintainAuthorizationPermission");
    }
}
