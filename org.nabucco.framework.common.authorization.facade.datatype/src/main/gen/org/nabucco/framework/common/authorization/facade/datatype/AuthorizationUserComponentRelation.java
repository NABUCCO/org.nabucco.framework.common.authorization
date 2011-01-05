/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelation;
import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelationType;

/**
 * AuthorizationUserComponentRelation<p/>Represents a user within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationUserComponentRelation extends ComponentRelation<AuthorizationUser> {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationUserComponentRelation instance. */
    protected AuthorizationUserComponentRelation() {
        super();
    }

    /**
     * Constructs a new AuthorizationUserComponentRelation instance.
     *
     * @param relationType the ComponentRelationType.
     */
    public AuthorizationUserComponentRelation(ComponentRelationType relationType) {
        super(relationType);
    }

    /**
     * Getter for the Tarthe .
     *
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getTarget() {
        return super.getTarget();
    }

    /**
     * Setter for the Target.
     *
     * @param target the AuthorizationUser.
     */
    public void setTarget(AuthorizationUser target) {
        super.setTarget(target);
    }

    @Override
    public AuthorizationUserComponentRelation cloneObject() {
        AuthorizationUserComponentRelation clone = new AuthorizationUserComponentRelation();
        super.cloneObject(clone);
        return clone;
    }
}
