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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * SelectUserFromPickerDialogAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SelectUserFromPickerDialogAction extends ProceedPickerDialogSelection {

    @Override
    protected void addToEditorRelationTab(String value, PickerDialog pickerDialog, RelationTab source,
            WebActionResult result) throws ClientException {

        String propertyName = source.getProperty();
        Datatype newElement = pickerDialog.getTableModel().getDatatypeByObjectId(Long.parseLong(value));

        if (!(newElement instanceof AuthorizationUser)) {
            return;
        }

        AuthorizationUser user = (AuthorizationUser) newElement;

        WorkItem selectedWorkItem = NabuccoServletUtil.getSelectedWorkItem();

        if (selectedWorkItem.getType() != WebElementType.EDITOR) {
            throw new ClientException(
                    "Cannot add user to the given source item because the type of the source item is not supported.");
        }

        Datatype sourceDatatype = ((EditorItem) selectedWorkItem).getModel().getDatatype();

        if (sourceDatatype instanceof AuthorizationGroup) {
            AuthorizationGroupUserRelation relation = new AuthorizationGroupUserRelation();
            relation.setDatatypeState(DatatypeState.INITIALIZED);
            relation.setUser(user);
            super.addProperty(relation, sourceDatatype, propertyName);
        }

        result.addItem(new RefreshItem(WebElementType.EDITOR_RELATION_AREA, selectedWorkItem.getInstanceId()));
        result.addItem(new RefreshItem(WebElementType.WORK_AREA));
    }

}
