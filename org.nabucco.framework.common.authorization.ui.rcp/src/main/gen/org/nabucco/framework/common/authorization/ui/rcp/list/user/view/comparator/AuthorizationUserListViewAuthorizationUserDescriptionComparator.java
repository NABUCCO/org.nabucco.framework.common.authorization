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
package org.nabucco.framework.common.authorization.ui.rcp.list.user.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationUserListViewAuthorizationUserDescriptionComparator
 * 
 * @author Undefined
 */
public class AuthorizationUserListViewAuthorizationUserDescriptionComparator extends
        NabuccoColumComparator<AuthorizationUser> {

    /** Constructs a new AuthorizationUserListViewAuthorizationUserDescriptionComparator instance. */
    public AuthorizationUserListViewAuthorizationUserDescriptionComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationUser object1, AuthorizationUser object2) {
        return this.compareBasetype(object1.getDescription(), object2.getDescription());
    }
}
