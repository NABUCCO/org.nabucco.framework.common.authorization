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
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator.AuthorizationGroupListViewAuthorizationGroupNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.role.view.comparator.AuthorizationRoleListViewAuthorizationRoleNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.role.view.label.AuthorizationRoleListViewAuthorizationRoleNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.role.view.label.AuthorizationRoleListViewAuthorizationRoleTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.comparator.AuthorizationUserListViewAuthorizationUserNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserNameLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserTypeLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.util.AuthorizationLayouterUtility;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
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
public class AuthorizationPermissionEditViewLayouter implements
        NabuccoLayouter<AuthorizationPermissionEditViewModel> {

    private NabuccoFormToolkit ntk;

    private AuthorizationPermissionEditViewWidgetFactory widgetFactory;

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationPermissionEditViewModel model,
            Layoutable<AuthorizationPermissionEditViewModel> view) {
        return layout(parent, messageManager, model);
    }

    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationPermissionEditViewModel model) {
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
    private void layout(Composite parent, AuthorizationPermissionEditViewModel model) {
        ntk = new NabuccoFormToolkit(parent);
        widgetFactory = new AuthorizationPermissionEditViewWidgetFactory(ntk, model);
        layoutSomeSection(parent, model);
    }

    /**
     * Layouts the main section.
     * 
     * @param model
     * 
     * @param parent
     *            the parent to add the section
     */
    private void layoutSomeSection(Composite aParent, AuthorizationPermissionEditViewModel model) {
        Section editGroupSection = widgetFactory.createSectionHeading(aParent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite child = ntk.createComposite(editGroupSection, layout);
        editGroupSection.setClient(child);

        layoutLabelAndInputFieldPermissionName(child);
        layoutLabelAndInputFieldPermissionType(child);
        layoutLabelAndInputFieldPermissionDescription(child);
        layoutLabelAndInputFieldPermissionOwner(child);
        layoutAuthorizationGroupPicker(child, model);
        layoutAuthorizationUserPicker(child, model);
        layoutAuthorizationRolePicker(child, model);
    }

    /**
     * Layout the permission name.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldPermissionName(Composite parent) {
        Label label = widgetFactory.createLabelAuthorizationPermissionPermissionname(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldAuthorizationPermissionPermissionname(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the permission description.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldPermissionDescription(Composite parent) {
        Label label = widgetFactory.createLabelAuthorizationPermissionDescription(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldAuthorizationPermissionDescription(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the permission owner.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldPermissionOwner(Composite parent) {
        Label label = widgetFactory.createLabelAuthorizationPermissionOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldAuthorizationPermissionOwner(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the permission type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldPermissionType(Composite parent) {
        Label label = widgetFactory.createLabelAuthorizationPermissionType(parent);
        AuthorizationLayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldAuthorizationPermissionType(parent);
        AuthorizationLayouterUtility.layoutDefault(text);
    }

    /**
     * Layouts the group picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutAuthorizationGroupPicker(final Composite parent,
            AuthorizationPermissionEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(
                new NabuccoDefaultTableSorter<AuthorizationGroup>(createGroupTableComparator()),
                new AuthorizationGroupLabelProvider(),
                new AuthorizationPermissionGroupPickerContentProvider(model),
                createGroupTableColumnInfo());
        widgetFactory.createLabelAuthorizationPermissionGroupPicker(parent);
        widgetFactory.createListPickerAuthorizationPermissionGroupPicker(parent, params);
    }

    /**
     * Layouts the user picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutAuthorizationUserPicker(final Composite parent,
            AuthorizationPermissionEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(
                new NabuccoDefaultTableSorter<AuthorizationUser>(createUserTableComparator()),
                new AuthorizationUserLabelProvider(),
                new AuthorizationPermissionUserPickerContentProvider(model),
                createUserTableColumnInfo());
        widgetFactory.createLabelAuthorizationPermissionUserPicker(parent);
        widgetFactory.createListPickerAuthorizationPermissionUserPicker(parent, params);
    }

    /**
     * Layouts the permission picker.
     * 
     * @param parent
     *            the parent to add the picker
     * @param model
     */
    private void layoutAuthorizationRolePicker(Composite parent,
            AuthorizationPermissionEditViewModel model) {
        ElementPickerParameter params = new ElementPickerParameter(
                new NabuccoDefaultTableSorter<AuthorizationRole>(createRoleTableComparator()),
                new AuthorizationRoleLabelProvider(),
                new AuthorizationPermissionRolePickerContentProvider(model),
                createRoleTableColumnInfo());
        widgetFactory.createLabelAuthorizationPermissionRolePicker(parent);
        widgetFactory.createListPickerAuthorizationPermissionRolePicker(parent, params);
    }

    /**
     * Creates a comparator for the group picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationGroup>> createGroupTableComparator() {

        List<Comparator<AuthorizationGroup>> result = new LinkedList<Comparator<AuthorizationGroup>>();
        result.add(new AuthorizationGroupListViewAuthorizationGroupNameComparator());
        return result;
    }

    /**
     * Creates a comparator for the user picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationUser>> createUserTableComparator() {
        List<Comparator<AuthorizationUser>> result = new LinkedList<Comparator<AuthorizationUser>>();
        result.add(new AuthorizationUserListViewAuthorizationUserNameComparator());

        return result;
    }

    /**
     * Creates a comparator for the permission picker table.
     * 
     * @return the comparator
     */
    private List<Comparator<AuthorizationRole>> createRoleTableComparator() {
        List<Comparator<AuthorizationRole>> result = new LinkedList<Comparator<AuthorizationRole>>();
        result.add(new AuthorizationRoleListViewAuthorizationRoleNameComparator());

        return result;
    }

    /**
     * Create column information for the group picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createGroupTableColumnInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(2);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.group.column.code.name",
                "org.nabucco.framework.common.authorization.ui.picker.group.column.code.tooltip",
                50, SWT.LEFT, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupTypeLabelProvider());
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.group.column.name.name",
                "org.nabucco.framework.common.authorization.ui.picker.group.column.name.tooltip",
                100, SWT.LEFT, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

    /**
     * Create column information for the user picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createUserTableColumnInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(2);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.user.column.code.name",
                "org.nabucco.framework.common.authorization.ui.picker.user.column.code.tooltip",
                50, SWT.LEFT, SWT.CENTER,
                new AuthorizationUserListViewAuthorizationUserTypeLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.user.column.name.name",
                "org.nabucco.framework.common.authorization.ui.picker.user.column.name.tooltip",
                100, SWT.LEFT, SWT.CENTER,
                new AuthorizationUserListViewAuthorizationUserNameLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

    /**
     * Create column information for the permission picker table.
     * 
     * @return the column information
     */
    private NabuccoTableColumnInfo[] createRoleTableColumnInfo() {

        NabuccoTableColumnInfo info;
        List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(2);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.permission.column.code.name",
                "org.nabucco.framework.common.authorization.ui.picker.permission.column.code.tooltip",
                50, SWT.LEFT, SWT.CENTER,
                new AuthorizationRoleListViewAuthorizationRoleTypeLabelProvider());
        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        info = new NabuccoTableColumnInfo(
                "org.nabucco.framework.common.authorization.ui.picker.permission.column.name.name",
                "org.nabucco.framework.common.authorization.ui.picker.permission.column.name.tooltip",
                100, SWT.LEFT, SWT.CENTER,
                new AuthorizationRoleListViewAuthorizationRoleNameLabelProvider());

        info.setResizable(false);
        info.setMoveable(false);
        columnInfoList.add(info);

        return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    }

}
