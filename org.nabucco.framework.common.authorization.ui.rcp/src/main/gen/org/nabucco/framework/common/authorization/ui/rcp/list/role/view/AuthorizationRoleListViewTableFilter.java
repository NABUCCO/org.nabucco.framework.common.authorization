/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.role.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;

/**
 * AuthorizationRoleListViewTableFilter<p/>ListView for AuthorizationRole<p/>
 *
 * @author Stefanie Feld, PRODYNA AG, 2010-03-15
 */
public class AuthorizationRoleListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new AuthorizationRoleListViewTableFilter instance. */
    public AuthorizationRoleListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof AuthorizationRole)) {
            AuthorizationRole datatype = ((AuthorizationRole) element);
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getRolename(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getRoleType(), searchFilter.getFilter()));
        }
        return result;
    }
}
