/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message.search;

import java.util.ArrayList;
import java.util.List;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.code.CodeType;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
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

    private static final String[] PROPERTY_NAMES = { "name", "description", "owner", "codeType",
            "parameterList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m0,1;", "l0,n;m0,1;",
            "l0,n;m0,1;", "l0,n;m0,1;", "m0,n;" };

    /** Name of the datatype to search */
    private Name name;

    /** of the datatype to search */
    private Description description;

    /** Owner of the datatype to search */
    private Owner owner;

    /** CodeType of the datatype to search */
    private CodeType codeType;

    /** The search parameter list for referencing datatypes */
    private List<AuthorizationSearchParameter> parameterList;

    /** Constructs a new AuthorizationSearchMsg instance. */
    public AuthorizationSearchMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Name>(PROPERTY_NAMES[0], Name.class,
                PROPERTY_CONSTRAINTS[0], this.name));
        properties.add(new BasetypeProperty<Description>(PROPERTY_NAMES[1], Description.class,
                PROPERTY_CONSTRAINTS[1], this.description));
        properties.add(new BasetypeProperty<Owner>(PROPERTY_NAMES[2], Owner.class,
                PROPERTY_CONSTRAINTS[2], this.owner));
        properties.add(new BasetypeProperty<CodeType>(PROPERTY_NAMES[3], CodeType.class,
                PROPERTY_CONSTRAINTS[3], this.codeType));
        properties.add(new ListProperty<AuthorizationSearchParameter>(PROPERTY_NAMES[4],
                AuthorizationSearchParameter.class, PROPERTY_CONSTRAINTS[4], this.parameterList));
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
        result = ((PRIME * result) + ((this.parameterList == null) ? 0 : this.parameterList
                .hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationSearchMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<name>" + this.name) + "</name>\n"));
        appendable.append((("<description>" + this.description) + "</description>\n"));
        appendable.append((("<owner>" + this.owner) + "</owner>\n"));
        appendable.append((("<codeType>" + this.codeType) + "</codeType>\n"));
        appendable.append((("<parameterList>" + this.parameterList) + "</parameterList>\n"));
        appendable.append("</AuthorizationSearchMsg>\n");
        return appendable.toString();
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
     * of the datatype to search
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * of the datatype to search
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
     * @return the List<AuthorizationSearchParameter>.
     */
    public List<AuthorizationSearchParameter> getParameterList() {
        if ((this.parameterList == null)) {
            this.parameterList = new ArrayList<AuthorizationSearchParameter>();
        }
        return this.parameterList;
    }
}
