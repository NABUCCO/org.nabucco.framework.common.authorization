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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.view.AuthorizationGroupEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.close.AbstractDeleteDatatypeHandler;

/**
 * DeleteAuthorizationGroupHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class DeleteAuthorizationGroupHandlerImpl extends AbstractDeleteDatatypeHandler<AuthorizationGroupEditView>
        implements DeleteAuthorizationGroupHandler {

    @Override
    public String getId() {
        return AuthorizationGroupEditView.ID;
    }

    @Override
    public void deleteAuthorizationGroup() {
        super.run();
    }

    @Override
    protected boolean preClose(AuthorizationGroupEditView view) throws ClientException {

        AuthorizationGroupEditViewModel viewModel = view.getModel();
        AuthorizationGroup group = viewModel.getGroup();
        group.setDatatypeState(DatatypeState.DELETED);

        AuthorizationGroupEditBusinessModel businessModel = (AuthorizationGroupEditBusinessModel) Activator
                .getDefault().getModel().getBusinessModel(AuthorizationGroupEditBusinessModel.ID);

        businessModel.save(group, null);

        return super.preClose(view);
    }

}
