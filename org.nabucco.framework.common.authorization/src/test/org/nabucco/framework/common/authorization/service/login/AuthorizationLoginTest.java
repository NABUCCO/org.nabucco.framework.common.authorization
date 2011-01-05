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
package org.nabucco.framework.common.authorization.service.login;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.test.RuntimeTestSupport;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.login.LoginMsg;
import org.nabucco.framework.common.authorization.facade.message.login.LoginRs;
import org.nabucco.framework.common.authorization.util.AuthorizationTestUtility;

/**
 * AuthorizationLoginTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationLoginTest extends RuntimeTestSupport {

    private AuthorizationComponent component;

    @Before
    public void setUp() throws Exception {
        component = super.getComponent(AuthorizationComponentLocator.getInstance());
    }

    @Test
    public void testLogin() throws Exception {

        AuthorizationUser user = this.createUser();

        LoginMsg msg = new LoginMsg();
        msg.setUsername(new UserId("testuser"));
        msg.setPassword(new Password("test"));

        ServiceRequest<LoginMsg> rq = new ServiceRequest<LoginMsg>(super.createServiceContext());
        rq.setRequestMessage(msg);

        ServiceResponse<LoginRs> rs = this.component.getLogin().login(rq);

        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs.getResponseMessage());

        Subject subject = rs.getResponseMessage().getSubject();

        Assert.assertNotNull(subject);
        Assert.assertNotNull(subject.getUserId());
        Assert.assertEquals("testuser", subject.getUserId().getValue());

        Assert.assertNotNull(subject.getToken());

        user = (AuthorizationUser) subject.getUser();

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getUsername());
        Assert.assertEquals("testuser", user.getUsername().getValue());

        AuthorizationTestUtility.remove(component, user);
    }

    private AuthorizationUser createUser() throws ServiceException {
        AuthorizationUser user = AuthorizationTestUtility.dummyUser("testuser");
        return AuthorizationTestUtility.create(component, user);
    }
}
