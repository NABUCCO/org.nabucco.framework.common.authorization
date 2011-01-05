/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.produce;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;

/**
 * ProduceAuthorizationPermissionServiceHandler<p/>Authorization produce service<p/>
 *
 * @version 1.0
 * @author Stefanie Feld, PRODYNA AG, 2010-03-29
 */
public abstract class ProduceAuthorizationPermissionServiceHandler extends ServiceHandlerSupport
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.produce.ProduceAuthorizationPermissionServiceHandler";

    /** Constructs a new ProduceAuthorizationPermissionServiceHandler instance. */
    public ProduceAuthorizationPermissionServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<EmptyServiceMessage>.
     * @return the ServiceResponse<AuthorizationPermissionMsg>.
     * @throws ProduceException
     */
    protected ServiceResponse<AuthorizationPermissionMsg> invoke(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        ServiceResponse<AuthorizationPermissionMsg> rs;
        AuthorizationPermissionMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.produceAuthorizationPermission(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<AuthorizationPermissionMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ProduceException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ProduceException wrappedException = new ProduceException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ProduceException(e.getMessage());
        }
    }

    /**
     * Creates a new AuthorizationPermission.
     *
     * @param msg the EmptyServiceMessage.
     * @return the AuthorizationPermissionMsg.
     * @throws ProduceException
     */
    protected abstract AuthorizationPermissionMsg produceAuthorizationPermission(
            EmptyServiceMessage msg) throws ProduceException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
