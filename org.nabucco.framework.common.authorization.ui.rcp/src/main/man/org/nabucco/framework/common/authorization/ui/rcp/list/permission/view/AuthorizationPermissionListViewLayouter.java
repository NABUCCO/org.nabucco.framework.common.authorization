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
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.comparator.AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.comparator.AuthorizationPermissionListViewAuthorizationPermissionNameComparator;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.label.AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.label.AuthorizationPermissionListViewAuthorizationPermissionNameLabelProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultListContentProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * AuthorizationPermissionListViewLayouter
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationPermissionListViewLayouter extends
        NabuccoAbstractListLayouter<AuthorizationPermissionListViewModel> {

    private static final String COLUMN_NAME_TITLE = AuthorizationPermissionListView.ID + ".column.name.title";

    private static final String COLUMN_NAME_TOOLTIP = AuthorizationPermissionListView.ID + ".column.name.tooltip";

    private static final String COLUMN_DESCRIPTION_TITLE = AuthorizationPermissionListView.ID
            + ".column.description.title";

    private static final String COLUMN_DESCRIPTION_TOOLTIP = AuthorizationPermissionListView.ID
            + ".column.description.tooltip";

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationPermissionListViewModel model, Layoutable<AuthorizationPermissionListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        AuthorizationPermissionListViewWidgetFactory widgetFactory = new AuthorizationPermissionListViewWidgetFactory(
                ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<AuthorizationPermission>(createTableComparators()),
                new AuthorizationPermissionListViewTableFilter(), new NabuccoDefaultListContentProvider(model),
                createTableColumnInfo(), getDoubleClickCommand(view));
        return widgetFactory.createTable(parent, parameter, model);
    }

    private List<Comparator<AuthorizationPermission>> createTableComparators() {
        List<Comparator<AuthorizationPermission>> result = new LinkedList<Comparator<AuthorizationPermission>>();
        result.add(new AuthorizationPermissionListViewAuthorizationPermissionNameComparator());
        result.add(new AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator());
        return result;
    }

    /**
     * Creates needed tables.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        return new NabuccoTableColumnInfo[] {

                new NabuccoTableColumnInfo(COLUMN_NAME_TITLE, COLUMN_NAME_TOOLTIP, 200, SWT.CENTER, SWT.CENTER,
                        new AuthorizationPermissionListViewAuthorizationPermissionNameLabelProvider()),

                new NabuccoTableColumnInfo(COLUMN_DESCRIPTION_TITLE, COLUMN_DESCRIPTION_TOOLTIP, 300, SWT.RIGHT,
                        SWT.RIGHT, new AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider()) };
    }
}
