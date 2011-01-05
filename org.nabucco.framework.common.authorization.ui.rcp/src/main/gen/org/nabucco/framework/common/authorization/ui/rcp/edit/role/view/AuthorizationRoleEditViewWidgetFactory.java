/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

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
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.component.picker.dialog.ListPickerComposite;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationRoleEditViewWidgetFactory<p/>Edit view for datatype AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationRoleEditViewWidgetFactory extends WidgetFactory {

    private AuthorizationRoleEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_NAME = "authorization.role.name";

    public static final String OBSERVE_VALUE_NAME = AuthorizationRoleEditViewModel.PROPERTY_ROLE_ROLENAME;

    public static final String LABEL_DESCRIPTION = "authorization.role.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = AuthorizationRoleEditViewModel.PROPERTY_ROLE_DESCRIPTION;

    public static final String LABEL_OWNER = "authorization.role.owner";

    public static final String OBSERVE_VALUE_OWNER = AuthorizationRoleEditViewModel.PROPERTY_ROLE_OWNER;

    public static final String LABEL_GROUPTYPE = "authorization.role.type";

    public static final String OBSERVE_VALUE_GROUPTYPE = AuthorizationRoleEditViewModel.PROPERTY_ROLE_ROLETYPE;

    public static final String LABEL_AUTHORIZATIONROLEGROUPPICKER = "authorization.group";

    public static final String TITLE_AUTHORIZATIONROLEGROUPPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONROLEGROUPPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONROLEGROUPPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONROLEGROUPPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONROLEGROUPPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONROLEGROUPPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONROLEGROUPPICKER = AuthorizationRoleEditViewModel.PROPERTY_GROUPSET_GROUPNAME;

    public static final String LABEL_AUTHORIZATIONROLEUSERPICKER = "authorization.user";

    public static final String TITLE_AUTHORIZATIONROLEUSERPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONROLEUSERPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONROLEUSERPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONROLEUSERPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONROLEUSERPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONROLEUSERPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONROLEUSERPICKER = AuthorizationRoleEditViewModel.PROPERTY_USERSET_USERNAME;

    /**
     * Constructs a new AuthorizationRoleEditViewWidgetFactory instance.
     *
     * @param model the AuthorizationRoleEditViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public AuthorizationRoleEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit,
            AuthorizationRoleEditViewModel model) {
        super(nabuccoFormToolKit);
        this.model = model;
    }

    /**
     * CreateSectionHeading.
     *
     * @param parent the Composite.
     * @return the Section.
     */
    public Section createSectionHeading(Composite parent) {
        return nabuccoFormToolKit.createSection(parent, SECTION, new GridLayout());
    }

    /**
     * CreateLabelName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAME);
    }

    /**
     * CreateInputFieldName.
     *
     * @param parent the Composite.
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
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTION);
    }

    /**
     * CreateInputFieldDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_DESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwner.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNER);
    }

    /**
     * CreateInputFieldOwner.
     *
     * @param parent the Composite.
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
     * CreateLabelGroupType.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelGroupType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_GROUPTYPE);
    }

    /**
     * CreateInputFieldGroupType.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldGroupType(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_GROUPTYPE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelAuthorizationRoleGroupPicker.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationRoleGroupPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONROLEGROUPPICKER);
    }

    /**
     * CreateListPickerAuthorizationRoleGroupPicker.
     *
     * @param params the ElementPickerParameter.
     * @param parent the Composite.
     */
    public void createListPickerAuthorizationRoleGroupPicker(Composite parent,
            ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(
                        TITLE_AUTHORIZATIONROLEGROUPPICKER, MESSAGE_AUTHORIZATIONROLEGROUPPICKER,
                        SHELL_TITLE_AUTHORIZATIONROLEGROUPPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONROLEGROUPPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONROLEGROUPPICKER,
                        PATH_LABEL_AUTHORIZATIONROLEGROUPPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_AUTHORIZATIONROLEGROUPPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationRoleGroupPickerHandler(model));
    }

    /**
     * CreateLabelAuthorizationRoleUserPicker.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationRoleUserPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONROLEUSERPICKER);
    }

    /**
     * CreateListPickerAuthorizationRoleUserPicker.
     *
     * @param params the ElementPickerParameter.
     * @param parent the Composite.
     */
    public void createListPickerAuthorizationRoleUserPicker(Composite parent,
            ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(
                        TITLE_AUTHORIZATIONROLEUSERPICKER, MESSAGE_AUTHORIZATIONROLEUSERPICKER,
                        SHELL_TITLE_AUTHORIZATIONROLEUSERPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONROLEUSERPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONROLEUSERPICKER,
                        PATH_LABEL_AUTHORIZATIONROLEUSERPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_AUTHORIZATIONROLEUSERPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationRoleUserPickerHandler(model));
    }
}
