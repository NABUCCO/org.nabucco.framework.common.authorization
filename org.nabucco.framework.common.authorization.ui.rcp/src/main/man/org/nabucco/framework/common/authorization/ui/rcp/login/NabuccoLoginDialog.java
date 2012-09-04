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
import org.nabucco.framework.base.facade.datatype.session.authorization.SecurityContext;
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

    private String defaultTenant;

    /**
     * Creates a new {@link NabuccoLoginDialog} instance.
     */
    public NabuccoLoginDialog(String defaultTenant) {
        this(null, defaultTenant);
    }

    /**
     * Creates a new {@link NabuccoLoginDialog} instance.
     * 
     * @param parentShell
     *            the shell to add the dialog
     * @param defaultTenant
     *            the default login tenant
     */
    public NabuccoLoginDialog(Shell parentShell, String defaultTenant) {
        super(parentShell);
        this.defaultTenant = defaultTenant;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);

        newShell.setText(I18N.i18n(LOGIN_TITLE));

        ImageDescriptor imageDescriptor = Activator.getImageDescriptor("icons/shield.png");
        if (imageDescriptor != null) {
            newShell.setImage(imageDescriptor.createImage());
        }

        this.layouter = new NabuccoLoginDialogLayouter();
        this.model = new NabuccoLoginDialogModel();
        this.model.setTenant(this.defaultTenant);
        this.model.setConnections(ConnectionSpecification.getAllConnectionSpecifications());
        this.businessModel = new NabuccoLoginDialogBusinessModel();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        this.layouter.layout(parent, null, this.model);
        return parent;
    }

    @Override
    public void buttonPressed(int buttonId) {
        if (buttonId != IDialogConstants.OK_ID) {
            super.buttonPressed(buttonId);
            return;
        }

        boolean success = this.login();

        if (success) {
            super.buttonPressed(buttonId);
        }
    }

    /**
     * Checks whether a user can be logged-in.
     * 
     * @return true: login sucessfully false: login failed
     */
    private boolean login() {
        SecurityContext securityContext = this.businessModel.login(this.model.getUserName(), this.model.getPassword(),
                this.model.getTenant());
        this.model.setSecurityContext(securityContext);

        if (!securityContext.isAuthenticated()) {
            Map<String, Serializable> values = new HashMap<String, Serializable>();
            values.put("username", this.model.getUserName());

            MessageDialog.open(CANCEL, getParentShell(), I18N.i18n(LOGIN_FAILED_MSG),
                    I18N.i18n(LOGIN_FAILED_TITLE, values), SWT.NONE);
        }

        this.model.setPassword("");

        return securityContext.isAuthenticated();
    }

    /**
     * Getter for the connection specification.
     * 
     * @return the connection specification
     */
    public ConnectionSpecification getConnectionSpecification() {
        return this.model.getSelectedConnection();
    }

    /**
     * Getter for the security context.
     * 
     * @return the security context
     */
    public SecurityContext getSecurityContext() {
        return this.model.getSecurityContext();
    }
}
