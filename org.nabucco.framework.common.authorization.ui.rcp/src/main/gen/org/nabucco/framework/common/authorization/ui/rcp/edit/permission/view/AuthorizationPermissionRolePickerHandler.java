/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

import java.util.Set;
import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * AuthorizationPermissionRolePickerHandler<p/>Edit view for datatype AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermissionRolePickerHandler implements ElementPickerListener {

    private AuthorizationPermissionEditViewModel model;

    /**
     * Constructs a new AuthorizationPermissionRolePickerHandler instance.
     *
     * @param model the AuthorizationPermissionEditViewModel.
     */
    public AuthorizationPermissionRolePickerHandler(final AuthorizationPermissionEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void elementSelected(TypedEvent event) {
        if ((event.data instanceof Set<?>)) {
            model.setRoleSet(((Set<AuthorizationRole>) event.data));
        } else if ((event.data == null)) {
            model.setRoleSet(null);
        }
    }
}
