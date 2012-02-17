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
package org.nabucco.framework.common.authorization.impl.service.extension.resolving;

import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.extension.schema.property.PropertyExtension;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.impl.service.aspect.resolving.ResolvingAspect;
import org.nabucco.framework.base.impl.service.aspect.resolving.ResolvingException;
import org.nabucco.framework.base.impl.service.aspect.resolving.ResolvingSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * AuthorizationResolver
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationResolver extends ResolvingSupport implements ResolvingAspect {

    @Override
    public void configure(PropertyExtension properties) {
        // Nothing to configure!
    }

    @Override
    public void resolveBefore(ServiceMessage requestMessage) throws ResolvingException {
        // Nothing to do before!
    }

    @Override
    public void resolveAfter(ServiceMessage requestMessage, ServiceMessage responseMessage) throws ResolvingException {
        if (responseMessage instanceof AuthorizationGroupMaintainMsg) {
            this.resolveGroupCodes(((AuthorizationGroupMaintainMsg) responseMessage).getAuthorizationGroup());
        } else if (responseMessage instanceof AuthorizationUserMaintainMsg) {
            this.resolveUserCodes(((AuthorizationUserMaintainMsg) responseMessage).getAuthorizationUser());
        } else if (responseMessage instanceof AuthorizationRoleMaintainMsg) {
            this.resolveRoleCodes(((AuthorizationRoleMaintainMsg) responseMessage).getAuthorizationRole());
        } else if (responseMessage instanceof AuthorizationPermissionMaintainMsg) {
            this.resolveRoleCodes(((AuthorizationPermissionMaintainMsg) responseMessage).getAuthorizationPermission());
        }
    }

    /**
     * Resolves the dynamic codes for authorization group.
     * 
     * @param group
     *            the group to resolve
     * 
     * @throws ResolvingException
     *             when the code cannot be resolved
     */
    private void resolveGroupCodes(AuthorizationGroup group) throws ResolvingException {
        Code groupType = super.resolveCode(group.getGroupTypeRefId());
        group.setGroupType(groupType);
    }

    /**
     * Resolves the dynamic codes for authorization user.
     * 
     * @param user
     *            the user to resolve
     * 
     * @throws ResolvingException
     *             when the code cannot be resolved
     */
    private void resolveUserCodes(AuthorizationUser user) throws ResolvingException {
        Code userType = super.resolveCode(user.getUserTypeRefId());
        user.setUserType(userType);
    }

    /**
     * Resolves the dynamic codes for authorization role.
     * 
     * @param role
     *            the role to resolve
     * 
     * @throws ResolvingException
     *             when the code cannot be resolved
     */
    private void resolveRoleCodes(AuthorizationRole role) throws ResolvingException {
        Code roleType = super.resolveCode(role.getRoleTypeRefId());
        role.setRoleType(roleType);
    }

    /**
     * Resolves the dynamic codes for authorization permission.
     * 
     * @param permission
     *            the permission to resolve
     * 
     * @throws ResolvingException
     *             when the code cannot be resolved
     */
    private void resolveRoleCodes(AuthorizationPermission permission) throws ResolvingException {
        Code permissionType = super.resolveCode(permission.getPermissionTypeRefId());
        permission.setPermissionType(permissionType);
    }
}
