/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.login;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.handler.ServiceHandler;
import org.nabucco.framework.base.impl.service.handler.ServiceHandlerSupport;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;

/**
 * LoginServiceHandler<p/>Authorization login service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public abstract class LoginServiceHandler extends ServiceHandlerSupport implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.common.authorization.impl.service.login.LoginServiceHandler";

    /** Constructs a new LoginServiceHandler instance. */
    public LoginServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<LoginMsg>.
     * @return the ServiceResponse<LoginRs>.
     * @throws LoginException
     */
    protected ServiceResponse<LoginRs> invoke(ServiceRequest<LoginMsg> rq) throws LoginException {
        ServiceResponse<LoginRs> rs;
        LoginRs msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.login(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<LoginRs>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (LoginException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            LoginException wrappedException = new LoginException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new LoginException(e.getMessage());
        }
    }

    /**
     * Missing description at method login.
     *
     * @param msg the LoginMsg.
     * @return the LoginRs.
     * @throws LoginException
     */
    protected abstract LoginRs login(LoginMsg msg) throws LoginException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
