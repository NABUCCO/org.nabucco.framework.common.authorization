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
package org.nabucco.framework.common.authorization.ui.rcp.list.user.view;

import java.util.Comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserListNameComparator
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationUserListNameComparator implements Comparator<AuthorizationUser> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(AuthorizationUser o1, AuthorizationUser o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }

}
