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
package org.nabucco.framework.common.authorization.ui.web.action.permission;

import org.nabucco.framework.base.ui.web.action.handler.OpenPickerActionHandler;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * OpenPermissionPickerDialogAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class OpenPermissionPickerDialogAction extends OpenPickerActionHandler<AuthorizationPermission> {

    private static final String PICKER_ID = "AuthorizationPermissionPickerDialog";

    @Override
    protected String getPickerId() {
        return PICKER_ID;
    }

}
