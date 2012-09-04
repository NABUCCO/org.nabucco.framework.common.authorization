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
package org.nabucco.framework.common.authorization.facade.message.maintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * AuthorizationGroupMaintainMsg<p/>Message for persisting an AuthorizationGroup with its parent AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-02-22
 */
public class AuthorizationGroupMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,1;", "m1,1;" };

    public static final String PARENTAUTHORIZATIONGROUP = "parentAuthorizationGroup";

    public static final String AUTHORIZATIONGROUP = "authorizationGroup";

    /** The parent authorization group. */
    private AuthorizationGroup parentAuthorizationGroup;

    /** The authorization group to maintain. */
    private AuthorizationGroup authorizationGroup;

    /** Constructs a new AuthorizationGroupMaintainMsg instance. */
    public AuthorizationGroupMaintainMsg() {
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
        propertyMap.put(PARENTAUTHORIZATIONGROUP, PropertyDescriptorSupport.createDatatype(PARENTAUTHORIZATIONGROUP,
                AuthorizationGroup.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONGROUP, PropertyDescriptorSupport.createDatatype(AUTHORIZATIONGROUP,
                AuthorizationGroup.class, 1, PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(
                AuthorizationGroupMaintainMsg.getPropertyDescriptor(PARENTAUTHORIZATIONGROUP),
                this.getParentAuthorizationGroup()));
        properties.add(super.createProperty(AuthorizationGroupMaintainMsg.getPropertyDescriptor(AUTHORIZATIONGROUP),
                this.getAuthorizationGroup()));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(PARENTAUTHORIZATIONGROUP) && (property.getType() == AuthorizationGroup.class))) {
            this.setParentAuthorizationGroup(((AuthorizationGroup) property.getInstance()));
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONGROUP) && (property.getType() == AuthorizationGroup.class))) {
            this.setAuthorizationGroup(((AuthorizationGroup) property.getInstance()));
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
        final AuthorizationGroupMaintainMsg other = ((AuthorizationGroupMaintainMsg) obj);
        if ((this.parentAuthorizationGroup == null)) {
            if ((other.parentAuthorizationGroup != null))
                return false;
        } else if ((!this.parentAuthorizationGroup.equals(other.parentAuthorizationGroup)))
            return false;
        if ((this.authorizationGroup == null)) {
            if ((other.authorizationGroup != null))
                return false;
        } else if ((!this.authorizationGroup.equals(other.authorizationGroup)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.parentAuthorizationGroup == null) ? 0 : this.parentAuthorizationGroup
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroup == null) ? 0 : this.authorizationGroup.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The parent authorization group.
     *
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getParentAuthorizationGroup() {
        return this.parentAuthorizationGroup;
    }

    /**
     * The parent authorization group.
     *
     * @param parentAuthorizationGroup the AuthorizationGroup.
     */
    public void setParentAuthorizationGroup(AuthorizationGroup parentAuthorizationGroup) {
        this.parentAuthorizationGroup = parentAuthorizationGroup;
    }

    /**
     * The authorization group to maintain.
     *
     * @return the AuthorizationGroup.
     */
    public AuthorizationGroup getAuthorizationGroup() {
        return this.authorizationGroup;
    }

    /**
     * The authorization group to maintain.
     *
     * @param authorizationGroup the AuthorizationGroup.
     */
    public void setAuthorizationGroup(AuthorizationGroup authorizationGroup) {
        this.authorizationGroup = authorizationGroup;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupMaintainMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationGroupMaintainMsg.class).getAllProperties();
    }
}
