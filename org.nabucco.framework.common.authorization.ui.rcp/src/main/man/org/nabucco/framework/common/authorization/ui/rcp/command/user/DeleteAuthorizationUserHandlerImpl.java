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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.command.user.DeleteAuthorizationUserHandler;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.view.AuthorizationUserEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;

/**
 * DeleteAuthorizationUserHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class DeleteAuthorizationUserHandlerImpl extends AbstractDeleteDatatypeHandler<AuthorizationUserEditView>
        implements DeleteAuthorizationUserHandler {

    @Override
    public String getId() {
        return AuthorizationUserEditView.ID;
    }

    @Override
    public void deleteAuthorizationUser() {
        super.run();
    }

    @Override
    protected boolean preClose(AuthorizationUserEditView view) throws ClientException {

        AuthorizationUserEditViewModel viewModel = view.getModel();
        AuthorizationUser user = viewModel.getUser();
        user.setDatatypeState(DatatypeState.DELETED);

        AuthorizationUserEditBusinessModel businessModel = (AuthorizationUserEditBusinessModel) Activator.getDefault()
                .getModel().getBusinessModel(AuthorizationUserEditBusinessModel.ID);

        businessModel.save(user, null);

        return super.preClose(view);
    }

}
