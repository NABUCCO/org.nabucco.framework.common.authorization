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
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.component.picker.dialog.ListPickerComposite;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationPermissionEditViewWidgetFactory
 * <p/>
 * Edit view for datatype AuthorizationPermission
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermissionEditViewWidgetFactory extends WidgetFactory {

    private AuthorizationPermissionEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_AUTHORIZATIONPERMISSIONPERMISSIONNAME = "authorization.permissionname";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONPERMISSIONNAME = AuthorizationPermissionEditViewModel.PROPERTY_PERMISSION_PERMISSIONNAME;

    public static final String LABEL_AUTHORIZATIONPERMISSIONDESCRIPTION = "authorization.permission.description";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONDESCRIPTION = AuthorizationPermissionEditViewModel.PROPERTY_PERMISSION_DESCRIPTION;

    public static final String LABEL_AUTHORIZATIONPERMISSIONOWNER = "authorization.permission.description";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONOWNER = AuthorizationPermissionEditViewModel.PROPERTY_PERMISSION_OWNER;

    public static final String LABEL_AUTHORIZATIONPERMISSIONGROUPPICKER = "authorization.group";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONTYPE = AuthorizationPermissionEditViewModel.PROPERTY_PERMISSION_PERMISSIONTYPE;

    public static final String LABEL_AUTHORIZATIONPERMISSIONTYPE = "authorization.permission.permissionType";

    public static final String TITLE_AUTHORIZATIONPERMISSIONGROUPPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONPERMISSIONGROUPPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONPERMISSIONGROUPPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONPERMISSIONGROUPPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONPERMISSIONGROUPPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONPERMISSIONGROUPPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONGROUPPICKER = AuthorizationPermissionEditViewModel.PROPERTY_GROUPSET_GROUPNAME;

    public static final String LABEL_AUTHORIZATIONPERMISSIONUSERPICKER = "authorization.user";

    public static final String TITLE_AUTHORIZATIONPERMISSIONUSERPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONPERMISSIONUSERPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONPERMISSIONUSERPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONPERMISSIONUSERPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONPERMISSIONUSERPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONPERMISSIONUSERPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONUSERPICKER = AuthorizationPermissionEditViewModel.PROPERTY_USERSET_USERNAME;

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONPERMISSIONTYPE = AuthorizationPermissionEditViewModel.PROPERTY_PERMISSION_PERMISSIONTYPE;

    public static final String LABEL_AUTHORIZATIONPERMISSIONROLEPICKER = "authorization.role";

    public static final String TITLE_AUTHORIZATIONPERMISSIONROLEPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONPERMISSIONROLEPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONPERMISSIONROLEPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONPERMISSIONROLEPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONPERMISSIONROLEPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONPERMISSIONROLEPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONPERMISSIONROLEPICKER = AuthorizationPermissionEditViewModel.PROPERTY_ROLESET_ROLENAME;

    /**
     * Constructs a new AuthorizationPermissionEditViewWidgetFactory instance.
     * 
     * @param model
     *            the AuthorizationPermissionEditViewModel.
     * @param nabuccoFormToolKit
     *            the NabuccoFormToolkit.
     */
    public AuthorizationPermissionEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit,
            AuthorizationPermissionEditViewModel model) {
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
     * CreateLabelAuthorizationPermissionPermissionname.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionPermissionname(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONPERMISSIONNAME);
    }

    /**
     * CreateInputFieldAuthorizationPermissionPermissionname.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldAuthorizationPermissionPermissionname(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_AUTHORIZATIONPERMISSIONPERMISSIONNAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelAuthorizationPermissionDescription.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONDESCRIPTION);
    }

    /**
     * CreateInputFieldAuthorizationPermissionDescription.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldAuthorizationPermissionDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_AUTHORIZATIONPERMISSIONDESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelAuthorizationPermissionOwner.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONOWNER);
    }

    /**
     * CreateInputFieldAuthorizationPermissionOwner.
     * 
     * @param parent
     *            the Composite.
     * @return the Text.
     */
    public Text createInputFieldAuthorizationPermissionOwner(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables
                .observeValue(model, OBSERVE_VALUE_AUTHORIZATIONPERMISSIONOWNER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelAuthorizationPermissionGroupPicker.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionGroupPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONGROUPPICKER);
    }

    /**
     * CreateListPickerAuthorizationPermissionGroupPicker.
     * 
     * @param params
     *            the ElementPickerParameter.
     * @param parent
     *            the Composite.
     */
    public void createListPickerAuthorizationPermissionGroupPicker(Composite parent, ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(
                parent,
                SWT.NONE,
                params,
                params.getInputFieldLabelProvider(),
                new LabelForDialog(TITLE_AUTHORIZATIONPERMISSIONGROUPPICKER,
                        MESSAGE_AUTHORIZATIONPERMISSIONGROUPPICKER, SHELL_TITLE_AUTHORIZATIONPERMISSIONGROUPPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONPERMISSIONGROUPPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONPERMISSIONGROUPPICKER, PATH_LABEL_AUTHORIZATIONPERMISSIONGROUPPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_AUTHORIZATIONPERMISSIONGROUPPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationPermissionGroupPickerHandler(model));
    }

    /**
     * CreateLabelAuthorizationPermissionUserPicker.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionUserPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONUSERPICKER);
    }

    /**
     * CreateListPickerAuthorizationPermissionUserPicker.
     * 
     * @param params
     *            the ElementPickerParameter.
     * @param parent
     *            the Composite.
     */
    public void createListPickerAuthorizationPermissionUserPicker(Composite parent, ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(TITLE_AUTHORIZATIONPERMISSIONUSERPICKER,
                        MESSAGE_AUTHORIZATIONPERMISSIONUSERPICKER, SHELL_TITLE_AUTHORIZATIONPERMISSIONUSERPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONPERMISSIONUSERPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONPERMISSIONUSERPICKER, PATH_LABEL_AUTHORIZATIONPERMISSIONUSERPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_AUTHORIZATIONPERMISSIONUSERPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationPermissionUserPickerHandler(model));
    }

    /**
     * CreateLabelAuthorizationPermissionRolePicker.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationPermissionRolePicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONROLEPICKER);
    }

    /**
     * CreateLabelAuthorizationPermissionRolePicker.
     * 
     * @param parent
     *            the Composite.
     * @return the Label.
     */
    public Label createLabelPermissionPermissionTypeCombo(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONPERMISSIONTYPE);
    }

    public CodeComboViewer createPermissionPermissionTypeCombo(Composite parent) {
        CodeComboViewer ccv = new CodeComboViewer(parent, AuthorizationPermission.getPermissionTypeCodePath(), model,
                OBSERVE_VALUE_AUTHORIZATIONPERMISSIONPERMISSIONTYPE);
        return ccv;

    }

    /**
     * CreateListPickerAuthorizationPermissionRolePicker.
     * 
     * @param params
     *            the ElementPickerParameter.
     * @param parent
     *            the Composite.
     */
    public void createListPickerAuthorizationPermissionRolePicker(Composite parent, ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(TITLE_AUTHORIZATIONPERMISSIONROLEPICKER,
                        MESSAGE_AUTHORIZATIONPERMISSIONROLEPICKER, SHELL_TITLE_AUTHORIZATIONPERMISSIONROLEPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONPERMISSIONROLEPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONPERMISSIONROLEPICKER, PATH_LABEL_AUTHORIZATIONPERMISSIONROLEPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_AUTHORIZATIONPERMISSIONROLEPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationPermissionRolePickerHandler(model));
    }
}
