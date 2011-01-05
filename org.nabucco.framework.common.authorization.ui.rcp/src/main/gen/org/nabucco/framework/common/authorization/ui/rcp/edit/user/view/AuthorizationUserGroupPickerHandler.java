/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.view;

import java.util.Set;
import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * AuthorizationUserGroupPickerHandler<p/>Edit view for datatype AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationUserGroupPickerHandler implements ElementPickerListener {

    private AuthorizationUserEditViewModel model;

    /**
     * Constructs a new AuthorizationUserGroupPickerHandler instance.
     *
     * @param model the AuthorizationUserEditViewModel.
     */
    public AuthorizationUserGroupPickerHandler(final AuthorizationUserEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void elementSelected(TypedEvent event) {
        if ((event.data instanceof Set<?>)) {
            model.setGroupSet(((Set<AuthorizationGroup>) event.data));
        } else if ((event.data == null)) {
            model.setGroupSet(null);
        }
    }
}
