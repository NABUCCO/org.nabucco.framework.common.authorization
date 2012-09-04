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

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationUserMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.produce.ProduceAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.view.AuthorizationUserEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractAddDatatypeHandlerImpl;

/**
 * Add user command manual implementation.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AddUserHandlerImpl extends AbstractAddDatatypeHandlerImpl<AuthorizationUserEditViewModel> implements
        AddUserHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(final AuthorizationUserEditViewModel viewModel) {
        final AuthorizationUser freshAuthorizationUser = createFreshAuthorizationUser();
        viewModel.setUser(freshAuthorizationUser);
        viewModel.setGroupSet(null);
    }

    /**
     * Create new AuthorizationGroup.
     * 
     * @return createdAuthorizationGroup OR null if an error occured.
     */
    private AuthorizationUser createFreshAuthorizationUser() {
        AuthorizationUser result = null;
        try {
            final ProduceAuthorizationDelegate produceAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getProduceAuthorization();
            final AuthorizationUserMsg response = produceAuthorization
                    .produceAuthorizationUser(new EmptyServiceMessage());

            result = response.getAuthorizationUser();
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
        return AuthorizationUserEditView.ID;
    }

}
