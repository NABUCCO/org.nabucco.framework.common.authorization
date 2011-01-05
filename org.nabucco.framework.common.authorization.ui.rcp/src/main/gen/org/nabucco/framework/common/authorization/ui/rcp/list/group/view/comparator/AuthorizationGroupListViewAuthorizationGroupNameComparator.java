/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationGroupListViewAuthorizationGroupNameComparator
 *
 * @author Undefined
 */
public class AuthorizationGroupListViewAuthorizationGroupNameComparator extends
        NabuccoColumComparator<AuthorizationGroup> {

    /** Constructs a new AuthorizationGroupListViewAuthorizationGroupNameComparator instance. */
    public AuthorizationGroupListViewAuthorizationGroupNameComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationGroup object1, AuthorizationGroup object2) {
        return this.compareBasetype(object1.getGroupname(), object2.getGroupname());
    }
}
