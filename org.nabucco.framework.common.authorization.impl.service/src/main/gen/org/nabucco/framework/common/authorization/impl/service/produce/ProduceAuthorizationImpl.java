/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.produce;

import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization;

/**
 * ProduceAuthorizationImpl<p/>Authorization produce service<p/>
 *
 * @version 1.0
 * @author Stefanie Feld, PRODYNA AG, 2010-03-29
 */
public class ProduceAuthorizationImpl extends ServiceSupport implements ProduceAuthorization {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceAuthorization";

    private ProduceAuthorizationGroupServiceHandler produceAuthorizationGroupServiceHandler;

    private ProduceAuthorizationUserServiceHandler produceAuthorizationUserServiceHandler;

    private ProduceAuthorizationRoleServiceHandler produceAuthorizationRoleServiceHandler;

    private ProduceAuthorizationPermissionServiceHandler produceAuthorizationPermissionServiceHandler;

    /** Constructs a new ProduceAuthorizationImpl instance. */
    public ProduceAuthorizationImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.produceAuthorizationGroupServiceHandler = injector
                .inject(ProduceAuthorizationGroupServiceHandler.getId());
        if ((this.produceAuthorizationGroupServiceHandler != null)) {
            this.produceAuthorizationGroupServiceHandler.setEntityManager(null);
            this.produceAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationUserServiceHandler = injector
                .inject(ProduceAuthorizationUserServiceHandler.getId());
        if ((this.produceAuthorizationUserServiceHandler != null)) {
            this.produceAuthorizationUserServiceHandler.setEntityManager(null);
            this.produceAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationRoleServiceHandler = injector
                .inject(ProduceAuthorizationRoleServiceHandler.getId());
        if ((this.produceAuthorizationRoleServiceHandler != null)) {
            this.produceAuthorizationRoleServiceHandler.setEntityManager(null);
            this.produceAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationPermissionServiceHandler = injector
                .inject(ProduceAuthorizationPermissionServiceHandler.getId());
        if ((this.produceAuthorizationPermissionServiceHandler != null)) {
            this.produceAuthorizationPermissionServiceHandler.setEntityManager(null);
            this.produceAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<AuthorizationGroupMsg> produceAuthorizationGroup(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceAuthorizationGroup().");
            throw new InjectionException(
                    "No service implementation configured for produceAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupMsg> rs;
        this.produceAuthorizationGroupServiceHandler.init();
        rs = this.produceAuthorizationGroupServiceHandler.invoke(rq);
        this.produceAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserMsg> produceAuthorizationUser(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceAuthorizationUserServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceAuthorizationUser().");
            throw new InjectionException(
                    "No service implementation configured for produceAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserMsg> rs;
        this.produceAuthorizationUserServiceHandler.init();
        rs = this.produceAuthorizationUserServiceHandler.invoke(rq);
        this.produceAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleMsg> produceAuthorizationRole(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceAuthorizationRole().");
            throw new InjectionException(
                    "No service implementation configured for produceAuthorizationRole().");
        }
        ServiceResponse<AuthorizationRoleMsg> rs;
        this.produceAuthorizationRoleServiceHandler.init();
        rs = this.produceAuthorizationRoleServiceHandler.invoke(rq);
        this.produceAuthorizationRoleServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationPermissionMsg> produceAuthorizationPermission(
            ServiceRequest<EmptyServiceMessage> rq) throws ProduceException {
        if ((this.produceAuthorizationPermissionServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for produceAuthorizationPermission().");
            throw new InjectionException(
                    "No service implementation configured for produceAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionMsg> rs;
        this.produceAuthorizationPermissionServiceHandler.init();
        rs = this.produceAuthorizationPermissionServiceHandler.invoke(rq);
        this.produceAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
