/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.common.authorization.impl.service.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
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

    private static Map<String, String[]> ASPECTS;

    private SearchAuthorizationGroupServiceHandler searchAuthorizationGroupServiceHandler;

    private SearchAuthorizationUserServiceHandler searchAuthorizationUserServiceHandler;

    private SearchAuthorizationRoleServiceHandler searchAuthorizationRoleServiceHandler;

    private SearchAuthorizationPermissionServiceHandler searchAuthorizationPermissionServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new SearchAuthorizationImpl instance. */
    public SearchAuthorizationImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.searchAuthorizationGroupServiceHandler = injector.inject(SearchAuthorizationGroupServiceHandler.getId());
        if ((this.searchAuthorizationGroupServiceHandler != null)) {
            this.searchAuthorizationGroupServiceHandler.setPersistenceManager(persistenceManager);
            this.searchAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationUserServiceHandler = injector.inject(SearchAuthorizationUserServiceHandler.getId());
        if ((this.searchAuthorizationUserServiceHandler != null)) {
            this.searchAuthorizationUserServiceHandler.setPersistenceManager(persistenceManager);
            this.searchAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationRoleServiceHandler = injector.inject(SearchAuthorizationRoleServiceHandler.getId());
        if ((this.searchAuthorizationRoleServiceHandler != null)) {
            this.searchAuthorizationRoleServiceHandler.setPersistenceManager(persistenceManager);
            this.searchAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.searchAuthorizationPermissionServiceHandler = injector.inject(SearchAuthorizationPermissionServiceHandler
                .getId());
        if ((this.searchAuthorizationPermissionServiceHandler != null)) {
            this.searchAuthorizationPermissionServiceHandler.setPersistenceManager(persistenceManager);
            this.searchAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("searchAuthorizationGroup", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("searchAuthorizationUser", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("searchAuthorizationRole", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("searchAuthorizationPermission", new String[] { "org.nabucco.aspect.resolving" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<AuthorizationGroupListMsg> searchAuthorizationGroup(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException {
        if ((this.searchAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchAuthorizationGroup().");
            throw new InjectionException("No service implementation configured for searchAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupListMsg> rs;
        this.searchAuthorizationGroupServiceHandler.init();
        rs = this.searchAuthorizationGroupServiceHandler.invoke(rq);
        this.searchAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserListMsg> searchAuthorizationUser(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException {
        if ((this.searchAuthorizationUserServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchAuthorizationUser().");
            throw new InjectionException("No service implementation configured for searchAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserListMsg> rs;
        this.searchAuthorizationUserServiceHandler.init();
        rs = this.searchAuthorizationUserServiceHandler.invoke(rq);
        this.searchAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleListMsg> searchAuthorizationRole(ServiceRequest<AuthorizationSearchMsg> rq)
            throws SearchException {
        if ((this.searchAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchAuthorizationRole().");
            throw new InjectionException("No service implementation configured for searchAuthorizationRole().");
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
            super.getLogger().error("No service implementation configured for searchAuthorizationPermission().");
            throw new InjectionException("No service implementation configured for searchAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionListMsg> rs;
        this.searchAuthorizationPermissionServiceHandler.init();
        rs = this.searchAuthorizationPermissionServiceHandler.invoke(rq);
        this.searchAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
