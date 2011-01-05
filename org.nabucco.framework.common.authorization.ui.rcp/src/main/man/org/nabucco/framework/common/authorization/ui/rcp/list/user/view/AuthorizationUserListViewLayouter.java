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
package org.nabucco.framework.common.authorization.ui.rcp.list.user.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.model.AuthorizationUserListViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserDescriptionLabelProvider;
import org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label.AuthorizationUserListViewAuthorizationUserNameLabelProvider;
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
 * AuthorizationUserListViewLayouter
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserListViewLayouter extends
        NabuccoAbstractListLayouter<AuthorizationUserListViewModel> {

    private static final String COLUMN_NAME_TITLE = AuthorizationUserListView.ID
            + ".column.name.title";

    private static final String COLUMN_NAME_TOOLTIP = AuthorizationUserListView.ID
            + ".column.name.tooltip";

    private static final String COLUMN_DESCRIPTION_TITLE = AuthorizationUserListView.ID
            + ".column.description.title";

    private static final String COLUMN_DESCRIPTION_TOOLTIP = AuthorizationUserListView.ID
            + ".column.description.tooltip";

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            AuthorizationUserListViewModel model, Layoutable<AuthorizationUserListViewModel> view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);

        AuthorizationUserListViewWidgetFactory widgetFactory = new AuthorizationUserListViewWidgetFactory(
                ntk);

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<AuthorizationUser>(createComparators()),
                new AuthorizationUserListViewTableFilter(), new NabuccoDefaultListContentProvider(
                        model), createTableColumnInfo(), getDoubleClickCommand(view));

        return widgetFactory.createTable(parent, parameter, model);
    }

    private List<Comparator<AuthorizationUser>> createComparators() {
        List<Comparator<AuthorizationUser>> comparators = new ArrayList<Comparator<AuthorizationUser>>();
        comparators.add(new AuthorizationUserListNameComparator());
        comparators.add(new AuthorizationUserListIdComparator());

        return comparators;
    }

    /**
     * Creates needed tables.
     * 
     * @return table columns
     */
    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo[] result = {

                new NabuccoTableColumnInfo(COLUMN_NAME_TITLE, COLUMN_NAME_TOOLTIP, 200, SWT.CENTER,
                        SWT.CENTER,
                        new AuthorizationUserListViewAuthorizationUserNameLabelProvider()),

                new NabuccoTableColumnInfo(COLUMN_DESCRIPTION_TITLE, COLUMN_DESCRIPTION_TOOLTIP,
                        300, SWT.RIGHT, SWT.RIGHT,
                        new AuthorizationUserListViewAuthorizationUserDescriptionLabelProvider()) };

        return result;
    }

}
