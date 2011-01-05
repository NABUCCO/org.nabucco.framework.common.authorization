/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.resolve;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;

/**
 * ResolveAuthorizationGroupServiceHandler<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-05-06
 */
public abstract class ResolveAuthorizationGroupServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.resolve.ResolveAuthorizationGroupServiceHandler";

    /** Constructs a new ResolveAuthorizationGroupServiceHandler instance. */
    public ResolveAuthorizationGroupServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationGroupMsg>.
     * @return the ServiceResponse<AuthorizationGroupMaintainMsg>.
     * @throws ResolveException
     */
    protected ServiceResponse<AuthorizationGroupMaintainMsg> invoke(
            ServiceRequest<AuthorizationGroupMsg> rq) throws ResolveException {
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        AuthorizationGroupMaintainMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.resolveAuthorizationGroup(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationGroupMaintainMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ResolveException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ResolveException wrappedException = new ResolveException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ResolveException(e.getMessage());
        }
    }

    /**
     * Missing description at method resolveAuthorizationGroup.
     *
     * @param msg the AuthorizationGroupMsg.
     * @return the AuthorizationGroupMaintainMsg.
     * @throws ResolveException
     */
    protected abstract AuthorizationGroupMaintainMsg resolveAuthorizationGroup(
            AuthorizationGroupMsg msg) throws ResolveException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
