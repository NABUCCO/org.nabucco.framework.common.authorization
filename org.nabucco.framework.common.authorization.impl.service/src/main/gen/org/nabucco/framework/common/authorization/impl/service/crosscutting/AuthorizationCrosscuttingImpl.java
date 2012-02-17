/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.impl.service.crosscutting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.authorization.GroupListRs;
import org.nabucco.framework.base.facade.message.authorization.PermissionListRs;
import org.nabucco.framework.base.facade.message.authorization.RoleListRs;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.facade.message.authorization.UserRs;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.common.authorization.facade.service.crosscutting.AuthorizationCrosscutting;

/**
 * AuthorizationCrosscuttingImpl<p/>Authorization service operation facade for accessing authorization services<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class AuthorizationCrosscuttingImpl extends ServiceSupport implements AuthorizationCrosscutting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "AuthorizationCrosscutting";

    private static Map<String, String[]> ASPECTS;

    private EntityManager entityManager;

    private ResolveUserServiceHandler resolveUserServiceHandler;

    private GetGroupsServiceHandler getGroupsServiceHandler;

    private GetRolesServiceHandler getRolesServiceHandler;

    private GetPermissionsServiceHandler getPermissionsServiceHandler;

    /** Constructs a new AuthorizationCrosscuttingImpl instance. */
    public AuthorizationCrosscuttingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveUserServiceHandler = injector.inject(ResolveUserServiceHandler.getId());
        if ((this.resolveUserServiceHandler != null)) {
            this.resolveUserServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveUserServiceHandler.setLogger(super.getLogger());
        }
        this.getGroupsServiceHandler = injector.inject(GetGroupsServiceHandler.getId());
        if ((this.getGroupsServiceHandler != null)) {
            this.getGroupsServiceHandler.setPersistenceManager(persistenceManager);
            this.getGroupsServiceHandler.setLogger(super.getLogger());
        }
        this.getRolesServiceHandler = injector.inject(GetRolesServiceHandler.getId());
        if ((this.getRolesServiceHandler != null)) {
            this.getRolesServiceHandler.setPersistenceManager(persistenceManager);
            this.getRolesServiceHandler.setLogger(super.getLogger());
        }
        this.getPermissionsServiceHandler = injector.inject(GetPermissionsServiceHandler.getId());
        if ((this.getPermissionsServiceHandler != null)) {
            this.getPermissionsServiceHandler.setPersistenceManager(persistenceManager);
            this.getPermissionsServiceHandler.setLogger(super.getLogger());
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
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<UserRs> resolveUser(ServiceRequest<UserRq> rq) throws SearchException {
        if ((this.resolveUserServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveUser().");
            throw new InjectionException("No service implementation configured for resolveUser().");
        }
        ServiceResponse<UserRs> rs;
        this.resolveUserServiceHandler.init();
        rs = this.resolveUserServiceHandler.invoke(rq);
        this.resolveUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<GroupListRs> getGroups(ServiceRequest<UserRq> rq) throws SearchException {
        if ((this.getGroupsServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getGroups().");
            throw new InjectionException("No service implementation configured for getGroups().");
        }
        ServiceResponse<GroupListRs> rs;
        this.getGroupsServiceHandler.init();
        rs = this.getGroupsServiceHandler.invoke(rq);
        this.getGroupsServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<RoleListRs> getRoles(ServiceRequest<UserRq> rq) throws SearchException {
        if ((this.getRolesServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getRoles().");
            throw new InjectionException("No service implementation configured for getRoles().");
        }
        ServiceResponse<RoleListRs> rs;
        this.getRolesServiceHandler.init();
        rs = this.getRolesServiceHandler.invoke(rq);
        this.getRolesServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<PermissionListRs> getPermissions(ServiceRequest<UserRq> rq) throws SearchException {
        if ((this.getPermissionsServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getPermissions().");
            throw new InjectionException("No service implementation configured for getPermissions().");
        }
        ServiceResponse<PermissionListRs> rs;
        this.getPermissionsServiceHandler.init();
        rs = this.getPermissionsServiceHandler.invoke(rq);
        this.getPermissionsServiceHandler.finish();
        return rs;
    }
}
