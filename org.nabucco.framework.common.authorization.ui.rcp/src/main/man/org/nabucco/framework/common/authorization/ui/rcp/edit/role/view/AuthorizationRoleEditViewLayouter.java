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
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.AuthorizationPickerConstants;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.AuthorizationGroupListViewTableFilter;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator.AuthorizationGroupListViewAuthorizationGroupNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.AuthorizationUserListViewTableFilter;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.comparator.AuthorizationUserListViewAuthorizationUserNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserDescriptionLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserTypeLabelProvider;
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
public class AuthorizationRoleEditViewLayouter implements NabuccoLayouter<AuthorizationRoleEditViewModel>,
        AuthorizationPickerConstants {

    private NabuccoFormToolkit ntk;

    private AuthorizationRoleEditViewWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationRoleEditViewModel model, Layoutable<AuthorizationRoleEditViewModel> view) {
        return layout(parent, messageManager, model);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager, AuthorizationRoleEditViewModel model) {
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
    private void layout(Composite parent, AuthorizationRoleEditViewModel model) {
        ntk = new NabuccoFormToolkit(parent);
        widgetFactory = new AuthorizationRoleEditViewWidgetFactory(ntk, model);
        createSomeSection(parent, model);
    }

    /**
     * Layouts the main section.
     * 
     * @param parent
     *            the parent to add the section
     * @param model
     */
    private void createSomeSection(Composite parent, AuthorizationRoleEditViewModel model) {
        Section editGroupSection = widgetFactory.createSectionHeading(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite child = ntk.createComposite(editGroupSection, layout);
        editGroupSection.setClient(child);

        layoutLabelAndInputFieldName(child);
        layoutLabelAndInputFieldRoleType(child);
        layoutLabelAndInputFieldDescription(child);
        layoutLabelAndInputFieldOwner(child);
        layoutGroupPicker(child, model);
        layoutUserPicker(child, model);
    }

    /**
     * Layout the role name.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldName(Composite child) {
        Label label = widgetFactory.createLabelName(child);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldName(child);
        AuthorizationLayouterUtility.layoutDefault(text);

    }

    /**
     * Layout the role description.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldDescription(Composite child) {
        Label label = widgetFactory.createLabelDescription(child);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldDescription(child);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the role owner.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldOwner(Composite child) {
        Label label = widgetFactory.createLabelOwner(child);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwner(child);
        AuthorizationLayouterUtility.layoutDefault(text);

        text.setEditable(false);
        text.setEnabled(false);
    }

    /**
     * Layout the role type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldRoleType(Composite child) {
        Label label = widgetFactory.createLabelType(child);
        AuthorizationLayouterUtility.layoutDefault(label);

        CodeComboViewer combo = widgetFactory.createComboType(child);
        AuthorizationLayouterUtility.layoutDefault(combo.getCombo());
    }

    /**
     * Layouts the group picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutGroupPicker(final Composite parent, AuthorizationRoleEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(new NabuccoDefaultTableSorter<AuthorizationGroup>(
                createTableColumnComparator()), new AuthorizationGroupListViewTableFilter(),
                new AuthorizationGroupLabelProvider(), new AuthorizationRoleGroupPickerContentProvider(model),
                createTableColumnGroupInfo());
        widgetFactory.createLabelAuthorizationRoleGroupPicker(parent);
        widgetFactory.createListPickerAuthorizationRoleGroupPicker(parent, params);
    }

    /**
     * Layouts the user picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutUserPicker(final Composite parent, AuthorizationRoleEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(new NabuccoDefaultTableSorter<AuthorizationUser>(
                createTableColumnUserComparator()), new AuthorizationUserListViewTableFilter(),
                new AuthorizationUserLabelProvider(), new AuthorizationRoleUserPickerContentProvider(model),
                createTableColumnUserInfo());
        widgetFactory.createLabelAuthorizationRoleUserPicker(parent);
        widgetFactory.createListPickerAuthorizationRoleUserPicker(parent, params);
    }

    /**
     * Creates a comparator for the group picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationGroup>> createTableColumnComparator() {
        List<Comparator<AuthorizationGroup>> result = new LinkedList<Comparator<AuthorizationGroup>>();
        result.add(new AuthorizationGroupListViewAuthorizationGroupNameComparator());
        return result;
    }

    /**
     * Creates a comparator for the user picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationUser>> createTableColumnUserComparator() {
        List<Comparator<AuthorizationUser>> result = new LinkedList<Comparator<AuthorizationUser>>();
        result.add(new AuthorizationUserListViewAuthorizationUserNameComparator());
        return result;
    }

    /**
     * Create column information for the group picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createTableColumnGroupInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(3);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_TYPE_LABEL, COLUMN_GROUP_TYPE_TOOLTIP, 75, SWT.LEFT, SWT.LEFT,
                new AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider());
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_NAME_LABEL, COLUMN_GROUP_NAME_TOOLTIP, 150, SWT.LEFT, SWT.LEFT,
                new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_GROUP_DESCRIPTION_LABEL, COLUMN_GROUP_DESCRIPTION_TOOLTIP, 200,
                SWT.LEFT, SWT.LEFT, new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

    /**
     * Create column information for the user picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createTableColumnUserInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(3);

        info = new NabuccoTableColumnInfo(COLUMN_USER_TYPE_LABEL, COLUMN_USER_TYPE_TOOLTIP, 75, SWT.LEFT, SWT.LEFT,
                new AuthorizationUserListViewAuthorizationUserTypeLabelProvider());

        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_USER_NAME_LABEL, COLUMN_USER_NAME_TOOLTIP, 150, SWT.LEFT, SWT.LEFT,
                new AuthorizationUserListViewAuthorizationUserNameLabelProvider());

        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(COLUMN_USER_DESCRIPTION_LABEL, COLUMN_USER_DESCRIPTION_TOOLTIP, 200,
                SWT.LEFT, SWT.LEFT, new AuthorizationUserListViewAuthorizationUserDescriptionLabelProvider());

        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

}
