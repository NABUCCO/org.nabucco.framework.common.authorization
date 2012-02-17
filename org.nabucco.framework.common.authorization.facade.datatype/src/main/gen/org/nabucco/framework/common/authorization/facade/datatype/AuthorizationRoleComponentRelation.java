/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
