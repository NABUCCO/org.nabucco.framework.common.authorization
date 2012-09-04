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
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.AuthorizationPickerConstants;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.AuthorizationGroupListViewTableFilter;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator.AuthorizationGroupListViewAuthorizationGroupNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.util.AuthorizationLayouterUtility;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.picker.combo.CodeComboViewer;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * AuthorizationGroupEditViewLayouter.
 * 
 * @author Silas Schwarz PRODYNA AG
 */
public class AuthorizationGroupEditViewLayouter implements NabuccoLayouter<AuthorizationGroupEditViewModel>,
        AuthorizationPickerConstants {

    private NabuccoFormToolkit ntk;

    private AuthorizationGroupEditViewWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationGroupEditViewModel model, Layoutable<AuthorizationGroupEditViewModel> view) {
        return layout(parent, messageManager, model);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationGroupEditViewModel model) {
        layout(parent, model);
        return null;
    }

    /**
     * Layouts the view.
     * 
     * @param parent
     *            the parent composite
     * @param model
     *            the view model
     */
    private void layout(Composite parent, AuthorizationGroupEditViewModel model) {
        this.ntk = new NabuccoFormToolkit(parent);
        this.widgetFactory = new AuthorizationGroupEditViewWidgetFactory(ntk, model);
        layoutSection(parent, model);
    }

    /**
     * Layouts the main section.
     * 
     * @param parent
     *            the parent to add the section
     * @param model
     */
    private void layoutSection(Composite parent, AuthorizationGroupEditViewModel model) {
        Section section = widgetFactory.createSectionHeading(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = ntk.createComposite(section, layout);
        section.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);
        layoutLabelAndInputFieldGroupType(sectionBody);
        layoutLabelAndInputFieldDescription(sectionBody);
        layoutLabelAndInputFieldOwner(sectionBody);
        layoutAuthorizationGroupPicker(sectionBody, model);
    }

    /**
     * Layout the group name.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldName(Composite parent) {
        Label label = widgetFactory.createLabelName(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldName(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the group type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldGroupType(Composite parent) {
        Label createLabelUserType = widgetFactory.createLabelUserType(parent);
        AuthorizationLayouterUtility.layoutDefault(createLabelUserType);

        CodeComboViewer createUserTypeCombo = widgetFactory.createUserTypeCombo(parent);
        AuthorizationLayouterUtility.layoutDefault(createUserTypeCombo.getCombo());
    }

    /**
     * Layout the group description.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldDescription(Composite parent) {
        Label label = widgetFactory.createLabelDescription(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldDescription(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the group owner.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(text);

        text.setEditable(false);
        text.setEnabled(false);
    }

    /**
     * Layouts the parent group picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutAuthorizationGroupPicker(Composite parent, AuthorizationGroupEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(new NabuccoDefaultTableSorter<AuthorizationGroup>(
                createComparators()), new AuthorizationGroupListViewTableFilter(),
                new AuthorizationGroupLabelProvider(), new AuthorizationGroupGroupPickerContentProvider(model),
                createTableColumnInfo());

        widgetFactory.createLabelAuthorizationGroupGroupPicker(parent);
        widgetFactory.createElementPickerAuthorizationGroupGroupPicker(parent, params);
    }

    /**
     * Creates a comparator for the parent group picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationGroup>> createComparators() {
        List<Comparator<AuthorizationGroup>> result = new LinkedList<Comparator<AuthorizationGroup>>();
        result.add(new AuthorizationGroupListViewAuthorizationGroupNameComparator());
        return result;
    }

    /**
     * Create column information for the parent group picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(3);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_TYPE_LABEL, COLUMN_GROUP_TYPE_TOOLTIP, 75, SWT.LEFT, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_NAME_LABEL, COLUMN_GROUP_NAME_TOOLTIP, 150, SWT.LEFT,
                SWT.CENTER, new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_DESCRIPTION_LABEL, COLUMN_GROUP_DESCRIPTION_TOOLTIP, 200,
                SWT.LEFT, SWT.CENTER, new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }
}
