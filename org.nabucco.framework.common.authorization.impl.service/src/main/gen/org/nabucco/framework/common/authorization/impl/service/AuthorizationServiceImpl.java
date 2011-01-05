/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationNameMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.facade.service.AuthorizationService;

/**
 * AuthorizationServiceImpl<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class AuthorizationServiceImpl extends ServiceSupport implements AuthorizationService {

    private static final long serialVersionUID = 1L;

    private static final String ID = "AuthorizationService";

    private EntityManager em;

    private HasPermissionServiceHandler hasPermissionServiceHandler;

    private HasPermissionByNameServiceHandler hasPermissionByNameServiceHandler;

    /** Constructs a new AuthorizationServiceImpl instance. */
    public AuthorizationServiceImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.hasPermissionServiceHandler = injector.inject(HasPermissionServiceHandler.getId());
        if ((this.hasPermissionServiceHandler != null)) {
            this.hasPermissionServiceHandler.setEntityManager(this.em);
            this.hasPermissionServiceHandler.setLogger(super.getLogger());
        }
        this.hasPermissionByNameServiceHandler = injector.inject(HasPermissionByNameServiceHandler
                .getId());
        if ((this.hasPermissionByNameServiceHandler != null)) {
            this.hasPermissionByNameServiceHandler.setEntityManager(this.em);
            this.hasPermissionByNameServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<AuthorizationRs> hasPermission(
            ServiceRequest<AuthorizationPermissionMsg> rq) throws AuthorizationException {
        if ((this.hasPermissionServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for hasPermission().");
            throw new InjectionException(
                    "No service implementation configured for hasPermission().");
        }
        ServiceResponse<AuthorizationRs> rs;
        this.hasPermissionServiceHandler.init();
        rs = this.hasPermissionServiceHandler.invoke(rq);
        this.hasPermissionServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRs> hasPermissionByName(
            ServiceRequest<AuthorizationNameMsg> rq) throws AuthorizationException {
        if ((this.hasPermissionByNameServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for hasPermissionByName().");
            throw new InjectionException(
                    "No service implementation configured for hasPermissionByName().");
        }
        ServiceResponse<AuthorizationRs> rs;
        this.hasPermissionByNameServiceHandler.init();
        rs = this.hasPermissionByNameServiceHandler.invoke(rq);
        this.hasPermissionByNameServiceHandler.finish();
        return rs;
    }
}
