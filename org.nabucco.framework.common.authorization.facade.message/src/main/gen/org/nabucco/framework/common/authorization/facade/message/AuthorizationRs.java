/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Flag;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * AuthorizationRs<p/>Message for passing an AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationRs extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "valid" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;" };

    private Flag valid;

    /** Constructs a new AuthorizationRs instance. */
    public AuthorizationRs() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<Flag>(PROPERTY_NAMES[0], Flag.class,
                PROPERTY_CONSTRAINTS[0], this.valid));
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
        final AuthorizationRs other = ((AuthorizationRs) obj);
        if ((this.valid == null)) {
            if ((other.valid != null))
                return false;
        } else if ((!this.valid.equals(other.valid)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.valid == null) ? 0 : this.valid.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<AuthorizationRs>\n");
        appendable.append(super.toString());
        appendable.append((("<valid>" + this.valid) + "</valid>\n"));
        appendable.append("</AuthorizationRs>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getValid.
     *
     * @return the Flag.
     */
    public Flag getValid() {
        return this.valid;
    }

    /**
     * Missing description at method setValid.
     *
     * @param valid the Flag.
     */
    public void setValid(Flag valid) {
        this.valid = valid;
    }
}
