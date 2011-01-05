/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
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
