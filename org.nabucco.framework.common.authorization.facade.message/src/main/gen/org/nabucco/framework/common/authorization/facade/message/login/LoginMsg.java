/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.message.login;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.property.BasetypeProperty;
import org.nabucco.framework.base.facade.datatype.property.EnumProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.login.AuthenticationType;

/**
 * LoginMsg<p/>Login request message<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-09-20
 */
public class LoginMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_NAMES = { "username", "password", "loginType" };

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,n;m1,1;", "l0,n;m1,1;", "m0,1;" };

    private UserId username;

    private Password password;

    private AuthenticationType loginType;

    /** Constructs a new LoginMsg instance. */
    public LoginMsg() {
        super();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new BasetypeProperty<UserId>(PROPERTY_NAMES[0], UserId.class,
                PROPERTY_CONSTRAINTS[0], this.username));
        properties.add(new BasetypeProperty<Password>(PROPERTY_NAMES[1], Password.class,
                PROPERTY_CONSTRAINTS[1], this.password));
        properties.add(new EnumProperty<AuthenticationType>(PROPERTY_NAMES[2],
                AuthenticationType.class, PROPERTY_CONSTRAINTS[2], this.loginType));
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
        final LoginMsg other = ((LoginMsg) obj);
        if ((this.username == null)) {
            if ((other.username != null))
                return false;
        } else if ((!this.username.equals(other.username)))
            return false;
        if ((this.password == null)) {
            if ((other.password != null))
                return false;
        } else if ((!this.password.equals(other.password)))
            return false;
        if ((this.loginType == null)) {
            if ((other.loginType != null))
                return false;
        } else if ((!this.loginType.equals(other.loginType)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.username == null) ? 0 : this.username.hashCode()));
        result = ((PRIME * result) + ((this.password == null) ? 0 : this.password.hashCode()));
        result = ((PRIME * result) + ((this.loginType == null) ? 0 : this.loginType.hashCode()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append("<LoginMsg>\n");
        appendable.append(super.toString());
        appendable.append((("<username>" + this.username) + "</username>\n"));
        appendable.append((("<password>" + this.password) + "</password>\n"));
        appendable.append((("<loginType>" + this.loginType) + "</loginType>\n"));
        appendable.append("</LoginMsg>\n");
        return appendable.toString();
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Missing description at method getUsername.
     *
     * @return the UserId.
     */
    public UserId getUsername() {
        return this.username;
    }

    /**
     * Missing description at method setUsername.
     *
     * @param username the UserId.
     */
    public void setUsername(UserId username) {
        this.username = username;
    }

    /**
     * Missing description at method getPassword.
     *
     * @return the Password.
     */
    public Password getPassword() {
        return this.password;
    }

    /**
     * Missing description at method setPassword.
     *
     * @param password the Password.
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * Missing description at method getLoginType.
     *
     * @return the AuthenticationType.
     */
    public AuthenticationType getLoginType() {
        return this.loginType;
    }

    /**
     * Missing description at method setLoginType.
     *
     * @param loginType the AuthenticationType.
     */
    public void setLoginType(AuthenticationType loginType) {
        this.loginType = loginType;
    }
}
