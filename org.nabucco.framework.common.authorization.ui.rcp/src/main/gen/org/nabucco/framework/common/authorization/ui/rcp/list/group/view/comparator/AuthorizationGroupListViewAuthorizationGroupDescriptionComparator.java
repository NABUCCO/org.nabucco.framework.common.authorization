/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationGroupListViewAuthorizationGroupDescriptionComparator
 *
 * @author Undefined
 */
public class AuthorizationGroupListViewAuthorizationGroupDescriptionComparator extends
        NabuccoColumComparator<AuthorizationGroup> {

    /** Constructs a new AuthorizationGroupListViewAuthorizationGroupDescriptionComparator instance. */
    public AuthorizationGroupListViewAuthorizationGroupDescriptionComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationGroup object1, AuthorizationGroup object2) {
        return this.compareBasetype(object1.getDescription(), object2.getDescription());
    }
}
