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
package org.nabucco.framework.common.authorization.impl.service.produce;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;

/**
 * ProduceAuthorizationPermissionServiceHandlerImpl
 * 
 * @author Stefanie Feld, PRODYNA AG
 */
public class ProduceAuthorizationPermissionServiceHandlerImpl extends
        ProduceAuthorizationPermissionServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public AuthorizationPermissionMsg produceAuthorizationPermission(EmptyServiceMessage msg)
            throws ProduceException {
        AuthorizationPermissionMsg authorizationPermissionMsg = new AuthorizationPermissionMsg();

        AuthorizationPermission authorizationPermission = new AuthorizationPermission();
        authorizationPermission.setDatatypeState(DatatypeState.INITIALIZED);

        authorizationPermission.setOwner(new Owner());
        authorizationPermission.setPermissionname(new Name());
        authorizationPermission.setDescription(new Description());
        authorizationPermission.setPermissionType(new CodeType());

        authorizationPermissionMsg.setAuthorizationPermission(authorizationPermission);

        return authorizationPermissionMsg;
    }

}
