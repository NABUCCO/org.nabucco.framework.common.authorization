/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;

/**
 * AuthorizationPermissionListViewTableFilter<p/>ListView for AuthorizationPermission<p/>
 *
 * @author Stefanie Feld, PRODYNA AG, 2010-03-15
 */
public class AuthorizationPermissionListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new AuthorizationPermissionListViewTableFilter instance. */
    public AuthorizationPermissionListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof AuthorizationPermission)) {
            AuthorizationPermission datatype = ((AuthorizationPermission) element);
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getPermissionname(),
                    searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getPermissionType(),
                    searchFilter.getFilter()));
        }
        return result;
    }
}
