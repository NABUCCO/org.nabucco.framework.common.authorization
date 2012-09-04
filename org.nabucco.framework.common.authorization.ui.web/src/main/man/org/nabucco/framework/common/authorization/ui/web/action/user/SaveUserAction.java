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
package org.nabucco.framework.common.authorization.ui.web.action.user;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationUserMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.maintain.MaintainAuthorizationDelegate;

/**
 * SaveUserAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SaveUserAction extends SaveActionHandler<AuthorizationUser> {

    @Override
    protected AuthorizationUser saveDatatype(AuthorizationUser user, EditorItem editor, WebActionParameter parameter)
            throws ClientException {

        user = this.maintainUser(user, parameter);

        this.addToParent(user, editor);

        return user;
    }

    /**
     * Maintain the user to the database.
     * 
     * @param user
     *            the user to maintain
     * @param parameter
     *            the web action parameter
     * 
     * @return the maintained user
     * 
     * @throws ClientException
     *             when the user cannot be maintained
     */
    private AuthorizationUser maintainUser(AuthorizationUser user, WebActionParameter parameter) throws ClientException {
        MaintainAuthorizationDelegate maintainService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getMaintainAuthorization();

        AuthorizationUserMaintainMsg rq = new AuthorizationUserMaintainMsg();
        rq.setAuthorizationUser(user);

        try {
            AuthorizationUserMaintainMsg rs = maintainService.maintainAuthorizationUser(rq, parameter.getSession());
            return rs.getAuthorizationUser();
        } catch (MaintainException e) {
            throw new ActionException("Error maintaining Authorization User.", e);
        }
    }

    /**
     * Add the user to the parent datatype if exists.
     * 
     * @param user
     *            the user to add to the parent
     * @param editor
     *            the editor holding the source reference
     */
    private void addToParent(AuthorizationUser user, EditorItem editor) {
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

            for (AuthorizationGroupUserRelation relation : group.getUserList()) {
                if (relation.getUser() == null) {
                    continue;
                }

                if (relation.getUser().getId().equals(user.getId())) {
                    relation.setUser(user);
                    return;
                }
            }

            AuthorizationGroupUserRelation relation = new AuthorizationGroupUserRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setUser(user);
            group.getUserList().add(relation);
        }
    }
}
