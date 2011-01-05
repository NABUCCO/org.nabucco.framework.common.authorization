/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelation;
import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelationType;

/**
 * AuthorizationGroupComponentRelation<p/>Represents a group within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationGroupComponentRelation extends ComponentRelation<AuthorizationGroup> {

    private static final long serialVersionUID = 1L;

    /** Constructs a new AuthorizationGroupComponentRelation instance. */
    protected AuthorizationGroupComponentRelation() {
        super();
    }

    /**
     * Constructs a new AuthorizationGroupComponentRelation instance.
     *
     * @param relationType the ComponentRelationType.
     */
    public AuthorizationGroupComponentRelation(ComponentRelationType relationType) {
        super(relationType);
    }

    /**
     * Getter for the Tarthe .
     *
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getTarget() {
        return super.getTarget();
    }

    /**
     * Setter for the Target.
     *
     * @param target the AuthorizationGroup.
     */
    public void setTarget(AuthorizationGroup target) {
        super.setTarget(target);
    }

    @Override
    public AuthorizationGroupComponentRelation cloneObject() {
        AuthorizationGroupComponentRelation clone = new AuthorizationGroupComponentRelation();
        super.cloneObject(clone);
        return clone;
    }
}
