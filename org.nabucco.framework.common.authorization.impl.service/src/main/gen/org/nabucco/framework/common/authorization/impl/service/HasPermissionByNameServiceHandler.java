/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;

/**
 * HasPermissionByNameServiceHandler<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public abstract class HasPermissionByNameServiceHandler extends ServiceHandlerSupport implements
        ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.HasPermissionByNameServiceHandler";

    /** Constructs a new HasPermissionByNameServiceHandler instance. */
    public HasPermissionByNameServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationNameMsg>.
     * @return the ServiceResponse<AuthorizationRs>.
     * @throws AuthorizationException
     */
    protected ServiceResponse<AuthorizationRs> invoke(ServiceRequest<AuthorizationNameMsg> rq)
            throws AuthorizationException {
        ServiceResponse<AuthorizationRs> rs;
        AuthorizationRs msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.hasPermissionByName(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationRs>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (AuthorizationException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            AuthorizationException wrappedException = new AuthorizationException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new AuthorizationException(e.getMessage());
        }
    }

    /**
     * Missing description at method hasPermissionByName.
     *
     * @param msg the AuthorizationNameMsg.
     * @return the AuthorizationRs.
     * @throws AuthorizationException
     */
    protected abstract AuthorizationRs hasPermissionByName(AuthorizationNameMsg msg)
            throws AuthorizationException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
