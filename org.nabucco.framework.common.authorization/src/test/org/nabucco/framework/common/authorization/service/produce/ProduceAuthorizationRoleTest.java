/*
* Copyright 2010 PRODYNA AG
*
* Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.opensource.org/licenses/eclipse-1.0.php or
* http://www.nabucco-source.org/nabucco-license.html
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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;

/**
 * ProduceAuthorizationGroupTest
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class ProduceAuthorizationRoleTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testProduceRole() throws Exception {
        EmptyServiceMessage emptyServiceMsg = new EmptyServiceMessage();
        ServiceRequest<EmptyServiceMessage> emptySR = new ServiceRequest<EmptyServiceMessage>(super
                .createServiceContext());
        emptySR.setRequestMessage(emptyServiceMsg);

        ServiceResponse<AuthorizationRoleMsg> srAuthorizationRole = component
                .getProduceAuthorization().produceAuthorizationRole(emptySR);

        Assert.assertNotNull(srAuthorizationRole);
        Assert.assertNotNull(srAuthorizationRole.getResponseMessage());
        Assert.assertNotNull(srAuthorizationRole.getResponseMessage().getAuthorizationRole());

        AuthorizationRole role = srAuthorizationRole.getResponseMessage().getAuthorizationRole();

        // all Basetypes are initialized
        Assert.assertNotNull(role);
        Assert.assertEquals(DatatypeState.INITIALIZED, role.getDatatypeState());
        Assert.assertNotNull(role.getOwner());
        Assert.assertNotNull(role.getRolename());
        Assert.assertNotNull(role.getDescription());
        Assert.assertNotNull(role.getRoleType());

    }
}
