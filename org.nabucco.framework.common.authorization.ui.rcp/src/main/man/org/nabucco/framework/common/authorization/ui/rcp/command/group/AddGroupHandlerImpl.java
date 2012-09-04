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
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.message.AuthorizationGroupMsg;
import org.nabucco.framework.common.authorization.ui.rcp.communication.AuthorizationComponentServiceDelegateFactory;
import org.nabucco.framework.common.authorization.ui.rcp.communication.produce.ProduceAuthorizationDelegate;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.view.AuthorizationGroupEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractAddDatatypeHandlerImpl;

/**
 * Implements AddGroupHandler.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AddGroupHandlerImpl extends AbstractAddDatatypeHandlerImpl<AuthorizationGroupEditViewModel> implements
        AddGroupHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGroup() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditViewId() {
        return AuthorizationGroupEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(final AuthorizationGroupEditViewModel viewModel) {
        final AuthorizationGroup freshAuthorizationGroup = createNewAuthorizationGroup();
        viewModel.setGroup(freshAuthorizationGroup);
        viewModel.setParentGroup(null);
    }

    /**
     * Create new AuthorizationGroup.
     * 
     * @return createdAuthorizationGroup OR null if an error occured.
     */
    private AuthorizationGroup createNewAuthorizationGroup() {
        AuthorizationGroup result = null;
        try {
            final ProduceAuthorizationDelegate produceAuthorization = AuthorizationComponentServiceDelegateFactory
                    .getInstance().getProduceAuthorization();
            final AuthorizationGroupMsg response = produceAuthorization
                    .produceAuthorizationGroup(new EmptyServiceMessage());
            result = response.getAuthorizationGroup();
        } catch (final ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

}
