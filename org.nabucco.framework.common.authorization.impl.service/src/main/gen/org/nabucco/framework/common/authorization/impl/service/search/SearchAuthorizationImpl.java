/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.impl.service.search;

import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * SearchAuthorizationImpl<p/>Authorization search service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class SearchAuthorizationImpl extends ServiceSupport implements SearchAuthorization {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchAuthorization";

    private EntityManager em;

    private SearchAuthorizationGroupServiceHandler searchAuthorizationGroupServiceHandler;

    private SearchAuthorizationUserServiceHandler searchAuthorizationUserServiceHandler;

    private SearchAuthorizationRoleServiceHandler searchAuthorizationRoleServiceHandler;

    private SearchAuthorizationPermissionServiceHandler searchAuthorizationPermissionServiceHandler;

    /** Constructs a new SearchAuthorizationImpl instance. */
    public SearchAuthorizationImpl() {
        super();
    }

    /** PostConstruct. */
    public void postConstruct() {
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        this.searchAuthorizationGroupServiceHandler = injector
                .inject(SearchAuthorizationGroupServiceHandler.getId());
        if ((this.searchAuthorizationGroupServiceHandler != null)) {
            this.searchAuthorizationGroupServiceHandler.setEntityManager(this.em);
            this.searchAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationUserServiceHandler = injector
                .inject(SearchAuthorizationUserServiceHandler.getId());
        if ((this.searchAuthorizationUserServiceHandler != null)) {
            this.searchAuthorizationUserServiceHandler.setEntityManager(this.em);
            this.searchAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationRoleServiceHandler = injector
                .inject(SearchAuthorizationRoleServiceHandler.getId());
        if ((this.searchAuthorizationRoleServiceHandler != null)) {
            this.searchAuthorizationRoleServiceHandler.setEntityManager(this.em);
            this.searchAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationPermissionServiceHandler = injector
                .inject(SearchAuthorizationPermissionServiceHandler.getId());
        if ((this.searchAuthorizationPermissionServiceHandler != null)) {
            this.searchAuthorizationPermissionServiceHandler.setEntityManager(this.em);
            this.searchAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
        }
    }

    /** PreDestroy. */
    public void preDestroy() {
    }

    @Override
    public ServiceResponse<AuthorizationGroupListMsg> searchAuthorizationGroup(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException {
        if ((this.searchAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for searchAuthorizationGroup().");
            throw new InjectionException(
                    "No service implementation configured for searchAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupListMsg> rs;
        this.searchAuthorizationGroupServiceHandler.init();
        rs = this.searchAuthorizationGroupServiceHandler.invoke(rq);
        this.searchAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserListMsg> searchAuthorizationUser(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException {
        if ((this.searchAuthorizationUserServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for searchAuthorizationUser().");
            throw new InjectionException(
                    "No service implementation configured for searchAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserListMsg> rs;
        this.searchAuthorizationUserServiceHandler.init();
        rs = this.searchAuthorizationUserServiceHandler.invoke(rq);
        this.searchAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleListMsg> searchAuthorizationRole(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException {
        if ((this.searchAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for searchAuthorizationRole().");
            throw new InjectionException(
                    "No service implementation configured for searchAuthorizationRole().");
        }
        ServiceResponse<AuthorizationRoleListMsg> rs;
        this.searchAuthorizationRoleServiceHandler.init();
        rs = this.searchAuthorizationRoleServiceHandler.invoke(rq);
        this.searchAuthorizationRoleServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationPermissionListMsg> searchAuthorizationPermission(
            ServiceRequest<AuthorizationSearchMsg> rq) throws SearchException {
        if ((this.searchAuthorizationPermissionServiceHandler == null)) {
            super.getLogger().error(
                    "No service implementation configured for searchAuthorizationPermission().");
            throw new InjectionException(
                    "No service implementation configured for searchAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        this.searchAuthorizationPermissionServiceHandler.init();
        rs = this.searchAuthorizationPermissionServiceHandler.invoke(rq);
        this.searchAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
