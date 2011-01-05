/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.group.view;

import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectable;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.group.model.AuthorizationGroupEditViewModel;

/**
 * AuthorizationGroupGroupPickerContentProviderHandler<p/>Edit view for datatype AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationGroupGroupPickerContentProviderHandler extends NabuccoInjectable {

    /**
     * LoadAllAuthorizationGroup.
     *
     * @param viewModel the AuthorizationGroupEditViewModel.
     * @return the Map<String, AuthorizationGroup[]>.
     */
    Map<String, AuthorizationGroup[]> loadAllAuthorizationGroup(
            AuthorizationGroupEditViewModel viewModel);
}
