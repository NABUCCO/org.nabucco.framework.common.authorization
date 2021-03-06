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
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.ui.rcp.search.permission.model.AuthorizationPermissionSearchViewModel;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;

/**
 * AuthorizationPermissionSearchView
 * <p/>
 * @TODO
 * <p/>
 * 
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationPermissionSearchView extends
        AbstractNabuccoSearchView<AuthorizationPermissionSearchViewModel> implements NabuccoSearchView {

    private AuthorizationPermissionSearchViewModel model;

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.permission.AuthorizationPermissionSearchView";

    /** Constructs a new AuthorizationPermissionSearchView instance. */
    public AuthorizationPermissionSearchView() {
        super();
        model = new AuthorizationPermissionSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent, final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public AuthorizationPermissionSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return AuthorizationPermissionSearchView.ID;
    }
}
