/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import java.util.Map;
import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;

/**
 * AuthorizationGroupGroupPickerContentProvider<p/>Edit view for datatype AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationGroupGroupPickerContentProvider implements ElementPickerContentProvider,
        NabuccoInjectionReciever {

    private AuthorizationGroupGroupPickerContentProviderHandler handler = NabuccoInjector
            .getInstance(AuthorizationGroupGroupPickerContentProvider.class).inject(
                    AuthorizationGroupGroupPickerContentProviderHandler.class);

    private Map<String, AuthorizationGroup[]> values;

    private AuthorizationGroupEditViewModel viewModel;

    /**
     * Constructs a new AuthorizationGroupGroupPickerContentProvider instance.
     *
     * @param viewModel the AuthorizationGroupEditViewModel.
     */
    public AuthorizationGroupGroupPickerContentProvider(AuthorizationGroupEditViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    /** InitValues. */
    private void initValues() {
        values = handler.loadAllAuthorizationGroup(viewModel);
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
