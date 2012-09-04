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
package org.nabucco.framework.common.authorization.ui.rcp.overview.view;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.NabuccoRcpToolkit;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewAction;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationOverviewWidgetFactory
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationOverviewWidgetFactory extends WidgetFactory {

    private static final String TITLE = "org.nabucco.framework.common.authorization.ui.rcp.overview.view.Title";

    private static final String SUMMARY = "org.nabucco.framework.common.authorization.ui.rcp.overview.view.Summary";

    private static final String ACTIONS = "org.nabucco.framework.common.authorization.ui.rcp.overview.view.Actions";

    /**
     * Creates a new {@link AuthorizationOverviewWidgetFactory} instance.
     * 
     * @param nabuccoFormToolkit
     *            the form toolkit
     */
    public AuthorizationOverviewWidgetFactory(NabuccoFormToolkit nabuccoFormToolkit) {
        super(nabuccoFormToolkit);
    }

    /**
     * Create Heading for summary.
     * 
     * @param parent
     *            the parent composite
     * 
     * @return the layouted section
     */
    public Section createSectionHeadingSummary(Composite parent) {
        Section result = super.nabuccoFormToolKit.createSection(parent, TITLE, new RowLayout());

        return result;
    }

    /**
     * Create Description text.
     * 
     * @param parent
     *            parent of element
     * 
     * @return created element
     */
    public StyledText createTextDescription(Composite parent) {
        StyledText result = super.nabuccoFormToolKit.createMultipleLineLabel(parent, SUMMARY);
        return result;
    }

    public Section createSectionHeadingAction(Composite aParent) {
        Section result = super.nabuccoFormToolKit.createSection(aParent, ACTIONS, new RowLayout());
        return result;
    }

    public void createActionElement(final Composite aParent, final NabuccoOverviewAction overviewActionItem) {

        Link button = super.nabuccoFormToolKit.createLink(aParent, overviewActionItem.getActionDescription());
        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                NabuccoRcpToolkit.showView(overviewActionItem.getViewId());
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
            }
        });
    }

    public Label createActionDescription(final Composite aParent, final NabuccoOverviewAction overviewActionItem) {
        return super.nabuccoFormToolKit.createRealLabel(aParent, overviewActionItem.getDescription());
    }
}
