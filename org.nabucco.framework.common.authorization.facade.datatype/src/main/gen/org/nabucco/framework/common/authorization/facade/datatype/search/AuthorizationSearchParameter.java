/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype.search;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeSupport;
import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.common.authorization.facade.datatype.search.AuthorizationType;

/**
 * AuthorizationSearchParameter<p/>Search Parameter for referencing Authorization Elements (e.g. Roles referenced by User)<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-26
 */
public class AuthorizationSearchParameter extends DatatypeSupport implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "id", "name", "owner", "type" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "m1,1;" };

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

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Identifier>(PROPERTY_NAMES[0], Identifier.class,
                PROPERTY_CONSTRAINTS[0], this.id));
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[1], Name.class,
                PROPERTY_CONSTRAINTS[1], this.name));
        properties.add(new BasetypeProperty<Owner>(PROPERTY_NAMES[2], Owner.class,
                PROPERTY_CONSTRAINTS[2], this.owner));
        properties.add(new EnumProperty<AuthorizationType>(PROPERTY_NAMES[3],
                AuthorizationType.class, PROPERTY_CONSTRAINTS[3], this.type));
        return properties;
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
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationSearchParameter>\n");
        appendable.append(super.toString());
        appendable.append((("<id>" + this.id) + "</id>\n"));
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<owner>" + this.owner) + "</owner>\n"));
        appendable.append((("<type>" + this.type) + "</type>\n"));
        appendable.append("</AuthorizationSearchParameter>\n");
        return appendable.toString();
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
}
