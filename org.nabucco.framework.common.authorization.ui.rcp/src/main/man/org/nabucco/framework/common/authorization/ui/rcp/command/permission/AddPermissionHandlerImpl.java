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
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationPermissionMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.produce.ProduceAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view.AuthorizationPermissionEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.NabuccoAbstractAddDatatypeHandlerImpl;

/**
 * AddPermissionHandlerImpl
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AddPermissionHandlerImpl extends
        NabuccoAbstractAddDatatypeHandlerImpl<AuthorizationPermissionEditViewModel> implements
        AddPermissionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPermission() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(final AuthorizationPermissionEditViewModel viewModel) {
        final AuthorizationPermission freshAuthorizationUser = createFreshAuthorizationUser();
        viewModel.setPermission(freshAuthorizationUser);
        viewModel.setGroupSet(null);
        viewModel.setUserSet(null);
        viewModel.setRoleSet(null);
    }

    /**
     * Create new AuthorizationGroup.
     * 
     * @return createdAuthorizationGroup OR null if an error occured.
     */
    private AuthorizationPermission createFreshAuthorizationUser() {
        AuthorizationPermission result = null;
        try {
            final ProduceAuthorizationDelegate produceAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getProduceAuthorization();
            final AuthorizationPermissionMsg response = produceAuthorization
                    .produceAuthorizationPermission(new EmptyServiceMessage());

            result = response.getAuthorizationPermission();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditViewId() {
        return AuthorizationPermissionEditView.ID;
    }

}
