/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.maintain;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;

/**
 * MaintainAuthorizationImpl<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class MaintainAuthorizationImpl extends ServiceSupport implements MaintainAuthorization {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainAuthorization";

    private EntityManager em;

    private MaintainAuthorizationGroupServiceHandler maintainAuthorizationGroupServiceHandler;

    private MaintainAuthorizationUserServiceHandler maintainAuthorizationUserServiceHandler;

    private MaintainAuthorizationRoleServiceHandler maintainAuthorizationRoleServiceHandler;

    private MaintainAuthorizationPermissionServiceHandler maintainAuthorizationPermissionServiceHandler;

    /** Constructs a new MaintainAuthorizationImpl instance. */
    public MaintainAuthorizationImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.maintainAuthorizationGroupServiceHandler = injector
                .inject(MaintainAuthorizationGroupServiceHandler.getId());
        if ((this.maintainAuthorizationGroupServiceHandler != null)) {
            this.maintainAuthorizationGroupServiceHandler.setEntityManager(this.em);
            this.maintainAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationUserServiceHandler = injector
                .inject(MaintainAuthorizationUserServiceHandler.getId());
        if ((this.maintainAuthorizationUserServiceHandler != null)) {
            this.maintainAuthorizationUserServiceHandler.setEntityManager(this.em);
            this.maintainAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationRoleServiceHandler = injector
                .inject(MaintainAuthorizationRoleServiceHandler.getId());
        if ((this.maintainAuthorizationRoleServiceHandler != null)) {
            this.maintainAuthorizationRoleServiceHandler.setEntityManager(this.em);
            this.maintainAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationPermissionServiceHandler = injector
                .inject(MaintainAuthorizationPermissionServiceHandler.getId());
        if ((this.maintainAuthorizationPermissionServiceHandler != null)) {
            this.maintainAuthorizationPermissionServiceHandler.setEntityManager(this.em);
            this.maintainAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<AuthorizationGroupMaintainMsg> maintainAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainAuthorizationGroup().");
            throw new InjectionException(
                    "No service implementation configured for maintainAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        this.maintainAuthorizationGroupServiceHandler.init();
        rs = this.maintainAuthorizationGroupServiceHandler.invoke(rq);
        this.maintainAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserMaintainMsg> maintainAuthorizationUser(
            ServiceRequest<AuthorizationUserMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationUserServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainAuthorizationUser().");
            throw new InjectionException(
                    "No service implementation configured for maintainAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        this.maintainAuthorizationUserServiceHandler.init();
        rs = this.maintainAuthorizationUserServiceHandler.invoke(rq);
        this.maintainAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleMaintainMsg> maintainAuthorizationRole(
            ServiceRequest<AuthorizationRoleMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainAuthorizationRole().");
            throw new InjectionException(
                    "No service implementation configured for maintainAuthorizationRole().");
        }
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        this.maintainAuthorizationRoleServiceHandler.init();
        rs = this.maintainAuthorizationRoleServiceHandler.invoke(rq);
        this.maintainAuthorizationRoleServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationPermissionMaintainMsg> maintainAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationPermissionServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for maintainAuthorizationPermission().");
            throw new InjectionException(
                    "No service implementation configured for maintainAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        this.maintainAuthorizationPermissionServiceHandler.init();
        rs = this.maintainAuthorizationPermissionServiceHandler.invoke(rq);
        this.maintainAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
