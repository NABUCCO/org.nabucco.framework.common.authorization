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
package org.nabucco.framework.common.authorization.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
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

    private static Map<String, String[]> ASPECTS;

    private ProduceAuthorizationGroupServiceHandler produceAuthorizationGroupServiceHandler;

    private ProduceAuthorizationUserServiceHandler produceAuthorizationUserServiceHandler;

    private ProduceAuthorizationRoleServiceHandler produceAuthorizationRoleServiceHandler;

    private ProduceAuthorizationPermissionServiceHandler produceAuthorizationPermissionServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ProduceAuthorizationImpl instance. */
    public ProduceAuthorizationImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceAuthorizationGroupServiceHandler = injector.inject(ProduceAuthorizationGroupServiceHandler.getId());
        if ((this.produceAuthorizationGroupServiceHandler != null)) {
            this.produceAuthorizationGroupServiceHandler.setPersistenceManager(persistenceManager);
            this.produceAuthorizationGroupServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationUserServiceHandler = injector.inject(ProduceAuthorizationUserServiceHandler.getId());
        if ((this.produceAuthorizationUserServiceHandler != null)) {
            this.produceAuthorizationUserServiceHandler.setPersistenceManager(persistenceManager);
            this.produceAuthorizationUserServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationRoleServiceHandler = injector.inject(ProduceAuthorizationRoleServiceHandler.getId());
        if ((this.produceAuthorizationRoleServiceHandler != null)) {
            this.produceAuthorizationRoleServiceHandler.setPersistenceManager(persistenceManager);
            this.produceAuthorizationRoleServiceHandler.setLogger(super.getLogger());
        }
        this.produceAuthorizationPermissionServiceHandler = injector
                .inject(ProduceAuthorizationPermissionServiceHandler.getId());
        if ((this.produceAuthorizationPermissionServiceHandler != null)) {
            this.produceAuthorizationPermissionServiceHandler.setPersistenceManager(persistenceManager);
            this.produceAuthorizationPermissionServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceAuthorizationGroup", NO_ASPECTS);
            ASPECTS.put("produceAuthorizationUser", NO_ASPECTS);
            ASPECTS.put("produceAuthorizationRole", NO_ASPECTS);
            ASPECTS.put("produceAuthorizationPermission", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<AuthorizationGroupMsg> produceAuthorizationGroup(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceAuthorizationGroupServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceAuthorizationGroup().");
            throw new InjectionException("No service implementation configured for produceAuthorizationGroup().");
        }
        ServiceResponse<AuthorizationGroupMsg> rs;
        this.produceAuthorizationGroupServiceHandler.init();
        rs = this.produceAuthorizationGroupServiceHandler.invoke(rq);
        this.produceAuthorizationGroupServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationUserMsg> produceAuthorizationUser(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceAuthorizationUserServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceAuthorizationUser().");
            throw new InjectionException("No service implementation configured for produceAuthorizationUser().");
        }
        ServiceResponse<AuthorizationUserMsg> rs;
        this.produceAuthorizationUserServiceHandler.init();
        rs = this.produceAuthorizationUserServiceHandler.invoke(rq);
        this.produceAuthorizationUserServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRoleMsg> produceAuthorizationRole(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceAuthorizationRoleServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceAuthorizationRole().");
            throw new InjectionException("No service implementation configured for produceAuthorizationRole().");
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
            super.getLogger().error("No service implementation configured for produceAuthorizationPermission().");
            throw new InjectionException("No service implementation configured for produceAuthorizationPermission().");
        }
        ServiceResponse<AuthorizationPermissionMsg> rs;
        this.produceAuthorizationPermissionServiceHandler.init();
        rs = this.produceAuthorizationPermissionServiceHandler.invoke(rq);
        this.produceAuthorizationPermissionServiceHandler.finish();
        return rs;
    }
}
