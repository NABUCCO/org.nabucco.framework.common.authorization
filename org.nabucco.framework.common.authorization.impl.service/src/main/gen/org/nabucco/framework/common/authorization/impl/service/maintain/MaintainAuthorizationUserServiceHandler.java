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
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * MaintainAuthorizationUserServiceHandler<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public abstract class MaintainAuthorizationUserServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.maintain.MaintainAuthorizationUserServiceHandler";

    /** Constructs a new MaintainAuthorizationUserServiceHandler instance. */
    public MaintainAuthorizationUserServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<AuthorizationUserMaintainMsg>.
     * @return the ServiceResponse<AuthorizationUserMaintainMsg>.
     * @throws MaintainException
     */
    protected ServiceResponse<AuthorizationUserMaintainMsg> invoke(
            ServiceRequest<AuthorizationUserMaintainMsg> rq) throws MaintainException {
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        AuthorizationUserMaintainMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.maintainAuthorizationUser(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationUserMaintainMsg>(rq.getContext());
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
     * Missing description at method maintainAuthorizationUser.
     *
     * @param msg the AuthorizationUserMaintainMsg.
     * @return the AuthorizationUserMaintainMsg.
     * @throws MaintainException
     */
    protected abstract AuthorizationUserMaintainMsg maintainAuthorizationUser(
            AuthorizationUserMaintainMsg msg) throws MaintainException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
