/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.user.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.ui.rcp.search.user.model.AuthorizationUserSearchViewModel;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;

/**
 * AuthorizationUserSearchView<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationUserSearchView extends
        AbstractNabuccoSearchView<AuthorizationUserSearchViewModel> implements NabuccoSearchView {

    private AuthorizationUserSearchViewModel model;

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.user.AuthorizationUserSearchView";

    /** Constructs a new AuthorizationUserSearchView instance. */
    public AuthorizationUserSearchView() {
        super();
        model = new AuthorizationUserSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public AuthorizationUserSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return AuthorizationUserSearchView.ID;
    }
}
