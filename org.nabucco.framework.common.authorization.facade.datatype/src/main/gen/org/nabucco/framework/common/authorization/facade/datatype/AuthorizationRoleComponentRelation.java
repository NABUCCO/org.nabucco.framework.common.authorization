/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelation;
import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelationType;

/**
 * AuthorizationRoleComponentRelation<p/>Represents a role within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationRoleComponentRelation extends ComponentRelation<AuthorizationRole> {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationRoleComponentRelation instance. */
    protected AuthorizationRoleComponentRelation() {
        super();
    }

    /**
     * Constructs a new AuthorizationRoleComponentRelation instance.
     *
     * @param relationType the ComponentRelationType.
     */
    public AuthorizationRoleComponentRelation(ComponentRelationType relationType) {
        super(relationType);
    }

    /**
     * Getter for the Tarthe .
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getTarget() {
        return super.getTarget();
    }

    /**
     * Setter for the Target.
     *
     * @param target the AuthorizationRole.
     */
    public void setTarget(AuthorizationRole target) {
        super.setTarget(target);
    }

    @Override
    public AuthorizationRoleComponentRelation cloneObject() {
        AuthorizationRoleComponentRelation clone = new AuthorizationRoleComponentRelation();
        super.cloneObject(clone);
        return clone;
    }
}
