/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view;

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;

/**
 * AuthorizationGroupListViewTableFilter<p/>ListView for AuthorizationGroup<p/>
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
            result = (result || this.contains(datatype.getGroupType(), searchFilter.getFilter()));
        }
        return result;
    }
}
