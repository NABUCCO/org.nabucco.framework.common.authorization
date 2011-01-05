/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication.search;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

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
     * @throws SearchException
     */
    public AuthorizationGroupListMsg searchAuthorizationGroup(AuthorizationSearchMsg rq)
            throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationGroup(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationGroup.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationGroupListMsg.
     * @throws SearchException
     */
    public AuthorizationGroupListMsg searchAuthorizationGroup(AuthorizationSearchMsg rq,
            Subject subject) throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationGroupListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationGroup(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationGroup");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationUser.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationUserListMsg.
     * @throws SearchException
     */
    public AuthorizationUserListMsg searchAuthorizationUser(AuthorizationSearchMsg rq)
            throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationUser(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationUser.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationUserListMsg.
     * @throws SearchException
     */
    public AuthorizationUserListMsg searchAuthorizationUser(AuthorizationSearchMsg rq,
            Subject subject) throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationUserListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationUser(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationUser");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationRole.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws SearchException
     */
    public AuthorizationRoleListMsg searchAuthorizationRole(AuthorizationSearchMsg rq)
            throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationRole(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationRole.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationRoleListMsg.
     * @throws SearchException
     */
    public AuthorizationRoleListMsg searchAuthorizationRole(AuthorizationSearchMsg rq,
            Subject subject) throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationRoleListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationRole(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationRole");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationPermission.
     *
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws SearchException
     */
    public AuthorizationPermissionListMsg searchAuthorizationPermission(AuthorizationSearchMsg rq)
            throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationPermission(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }

    /**
     * SearchAuthorizationPermission.
     *
     * @param subject the Subject.
     * @param rq the AuthorizationSearchMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws SearchException
     */
    public AuthorizationPermissionListMsg searchAuthorizationPermission(AuthorizationSearchMsg rq,
            Subject subject) throws SearchException {
        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        if ((service != null)) {
            rs = service.searchAuthorizationPermission(request);
        } else {
            throw new SearchException(
                    "Cannot execute service operation: SearchAuthorization.searchAuthorizationPermission");
        }
        return rs.getResponseMessage();
    }
}
