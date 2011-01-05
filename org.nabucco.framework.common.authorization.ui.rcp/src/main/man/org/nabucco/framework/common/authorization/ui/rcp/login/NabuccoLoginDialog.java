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
package org.nabucco.framework.common.authorization.ui.rcp.login;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.component.connection.ConnectionSpecification;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogBusinessModel;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * NabuccoLoginDialog
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialog extends Dialog {

    private static final String LOGIN_TITLE = "org.nabucco.framework.common.authorization.ui.rcp.login.title";

    private static final String LOGIN_FAILED_TITLE = "org.nabucco.framework.common.authorization.ui.rcp.login.failedMessage";

    private static final String LOGIN_FAILED_MSG = "org.nabucco.framework.common.authorization.ui.rcp.login.failedTitle";

    private NabuccoLoginDialogLayouter layouter;

    private NabuccoLoginDialogModel model;

    private NabuccoLoginDialogBusinessModel businessModel;

    /**
     * Creates a new {@link NabuccoLoginDialog} instance.
     */
    public NabuccoLoginDialog() {
        this(null);
    }

    /**
     * Creates a new {@link NabuccoLoginDialog} instance.
     * 
     * @param parentShell
     *            the shell to add the dialog
     */
    public NabuccoLoginDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);

        newShell.setText(I18N.i18n(LOGIN_TITLE));

        final ImageDescriptor imageDescriptor = Activator.getImageDescriptor("icons/shield.png");
        if (imageDescriptor != null) {
            newShell.setImage(imageDescriptor.createImage());
        }
        layouter = new NabuccoLoginDialogLayouter();
        model = new NabuccoLoginDialogModel();
        model.setConnectionSpecifications(ConnectionSpecification.getAllConnectionSpecifications());
        businessModel = new NabuccoLoginDialogBusinessModel();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        layouter.layout(parent, null, model);
        return parent;
    }

    @Override
    public void buttonPressed(int buttonId) {
        if ((buttonId == IDialogConstants.OK_ID && login()) || buttonId != IDialogConstants.OK_ID) {
            super.buttonPressed(buttonId);
        }
    }

    /**
     * Checks whether a user can be logged-in.
     * 
     * @return true: login sucessfully false: login failed
     */
    private boolean login() {
        Subject subject = businessModel.login(model.getUserName(), model.getPassword());
        model.setAuthenticatedSubject(subject);

        if (subject == null) {
            Map<String, Serializable> values = new HashMap<String, Serializable>();
            values.put("username", model.getUserName());

            MessageDialog.open(CANCEL, getParentShell(), I18N.i18n(LOGIN_FAILED_MSG),
                    I18N.i18n(LOGIN_FAILED_TITLE, values), SWT.NONE);
        }

        model.setPassword("");

        boolean loginSuccessful = model.getAuthenticatedSubject() != null;
        return loginSuccessful;
    }

    /**
     * Getter for the connection specification.
     * 
     * @return the connection specification
     */
    public ConnectionSpecification getConnectionSpecification() {
        return model.getSelectedConnectionSpecification();
    }

    /**
     * Getter for the user identifier.
     * 
     * @return the userId
     */
    public Subject getSubject() {
        return model.getAuthenticatedSubject();
    }
}
