/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator
 *
 * @author Undefined
 */
public class AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator extends
        NabuccoColumComparator<AuthorizationPermission> {

    /** Constructs a new AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator instance. */
    public AuthorizationPermissionListViewAuthorizationPermissionDescriptionComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationPermission object1, AuthorizationPermission object2) {
        return this.compareBasetype(object1.getDescription(), object2.getDescription());
    }
}
