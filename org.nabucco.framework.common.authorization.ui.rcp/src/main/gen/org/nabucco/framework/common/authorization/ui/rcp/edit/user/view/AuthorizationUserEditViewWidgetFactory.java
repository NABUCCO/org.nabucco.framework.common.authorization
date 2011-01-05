/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.view;

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
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.component.picker.dialog.ListPickerComposite;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationUserEditViewWidgetFactory<p/>Edit view for datatype AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationUserEditViewWidgetFactory extends WidgetFactory {

    private AuthorizationUserEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_NAME = "authorization.user.name";

    public static final String OBSERVE_VALUE_NAME = AuthorizationUserEditViewModel.PROPERTY_USER_USERNAME;

    public static final String LABEL_DESCRIPTION = "authorization.user.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = AuthorizationUserEditViewModel.PROPERTY_USER_DESCRIPTION;

    public static final String LABEL_OWNER = "authorization.user.owner";

    public static final String OBSERVE_VALUE_OWNER = AuthorizationUserEditViewModel.PROPERTY_USER_OWNER;

    public static final String LABEL_USERTYPE = "authorization.user.userType";

    public static final String OBSERVE_VALUE_USERTYPE = AuthorizationUserEditViewModel.PROPERTY_USER_USERTYPE;

    public static final String LABEL_AUTHORIZATIONUSERGROUPPICKER = "authorization.group";

    public static final String TITLE_AUTHORIZATIONUSERGROUPPICKER = "newTITLE";

    public static final String MESSAGE_AUTHORIZATIONUSERGROUPPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_AUTHORIZATIONUSERGROUPPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_AUTHORIZATIONUSERGROUPPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_AUTHORIZATIONUSERGROUPPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_AUTHORIZATIONUSERGROUPPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_AUTHORIZATIONUSERGROUPPICKER = AuthorizationUserEditViewModel.PROPERTY_GROUPSET_GROUPTYPE;

    /**
     * Constructs a new AuthorizationUserEditViewWidgetFactory instance.
     *
     * @param model the AuthorizationUserEditViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public AuthorizationUserEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit,
            AuthorizationUserEditViewModel model) {
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
     * CreateLabelUserType.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelUserType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_USERTYPE);
    }

    /**
     * CreateInputFieldUserType.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldUserType(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables
                .observeValue(model, OBSERVE_VALUE_USERTYPE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelAuthorizationUserGroupPicker.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelAuthorizationUserGroupPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_AUTHORIZATIONUSERGROUPPICKER);
    }

    /**
     * CreateListPickerAuthorizationUserGroupPicker.
     *
     * @param params the ElementPickerParameter.
     * @param parent the Composite.
     */
    public void createListPickerAuthorizationUserGroupPicker(Composite parent,
            ElementPickerParameter params) {
        ListPickerComposite picker = new ListPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(
                        TITLE_AUTHORIZATIONUSERGROUPPICKER, MESSAGE_AUTHORIZATIONUSERGROUPPICKER,
                        SHELL_TITLE_AUTHORIZATIONUSERGROUPPICKER,
                        MESSAGE_TABLE_AUTHORIZATIONUSERGROUPPICKER,
                        MESSAGE_COMBO_AUTHORIZATIONUSERGROUPPICKER,
                        PATH_LABEL_AUTHORIZATIONUSERGROUPPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_AUTHORIZATIONUSERGROUPPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new AuthorizationUserGroupPickerHandler(model));
    }
}
