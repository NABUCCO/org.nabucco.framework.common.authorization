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
package org.nabucco.framework.common.authorization.impl.service.maintain;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.impl.service.maintain.support.AuthorizationMaintainSupport;

/**
 * MaintainAuthorizationGroupServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class MaintainAuthorizationGroupServiceHandlerImpl extends
        MaintainAuthorizationGroupServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    private AuthorizationGroup group;

    private AuthorizationGroup parentGroup;

    @Override
    public AuthorizationGroupMaintainMsg maintainAuthorizationGroup(
            AuthorizationGroupMaintainMsg msg) throws MaintainException {

        this.group = msg.getAuthorizationGroup();
        this.parentGroup = msg.getParentAuthorizationGroup();

        this.helper = new PersistenceHelper(super.getEntityManager());

        this.maintain();

        AuthorizationGroupMaintainMsg rsMsg = new AuthorizationGroupMaintainMsg();
        rsMsg.setAuthorizationGroup(this.group);
        rsMsg.setParentAuthorizationGroup(this.parentGroup);

        return rsMsg;
    }

    /**
     * Maintain the {@link AuthorizationGroup} instance.
     * 
     * @throws MaintainException
     *             when the persistence operation failed
     */
    private void maintain() throws MaintainException {
        if (this.group.equals(this.parentGroup)) {
            throw new MaintainException(
                    "Error maintaining AuthorizationGroup. Parent AuthorizationGroup must not reference on itsself.");
        }

        try {
            this.maintainGroup();

            if (this.parentGroup != null && this.group.getDatatypeState() != DatatypeState.DELETED) {
                this.maintainParentGroup();
            }

        } catch (PersistenceException pe) {
            throw new MaintainException("Error maintaining AuthorizationGroup.", pe);
        }
    }

    /**
     * Maintains the group.
     * 
     * @throws PersistenceException
     */
    private void maintainGroup() throws PersistenceException {
        AuthorizationMaintainSupport support = new AuthorizationMaintainSupport(this.helper);
        this.group = support.maintainAuthorizationGroup(this.group);
    }

    /**
     * Maintains the parent group.
     * 
     * @throws PersistenceException
     *             when the parent group is not persistent
     */
    private void maintainParentGroup() throws PersistenceException {
        if (this.parentGroup.getId() == null) {
            throw new PersistenceException(
                    "Cannot modify non-persistent parent AuthorizationGroup.");
        }

        this.parentGroup = super.getEntityManager().merge(this.parentGroup);
        this.parentGroup.getChildGroupList().add(this.group);
    }

}
