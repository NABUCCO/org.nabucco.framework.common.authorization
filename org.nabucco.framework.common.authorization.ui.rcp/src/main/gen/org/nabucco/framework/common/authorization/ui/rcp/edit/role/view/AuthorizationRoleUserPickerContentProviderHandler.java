/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectable;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;

/**
 * AuthorizationRoleUserPickerContentProviderHandler<p/>Edit view for datatype AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationRoleUserPickerContentProviderHandler extends NabuccoInjectable {

    /**
     * LoadAllAuthorizationUser.
     *
     * @param viewModel the AuthorizationRoleEditViewModel.
     * @return the Map<String, AuthorizationUser[]>.
     */
    Map<String, AuthorizationUser[]> loadAllAuthorizationUser(
            AuthorizationRoleEditViewModel viewModel);
}
