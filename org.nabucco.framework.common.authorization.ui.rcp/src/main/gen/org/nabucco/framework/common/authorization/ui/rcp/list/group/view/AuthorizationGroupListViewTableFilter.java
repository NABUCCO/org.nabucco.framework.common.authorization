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
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;

/**
 * AuthorizationGroupListViewTableFilter
 * <p/>
 * ListView for AuthorizationGroup
 * <p/>
 * 
 * @author Stefanie Feld, PRODYNA AG, 2010-03-15
 */
public class AuthorizationGroupListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new AuthorizationGroupListViewTableFilter instance. */
    public AuthorizationGroupListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof AuthorizationGroup)) {
            AuthorizationGroup datatype = ((AuthorizationGroup) element);
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getGroupname(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
        }
        return result;
    }
}
