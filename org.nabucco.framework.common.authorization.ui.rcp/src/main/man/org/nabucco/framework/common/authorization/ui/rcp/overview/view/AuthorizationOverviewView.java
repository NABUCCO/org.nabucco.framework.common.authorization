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
package org.nabucco.framework.common.authorization.ui.rcp.overview.view;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewAction;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewModel;
import org.nabucco.framework.plugin.base.component.overview.view.NabuccoOverviewView;

/**
 * AuthorizationOverviewView
 * <p/>
 * Overview for authorization.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationOverviewView extends NabuccoOverviewView {

    public final static String ID = "org.nabucco.framework.common.authorization.ui.rcp.overview.view";

    /**
     * Creates a new {@link AuthorizationOverviewView} instance.
     */
    public AuthorizationOverviewView() {
        createModel();
    }

    @Override
    protected void createFormControl(Form form) {
        this.getLayouter().layout(form.getBody(), getMessageManager(), getModel());
    }

    /**
     * Create the NABUCCO Overview ViewModel.
     * 
     * @see NabuccoOverviewModel
     */
    private void createModel() {
        NabuccoOverviewModel localModel = new NabuccoOverviewModel();

        NabuccoOverviewAction action = new NabuccoOverviewAction(
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.group.description",
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.group.name",
                "org.nabucco.framework.common.authorization.ui.edit.group.AuthorizationGroupEditView");
        localModel.getComponentActions().add(action);

        action = new NabuccoOverviewAction(
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.user.description",
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.user.name",
                "org.nabucco.framework.common.authorization.ui.edit.user.AuthorizationUserEditView");
        localModel.getComponentActions().add(action);

        action = new NabuccoOverviewAction(
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.role.description",
                "org.nabucco.framework.common.authorization.ui.rcp.overview.view.edit.role.name",
                "org.nabucco.framework.common.authorization.ui.edit.role.AuthorizationRoleEditView");
        localModel.getComponentActions().add(action);

        this.model = localModel;
    }

    @Override
    protected void createHeadControl(Composite head) {
    }

    @Override
    protected void createToolbarActions(IToolBarManager toolbarManager) {
    }

    @Override
    protected String getManagedFormTitle() {
        return I18N.i18n("org.nabucco.framework.common.authorization.ui.rcp.overview.view");
    }

    @Override
    public AuthorizationOverviewLayouter getLayouter() {
        return new AuthorizationOverviewLayouter();
    }

}
