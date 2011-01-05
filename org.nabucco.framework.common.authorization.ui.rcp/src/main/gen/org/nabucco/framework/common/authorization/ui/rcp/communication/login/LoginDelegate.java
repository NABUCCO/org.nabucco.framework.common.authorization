/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.communication.login;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.facade.service.login.Login;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;
import org.nabucco.framework.plugin.base.logging.NabuccoLogMessage;

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
     * @throws ClientException
     */
    public LoginRs login(LoginMsg rq) throws ClientException {
        ServiceRequest<LoginMsg> request = new ServiceRequest<LoginMsg>(
                super.createServiceContext());
        request.setRequestMessage(rq);
        ServiceResponse<LoginRs> rs;
        if ((service != null)) {
            long start = System.currentTimeMillis();
            try {
                rs = service.login(request);
                return rs.getResponseMessage();
            } catch (Exception exception) {
                super.processException(exception);
            } finally {
                long end = System.currentTimeMillis();
                Activator.getDefault().logDebug(
                        new NabuccoLogMessage(LoginDelegate.class, "Service: ", "Login.login",
                                " Time: ", String.valueOf((end - start)), "ms."));
            }
        }
        throw new ClientException("Cannot execute service operation: Login.login");
    }
}
