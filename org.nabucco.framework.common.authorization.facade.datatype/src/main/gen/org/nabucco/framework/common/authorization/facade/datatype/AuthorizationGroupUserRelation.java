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
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationGroupUserRelation<p/>Relation with a target AuthorizationUser<p/>
 *
 * @version 1.0
 * @author nmoser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationGroupUserRelation extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final Flag DEPUTY_DEFAULT = new Flag(false);

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "l0,n;u0,n;m1,1;" };

    public static final String USER = "user";

    public static final String DEPUTY = "deputy";

    /** The AuthorizationUser relation target. */
    private AuthorizationUser user;

    /** Defines a user as deputy of a group */
    private Flag deputy;

    /** Constructs a new AuthorizationGroupUserRelation instance. */
    public AuthorizationGroupUserRelation() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        deputy = DEPUTY_DEFAULT;
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationGroupUserRelation.
     */
    protected void cloneObject(AuthorizationGroupUserRelation clone) {
        super.cloneObject(clone);
        if ((this.getUser() != null)) {
            clone.setUser(this.getUser().cloneObject());
        }
        if ((this.getDeputy() != null)) {
            clone.setDeputy(this.getDeputy().cloneObject());
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
        propertyMap.put(USER, PropertyDescriptorSupport.createDatatype(USER, AuthorizationUser.class, 3,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.AGGREGATION));
        propertyMap.put(DEPUTY,
                PropertyDescriptorSupport.createBasetype(DEPUTY, Flag.class, 4, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationGroupUserRelation.getPropertyDescriptor(USER), this.getUser(),
                null));
        properties.add(super.createProperty(AuthorizationGroupUserRelation.getPropertyDescriptor(DEPUTY), this.deputy,
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(USER) && (property.getType() == AuthorizationUser.class))) {
            this.setUser(((AuthorizationUser) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DEPUTY) && (property.getType() == Flag.class))) {
            this.setDeputy(((Flag) property.getInstance()));
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
        final AuthorizationGroupUserRelation other = ((AuthorizationGroupUserRelation) obj);
        if ((this.user == null)) {
            if ((other.user != null))
                return false;
        } else if ((!this.user.equals(other.user)))
            return false;
        if ((this.deputy == null)) {
            if ((other.deputy != null))
                return false;
        } else if ((!this.deputy.equals(other.deputy)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.user == null) ? 0 : this.user.hashCode()));
        result = ((PRIME * result) + ((this.deputy == null) ? 0 : this.deputy.hashCode()));
        return result;
    }

    @Override
    public AuthorizationGroupUserRelation cloneObject() {
        AuthorizationGroupUserRelation clone = new AuthorizationGroupUserRelation();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The AuthorizationUser relation target.
     *
     * @param user the AuthorizationUser.
     */
    public void setUser(AuthorizationUser user) {
        this.user = user;
    }

    /**
     * The AuthorizationUser relation target.
     *
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getUser() {
        return this.user;
    }

    /**
     * Defines a user as deputy of a group
     *
     * @return the Flag.
     */
    public Flag getDeputy() {
        return this.deputy;
    }

    /**
     * Defines a user as deputy of a group
     *
     * @param deputy the Flag.
     */
    public void setDeputy(Flag deputy) {
        this.deputy = deputy;
    }

    /**
     * Defines a user as deputy of a group
     *
     * @param deputy the Boolean.
     */
    public void setDeputy(Boolean deputy) {
        if ((this.deputy == null)) {
            if ((deputy == null)) {
                return;
            }
            this.deputy = new Flag();
        }
        this.deputy.setValue(deputy);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupUserRelation.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupUserRelation.class).getAllProperties();
    }
}
