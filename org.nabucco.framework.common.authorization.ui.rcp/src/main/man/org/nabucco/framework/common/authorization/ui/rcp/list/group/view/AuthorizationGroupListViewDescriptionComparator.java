/*
 * Copyright 2010 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco-source.org/nabucco-license.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view;

import java.util.Comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * Compares to groups on Description.
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class AuthorizationGroupListViewDescriptionComparator implements
        Comparator<AuthorizationGroup> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final AuthorizationGroup group1, final AuthorizationGroup group2) {
        return group1.getDescription().compareTo(group2.getDescription());
    }

}
