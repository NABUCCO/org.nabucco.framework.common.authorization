/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.maintain;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;

/**
 * MaintainAuthorizationRoleServiceHandler<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public abstract class MaintainAuthorizationRoleServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.maintain.MaintainAuthorizationRoleServiceHandler";

    /** Constructs a new MaintainAuthorizationRoleServiceHandler instance. */
    public MaintainAuthorizationRoleServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMaintainMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws MaintainException
     */
    protected ServiceResponse<AuthorizationRoleMaintainMsg> invoke(
            ServiceRequest<AuthorizationRoleMaintainMsg> rq) throws MaintainException {
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        AuthorizationRoleMaintainMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.maintainAuthorizationRole(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationRoleMaintainMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (MaintainException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            MaintainException wrappedException = new MaintainException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new MaintainException(e.getMessage());
        }
    }

    /**
     * Missing description at method maintainAuthorizationRole.
     *
     * @param msg the AuthorizationRoleMaintainMsg.
     * @return the AuthorizationRoleMaintainMsg.
     * @throws MaintainException
     */
    protected abstract AuthorizationRoleMaintainMsg maintainAuthorizationRole(
            AuthorizationRoleMaintainMsg msg) throws MaintainException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
