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
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.AuthorizationGroupListViewTableFilter;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator.AuthorizationGroupListViewAuthorizationGroupNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.util.AuthorizationLayouterUtility;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableSorter;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.layout.NabuccoLayouter;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * Layouts the addUser View.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserEditViewLayouter implements
        NabuccoLayouter<AuthorizationUserEditViewModel> {

    private NabuccoFormToolkit nabuccoFormToolkit;

    private AuthorizationUserEditViewWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationUserEditViewModel model) {
        return layout(parent, messageManager, model, null);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationUserEditViewModel model, Layoutable<AuthorizationUserEditViewModel> view) {
        this.nabuccoFormToolkit = new NabuccoFormToolkit(parent);
        this.widgetFactory = new AuthorizationUserEditViewWidgetFactory(nabuccoFormToolkit, model);
        return layoutSection(parent, model);
    }

    /**
     * Layouts the section.
     * 
     * @param parent
     *            the parent of the section
     * @param model
     * 
     * @return the layouted composite
     */
    private Composite layoutSection(Composite parent, AuthorizationUserEditViewModel model) {
        Section editUserSection = widgetFactory.createSectionHeading(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = nabuccoFormToolkit.createComposite(editUserSection, layout);
        editUserSection.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);
        layoutLabelAndInputFieldUserType(sectionBody);
        layoutLabelAndInputFieldDescription(sectionBody);
        layoutLabelAndInputFieldOwner(sectionBody);
        layoutPickerForAuthorizationGroup(sectionBody, model);

        return editUserSection;
    }

    /**
     * Layout the user name.
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
     * Layout the user description.
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
     * Layout the user owner.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the user type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldUserType(Composite parent) {
        Label label = widgetFactory.createLabelUserType(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldUserType(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layouts the group picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutPickerForAuthorizationGroup(final Composite parent,
            AuthorizationUserEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(layoutTableSorter(),
                new AuthorizationGroupListViewTableFilter(), new AuthorizationGroupLabelProvider(),
                new AuthorizationUserGroupPickerContentProvider(model), createTableColumnInfo());

        widgetFactory.createLabelAuthorizationUserGroupPicker(parent);
        widgetFactory.createListPickerAuthorizationUserGroupPicker(parent, params);
    }

    /**
     * Creates a comparator for the group picker table.
     * 
     * @return the comparator
     */
    private NabuccoTableSorter layoutTableSorter() {
        List<Comparator<AuthorizationGroup>> result = new ArrayList<Comparator<AuthorizationGroup>>();
        result.add(new AuthorizationGroupListViewAuthorizationGroupNameComparator());
        return new NabuccoDefaultTableSorter<AuthorizationGroup>(result);
    }

    /**
     * Create column information for the group picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(2);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.group.column.code.name",
                "org.nabucco.framework.common.authorization.ui.picker.group.column.code.tooltip",
                50, SWT.LEFT, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.group.column.name.name",
                "org.nabucco.framework.common.authorization.ui.picker.group.column.name.tooltip",
                100, SWT.LEFT, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

}
