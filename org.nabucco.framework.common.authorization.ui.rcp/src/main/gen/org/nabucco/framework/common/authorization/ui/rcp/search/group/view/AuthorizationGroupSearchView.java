/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.search.group.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.common.authorization.ui.rcp.search.group.model.AuthorizationGroupSearchViewModel;
import org.nabucco.framework.plugin.base.view.AbstractNabuccoSearchView;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;
import org.nabucco.framework.plugin.base.view.NabuccoSearchView;

/**
 * AuthorizationGroupSearchView<p/>@TODO<p/>
 *
 * @author Silas Schwarz, PRODYNA AG, 2010-02-26
 */
public class AuthorizationGroupSearchView extends
        AbstractNabuccoSearchView<AuthorizationGroupSearchViewModel> implements NabuccoSearchView {

    private AuthorizationGroupSearchViewModel model;

    public static final String ID = "org.nabucco.framework.common.authorization.ui.search.group.AuthorizationGroupSearchView";

    /** Constructs a new AuthorizationGroupSearchView instance. */
    public AuthorizationGroupSearchView() {
        super();
        model = new AuthorizationGroupSearchViewModel(this.getCorrespondingListView());
    }

    @Override
    public void createPartControl(final Composite parent,
            final NabuccoMessageManager aMessageManager) {
        this.getLayouter().layout(parent, aMessageManager, model);
    }

    @Override
    public AuthorizationGroupSearchViewModel getModel() {
        return model;
    }

    @Override
    public String getId() {
        return AuthorizationGroupSearchView.ID;
    }
}
