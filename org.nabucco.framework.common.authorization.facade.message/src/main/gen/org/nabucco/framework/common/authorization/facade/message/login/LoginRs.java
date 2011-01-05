/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message.login;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.DatatypeProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.security.Subject;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * LoginRs<p/>Response message for passing the loaded AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-01-20
 */
public class LoginRs extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "subject" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;" };

    private Subject subject;

    /** Constructs a new LoginRs instance. */
    public LoginRs() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new DatatypeProperty<Subject>(PROPERTY_NAMES[0], Subject.class,
                PROPERTY_CONSTRAINTS[0], this.subject));
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
        final LoginRs other = ((LoginRs) obj);
        if ((this.subject == null)) {
            if ((other.subject != null))
                return false;
        } else if ((!this.subject.equals(other.subject)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.subject == null) ? 0 : this.subject.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<LoginRs>\n");
        appendable.append(super.toString());
        appendable.append((("<subject>" + this.subject) + "</subject>\n"));
        appendable.append("</LoginRs>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getSubject.
     *
     * @return the Subject.
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Missing description at method setSubject.
     *
     * @param subject the Subject.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
