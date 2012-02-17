/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.ui.rcp.edit.role.view;

import java.util.Set;
import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.ui.rcp.edit.role.model.AuthorizationRoleEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * AuthorizationRoleGroupPickerHandler
 * <p/>
 * Edit view for datatype AuthorizationRole
 * <p/>
 * 
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationRoleGroupPickerHandler implements ElementPickerListener {

    private AuthorizationRoleEditViewModel model;

    /**
     * Constructs a new AuthorizationRoleGroupPickerHandler instance.
     * 
     * @param model
     *            the AuthorizationRoleEditViewModel.
     */
    public AuthorizationRoleGroupPickerHandler(final AuthorizationRoleEditViewModel model) {
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
