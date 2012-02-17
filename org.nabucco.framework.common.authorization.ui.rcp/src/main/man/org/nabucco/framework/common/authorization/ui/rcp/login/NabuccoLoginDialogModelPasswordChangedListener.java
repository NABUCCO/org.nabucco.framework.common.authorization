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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogModel;

/**
 * NabuccoLoginDialogModelPasswordChangedListener
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogModelPasswordChangedListener implements PropertyChangeListener {

    private Text inputField;

    private NabuccoLoginDialogModel model;

    public NabuccoLoginDialogModelPasswordChangedListener(final Text inputField, final NabuccoLoginDialogModel model) {
        this.inputField = inputField;
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        inputField.setText(model.getPassword());
        inputField.setSelection((model.getPassword().length()));
    }

}
