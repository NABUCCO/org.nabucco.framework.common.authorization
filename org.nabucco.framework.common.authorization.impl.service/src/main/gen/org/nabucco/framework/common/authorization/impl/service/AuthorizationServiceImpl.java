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
package org.nabucco.framework.common.authorization.impl.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationInformationRs;
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

    private static Map<String, String[]> ASPECTS;

    private HasPermissionServiceHandler hasPermissionServiceHandler;

    private HasPermissionByNameServiceHandler hasPermissionByNameServiceHandler;

    private GetInformationServiceHandler getInformationServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new AuthorizationServiceImpl instance. */
    public AuthorizationServiceImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.hasPermissionServiceHandler = injector.inject(HasPermissionServiceHandler.getId());
        if ((this.hasPermissionServiceHandler != null)) {
            this.hasPermissionServiceHandler.setPersistenceManager(persistenceManager);
            this.hasPermissionServiceHandler.setLogger(super.getLogger());
        }
        this.hasPermissionByNameServiceHandler = injector.inject(HasPermissionByNameServiceHandler.getId());
        if ((this.hasPermissionByNameServiceHandler != null)) {
            this.hasPermissionByNameServiceHandler.setPersistenceManager(persistenceManager);
            this.hasPermissionByNameServiceHandler.setLogger(super.getLogger());
        }
        this.getInformationServiceHandler = injector.inject(GetInformationServiceHandler.getId());
        if ((this.getInformationServiceHandler != null)) {
            this.getInformationServiceHandler.setPersistenceManager(persistenceManager);
            this.getInformationServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("hasPermission", NO_ASPECTS);
            ASPECTS.put("hasPermissionByName", NO_ASPECTS);
            ASPECTS.put("getInformation", new String[] { "org.nabucco.aspect.caching" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<AuthorizationRs> hasPermission(ServiceRequest<AuthorizationPermissionMsg> rq)
            throws AuthorizationException {
        if ((this.hasPermissionServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for hasPermission().");
            throw new InjectionException("No service implementation configured for hasPermission().");
        }
        ServiceResponse<AuthorizationRs> rs;
        this.hasPermissionServiceHandler.init();
        rs = this.hasPermissionServiceHandler.invoke(rq);
        this.hasPermissionServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationRs> hasPermissionByName(ServiceRequest<AuthorizationNameMsg> rq)
            throws AuthorizationException {
        if ((this.hasPermissionByNameServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for hasPermissionByName().");
            throw new InjectionException("No service implementation configured for hasPermissionByName().");
        }
        ServiceResponse<AuthorizationRs> rs;
        this.hasPermissionByNameServiceHandler.init();
        rs = this.hasPermissionByNameServiceHandler.invoke(rq);
        this.hasPermissionByNameServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<AuthorizationInformationRs> getInformation(ServiceRequest<UserRq> rq)
            throws AuthorizationException {
        if ((this.getInformationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for getInformation().");
            throw new InjectionException("No service implementation configured for getInformation().");
        }
        ServiceResponse<AuthorizationInformationRs> rs;
        this.getInformationServiceHandler.init();
        rs = this.getInformationServiceHandler.invoke(rq);
        this.getInformationServiceHandler.finish();
        return rs;
    }
}
