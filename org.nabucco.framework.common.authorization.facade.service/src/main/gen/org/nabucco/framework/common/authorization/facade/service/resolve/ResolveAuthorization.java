/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.service.resolve;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationRoleMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserListMsg;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * ResolveAuthorization<p/>Authorization resolution service<p/>
 *
 * @version 1.0
 * @author Jens Wurm, PRODYNA AG, 2010-05-06
 */
public interface ResolveAuthorization extends Service {

    /**
     * Missing description at method resolveAuthorizationGroup.
     *
     * @param rq the ServiceRequest<AuthorizationGroupMsg>.
     * @return the ServiceResponse<AuthorizationGroupMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationGroupMaintainMsg> resolveAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationGroupList.
     *
     * @param rq the ServiceRequest<AuthorizationGroupListMsg>.
     * @return the ServiceResponse<AuthorizationGroupListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationGroupListMsg> resolveAuthorizationGroupList(
            ServiceRequest<AuthorizationGroupListMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationPermission.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionMsg>.
     * @return the ServiceResponse<AuthorizationPermissionMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationPermissionMaintainMsg> resolveAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationPermissionList.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionListMsg>.
     * @return the ServiceResponse<AuthorizationPermissionListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationPermissionListMsg> resolveAuthorizationPermissionList(
            ServiceRequest<AuthorizationPermissionListMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationRole.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationRoleMaintainMsg> resolveAuthorizationRole(
            ServiceRequest<AuthorizationRoleMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationRoleList.
     *
     * @param rq the ServiceRequest<AuthorizationRoleListMsg>.
     * @return the ServiceResponse<AuthorizationRoleListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationRoleListMsg> resolveAuthorizationRoleList(
            ServiceRequest<AuthorizationRoleListMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationUser.
     *
     * @param rq the ServiceRequest<AuthorizationUserMsg>.
     * @return the ServiceResponse<AuthorizationUserMaintainMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationUserMaintainMsg> resolveAuthorizationUser(
            ServiceRequest<AuthorizationUserMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveAuthorizationUserList.
     *
     * @param rq the ServiceRequest<AuthorizationUserListMsg>.
     * @return the ServiceResponse<AuthorizationUserListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<AuthorizationUserListMsg> resolveAuthorizationUserList(
            ServiceRequest<AuthorizationUserListMsg> rq) throws ResolveException;
}
