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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationRoleMaintainMsg<p/>Message for persisting an AuthorizationRole with its AuthorizationGroup and AuthorizationUser<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-24
 */
public class AuthorizationRoleMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m0,n;", "m0,n;" };

    public static final String AUTHORIZATIONROLE = "authorizationRole";

    public static final String AUTHORIZATIONGROUPLIST = "authorizationGroupList";

    public static final String AUTHORIZATIONUSERLIST = "authorizationUserList";

    /** The authorization role to maintain. */
    private AuthorizationRole authorizationRole;

    /** The parent authorization groups to maintain. */
    private NabuccoList<AuthorizationGroup> authorizationGroupList;

    /** The parent authorization user to maintain. */
    private NabuccoList<AuthorizationUser> authorizationUserList;

    /** Constructs a new AuthorizationRoleMaintainMsg instance. */
    public AuthorizationRoleMaintainMsg() {
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
        propertyMap.put(AUTHORIZATIONROLE, PropertyDescriptorSupport.createDatatype(AUTHORIZATIONROLE,
                AuthorizationRole.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONGROUPLIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONGROUPLIST,
                AuthorizationGroup.class, 1, PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONUSERLIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONUSERLIST,
                AuthorizationUser.class, 2, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationRoleMaintainMsg.getPropertyDescriptor(AUTHORIZATIONROLE),
                this.getAuthorizationRole()));
        properties.add(super.createProperty(AuthorizationRoleMaintainMsg.getPropertyDescriptor(AUTHORIZATIONGROUPLIST),
                this.authorizationGroupList));
        properties.add(super.createProperty(AuthorizationRoleMaintainMsg.getPropertyDescriptor(AUTHORIZATIONUSERLIST),
                this.authorizationUserList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(AUTHORIZATIONROLE) && (property.getType() == AuthorizationRole.class))) {
            this.setAuthorizationRole(((AuthorizationRole) property.getInstance()));
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONGROUPLIST) && (property.getType() == AuthorizationGroup.class))) {
            this.authorizationGroupList = ((NabuccoList<AuthorizationGroup>) property.getInstance());
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONUSERLIST) && (property.getType() == AuthorizationUser.class))) {
            this.authorizationUserList = ((NabuccoList<AuthorizationUser>) property.getInstance());
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
        final AuthorizationRoleMaintainMsg other = ((AuthorizationRoleMaintainMsg) obj);
        if ((this.authorizationRole == null)) {
            if ((other.authorizationRole != null))
                return false;
        } else if ((!this.authorizationRole.equals(other.authorizationRole)))
            return false;
        if ((this.authorizationGroupList == null)) {
            if ((other.authorizationGroupList != null))
                return false;
        } else if ((!this.authorizationGroupList.equals(other.authorizationGroupList)))
            return false;
        if ((this.authorizationUserList == null)) {
            if ((other.authorizationUserList != null))
                return false;
        } else if ((!this.authorizationUserList.equals(other.authorizationUserList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationRole == null) ? 0 : this.authorizationRole.hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0 : this.authorizationGroupList
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationUserList == null) ? 0 : this.authorizationUserList.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The authorization role to maintain.
     *
     * @return the AuthorizationRole.
     */
    public AuthorizationRole getAuthorizationRole() {
        return this.authorizationRole;
    }

    /**
     * The authorization role to maintain.
     *
     * @param authorizationRole the AuthorizationRole.
     */
    public void setAuthorizationRole(AuthorizationRole authorizationRole) {
        this.authorizationRole = authorizationRole;
    }

    /**
     * The parent authorization groups to maintain.
     *
     * @return the NabuccoList<AuthorizationGroup>.
     */
    public NabuccoList<AuthorizationGroup> getAuthorizationGroupList() {
        if ((this.authorizationGroupList == null)) {
            this.authorizationGroupList = new NabuccoListImpl<AuthorizationGroup>(NabuccoCollectionState.INITIALIZED);
        }
        return this.authorizationGroupList;
    }

    /**
     * The parent authorization user to maintain.
     *
     * @return the NabuccoList<AuthorizationUser>.
     */
    public NabuccoList<AuthorizationUser> getAuthorizationUserList() {
        if ((this.authorizationUserList == null)) {
            this.authorizationUserList = new NabuccoListImpl<AuthorizationUser>(NabuccoCollectionState.INITIALIZED);
        }
        return this.authorizationUserList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationRoleMaintainMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationRoleMaintainMsg.class).getAllProperties();
    }
}
