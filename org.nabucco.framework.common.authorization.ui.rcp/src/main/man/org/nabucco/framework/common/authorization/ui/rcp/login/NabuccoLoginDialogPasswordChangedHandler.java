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

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogModel;

/**
 * NabuccoLoginDialogPasswordChangedHandler
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogPasswordChangedHandler implements ModifyListener {

    private NabuccoLoginDialogModel model;

    private Text textInputField;

    /**
     * Creates a new {@link NabuccoLoginDialogPasswordChangedHandler} instance.
     * 
     * @param model
     *            the login model
     * @param textInputField
     *            the input field
     */
    public NabuccoLoginDialogPasswordChangedHandler(NabuccoLoginDialogModel model, Text textInputField) {
        this.model = model;
        this.textInputField = textInputField;
    }

    @Override
    public void modifyText(ModifyEvent e) {
        model.setPassword(textInputField.getText());
    }

}
