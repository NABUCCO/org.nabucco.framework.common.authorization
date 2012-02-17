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
package org.nabucco.framework.common.authorization.facade.message.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.datatype.security.credential.Password;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * LoginMsg<p/>Login request message<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-09-20
 */
public class LoginMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l3,32;u0,n;m1,1;", "l3,128;u0,n;m1,1;", "l3,12;u0,n;m1,1;" };

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String TENANT = "tenant";

    /** The user ID. */
    private UserId username;

    /** The user password. */
    private Password password;

    /** The user tenant. */
    private Tenant tenant;

    /** Constructs a new LoginMsg instance. */
    public LoginMsg() {
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
        propertyMap.put(USERNAME,
                PropertyDescriptorSupport.createBasetype(USERNAME, UserId.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(PASSWORD,
                PropertyDescriptorSupport.createBasetype(PASSWORD, Password.class, 1, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(TENANT,
                PropertyDescriptorSupport.createBasetype(TENANT, Tenant.class, 2, PROPERTY_CONSTRAINTS[2], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(LoginMsg.getPropertyDescriptor(USERNAME), this.username));
        properties.add(super.createProperty(LoginMsg.getPropertyDescriptor(PASSWORD), this.password));
        properties.add(super.createProperty(LoginMsg.getPropertyDescriptor(TENANT), this.tenant));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(USERNAME) && (property.getType() == UserId.class))) {
            this.setUsername(((UserId) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PASSWORD) && (property.getType() == Password.class))) {
            this.setPassword(((Password) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TENANT) && (property.getType() == Tenant.class))) {
            this.setTenant(((Tenant) property.getInstance()));
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
        if ((this.tenant == null)) {
            if ((other.tenant != null))
                return false;
        } else if ((!this.tenant.equals(other.tenant)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.username == null) ? 0 : this.username.hashCode()));
        result = ((PRIME * result) + ((this.password == null) ? 0 : this.password.hashCode()));
        result = ((PRIME * result) + ((this.tenant == null) ? 0 : this.tenant.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The user ID.
     *
     * @return the UserId.
     */
    public UserId getUsername() {
        return this.username;
    }

    /**
     * The user ID.
     *
     * @param username the UserId.
     */
    public void setUsername(UserId username) {
        this.username = username;
    }

    /**
     * The user password.
     *
     * @return the Password.
     */
    public Password getPassword() {
        return this.password;
    }

    /**
     * The user password.
     *
     * @param password the Password.
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * The user tenant.
     *
     * @return the Tenant.
     */
    public Tenant getTenant() {
        return this.tenant;
    }

    /**
     * The user tenant.
     *
     * @param tenant the Tenant.
     */
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(LoginMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(LoginMsg.class).getAllProperties();
    }
}
