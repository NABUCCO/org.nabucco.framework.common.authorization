/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

import java.util.Set;
import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * AuthorizationRoleUserPickerHandler<p/>Edit view for datatype AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationRoleUserPickerHandler implements ElementPickerListener {

    private AuthorizationRoleEditViewModel model;

    /**
     * Constructs a new AuthorizationRoleUserPickerHandler instance.
     *
     * @param model the AuthorizationRoleEditViewModel.
     */
    public AuthorizationRoleUserPickerHandler(final AuthorizationRoleEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void elementSelected(TypedEvent event) {
        if ((event.data instanceof Set<?>)) {
            model.setUserSet(((Set<AuthorizationUser>) event.data));
        } else if ((event.data == null)) {
            model.setUserSet(null);
        }
    }
}
