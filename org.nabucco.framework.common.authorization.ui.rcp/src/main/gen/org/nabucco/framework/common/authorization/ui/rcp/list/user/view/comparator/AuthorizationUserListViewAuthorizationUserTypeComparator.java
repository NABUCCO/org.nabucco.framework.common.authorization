/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.user.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationUserListViewAuthorizationUserTypeComparator
 *
 * @author Undefined
 */
public class AuthorizationUserListViewAuthorizationUserTypeComparator extends
        NabuccoColumComparator<AuthorizationUser> {

    /** Constructs a new AuthorizationUserListViewAuthorizationUserTypeComparator instance. */
    public AuthorizationUserListViewAuthorizationUserTypeComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationUser object1, AuthorizationUser object2) {
        return this.compareBasetype(object1.getUserType(), object2.getUserType());
    }
}
