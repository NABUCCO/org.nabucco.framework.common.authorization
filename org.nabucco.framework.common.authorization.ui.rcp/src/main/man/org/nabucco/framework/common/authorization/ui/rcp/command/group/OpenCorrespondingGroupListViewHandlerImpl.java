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
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.common.authorization.ui.rcp.browser.group.AuthorizationGroupListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.model.AuthorizationGroupListViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.group.view.AuthorizationGroupListView;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenCorrespondingListViewHandlerImpl;

/**
 * Implements OpenCorrespondingGroupListViewHandler.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class OpenCorrespondingGroupListViewHandlerImpl
        extends
        AbstractNabuccoOpenCorrespondingListViewHandlerImpl<AuthorizationGroupListViewBrowserElement, AuthorizationGroupListViewModel>
        implements OpenCorrespondingGroupListViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openCorrespondingGroupListView() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getListViewId() {
        return AuthorizationGroupListView.ID;
    }

}
