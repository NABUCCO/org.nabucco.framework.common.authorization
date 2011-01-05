/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;

/**
 * MaintainAuthorization<p/>Authorization maintenance service<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, Nicolas Moser, PRODYNA AG, 2010-01-18
 */
public interface MaintainAuthorization extends Service {

    /**
     * Missing description at method maintainAuthorizationGroup.
     *
     * @param rq the ServiceRequest<AuthorizationGroupMaintainMsg>.
     * @return the ServiceResponse<AuthorizationGroupMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationGroupMaintainMsg> maintainAuthorizationGroup(
            ServiceRequest<AuthorizationGroupMaintainMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainAuthorizationUser.
     *
     * @param rq the ServiceRequest<AuthorizationUserMaintainMsg>.
     * @return the ServiceResponse<AuthorizationUserMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationUserMaintainMsg> maintainAuthorizationUser(
            ServiceRequest<AuthorizationUserMaintainMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainAuthorizationRole.
     *
     * @param rq the ServiceRequest<AuthorizationRoleMaintainMsg>.
     * @return the ServiceResponse<AuthorizationRoleMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationRoleMaintainMsg> maintainAuthorizationRole(
            ServiceRequest<AuthorizationRoleMaintainMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainAuthorizationPermission.
     *
     * @param rq the ServiceRequest<AuthorizationPermissionMaintainMsg>.
     * @return the ServiceResponse<AuthorizationPermissionMaintainMsg>.
     * @throws MaintainException
     */
    ServiceResponse<AuthorizationPermissionMaintainMsg> maintainAuthorizationPermission(
            ServiceRequest<AuthorizationPermissionMaintainMsg> rq) throws MaintainException;
}
