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
package org.nabucco.framework.common.authorization.ui.rcp.command.user;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.view.AuthorizationUserEditView;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.AuthorizationUserListView;
import org.nabucco.framework.plugin.base.command.AbstractOpenCorrespondingEditViewHandlerImpl;

/**
 * OpenCorrespondingUserEditViewHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class OpenCorrespondingUserEditViewHandlerImpl extends
        AbstractOpenCorrespondingEditViewHandlerImpl<AuthorizationUserEditViewModel, AuthorizationUser> implements
        OpenCorrespondingUserEditViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCorrespondingUserEditView() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditorViewId() {
        return AuthorizationUserEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getListViewId() {
        return AuthorizationUserListView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void openCorrespondingEditView(AuthorizationUserEditViewModel model, AuthorizationUser authorizationUser) {
        model.setUser(authorizationUser);
    }
}
