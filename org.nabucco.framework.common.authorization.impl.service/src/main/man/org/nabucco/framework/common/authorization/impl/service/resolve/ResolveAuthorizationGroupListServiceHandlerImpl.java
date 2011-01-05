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
package org.nabucco.framework.common.authorization.impl.service.resolve;

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceHelper;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupListMsg;

/**
 * ResolveAuthorizationGroupListServiceHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class ResolveAuthorizationGroupListServiceHandlerImpl extends
        ResolveAuthorizationGroupListServiceHandler {

    private static final long serialVersionUID = 1L;

    private PersistenceHelper helper;

    @Override
    protected AuthorizationGroupListMsg resolveAuthorizationGroupList(AuthorizationGroupListMsg msg)
            throws ResolveException {

        this.helper = new PersistenceHelper(super.getEntityManager());

        AuthorizationGroupListMsg response = new AuthorizationGroupListMsg();
        List<AuthorizationGroup> groupList = msg.getAuthorizationGroupList();

        for (AuthorizationGroup group : groupList) {

            try {
                AuthorizationGroup resolvedGroup = this.resolve(group);
                response.getAuthorizationGroupList().add(resolvedGroup);

            } catch (PersistenceException e) {
                throw new ResolveException("Cannot resolve AuthorizationGroup with id "
                        + group.getId(), e);
            } catch (Exception e) {
                throw new ResolveException("Cannot resolve AuthorizationGroup with id "
                        + group.getId(), e);
            }
        }

        return response;
    }

    /**
     * Resolve the current group and its child properties.
     * 
     * @param group
     *            the group to resolve
     * 
     * @return the resolved group
     * 
     * @throws PersistenceException
     *             when the group is not persistent and cannot be resolved
     */
    private AuthorizationGroup resolve(AuthorizationGroup group) throws PersistenceException {
        group = this.helper.find(AuthorizationGroup.class, group);

        group.getChildGroupList().size();
        group.getUserList().size();
        group.getRoleList().size();
        group.getPermissionList().size();

        return group;
    }

}
