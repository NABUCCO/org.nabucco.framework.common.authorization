/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.web.communication.login;

import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.facade.service.login.Login;

/**
 * LoginDelegate<p/>Authorization login service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class LoginDelegate extends ServiceDelegateSupport {

    private Login service;

    /**
     * Constructs a new LoginDelegate instance.
     *
     * @param service the Login.
     */
    public LoginDelegate(Login service) {
        super();
        this.service = service;
    }

    /**
     * Login.
     *
     * @param rq the LoginMsg.
     * @return the LoginRs.
     * @throws LoginException
     */
    public LoginRs login(LoginMsg rq) throws LoginException {
        ServiceRequest<LoginMsg> request = new ServiceRequest<LoginMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<LoginRs> rs;
        if ((service != null)) {
            rs = service.login(request);
        } else {
            throw new LoginException("Cannot execute service operation: Login.login");
        }
        return rs.getResponseMessage();
    }

    /**
     * Login.
     *
     * @param subject the Subject.
     * @param rq the LoginMsg.
     * @return the LoginRs.
     * @throws LoginException
     */
    public LoginRs login(LoginMsg rq, Subject subject) throws LoginException {
        ServiceRequest<LoginMsg> request = new ServiceRequest<LoginMsg>(
                super.createServiceContext(subject));
        request.setRequestMessage(rq);
        ServiceResponse<LoginRs> rs;
        if ((service != null)) {
            rs = service.login(request);
        } else {
            throw new LoginException("Cannot execute service operation: Login.login");
        }
        return rs.getResponseMessage();
    }
}
