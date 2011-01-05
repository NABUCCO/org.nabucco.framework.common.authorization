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
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;

/**
 * ResolveAuthorizationRoleServiceHandler<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-05-06
 */
public abstract class ResolveAuthorizationRoleServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.resolve.ResolveAuthorizationRoleServiceHandler";

    /** Constructs a new ResolveAuthorizationRoleServiceHandler instance. */
    public ResolveAuthorizationRoleServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws ResolveException
     */
    protected ServiceResponse<AuthorizationRoleMaintainMsg> invoke(
            ServiceRequest<AuthorizationRoleMsg> rq) throws ResolveException {
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        AuthorizationRoleMaintainMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.resolveAuthorizationRole(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationRoleMaintainMsg>(rq.getContext());
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
     * Missing description at method resolveAuthorizationRole.
     *
     * @param msg the AuthorizationRoleMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws ResolveException
     */
    protected abstract AuthorizationRoleMaintainMsg resolveAuthorizationRole(
            AuthorizationRoleMsg msg) throws ResolveException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
