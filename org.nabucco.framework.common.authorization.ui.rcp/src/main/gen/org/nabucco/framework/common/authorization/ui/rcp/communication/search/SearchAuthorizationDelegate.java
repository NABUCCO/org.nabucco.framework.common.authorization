/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

/**
 * SearchAuthorizationDelegate<p/>Authorization search service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class SearchAuthorizationDelegate extends ServiceDelegateSupport {

    private SearchAuthorization service;

    /**
     * Constructs a new SearchAuthorizationDelegate instance.
     *
     * @param service the SearchAuthorization.
     */
    public SearchAuthorizationDelegate(SearchAuthorization service) {
        super();
        this.service = service;
    }

    /**
     * SearchAuthorizationGroup.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws ClientException
     */
    public AuthorizationGroupListMsg searchAuthorizationGroup(AuthorizationSearchMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchAuthorizationGroup(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchAuthorizationDelegate.class, "Service: ",
                                "SearchAuthorization.searchAuthorizationGroup", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchAuthorization.searchAuthorizationGroup");
    }

    /**
     * SearchAuthorizationUser.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationUserListMsg.
     * @throws ClientException
     */
    public AuthorizationUserListMsg searchAuthorizationUser(AuthorizationSearchMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchAuthorizationUser(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchAuthorizationDelegate.class, "Service: ",
                                "SearchAuthorization.searchAuthorizationUser", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchAuthorization.searchAuthorizationUser");
    }

    /**
     * SearchAuthorizationRole.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws ClientException
     */
    public AuthorizationRoleListMsg searchAuthorizationRole(AuthorizationSearchMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchAuthorizationRole(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchAuthorizationDelegate.class, "Service: ",
                                "SearchAuthorization.searchAuthorizationRole", " Time: ", String
                                        .valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchAuthorization.searchAuthorizationRole");
    }

    /**
     * SearchAuthorizationPermission.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws ClientException
     */
    public AuthorizationPermissionListMsg searchAuthorizationPermission(AuthorizationSearchMsg rq)
            throws ClientException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.searchAuthorizationPermission(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(SearchAuthorizationDelegate.class, "Service: ",
                                "SearchAuthorization.searchAuthorizationPermission", " Time: ",
                                String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException(
                "Cannot execute service operation: SearchAuthorization.searchAuthorizationPermission");
    }
}
