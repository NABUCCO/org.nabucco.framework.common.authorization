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
package org.nabucco.framework.common.authorization.ui.web.action.role;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.client.action.ActionException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.ui.web.action.handler.SaveActionHandler;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.base.ui.web.component.work.WorkItemType;
import org.nabucco.framework.base.ui.web.component.work.editor.EditorItem;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationRoleMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.maintain.MaintainAuthorizationDelegate;

/**
 * SaveRoleAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SaveRoleAction extends SaveActionHandler<AuthorizationRole> {

    @Override
    protected AuthorizationRole saveDatatype(AuthorizationRole role, EditorItem editor, WebActionParameter parameter)
            throws ClientException {

        role = this.maintainRole(role, parameter);

        this.addToParent(role, editor);

        return role;
    }

    /**
     * Maintain the role to the database.
     * 
     * @param role
     *            the role to maintain
     * @param parameter
     *            the web action parameter
     * 
     * @return the maintained role
     * 
     * @throws ClientException
     *             when the role cannot be maintained
     */
    private AuthorizationRole maintainRole(AuthorizationRole role, WebActionParameter parameter) throws ClientException {
        MaintainAuthorizationDelegate maintainService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getMaintainAuthorization();

        AuthorizationRoleMaintainMsg rq = new AuthorizationRoleMaintainMsg();
        rq.setAuthorizationRole(role);

        try {
            AuthorizationRoleMaintainMsg rs = maintainService.maintainAuthorizationRole(rq, parameter.getSession());
            return rs.getAuthorizationRole();
        } catch (MaintainException e) {
            throw new ActionException("Error maintaining Authorization Role.", e);
        }
    }

    /**
     * Add the role to the parent datatype if exists.
     * 
     * @param role
     *            the role to add to the parent
     * @param editor
     *            the editor holding the source reference
     */
    private void addToParent(AuthorizationRole role, EditorItem editor) {
        if (editor.getSource() == null) {
            return;
        }
        if (editor.getSource().getItemType() != WorkItemType.EDITOR) {
            return;
        }

        EditorItem sourceEditor = (EditorItem) editor.getSource();
        Datatype sourceDatatype = sourceEditor.getModel().getDatatype();

        if (sourceDatatype instanceof AuthorizationGroup) {
            AuthorizationGroup group = (AuthorizationGroup) sourceDatatype;

            for (AuthorizationGroupRoleRelation relation : group.getRoleList()) {
                if (relation.getRole() == null) {
                    continue;
                }

                if (relation.getRole().getId().equals(role.getId())) {
                    relation.setRole(role);
                    return;
                }
            }

            AuthorizationGroupRoleRelation relation = new AuthorizationGroupRoleRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setRole(role);
            group.getRoleList().add(relation);

        } else if (sourceDatatype instanceof AuthorizationUser) {
            AuthorizationUser user = (AuthorizationUser) sourceDatatype;

            for (AuthorizationUserRoleRelation relation : user.getRoleList()) {
                if (relation.getRole() == null) {
                    continue;
                }

                if (relation.getRole().getId().equals(role.getId())) {
                    relation.setRole(role);
                    return;
                }
            }

            AuthorizationUserRoleRelation relation = new AuthorizationUserRoleRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setRole(role);
            user.getRoleList().add(relation);
        }
    }
}
