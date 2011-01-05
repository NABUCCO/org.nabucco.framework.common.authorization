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
package org.nabucco.framework.common.authorization.ui.rcp.search.user.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.common.authorization.ui.rcp.search.user.model.AuthorizationUserSearchViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.util.AuthorizationLayouterUtility;
import org.nabucco.framework.plugin.base.layout.BaseSearchViewLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * Layouter for authorizationUserSearch View.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserSearchViewLayouter extends
        BaseSearchViewLayouter<AuthorizationUserSearchViewModel> implements
        NabuccoLayouter<AuthorizationUserSearchViewModel> {

    private final String MESSAGE_OWNER_ID = "org.nabucco.framework.common.authorization.ui.rcp.user.search.view.AuthorizationUserSearchLayouter";

    private static final String SECTION_TITLE = AuthorizationUserSearchView.ID + ".section";

    private AuthorizationUserSearchViewWidgetFactory widgetFactory;

    @Override
    protected String getMessageOwnerId() {
        return MESSAGE_OWNER_ID;
    }

    @Override
    public Composite layoutComposite(Composite parent, NabuccoMessageManager msgManager,
            AuthorizationUserSearchViewModel model,
            Layoutable<AuthorizationUserSearchViewModel> view) {

        this.widgetFactory = new AuthorizationUserSearchViewWidgetFactory(nabuccoFormToolKit, model);

        Section section = layoutSection(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = nabuccoFormToolKit.createComposite(section, layout);
        section.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);
        layoutLabelAndInputFieldCode(sectionBody);
        layoutLabelAndInputFieldOwner(sectionBody);
        layoutLabelAndInputFieldDescription(sectionBody);

        return section;
    }

    /**
     * Layout the root section.
     * 
     * @param parent
     *            the parent composite
     * 
     * @return the layouted section
     */
    private Section layoutSection(Composite parent) {
        Section result = nabuccoFormToolKit.createSection(parent, SECTION_TITLE, new GridLayout(1,
                true));
        return result;
    }

    /**
     * Layout the search parameter name.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldName(Composite parent) {
        Label label = widgetFactory.createLabelNameText(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldNameText(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter code.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldCode(Composite parent) {
        Label label = widgetFactory.createLabelTypeText(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldTypeText(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter owner.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelOwnerText(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwnerText(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter description.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldDescription(Composite parent) {
        Label label = widgetFactory.createLabelDescriptionText(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldDescriptionText(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

}
