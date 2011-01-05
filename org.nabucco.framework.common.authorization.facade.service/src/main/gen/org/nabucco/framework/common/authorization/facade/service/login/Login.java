/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.service.login;

import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;

/**
 * Login<p/>Authorization login service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface Login extends Service {

    /**
     * Missing description at method login.
     *
     * @param rq the ServiceRequest<LoginMsg>.
     * @return the ServiceResponse<LoginRs>.
     * @throws LoginException
     */
    ServiceResponse<LoginRs> login(ServiceRequest<LoginMsg> rq) throws LoginException;
}
