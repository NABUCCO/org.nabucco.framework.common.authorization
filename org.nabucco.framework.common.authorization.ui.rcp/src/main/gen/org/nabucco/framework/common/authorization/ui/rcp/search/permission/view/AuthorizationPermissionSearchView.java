/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.permission.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.ui.rcp.search.permission.model.AuthorizationPermissionSearchViewModel;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;

/**
 * AuthorizationPermissionSearchView<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationPermissionSearchView extends
        AbstractNabuccoSearchView<AuthorizationPermissionSearchViewModel> implements
        NabuccoSearchView {

    private AuthorizationPermissionSearchViewModel model;

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.permission.AuthorizationPermissionSearchView";

    /** Constructs a new AuthorizationPermissionSearchView instance. */
    public AuthorizationPermissionSearchView() {
        super();
        model = new AuthorizationPermissionSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
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
