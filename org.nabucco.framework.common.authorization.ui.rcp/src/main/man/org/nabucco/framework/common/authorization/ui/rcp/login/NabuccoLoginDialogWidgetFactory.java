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
package org.nabucco.framework.common.authorization.ui.rcp.login;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.base.facade.component.connection.ConnectionSpecification;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogModel;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * NabuccoLoginDialogWidgetFactory
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogWidgetFactory extends WidgetFactory {

    private static final String CONNECTION = "org.nabucco.framework.common.authorization.ui.rcp.connection";

    private static final String USERNAME = "org.nabucco.framework.common.authorization.ui.rcp.username";

    private static final String PASSWORD = "org.nabucco.framework.common.authorization.ui.rcp.password";

    private NabuccoLoginDialogModel model;

    /**
     * Creates a new {@link NabuccoLoginDialogWidgetFactory} instance.
     * 
     * @param formToolkit
     *            the nabucco form toolkit
     * @param model
     *            the login model
     */
    public NabuccoLoginDialogWidgetFactory(NabuccoFormToolkit formToolkit, NabuccoLoginDialogModel model) {
        super(formToolkit);
        this.model = model;
    }

    public Label createLabelUsername(final Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, USERNAME);
    }

    public Text createTextInputUsername(final Composite parent) {
        final Text result = nabuccoFormToolKit.createTextInput(parent);
        result.addModifyListener(new NabuccoLoginDialogUsernameChangedHandler(model, result));
        return result;
    }

    public Label createLabelPassword(final Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, PASSWORD);
    }

    public Text createTextInputPassword(final Composite parent) {
        final Text result = nabuccoFormToolKit.createPasswordInput(parent);
        result.addModifyListener(new NabuccoLoginDialogPasswordChangedHandler(model, result));
        model.addPropertyChangeListener(NabuccoLoginDialogModel.PROPERTY_PASSWORD,
                new NabuccoLoginDialogModelPasswordChangedListener(result, model));

        return result;
    }

    public Label createLabelConnection(final Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, CONNECTION);
    }

    public Combo createDropdownBoxConnections(final Composite parent) {
        Combo dropDownBox = nabuccoFormToolKit.createDropdown(parent);

        for (ConnectionSpecification connectionSpecification : model.getConnections()) {
            dropDownBox.add(connectionSpecification.getEnvironment());
        }

        dropDownBox.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Combo combo = (Combo) e.getSource();
                int index = combo.getSelectionIndex();

                ConnectionSpecification spec = model.getConnections().get(index);
                model.setSelectedConnection(spec);

                Activator.getDefault().logDebug("Selected Connection: " + spec);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        if (dropDownBox.getItemCount() > 0) {
            dropDownBox.select(0);
            model.setSelectedConnection(model.getConnections().get(0));
        }

        return dropDownBox;
    }

}
