/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.role.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.ui.rcp.search.role.model.AuthorizationRoleSearchViewModel;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;

/**
 * AuthorizationRoleSearchView<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationRoleSearchView extends
        AbstractNabuccoSearchView<AuthorizationRoleSearchViewModel> implements NabuccoSearchView {

    private AuthorizationRoleSearchViewModel model;

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.role.AuthorizationRoleSearchView";

    /** Constructs a new AuthorizationRoleSearchView instance. */
    public AuthorizationRoleSearchView() {
        super();
        model = new AuthorizationRoleSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public AuthorizationRoleSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return AuthorizationRoleSearchView.ID;
    }
}
