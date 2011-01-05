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
package org.nabucco.framework.common.authorization.impl.service;

import java.util.ArrayList;
import java.util.List;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponent;
import org.nabucco.framework.common.authorization.facade.component.AuthorizationComponentLocator;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;

import org.nabucco.framework.common.authorization.facade.exception.AuthorizationException;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRs;
import org.nabucco.framework.common.authorization.facade.message.search.AuthorizationSearchMsg;
import org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization;

/**
 * HasPermissionServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG, 2010-07-18
 */
public class HasPermissionServiceHandlerImpl extends HasPermissionServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected AuthorizationRs hasPermission(AuthorizationPermissionMsg msg)
            throws AuthorizationException {

        AuthorizationRs rsMsg = new AuthorizationRs();

        this.validateSubject();

        try {
            List<AuthorizationPermission> permissions = this.loadPermissionsForUser();

            Flag valid = new Flag(Boolean.FALSE);
            for (AuthorizationPermission permission : permissions) {
                if (permission.equals(msg.getAuthorizationPermission())) {
                    valid.setValue(Boolean.TRUE);
                }
            }
            
            rsMsg.setValid(valid);

        } catch (ConnectionException ce) {
            throw new AuthorizationException("Cannot connect to AuthorizationComponent.", ce);
        } catch (ServiceException se) {
            throw new AuthorizationException("Cannot locate Service 'SearchAuthorization'.", se);
        }

        return rsMsg;
    }

    /**
     * Validate the current subject.
     * 
     * @throws AuthorizationException
     */
    private void validateSubject() throws AuthorizationException {
        ServiceMessageContext context = super.getContext();
        Subject subject = context.getSubject();

        if (subject == null) {
            throw new AuthorizationException("Cannot authorize User for subject [null].");
        }
        if (subject.getUser() == null) {
            throw new AuthorizationException("Cannot authorize User [null].");
        }
        if (subject.getUser().getUsername() == null) {
            throw new AuthorizationException("Cannot authorize User with username [null].");
        }
        if (subject.getToken() == null) {
            throw new AuthorizationException("User "
                    + subject.getUser().getUsername() + " is not logged in.");
        }
    }

    /**
     * Loads the permissions for the current user.
     * 
     * @return the list of permissions.
     * 
     * @throws ConnectionException
     *             if no connection can be established
     * @throws ServiceException
     *             if the permissions cannot be loaded
     */
    private List<AuthorizationPermission> loadPermissionsForUser() throws ConnectionException,
            ServiceException {

        ServiceMessageContext context = super.getContext();
        Subject subject = context.getSubject();

        List<AuthorizationPermission> permissionList = new ArrayList<AuthorizationPermission>();

        AuthorizationComponent authorizationComponent = AuthorizationComponentLocator.getInstance()
                .getComponent();

        SearchAuthorization searchAuthorization = authorizationComponent.getSearchAuthorization();

        ServiceRequest<AuthorizationSearchMsg> request = new ServiceRequest<AuthorizationSearchMsg>(
                context);

        AuthorizationSearchMsg msg = new AuthorizationSearchMsg();
        request.setRequestMessage(msg);

        AuthorizationSearchParameter parameter = new AuthorizationSearchParameter();
        parameter.setType(AuthorizationType.USER);
        parameter.setId(subject.getUser().getId());
        msg.getParameterList().add(parameter);

        ServiceResponse<AuthorizationPermissionListMsg> response = searchAuthorization
                .searchAuthorizationPermission(request);

        if (response.getResponseMessage() != null) {
            permissionList.addAll(response.getResponseMessage().getAuthorizationPermissionList());
        }
        
        return permissionList;
    }

}
