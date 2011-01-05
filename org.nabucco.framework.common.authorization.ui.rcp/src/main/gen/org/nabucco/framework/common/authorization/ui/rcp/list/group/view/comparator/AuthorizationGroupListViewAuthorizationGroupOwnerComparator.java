/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationGroupListViewAuthorizationGroupOwnerComparator
 *
 * @author Undefined
 */
public class AuthorizationGroupListViewAuthorizationGroupOwnerComparator extends
        NabuccoColumComparator<AuthorizationGroup> {

    /** Constructs a new AuthorizationGroupListViewAuthorizationGroupOwnerComparator instance. */
    public AuthorizationGroupListViewAuthorizationGroupOwnerComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationGroup object1, AuthorizationGroup object2) {
        return this.compareBasetype(object1.getOwner(), object2.getOwner());
    }
}
