/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.role.view.comparator;

import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * AuthorizationRoleListViewAuthorizationRoleDescriptionComparator
 *
 * @author Undefined
 */
public class AuthorizationRoleListViewAuthorizationRoleDescriptionComparator extends
        NabuccoColumComparator<AuthorizationRole> {

    /** Constructs a new AuthorizationRoleListViewAuthorizationRoleDescriptionComparator instance. */
    public AuthorizationRoleListViewAuthorizationRoleDescriptionComparator() {
        super();
    }

    @Override
    public int compareConcrete(AuthorizationRole object1, AuthorizationRole object2) {
        return this.compareBasetype(object1.getDescription(), object2.getDescription());
    }
}
