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
package org.nabucco.framework.common.authorization.ui.rcp.command.permission;

import org.nabucco.framework.common.authorization.ui.rcp.browser.permission.AuthorizationPermissionListViewBrowserElement;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.AuthorizationPermissionListView;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenCorrespondingListViewHandlerImpl;

/**
 * OpenCorrespondingPermissionListViewHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenCorrespondingPermissionListViewHandlerImpl
        extends
        AbstractNabuccoOpenCorrespondingListViewHandlerImpl<AuthorizationPermissionListViewBrowserElement, AuthorizationPermissionListViewModel>
        implements OpenCorrespondingPermissionListViewHandler {

    @Override
    public void openCorrespondingPermissionListView() {
        super.run();
    }

    @Override
    protected String getListViewId() {
        return AuthorizationPermissionListView.ID;
    }

}