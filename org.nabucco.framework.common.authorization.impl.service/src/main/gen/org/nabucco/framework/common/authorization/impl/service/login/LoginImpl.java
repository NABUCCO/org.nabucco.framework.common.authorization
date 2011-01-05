/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.login;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.exception.login.LoginException;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.facade.service.login.Login;

/**
 * LoginImpl<p/>Authorization login service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class LoginImpl extends ServiceSupport implements Login {

    private static final long serialVersionUID = 1L;

    private static final String ID = "Login";

    private EntityManager em;

    private LoginServiceHandler loginServiceHandler;

    /** Constructs a new LoginImpl instance. */
    public LoginImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.loginServiceHandler = injector.inject(LoginServiceHandler.getId());
        if ((this.loginServiceHandler != null)) {
            this.loginServiceHandler.setEntityManager(this.em);
            this.loginServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<LoginRs> login(ServiceRequest<LoginMsg> rq) throws LoginException {
        if ((this.loginServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for login().");
            throw new InjectionException("No service implementation configured for login().");
        }
        ServiceResponse<LoginRs> rs;
        this.loginServiceHandler.init();
        rs = this.loginServiceHandler.invoke(rq);
        this.loginServiceHandler.finish();
        return rs;
    }
}
