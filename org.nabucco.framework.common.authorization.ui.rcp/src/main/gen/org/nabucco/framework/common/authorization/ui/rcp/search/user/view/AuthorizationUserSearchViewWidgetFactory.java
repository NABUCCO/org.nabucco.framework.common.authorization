/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.user.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.common.authorization.ui.rcp.search.user.model.AuthorizationUserSearchViewModel;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationUserSearchViewWidgetFactory<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationUserSearchViewWidgetFactory extends WidgetFactory {

    private AuthorizationUserSearchViewModel model;

    public static final String LABEL_NAMETEXT = "user.username";

    public static final String OBSERVE_VALUE_NAMETEXT = AuthorizationUserSearchViewModel.PROPERTY_USER_USERNAME;

    public static final String LABEL_TYPETEXT = "user.userType";

    public static final String OBSERVE_VALUE_TYPETEXT = AuthorizationUserSearchViewModel.PROPERTY_USER_USERTYPE;

    public static final String LABEL_OWNERTEXT = "user.owner";

    public static final String OBSERVE_VALUE_OWNERTEXT = AuthorizationUserSearchViewModel.PROPERTY_USER_OWNER;

    public static final String LABEL_DESCRIPTIONTEXT = "user.description";

    public static final String OBSERVE_VALUE_DESCRIPTIONTEXT = AuthorizationUserSearchViewModel.PROPERTY_USER_DESCRIPTION;

    /**
     * Constructs a new AuthorizationUserSearchViewWidgetFactory instance.
     *
     * @param aModel the AuthorizationUserSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public AuthorizationUserSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final AuthorizationUserSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
    }

    /**
     * CreateLabelNameText.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelNameText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAMETEXT);
    }

    /**
     * CreateInputFieldNameText.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldNameText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables
                .observeValue(model, OBSERVE_VALUE_NAMETEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelTypeText.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelTypeText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_TYPETEXT);
    }

    /**
     * CreateInputFieldTypeText.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldTypeText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables
                .observeValue(model, OBSERVE_VALUE_TYPETEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwnerText.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelOwnerText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNERTEXT);
    }

    /**
     * CreateInputFieldOwnerText.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldOwnerText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_OWNERTEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelDescriptionText.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelDescriptionText(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTIONTEXT);
    }

    /**
     * CreateInputFieldDescriptionText.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescriptionText(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model,
                OBSERVE_VALUE_DESCRIPTIONTEXT);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }
}
