/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.resolve;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization;

/**
 * ResolveAuthorizationImpl<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-05-06
 */
public class ResolveAuthorizationImpl extends ServiceSupport implements ResolveAuthorization {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ResolveAuthorization";

    private EntityManager em;

    private ResolveAuthorizationGroupServiceHandler resolveAuthorizationGroupServiceHandler;

    private ResolveAuthorizationGroupListServiceHandler resolveAuthorizationGroupListServiceHandler;

    private ResolveAuthorizationPermissionServiceHandler resolveAuthorizationPermissionServiceHandler;

    private ResolveAuthorizationPermissionListServiceHandler resolveAuthorizationPermissionListServiceHandler;

    private ResolveAuthorizationRoleServiceHandler resolveAuthorizationRoleServiceHandler;

    private ResolveAuthorizationRoleListServiceHandler resolveAuthorizationRoleListServiceHandler;

    private ResolveAuthorizationUserServiceHandler resolveAuthorizationUserServiceHandler;

    private ResolveAuthorizationUserListServiceHandler resolveAuthorizationUserListServiceHandler;

    /** Constructs a new ResolveAuthorizationImpl instance. */
    public ResolveAuthorizationImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.resolveAuthorizationGroupServiceHandler = injector
                .inject(ResolveAuthorizationGroupServiceHandler.getId());
        if ((this.resolveAuthorizationGroupServiceHandler != null)) {
            this.resolveAuthorizationGroupServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationGroupListServiceHandler = injector
                .inject(ResolveAuthorizationGroupListServiceHandler.getId());
        if ((this.resolveAuthorizationGroupListServiceHandler != null)) {
            this.resolveAuthorizationGroupListServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationGroupListServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationPermissionServiceHandler = injector
                .inject(ResolveAuthorizationPermissionServiceHandler.getId());
        if ((this.resolveAuthorizationPermissionServiceHandler != null)) {
            this.resolveAuthorizationPermissionServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationPermissionListServiceHandler = injector
                .inject(ResolveAuthorizationPermissionListServiceHandler.getId());
        if ((this.resolveAuthorizationPermissionListServiceHandler != null)) {
            this.resolveAuthorizationPermissionListServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationPermissionListServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationRoleServiceHandler = injector
                .inject(ResolveAuthorizationRoleServiceHandler.getId());
        if ((this.resolveAuthorizationRoleServiceHandler != null)) {
            this.resolveAuthorizationRoleServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationRoleListServiceHandler = injector
                .inject(ResolveAuthorizationRoleListServiceHandler.getId());
        if ((this.resolveAuthorizationRoleListServiceHandler != null)) {
            this.resolveAuthorizationRoleListServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationRoleListServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationUserServiceHandler = injector
                .inject(ResolveAuthorizationUserServiceHandler.getId());
        if ((this.resolveAuthorizationUserServiceHandler != null)) {
            this.resolveAuthorizationUserServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.resolveAuthorizationUserListServiceHandler = injector
                .inject(ResolveAuthorizationUserListServiceHandler.getId());
        if ((this.resolveAuthorizationUserListServiceHandler != null)) {
            this.resolveAuthorizationUserListServiceHandler.setEntityManager(this.em);
            this.resolveAuthorizationUserListServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<AuthorizationGroupMaintainMsg> resolveAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationGroup().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        this.resolveAuthorizationGroupServiceHandler.init();
        rs = this.resolveAuthorizationGroupServiceHandler.invoke(rq);
        this.resolveAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationGroupListMsg> resolveAuthorizationGroupList(
            ServiceRequest<AuthorizationGroupListMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationGroupListServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationGroupList().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationGroupList().");
        }
        ServiceResponse<AuthorizationGroupListMsg> rs;
        this.resolveAuthorizationGroupListServiceHandler.init();
        rs = this.resolveAuthorizationGroupListServiceHandler.invoke(rq);
        this.resolveAuthorizationGroupListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationPermissionMaintainMsg> resolveAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationPermissionServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationPermission().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        this.resolveAuthorizationPermissionServiceHandler.init();
        rs = this.resolveAuthorizationPermissionServiceHandler.invoke(rq);
        this.resolveAuthorizationPermissionServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationPermissionListMsg> resolveAuthorizationPermissionList(
            ServiceRequest<AuthorizationPermissionListMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationPermissionListServiceHandler == null)) {
            super.getLogger()
                    .error("No service implementation configured for resolveAuthorizationPermissionList().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationPermissionList().");
        }
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        this.resolveAuthorizationPermissionListServiceHandler.init();
        rs = this.resolveAuthorizationPermissionListServiceHandler.invoke(rq);
        this.resolveAuthorizationPermissionListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleMaintainMsg> resolveAuthorizationRole(
            ServiceRequest<AuthorizationRoleMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationRole().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationRole().");
        }
        ServiceResponse<AuthorizationRoleMaintainMsg> rs;
        this.resolveAuthorizationRoleServiceHandler.init();
        rs = this.resolveAuthorizationRoleServiceHandler.invoke(rq);
        this.resolveAuthorizationRoleServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleListMsg> resolveAuthorizationRoleList(
            ServiceRequest<AuthorizationRoleListMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationRoleListServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationRoleList().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationRoleList().");
        }
        ServiceResponse<AuthorizationRoleListMsg> rs;
        this.resolveAuthorizationRoleListServiceHandler.init();
        rs = this.resolveAuthorizationRoleListServiceHandler.invoke(rq);
        this.resolveAuthorizationRoleListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserMaintainMsg> resolveAuthorizationUser(
            ServiceRequest<AuthorizationUserMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationUserServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationUser().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserMaintainMsg> rs;
        this.resolveAuthorizationUserServiceHandler.init();
        rs = this.resolveAuthorizationUserServiceHandler.invoke(rq);
        this.resolveAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserListMsg> resolveAuthorizationUserList(
            ServiceRequest<AuthorizationUserListMsg> rq) throws ResolveException {
        if ((this.resolveAuthorizationUserListServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for resolveAuthorizationUserList().");
            throw new InjectionException(
                    "No service implementation configured for resolveAuthorizationUserList().");
        }
        ServiceResponse<AuthorizationUserListMsg> rs;
        this.resolveAuthorizationUserListServiceHandler.init();
        rs = this.resolveAuthorizationUserListServiceHandler.invoke(rq);
        this.resolveAuthorizationUserListServiceHandler.finish();
        return rs;
    }
}
