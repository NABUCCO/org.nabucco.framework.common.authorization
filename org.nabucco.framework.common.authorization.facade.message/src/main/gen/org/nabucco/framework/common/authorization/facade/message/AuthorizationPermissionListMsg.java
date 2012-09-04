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
package org.nabucco.framework.common.authorization.facade.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * AuthorizationPermissionListMsg<p/>Message for passing an AuthorizationPermission list<p/>
 *
 * @version 1.0
 * @author Frank Raschinski, PRODYNA AG, 2010-01-20
 */
public class AuthorizationPermissionListMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    public static final String AUTHORIZATIONPERMISSIONLIST = "authorizationPermissionList";

    /** The list of authorization permissions. */
    private NabuccoList<AuthorizationPermission> authorizationPermissionList;

    /** Constructs a new AuthorizationPermissionListMsg instance. */
    public AuthorizationPermissionListMsg() {
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
        propertyMap.put(AUTHORIZATIONPERMISSIONLIST, PropertyDescriptorSupport.createCollection(
                AUTHORIZATIONPERMISSIONLIST, AuthorizationPermission.class, 0, PROPERTY_CONSTRAINTS[0], false,
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
        properties.add(super.createProperty(
                AuthorizationPermissionListMsg.getPropertyDescriptor(AUTHORIZATIONPERMISSIONLIST),
                this.authorizationPermissionList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(AUTHORIZATIONPERMISSIONLIST) && (property.getType() == AuthorizationPermission.class))) {
            this.authorizationPermissionList = ((NabuccoList<AuthorizationPermission>) property.getInstance());
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
        final AuthorizationPermissionListMsg other = ((AuthorizationPermissionListMsg) obj);
        if ((this.authorizationPermissionList == null)) {
            if ((other.authorizationPermissionList != null))
                return false;
        } else if ((!this.authorizationPermissionList.equals(other.authorizationPermissionList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationPermissionList == null) ? 0 : this.authorizationPermissionList
                .hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The list of authorization permissions.
     *
     * @return the NabuccoList<AuthorizationPermission>.
     */
    public NabuccoList<AuthorizationPermission> getAuthorizationPermissionList() {
        if ((this.authorizationPermissionList == null)) {
            this.authorizationPermissionList = new NabuccoListImpl<AuthorizationPermission>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.authorizationPermissionList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationPermissionListMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationPermissionListMsg.class).getAllProperties();
    }
}
