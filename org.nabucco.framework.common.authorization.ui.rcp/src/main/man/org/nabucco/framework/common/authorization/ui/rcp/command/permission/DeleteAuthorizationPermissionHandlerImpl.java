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
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view.AuthorizationPermissionEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;

/**
 * DeleteAuthorizationPermissionHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class DeleteAuthorizationPermissionHandlerImpl extends
        AbstractDeleteDatatypeHandler<AuthorizationPermissionEditView> implements DeleteAuthorizationPermissionHandler {

    @Override
    public String getId() {
        return AuthorizationPermissionEditView.ID;
    }

    @Override
    public void deleteAuthorizationPermission() {
        super.run();
    }

    @Override
    protected boolean preClose(AuthorizationPermissionEditView view) throws ClientException {

        AuthorizationPermissionEditViewModel viewModel = view.getModel();
        AuthorizationPermission permission = viewModel.getPermission();
        permission.setDatatypeState(DatatypeState.DELETED);

        AuthorizationPermissionEditBusinessModel businessModel = (AuthorizationPermissionEditBusinessModel) Activator
                .getDefault().getModel().getBusinessModel(AuthorizationPermissionEditBusinessModel.ID);

        businessModel.save(permission, null, null, null);

        return super.preClose(view);
    }

}
