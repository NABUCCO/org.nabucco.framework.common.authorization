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
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.common.authorization.ui.rcp.search.permission.model.AuthorizationPermissionSearchViewModel;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationPermissionSearchViewWidgetFactory
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationPermissionSearchViewWidgetFactory extends WidgetFactory {

    private AuthorizationPermissionSearchViewModel model;

    public static final String LABEL_NAMETEXT = "permission.permissionname";

    public static final String OBSERVE_VALUE_NAMETEXT = AuthorizationPermissionSearchViewModel.PROPERTY_PERMISSION_PERMISSIONNAME;

    public static final String LABEL_OWNERTEXT = "permission.owner";

    public static final String OBSERVE_VALUE_OWNERTEXT = AuthorizationPermissionSearchViewModel.PROPERTY_PERMISSION_OWNER;

    public static final String LABEL_DESCRIPTIONTEXT = "permission.description";

    public static final String OBSERVE_VALUE_DESCRIPTIONTEXT = AuthorizationPermissionSearchViewModel.PROPERTY_PERMISSION_DESCRIPTION;

    /**
     * Constructs a new AuthorizationPermissionSearchViewWidgetFactory instance.
     * 
     * @param aModel
     *            the AuthorizationPermissionSearchViewModel.
     * @param nabuccoFormToolKit
     *            the NabuccoFormToolkit.
     */
    public AuthorizationPermissionSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final AuthorizationPermissionSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
    }

    /**
     * CreateLabelNameText.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelNameText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAMETEXT);
    }

    /**
     * CreateInputFieldNameText.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldNameText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_NAMETEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwnerText.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelOwnerText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNERTEXT);
    }

    /**
     * CreateInputFieldOwnerText.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldOwnerText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_OWNERTEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelDescriptionText.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelDescriptionText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTIONTEXT);
    }

    /**
     * CreateInputFieldDescriptionText.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescriptionText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_DESCRIPTIONTEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
