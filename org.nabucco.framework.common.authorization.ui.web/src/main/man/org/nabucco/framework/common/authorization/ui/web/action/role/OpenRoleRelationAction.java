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
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.ui.web.action.parameter.WebActionParameter;
import org.nabucco.framework.base.ui.web.component.work.editor.EditorItem;
import org.nabucco.framework.base.ui.web.model.work.EditorModel;
import org.nabucco.framework.base.ui.web.servlet.util.NabuccoServletUtil;
import org.nabucco.framework.base.ui.web.servlet.util.path.NabuccoServletPathType;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;

/**
 * OpenRoleRelationAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenRoleRelationAction extends OpenRoleAction {

    @Override
    protected AuthorizationRole loadDatatype(Long relationId, WebActionParameter parameter) throws ClientException {

        if (relationId == null) {
            return null;
        }

        String editorId = parameter.get(NabuccoServletPathType.EDITOR);
        EditorItem editor = NabuccoServletUtil.getEditor(editorId);
        EditorModel model = editor.getModel();

        Datatype datatype = model.getDatatype();

        if (datatype instanceof AuthorizationGroup) {
            for (AuthorizationGroupRoleRelation relation : ((AuthorizationGroup) datatype).getRoleList()) {
                if (relation.getId().equals(relationId)) {
                    return super.loadDatatype(relation.getRole().getId(), parameter);
                }
            }
        }

        if (datatype instanceof AuthorizationUser) {
            for (AuthorizationUserRoleRelation relation : ((AuthorizationUser) datatype).getRoleList()) {
                if (relation.getId().equals(relationId)) {
                    return super.loadDatatype(relation.getRole().getId(), parameter);
                }
            }
        }

        return null;
    }

}
