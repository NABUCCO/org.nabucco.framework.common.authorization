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
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.model.AuthorizationGroupListViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupDescriptionLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label.AuthorizationGroupListViewAuthorizationGroupNameLabelProvider;
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
 * AuthorizationGroupListViewLayouter
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupListViewLayouter extends
        NabuccoAbstractListLayouter<AuthorizationGroupListViewModel> {

    private static final String COLUMN_NAME_TITLE = AuthorizationGroupListView.ID
            + ".column.name.title";

    private static final String COLUMN_NAME_TOOLTIP = AuthorizationGroupListView.ID
            + ".column.name.tooltip";

    private static final String COLUMN_DESCRIPTION_TITLE = AuthorizationGroupListView.ID
            + ".column.description.title";

    private static final String COLUMN_DESCRIPTION_TOOLTIP = AuthorizationGroupListView.ID
            + ".column.description.tooltip";

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationGroupListViewModel model, Layoutable<AuthorizationGroupListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);
        AuthorizationGroupListViewWidgetFactory widgetFactory = new AuthorizationGroupListViewWidgetFactory(
                ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<AuthorizationGroup>(createComparators()),
                new AuthorizationGroupListViewTableFilter(), new NabuccoDefaultListContentProvider(
                        model), createTableColumnInfo(), getDoubleClickCommand(view));

        return widgetFactory.createTable(parent, parameter, model);
    }

    /**
     * Create list of comparators.
     * 
     * @return List of comparators which should be used.
     */
    private List<Comparator<AuthorizationGroup>> createComparators() {
        List<Comparator<AuthorizationGroup>> result = new LinkedList<Comparator<AuthorizationGroup>>();
        result.add(new AuthorizationGroupListNameComparator());
        result.add(new AuthorizationGroupListViewDescriptionComparator());
        return result;
    }

    /**
     * Creates needed tables.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo[] result = new NabuccoTableColumnInfo[2];

        result[0] = new NabuccoTableColumnInfo(COLUMN_NAME_TITLE, COLUMN_NAME_TOOLTIP, 200,
                SWT.CENTER, SWT.CENTER,
                new AuthorizationGroupListViewAuthorizationGroupNameLabelProvider());

        result[1] = new NabuccoTableColumnInfo(COLUMN_DESCRIPTION_TITLE,
                COLUMN_DESCRIPTION_TOOLTIP, 300, SWT.RIGHT, SWT.RIGHT,
                new AuthorizationGroupListViewAuthorizationGroupDescriptionLabelProvider());

        return result;
    }

}
