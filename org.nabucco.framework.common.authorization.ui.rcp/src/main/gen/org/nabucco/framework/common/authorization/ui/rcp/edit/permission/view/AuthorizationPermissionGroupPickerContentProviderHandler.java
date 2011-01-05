/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.permission.view;

import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectable;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.permission.model.AuthorizationPermissionEditViewModel;

/**
 * AuthorizationPermissionGroupPickerContentProviderHandler<p/>Edit view for datatype AuthorizationPermission<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationPermissionGroupPickerContentProviderHandler extends NabuccoInjectable {

    /**
     * LoadAllAuthorizationGroup.
     *
     * @param viewModel the AuthorizationPermissionEditViewModel.
     * @return the Map<String, AuthorizationGroup[]>.
     */
    Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(
            AuthorizationPermissionEditViewModel viewModel);
}
