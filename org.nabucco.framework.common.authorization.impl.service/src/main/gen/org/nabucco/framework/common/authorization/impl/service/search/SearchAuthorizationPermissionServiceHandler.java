/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.search;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;

/**
 * SearchAuthorizationPermissionServiceHandler<p/>Authorization search service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public abstract class SearchAuthorizationPermissionServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.search.SearchAuthorizationPermissionServiceHandler";

    /** Constructs a new SearchAuthorizationPermissionServiceHandler instance. */
    public SearchAuthorizationPermissionServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationSearchMsg>.
     * @return the ServiceResponse<AuthorizationPermissionListMsg>.
     * @throws SearchException
     */
    protected ServiceResponse<AuthorizationPermissionListMsg> invoke(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException {
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        AuthorizationPermissionListMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.searchAuthorizationPermission(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationPermissionListMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (SearchException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            SearchException wrappedException = new SearchException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new SearchException(e.getMessage());
        }
    }

    /**
     * Missing description at method searchAuthorizationPermission.
     *
     * @param msg the AuthorizationSearchMsg.
     * @return the AuthorizationPermissionListMsg.
     * @throws SearchException
     */
    protected abstract AuthorizationPermissionListMsg searchAuthorizationPermission(
            AuthorizationSearchMsg msg) throws SearchException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}