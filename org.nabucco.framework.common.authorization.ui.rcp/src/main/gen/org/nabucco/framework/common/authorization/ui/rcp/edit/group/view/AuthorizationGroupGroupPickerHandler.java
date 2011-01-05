/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * AuthorizationGroupGroupPickerHandler<p/>Edit view for datatype AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationGroupGroupPickerHandler implements ElementPickerListener {

    private AuthorizationGroupEditViewModel model;

    /**
     * Constructs a new AuthorizationGroupGroupPickerHandler instance.
     *
     * @param model the AuthorizationGroupEditViewModel.
     */
    public AuthorizationGroupGroupPickerHandler(final AuthorizationGroupEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    public void elementSelected(TypedEvent event) {
        if ((event.data instanceof AuthorizationGroup)) {
            model.setParentGroup(((AuthorizationGroup) event.data));
        } else if ((event.data == null)) {
            model.setParentGroup(null);
        }
    }
}
