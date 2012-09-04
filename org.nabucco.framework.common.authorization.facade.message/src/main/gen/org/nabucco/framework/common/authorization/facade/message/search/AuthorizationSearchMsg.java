/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.common.authorization.facade.message.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationSearchParameter;

/**
 * AuthorizationSearchMsg<p/>Generic search message for all authorization search requests<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-25
 */
public class AuthorizationSearchMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m0,1;", "l0,255;u0,n;m0,1;",
            "l3,12;u0,n;m0,1;", "l1,32;u0,n;m0,1;", "m0,n;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String OWNER = "owner";

    public static final String CODETYPE = "codeType";

    public static final String PARAMETERLIST = "parameterList";

    /** Name of the datatype to search */
    private Name name;

    /** Description of the datatype to search */
    private Description description;

    /** Owner of the datatype to search */
    private Owner owner;

    /** CodeType of the datatype to search */
    private CodeType codeType;

    /** The search parameter list for referencing datatypes */
    private NabuccoList<AuthorizationSearchParameter> parameterList;

    /** Constructs a new AuthorizationSearchMsg instance. */
    public AuthorizationSearchMsg() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 1,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 2, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(CODETYPE,
                PropertyDescriptorSupport.createBasetype(CODETYPE, CodeType.class, 3, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(PARAMETERLIST, PropertyDescriptorSupport.createCollection(PARAMETERLIST,
                AuthorizationSearchParameter.class, 4, PROPERTY_CONSTRAINTS[4], false,
                PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationSearchMsg.getPropertyDescriptor(NAME), this.name));
        properties
                .add(super.createProperty(AuthorizationSearchMsg.getPropertyDescriptor(DESCRIPTION), this.description));
        properties.add(super.createProperty(AuthorizationSearchMsg.getPropertyDescriptor(OWNER), this.owner));
        properties.add(super.createProperty(AuthorizationSearchMsg.getPropertyDescriptor(CODETYPE), this.codeType));
        properties.add(super.createProperty(AuthorizationSearchMsg.getPropertyDescriptor(PARAMETERLIST),
                this.parameterList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CODETYPE) && (property.getType() == CodeType.class))) {
            this.setCodeType(((CodeType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PARAMETERLIST) && (property.getType() == AuthorizationSearchParameter.class))) {
            this.parameterList = ((NabuccoList<AuthorizationSearchParameter>) property.getInstance());
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
        final AuthorizationSearchMsg other = ((AuthorizationSearchMsg) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.codeType == null)) {
            if ((other.codeType != null))
                return false;
        } else if ((!this.codeType.equals(other.codeType)))
            return false;
        if ((this.parameterList == null)) {
            if ((other.parameterList != null))
                return false;
        } else if ((!this.parameterList.equals(other.parameterList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.codeType == null) ? 0 : this.codeType.hashCode()));
        result = ((PRIME * result) + ((this.parameterList == null) ? 0 : this.parameterList.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Name of the datatype to search
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the datatype to search
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Description of the datatype to search
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Description of the datatype to search
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Owner of the datatype to search
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of the datatype to search
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * CodeType of the datatype to search
     *
     * @return the CodeType.
     */
    public CodeType getCodeType() {
        return this.codeType;
    }

    /**
     * CodeType of the datatype to search
     *
     * @param codeType the CodeType.
     */
    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    /**
     * The search parameter list for referencing datatypes
     *
     * @return the NabuccoList<AuthorizationSearchParameter>.
     */
    public NabuccoList<AuthorizationSearchParameter> getParameterList() {
        if ((this.parameterList == null)) {
            this.parameterList = new NabuccoListImpl<AuthorizationSearchParameter>(NabuccoCollectionState.INITIALIZED);
        }
        return this.parameterList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationSearchMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationSearchMsg.class).getAllProperties();
    }
}
