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
import org.nabucco.framework.base.ui.web.action.handler.picker.ProceedPickerDialogSelection;
import org.nabucco.framework.base.ui.web.action.result.RefreshItem;
import org.nabucco.framework.base.ui.web.action.result.WebActionResult;
import org.nabucco.framework.base.ui.web.component.WebElementType;
import org.nabucco.framework.base.ui.web.component.dialog.PickerDialog;
import org.nabucco.framework.base.ui.web.component.work.WorkItem;
import org.nabucco.framework.base.ui.web.component.work.editor.EditorItem;
import org.nabucco.framework.base.ui.web.component.work.editor.RelationTab;
import org.nabucco.framework.base.ui.web.servlet.util.NabuccoServletUtil;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;

/**
 * SelectPermissionFromPickerDialogAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SelectPermissionFromPickerDialogAction extends ProceedPickerDialogSelection {

    @Override
    protected void addToEditorRelationTab(String value, PickerDialog pickerDialog, RelationTab source,
            WebActionResult result) throws ClientException {

        String propertyName = source.getProperty();
        Datatype newElement = pickerDialog.getTableModel().getDatatypeByObjectId(Long.parseLong(value));

        if (!(newElement instanceof AuthorizationPermission)) {
            return;
        }

        AuthorizationPermission user = (AuthorizationPermission) newElement;

        WorkItem selectedWorkItem = NabuccoServletUtil.getSelectedWorkItem();

        if (selectedWorkItem.getType() != WebElementType.EDITOR) {
            throw new ClientException(
                    "Cannot add permission to the given source item because the type of the source item is not supported.");
        }

        Datatype sourceDatatype = ((EditorItem) selectedWorkItem).getModel().getDatatype();

        if (sourceDatatype instanceof AuthorizationGroup) {
            AuthorizationGroupPermissionRelation relation = new AuthorizationGroupPermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(user);
            super.addProperty(relation, sourceDatatype, propertyName);
        } else if (sourceDatatype instanceof AuthorizationUser) {
            AuthorizationUserPermissionRelation relation = new AuthorizationUserPermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(user);
            super.addProperty(relation, sourceDatatype, propertyName);
        } else if (sourceDatatype instanceof AuthorizationRole) {
            AuthorizationRolePermissionRelation relation = new AuthorizationRolePermissionRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setPermission(user);
            super.addProperty(relation, sourceDatatype, propertyName);
        }

        result.addItem(new RefreshItem(WebElementType.EDITOR_RELATION_AREA, selectedWorkItem.getInstanceId()));
        result.addItem(new RefreshItem(WebElementType.WORK_AREA));
    }

}
