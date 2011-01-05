/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.user.view;

import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectable;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.user.model.AuthorizationUserEditViewModel;

/**
 * AuthorizationUserGroupPickerContentProviderHandler<p/>Edit view for datatype AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationUserGroupPickerContentProviderHandler extends NabuccoInjectable {

    /**
     * LoadAllAuthorizationGroup.
     *
     * @param viewModel the AuthorizationUserEditViewModel.
     * @return the Map<String, AuthorizationGroup[]>.
     */
    Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(
            AuthorizationUserEditViewModel viewModel);
}
