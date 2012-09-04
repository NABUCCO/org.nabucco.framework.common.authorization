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
package org.nabucco.framework.common.authorization.impl.service.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization;

/**
 * MaintainAuthorizationImpl<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public class MaintainAuthorizationImpl extends ServiceSupport implements MaintainAuthorization {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainAuthorization";

    private static Map<String, String[]> ASPECTS;

    private MaintainAuthorizationGroupServiceHandler maintainAuthorizationGroupServiceHandler;

    private MaintainAuthorizationGroupListServiceHandler maintainAuthorizationGroupListServiceHandler;

    private MaintainAuthorizationUserServiceHandler maintainAuthorizationUserServiceHandler;

    private MaintainAuthorizationRoleServiceHandler maintainAuthorizationRoleServiceHandler;

    private MaintainAuthorizationPermissionServiceHandler maintainAuthorizationPermissionServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new MaintainAuthorizationImpl instance. */
    public MaintainAuthorizationImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.maintainAuthorizationGroupServiceHandler = injector.inject(MaintainAuthorizationGroupServiceHandler
                .getId());
        if ((this.maintainAuthorizationGroupServiceHandler != null)) {
            this.maintainAuthorizationGroupServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationGroupListServiceHandler = injector
                .inject(MaintainAuthorizationGroupListServiceHandler.getId());
        if ((this.maintainAuthorizationGroupListServiceHandler != null)) {
            this.maintainAuthorizationGroupListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainAuthorizationGroupListServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationUserServiceHandler = injector.inject(MaintainAuthorizationUserServiceHandler.getId());
        if ((this.maintainAuthorizationUserServiceHandler != null)) {
            this.maintainAuthorizationUserServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationRoleServiceHandler = injector.inject(MaintainAuthorizationRoleServiceHandler.getId());
        if ((this.maintainAuthorizationRoleServiceHandler != null)) {
            this.maintainAuthorizationRoleServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.maintainAuthorizationPermissionServiceHandler = injector
                .inject(MaintainAuthorizationPermissionServiceHandler.getId());
        if ((this.maintainAuthorizationPermissionServiceHandler != null)) {
            this.maintainAuthorizationPermissionServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("maintainAuthorizationGroup", new String[] { "org.nabucco.aspect.permissioning",
                    "org.nabucco.aspect.validating", "org.nabucco.aspect.resolving" });
            ASPECTS.put("maintainAuthorizationGroupList", new String[] { "org.nabucco.aspect.permissioning",
                    "org.nabucco.aspect.validating", "org.nabucco.aspect.resolving" });
            ASPECTS.put("maintainAuthorizationUser", new String[] { "org.nabucco.aspect.permissioning",
                    "org.nabucco.aspect.validating", "org.nabucco.aspect.resolving" });
            ASPECTS.put("maintainAuthorizationRole", new String[] { "org.nabucco.aspect.permissioning",
                    "org.nabucco.aspect.validating", "org.nabucco.aspect.resolving" });
            ASPECTS.put("maintainAuthorizationPermission", new String[] { "org.nabucco.aspect.permissioning",
                    "org.nabucco.aspect.validating", "org.nabucco.aspect.resolving" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<AuthorizationGroupMaintainMsg> maintainAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainAuthorizationGroup().");
            throw new InjectionException("No service implementation configured for maintainAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupMaintainMsg> rs;
        this.maintainAuthorizationGroupServiceHandler.init();
        rs = this.maintainAuthorizationGroupServiceHandler.invoke(rq);
        this.maintainAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationGroupListMsg> maintainAuthorizationGroupList(
            ServiceRequest<AuthorizationGroupListMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationGroupListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainAuthorizationGroupList().");
            throw new InjectionException("No service implementation configured for maintainAuthorizationGroupList().");
        }
        ServiceResponse<AuthorizationGroupListMsg> rs;
        this.maintainAuthorizationGroupListServiceHandler.init();
        rs = this.maintainAuthorizationGroupListServiceHandler.invoke(rq);
        this.maintainAuthorizationGroupListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserMaintainMsg> maintainAuthorizationUser(
            ServiceRequest<AuthorizationUserMaintainMsg> rq) throws MaintainException {
        if ((this.maintainAuthorizationUserServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainAuthorizationUser().");
            throw new InjectionException("No service implementation configured for maintainAuthorizationUser().");
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
            super.getLogger().error("No service implementation configured for maintainAuthorizationRole().");
            throw new InjectionException("No service implementation configured for maintainAuthorizationRole().");
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
            super.getLogger().error("No service implementation configured for maintainAuthorizationPermission().");
            throw new InjectionException("No service implementation configured for maintainAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionMaintainMsg> rs;
        this.maintainAuthorizationPermissionServiceHandler.init();
        rs = this.maintainAuthorizationPermissionServiceHandler.invoke(rq);
        this.maintainAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
