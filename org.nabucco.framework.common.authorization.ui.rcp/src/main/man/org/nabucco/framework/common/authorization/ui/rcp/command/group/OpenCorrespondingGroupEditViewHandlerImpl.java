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
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.view.AuthorizationGroupEditView;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.AuthorizationGroupListView;
import org.nabucco.framework.plugin.base.command.AbstractOpenCorrespondingEditViewHandlerImpl;

/**
 * Manual implementation for opening a an groupEditView.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class OpenCorrespondingGroupEditViewHandlerImpl
        extends
        AbstractOpenCorrespondingEditViewHandlerImpl<AuthorizationGroupEditViewModel, AuthorizationGroup>
        implements OpenCorrespondingGroupEditViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCorrespondingGroupEditView() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void openCorrespondingEditView(AuthorizationGroupEditViewModel model,
            AuthorizationGroup authorizationGroup) {
        model.setGroup(authorizationGroup);
    }

    /**
     * Getter.
     * 
     * @return value
     */
    @Override
    protected String getListViewId() {
        return AuthorizationGroupListView.ID;
    }

    /**
     * Getter.
     * 
     * @return value
     */
    @Override
    protected String getEditorViewId() {
        return AuthorizationGroupEditView.ID;
    }
}