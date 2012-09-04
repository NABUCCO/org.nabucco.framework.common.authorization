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
package org.nabucco.framework.common.authorization.service.produce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;

/**
 * ProduceAuthorizationGroupTest
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class ProduceAuthorizationGroupTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testProduceGroup() throws Exception {
        EmptyServiceMessage emptyServiceMsg = new EmptyServiceMessage();
        ServiceRequest<EmptyServiceMessage> emptySR = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext());
        emptySR.setRequestMessage(emptyServiceMsg);

        ServiceResponse<AuthorizationGroupMsg> srAuthorizationGroup = component.getProduceAuthorization()
                .produceAuthorizationGroup(emptySR);

        Assert.assertNotNull(srAuthorizationGroup);
        Assert.assertNotNull(srAuthorizationGroup.getResponseMessage());
        Assert.assertNotNull(srAuthorizationGroup.getResponseMessage().getAuthorizationGroup());

        AuthorizationGroup group = srAuthorizationGroup.getResponseMessage().getAuthorizationGroup();

        // all Basetypes are initialized
        Assert.assertNotNull(group);
        Assert.assertEquals(DatatypeState.INITIALIZED, group.getDatatypeState());
        Assert.assertNotNull(group.getOwner());
        Assert.assertNotNull(group.getGroupname());
        Assert.assertNotNull(group.getDescription());

    }
}
