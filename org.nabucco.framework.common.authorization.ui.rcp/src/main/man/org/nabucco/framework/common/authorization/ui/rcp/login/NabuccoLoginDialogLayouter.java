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

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.common.authorization.ui.rcp.login.model.NabuccoLoginDialogModel;
import org.nabucco.framework.common.authorization.ui.rcp.util.AuthorizationLayouterUtility;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * NabuccoLoginDialogLayouter
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogLayouter implements NabuccoLayouter<NabuccoLoginDialogModel> {

    private NabuccoLoginDialogWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager, NabuccoLoginDialogModel model) {

        NabuccoFormToolkit nft = new NabuccoFormToolkit(parent);
        this.widgetFactory = new NabuccoLoginDialogWidgetFactory(nft, model);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginHeight = 20;
        gridLayout.verticalSpacing = 10;
        gridLayout.marginWidth = 20;
        gridLayout.horizontalSpacing = 10;

        Composite frame = nft.createComposite(parent, gridLayout);

        this.createUsername(frame);
        this.createPassword(frame);
        this.createConnection(frame);

        return frame;
    }

    /**
     * Create text field for username and put focus on it.
     * 
     * @param parent
     *            the parent dialog
     */
    private void createUsername(Composite parent) {
        Label label = this.widgetFactory.createLabelUsername(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = this.widgetFactory.createTextInputUsername(parent);
        AuthorizationLayouterUtility.layoutDefault(text);

        text.setFocus();
    }

    /**
     * Create text field for password.
     * 
     * @param parent
     *            the parent dialog
     */
    private void createPassword(Composite parent) {
        Label label = this.widgetFactory.createLabelPassword(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = this.widgetFactory.createTextInputPassword(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Create drop-down for connection.
     * 
     * @param parent
     *            the parent dialog
     */
    private void createConnection(Composite parent) {
        Label label = this.widgetFactory.createLabelConnection(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Combo combo = this.widgetFactory.createDropdownBoxConnections(parent);
        AuthorizationLayouterUtility.layoutDefault(combo);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager, NabuccoLoginDialogModel model,
            Layoutable<NabuccoLoginDialogModel> view) {
        return null;
    }

}
