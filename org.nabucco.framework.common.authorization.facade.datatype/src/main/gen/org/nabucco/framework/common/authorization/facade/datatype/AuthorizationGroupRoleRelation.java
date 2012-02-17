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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationGroupRoleRelation<p/>Relation with a target AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationGroupRoleRelation extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    public static final String ROLE = "role";

    /** The AuthorizationRole relation target. */
    private AuthorizationRole role;

    /** Constructs a new AuthorizationGroupRoleRelation instance. */
    public AuthorizationGroupRoleRelation() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationGroupRoleRelation.
     */
    protected void cloneObject(AuthorizationGroupRoleRelation clone) {
        super.cloneObject(clone);
        if ((this.getRole() != null)) {
            clone.setRole(this.getRole().cloneObject());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(ROLE, PropertyDescriptorSupport.createDatatype(ROLE, AuthorizationRole.class, 3,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.AGGREGATION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationGroupRoleRelation.getPropertyDescriptor(ROLE), this.getRole(),
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(ROLE) && (property.getType() == AuthorizationRole.class))) {
            this.setRole(((AuthorizationRole) property.getInstance()));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final AuthorizationGroupRoleRelation other = ((AuthorizationGroupRoleRelation) obj);
        if ((this.role == null)) {
            if ((other.role != null))
                return false;
        } else if ((!this.role.equals(other.role)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.role == null) ? 0 : this.role.hashCode()));
        return result;
    }

    @Override
    public AuthorizationGroupRoleRelation cloneObject() {
        AuthorizationGroupRoleRelation clone = new AuthorizationGroupRoleRelation();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The AuthorizationRole relation target.
     *
     * @param role the AuthorizationRole.
     */
    public void setRole(AuthorizationRole role) {
        this.role = role;
    }

    /**
     * The AuthorizationRole relation target.
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getRole() {
        return this.role;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupRoleRelation.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupRoleRelation.class).getAllProperties();
    }
}
