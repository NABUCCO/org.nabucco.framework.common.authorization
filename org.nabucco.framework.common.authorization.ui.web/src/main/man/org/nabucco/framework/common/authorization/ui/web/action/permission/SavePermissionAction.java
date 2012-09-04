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
package org.nabucco.framework.common.authorization.ui.web.action.permission;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationPermissionMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.maintain.MaintainAuthorizationDelegate;

/**
 * SavePermissionAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SavePermissionAction extends SaveActionHandler<AuthorizationPermission> {

    @Override
    protected AuthorizationPermission saveDatatype(AuthorizationPermission permission, EditorItem editor,
            WebActionParameter parameter) throws ClientException {

        permission = this.maintainPermission(permission, parameter);

        this.addToParent(permission, editor);

        return permission;
    }

    /**
     * Add the permission to the parent datatype if exists.
     * 
     * @param permission
     *            the permission to add to the parent
     * @param editor
     *            the editor holding the source reference
     */
    private AuthorizationPermission maintainPermission(AuthorizationPermission permission, WebActionParameter parameter)
            throws ClientException {
        MaintainAuthorizationDelegate maintainService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getMaintainAuthorization();

        AuthorizationPermissionMaintainMsg rq = new AuthorizationPermissionMaintainMsg();
        rq.setAuthorizationPermission(permission);

        try {
            AuthorizationPermissionMaintainMsg rs = maintainService.maintainAuthorizationPermission(rq,
                    parameter.getSession());
            return rs.getAuthorizationPermission();
        } catch (MaintainException e) {
            throw new ActionException("Error maintaining Authorization Permission.", e);
        }
    }

    /**
     * Add the permission to the parent datatype if exists.
     * 
     * @param permission
     *            the permission to add to the parent
     * @param editor
     *            the editor holding the source reference
     */
    private void addToParent(AuthorizationPermission permission, EditorItem editor) {
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

            for (AuthorizationGroupPermissionRelation relation : group.getPermissionList()) {
                if (relation.getPermission() == null) {
                    continue;
                }

                if (relation.getPermission().getId().equals(permission.getId())) {
                    relation.setPermission(permission);
                    return;
                }
            }

            AuthorizationGroupPermissionRelation relation = new AuthorizationGroupPermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(permission);
            group.getPermissionList().add(relation);

        } else if (sourceDatatype instanceof AuthorizationUser) {
            AuthorizationUser user = (AuthorizationUser) sourceDatatype;

            for (AuthorizationUserPermissionRelation relation : user.getPermissionList()) {
                if (relation.getPermission() == null) {
                    continue;
                }

                if (relation.getPermission().getId().equals(permission.getId())) {
                    relation.setPermission(permission);
                    return;
                }
            }

            AuthorizationUserPermissionRelation relation = new AuthorizationUserPermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(permission);
            user.getPermissionList().add(relation);

        } else if (sourceDatatype instanceof AuthorizationRole) {
            AuthorizationRole role = (AuthorizationRole) sourceDatatype;

            for (AuthorizationRolePermissionRelation relation : role.getPermissionList()) {
                if (relation.getPermission() == null) {
                    continue;
                }

                if (relation.getPermission().getId().equals(permission.getId())) {
                    relation.setPermission(permission);
                    return;
                }
            }

            AuthorizationRolePermissionRelation relation = new AuthorizationRolePermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(permission);
            role.getPermissionList().add(relation);
        }
    }
}
