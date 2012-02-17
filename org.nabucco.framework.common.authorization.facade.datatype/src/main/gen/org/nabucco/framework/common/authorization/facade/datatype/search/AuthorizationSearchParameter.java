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
package org.nabucco.framework.common.authorization.facade.datatype.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeSupport;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;

/**
 * AuthorizationSearchParameter<p/>Search Parameter for referencing Authorization Elements (e.g. Roles referenced by User)<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-26
 */
public class AuthorizationSearchParameter extends DatatypeSupport implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;u0,n;m0,1;", "l0,255;u0,n;m0,1;", "l3,12;u0,n;m0,1;",
            "m1,1;" };

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String TYPE = "type";

    /** ID of the referencing element */
    private Identifier id;

    /** Name of the referencing element */
    private Name name;

    /** Owner of the referencing element */
    private Owner owner;

    /** Type of the referencing element */
    private AuthorizationType type;

    /** Constructs a new AuthorizationSearchParameter instance. */
    public AuthorizationSearchParameter() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationSearchParameter.
     */
    protected void cloneObject(AuthorizationSearchParameter clone) {
        super.cloneObject(clone);
        if ((this.getId() != null)) {
            clone.setId(this.getId().cloneObject());
        }
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getOwner() != null)) {
            clone.setOwner(this.getOwner().cloneObject());
        }
        clone.setType(this.getType());
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(ID,
                PropertyDescriptorSupport.createBasetype(ID, Identifier.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 1, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 2, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(TYPE, PropertyDescriptorSupport.createEnumeration(TYPE, AuthorizationType.class, 3,
                PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationSearchParameter.getPropertyDescriptor(ID), this.id, null));
        properties.add(super.createProperty(AuthorizationSearchParameter.getPropertyDescriptor(NAME), this.name, null));
        properties
                .add(super.createProperty(AuthorizationSearchParameter.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(AuthorizationSearchParameter.getPropertyDescriptor(TYPE), this.getType(),
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(ID) && (property.getType() == Identifier.class))) {
            this.setId(((Identifier) property.getInstance()));
            return true;
        } else if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TYPE) && (property.getType() == AuthorizationType.class))) {
            this.setType(((AuthorizationType) property.getInstance()));
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
        final AuthorizationSearchParameter other = ((AuthorizationSearchParameter) obj);
        if ((this.id == null)) {
            if ((other.id != null))
                return false;
        } else if ((!this.id.equals(other.id)))
            return false;
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.type == null)) {
            if ((other.type != null))
                return false;
        } else if ((!this.type.equals(other.type)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.type == null) ? 0 : this.type.hashCode()));
        return result;
    }

    @Override
    public AuthorizationSearchParameter cloneObject() {
        AuthorizationSearchParameter clone = new AuthorizationSearchParameter();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * ID of the referencing element
     *
     * @return the Identifier.
     */
    public Identifier getId() {
        return this.id;
    }

    /**
     * ID of the referencing element
     *
     * @param id the Identifier.
     */
    public void setId(Identifier id) {
        this.id = id;
    }

    /**
     * ID of the referencing element
     *
     * @param id the Long.
     */
    public void setId(Long id) {
        if ((this.id == null)) {
            if ((id == null)) {
                return;
            }
            this.id = new Identifier();
        }
        this.id.setValue(id);
    }

    /**
     * Name of the referencing element
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the referencing element
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Name of the referencing element
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Owner of the referencing element
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of the referencing element
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Owner of the referencing element
     *
     * @param owner the String.
     */
    public void setOwner(String owner) {
        if ((this.owner == null)) {
            if ((owner == null)) {
                return;
            }
            this.owner = new Owner();
        }
        this.owner.setValue(owner);
    }

    /**
     * Type of the referencing element
     *
     * @return the AuthorizationType.
     */
    public AuthorizationType getType() {
        return this.type;
    }

    /**
     * Type of the referencing element
     *
     * @param type the AuthorizationType.
     */
    public void setType(AuthorizationType type) {
        this.type = type;
    }

    /**
     * Type of the referencing element
     *
     * @param type the String.
     */
    public void setType(String type) {
        if ((type == null)) {
            this.type = null;
        } else {
            this.type = AuthorizationType.valueOf(type);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationSearchParameter.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationSearchParameter.class).getAllProperties();
    }
}
