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
package org.nabucco.framework.common.authorization.impl.service.maintain;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;
import org.nabucco.framework.common.authorization.impl.service.maintain.support.AuthorizationMaintainSupport;

/**
 * MaintainAuthorizationGroupListServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationGroupListServiceHandlerImpl extends MaintainAuthorizationGroupListServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected AuthorizationGroupListMsg maintainAuthorizationGroupList(AuthorizationGroupListMsg rq)
            throws MaintainException {

        AuthorizationGroupListMsg rs = new AuthorizationGroupListMsg();

        for (AuthorizationGroup group : rq.getAuthorizationGroupList()) {
            if (group != null) {
                rs.getAuthorizationGroupList().add(this.maintainGroup(group));
            }
        }

        return rs;
    }

    /**
     * Maintains the group.
     * 
     * @param group
     *            the authorization group to maintain
     * 
     * @throws MaintainException
     *             when the group cannot be maintained
     */
    private AuthorizationGroup maintainGroup(AuthorizationGroup group) throws MaintainException {

        String name = String.valueOf(group.getGroupname());

        try {
            AuthorizationMaintainSupport support = new AuthorizationMaintainSupport(super.getPersistenceManager());
            return support.maintainAuthorizationGroup(group);
        } catch (PersistenceException pe) {
            throw new MaintainException("Error maintaining AuthorizationGroup '" + name + "'.", pe);
        }
    }
}
