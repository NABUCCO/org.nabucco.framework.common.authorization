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
package org.nabucco.framework.common.authorization.ui.web.action.group;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.client.action.ActionException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.ui.web.action.handler.SaveActionHandler;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.base.ui.web.component.work.WorkItemType;
import org.nabucco.framework.base.ui.web.component.work.editor.EditorItem;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.maintain.AuthorizationGroupMaintainMsg;
import org.nabucco.framework.common.authorization.ui.web.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.web.communication.maintain.MaintainAuthorizationDelegate;

/**
 * SaveGroupAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SaveGroupAction extends SaveActionHandler<AuthorizationGroup> {

    @Override
    protected AuthorizationGroup saveDatatype(AuthorizationGroup group, EditorItem editor, WebActionParameter parameter)
            throws ClientException {

        group = this.maintain(group, parameter);

        this.addToParent(group, editor);

        return group;
    }

    /**
     * Maintain the group to the database.
     * 
     * @param group
     *            the group to maintain
     * @param parameter
     *            the web action parameter
     * 
     * @return the maintained group
     * 
     * @throws ClientException
     *             when the group cannot be maintained
     */
    private AuthorizationGroup maintain(AuthorizationGroup group, WebActionParameter parameter) throws ClientException,
            ActionException {
        MaintainAuthorizationDelegate maintainService = AuthorizationComponentServiceDelegateFactory.getInstance()
                .getMaintainAuthorization();

        AuthorizationGroupMaintainMsg rq = new AuthorizationGroupMaintainMsg();
        rq.setAuthorizationGroup(group);

        try {
            AuthorizationGroupMaintainMsg rs = maintainService.maintainAuthorizationGroup(rq, parameter.getSession());
            return rs.getAuthorizationGroup();
        } catch (MaintainException e) {
            throw new ActionException("Error maintaining Authorization Group.", e);
        }
    }

    /**
     * Add the group to the parent datatype if exists.
     * 
     * @param group
     *            the group to add to the parent
     * @param editor
     *            the editor holding the source reference
     */
    private void addToParent(AuthorizationGroup group, EditorItem editor) {
        if (editor.getSource() == null) {
            return;
        }
        if (editor.getSource().getItemType() != WorkItemType.EDITOR) {
            return;
        }

        EditorItem sourceEditor = (EditorItem) editor.getSource();
        Datatype sourceDatatype = sourceEditor.getModel().getDatatype();

        if (sourceDatatype instanceof AuthorizationGroup) {
            AuthorizationGroup parentGroup = (AuthorizationGroup) sourceDatatype;

            for (int i = 0; i < parentGroup.getChildGroupList().size(); i++) {
                AuthorizationGroup existingGroup = parentGroup.getChildGroupList().get(i);
                if (existingGroup.getId().equals(group.getId())) {
                    parentGroup.getChildGroupList().set(i, group);
                    return;
                }
            }

            parentGroup.getChildGroupList().add(group);
        }
    }
}
