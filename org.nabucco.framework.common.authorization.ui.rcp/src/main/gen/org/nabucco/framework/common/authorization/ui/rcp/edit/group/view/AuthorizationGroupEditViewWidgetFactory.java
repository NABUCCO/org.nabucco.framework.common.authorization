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
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerComposite;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationGroupEditViewWidgetFactory
 * <p/>
 * Edit view for datatype AuthorizationGroup
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationGroupEditViewWidgetFactory extends WidgetFactory {

    private AuthorizationGroupEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_NAME = "authorization.group.name";

    public static final String OBSERVE_VALUE_NAME = AuthorizationGroupEditViewModel.PROPERTY_GROUP_GROUPNAME;

    public static final String LABEL_DESCRIPTION = "authorization.group.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = AuthorizationGroupEditViewModel.PROPERTY_GROUP_DESCRIPTION;

    public static final String LABEL_OWNER = "authorization.group.owner";

    public static final String OBSERVE_VALUE_OWNER = AuthorizationGroupEditViewModel.PROPERTY_GROUP_OWNER;

    public static final String LABEL_GROUPTYPE = "authorization.group.type";

    public static final String OBSERVE_VALUE_GROUPTYPE = AuthorizationGroupEditViewModel.PROPERTY_GROUP_GROUPTYPE;

    public static final String LABEL_AUTHORIZATIONGROUPGROUPPICKER = "authorization.group.group";

    public static final String TITLE_AUTHORIZATIONGROUPGROUPPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONGROUPGROUPPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONGROUPGROUPPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONGROUPGROUPPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONGROUPGROUPPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONGROUPGROUPPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONGROUPGROUPPICKER = AuthorizationGroupEditViewModel.PROPERTY_PARENTGROUP_GROUPNAME;

    /**
     * Constructs a new AuthorizationGroupEditViewWidgetFactory instance.
     * 
     * @param model
     *            the AuthorizationGroupEditViewModel.
     * @param nabuccoFormToolKit
     *            the NabuccoFormToolkit.
     */
    public AuthorizationGroupEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit,
            AuthorizationGroupEditViewModel model) {
        super(nabuccoFormToolKit);
        this.model = model;
    }

    /**
     * CreateSectionHeading.
     * 
     * @param parent
     *            the Composite.
     * @return the Section.
     */
    public Section createSectionHeading(Composite parent) {
        return nabuccoFormToolKit.createSection(parent, SECTION, new GridLayout());
    }

    /**
     * CreateLabelName.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAME);
    }

    /**
     * CreateInputFieldName.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_NAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelDescription.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTION);
    }

    /**
     * CreateInputFieldDescription.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_DESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwner.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNER);
    }

    /**
     * CreateInputFieldOwner.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldOwner(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_OWNER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * createLabelUserType.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelUserType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_GROUPTYPE);
    }

    public CodeComboViewer createUserTypeCombo(Composite parent) {
        CodeComboViewer codeComboViewer = new CodeComboViewer(parent, AuthorizationGroup.getGroupTypeCodePath(), model,
                OBSERVE_VALUE_GROUPTYPE);
        return codeComboViewer;
    }

    /**
     * CreateLabelAuthorizationGroupGroupPicker.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationGroupGroupPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONGROUPGROUPPICKER);
    }

    /**
     * CreateElementPickerAuthorizationGroupGroupPicker.
     * 
     * @param params
     *            the ElementPickerParameter.
     * @param parent
     *            the Composite.
     */
    public void createElementPickerAuthorizationGroupGroupPicker(Composite parent, ElementPickerParameter params) {
        ElementPickerComposite picker = new ElementPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(TITLE_AUTHORIZATIONGROUPGROUPPICKER,
                        MESSAGE_AUTHORIZATIONGROUPGROUPPICKER, SHELL_TITLE_AUTHORIZATIONGROUPGROUPPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONGROUPGROUPPICKER, MESSAGE_COMBO_AUTHORIZATIONGROUPGROUPPICKER,
                        PATH_LABEL_AUTHORIZATIONGROUPGROUPPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_AUTHORIZATIONGROUPGROUPPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationGroupGroupPickerHandler(model));
    }
}
