/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

import java.util.Map;
import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;

/**
 * AuthorizationPermissionUserPickerContentProvider<p/>Edit view for datatype AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermissionUserPickerContentProvider implements
        ElementPickerContentProvider, NabuccoInjectionReciever {

    private AuthorizationPermissionUserPickerContentProviderHandler handler = NabuccoInjector
            .getInstance(AuthorizationPermissionUserPickerContentProvider.class).inject(
                    AuthorizationPermissionUserPickerContentProviderHandler.class);

    private Map<String, AuthorizationUser[]> values;

    private AuthorizationPermissionEditViewModel viewModel;

    /**
     * Constructs a new AuthorizationPermissionUserPickerContentProvider instance.
     *
     * @param viewModel the AuthorizationPermissionEditViewModel.
     */
    public AuthorizationPermissionUserPickerContentProvider(
            AuthorizationPermissionEditViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    /** InitValues. */
    private void initValues() {
        values = handler.loadAllAuthorizationUser(viewModel);
    }

    @Override
    public String[] getPaths() {
        this.initValues();
        return values.keySet().toArray(new String[values.size()]);
    }

    @Override
    public Object[] getElements(Object arg0) {
        this.initValues();
        if ((arg0 instanceof String)) {
            return values.get(arg0);
        }
        return new Object[0];
    }

    @Override
    public void dispose() {
        values = null;
    }

    @Override
    public void inputChanged(Viewer arg0, Object anOldValue, Object anNewValue) {
    }
}
