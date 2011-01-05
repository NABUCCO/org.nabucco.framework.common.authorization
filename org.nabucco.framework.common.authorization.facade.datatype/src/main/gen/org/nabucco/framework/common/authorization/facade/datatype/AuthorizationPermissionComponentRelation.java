/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelation;
import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelationType;

/**
 * AuthorizationPermissionComponentRelation<p/>Represents a permission within the authorization component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermissionComponentRelation extends
        ComponentRelation<AuthorizationPermission> {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationPermissionComponentRelation instance. */
    protected AuthorizationPermissionComponentRelation() {
        super();
    }

    /**
     * Constructs a new AuthorizationPermissionComponentRelation instance.
     *
     * @param relationType the ComponentRelationType.
     */
    public AuthorizationPermissionComponentRelation(ComponentRelationType relationType) {
        super(relationType);
    }

    /**
     * Getter for the Tarthe .
     *
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getTarget() {
        return super.getTarget();
    }

    /**
     * Setter for the Target.
     *
     * @param target the AuthorizationPermission.
     */
    public void setTarget(AuthorizationPermission target) {
        super.setTarget(target);
    }

    @Override
    public AuthorizationPermissionComponentRelation cloneObject() {
        AuthorizationPermissionComponentRelation clone = new AuthorizationPermissionComponentRelation();
        super.cloneObject(clone);
        return clone;
    }
}
