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
package org.nabucco.framework.common.authorization.ui.rcp.overview.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewAction;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewModel;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * AuthorizationOverviewLayouter
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationOverviewLayouter implements NabuccoLayouter<NabuccoOverviewModel> {

    private AuthorizationOverviewWidgetFactory widgetFactory;

    private NabuccoOverviewModel model;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            NabuccoOverviewModel model) {
        return layout(parent, model);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            NabuccoOverviewModel model, Layoutable<NabuccoOverviewModel> view) {
        return layout(parent, messageManager, model);
    }

    /**
     * Layouts the overview view.
     * 
     * @param parent
     *            the parent composite
     * @param nabuccoOverviewModel
     *            the overview model
     * 
     * @return the layouted composite
     */
    public Composite layout(Composite parent, NabuccoOverviewModel nabuccoOverviewModel) {
        this.model = nabuccoOverviewModel;

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        Composite frame = ntk.createComposite(parent, new RowLayout(SWT.VERTICAL | SWT.NO_SCROLL));

        this.widgetFactory = new AuthorizationOverviewWidgetFactory(ntk);

        createSectionSummary(frame);
        createSectionBottom(frame);

        return null;
    }

    private void createSectionSummary(final Composite aParent) {
        Section section = widgetFactory.createSectionHeadingSummary(aParent);
        final Composite child = widgetFactory.getNabuccoFormToolKit().createComposite(section,
                new FormLayout());
        section.setClient(child);

        createDescription(aParent, child);
    }

    private void createDescription(final Composite aParent, final Composite child) {
        final Composite com = widgetFactory.getNabuccoFormToolKit().createComposite(child,
                new FillLayout());

        FormData areaForViewer = new FormData();
        areaForViewer.left = new FormAttachment(0, 0);
        areaForViewer.top = new FormAttachment(0, 0);
        areaForViewer.right = new FormAttachment(0, 0);
        areaForViewer.bottom = new FormAttachment(0, 0);

        com.setLayoutData(areaForViewer);
        widgetFactory.createTextDescription(com);
        aParent.getParent().addControlListener(new ControlListener() {

            @Override
            public void controlResized(ControlEvent arg0) {
                FormData a = (FormData) com.getLayoutData();
                if (0 != aParent.getClientArea().width) {
                    a.right.offset = aParent.getClientArea().width;
                    a.bottom.offset = com.computeSize(a.right.offset, SWT.DEFAULT, false).y;
                } else {
                    a.right.offset = 635;
                    a.bottom.offset = a.bottom.offset = com.computeSize(a.right.offset,
                            SWT.DEFAULT, false).y;
                }
                com.setLayoutData(a);
                aParent.layout();
            }

            @Override
            public void controlMoved(ControlEvent arg0) {
            }
        });
    }

    /**
     * Layouts the actions section.
     * 
     * @param parent
     *            the parent composite
     */
    private void createSectionBottom(Composite parent) {
        Section section = widgetFactory.createSectionHeadingAction(parent);

        Composite child = widgetFactory.getNabuccoFormToolKit().createComposite(section,
                new GridLayout(2, true));
        section.setClient(child);

        for (NabuccoOverviewAction action : model.getComponentActions()) {
            widgetFactory.createActionElement(child, action);
            widgetFactory.createActionDescription(child, action);
        }
    }
}
