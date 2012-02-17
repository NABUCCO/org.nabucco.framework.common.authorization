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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserMaintainMsg<p/>Message for persisting an AuthorizationUser with its AuthorizationGroup<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-02-22
 */
public class AuthorizationUserMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,n;" };

    public static final String AUTHORIZATIONUSER = "authorizationUser";

    public static final String AUTHORIZATIONGROUPLIST = "authorizationGroupList";

    /** The authorization user to maintain. */
    private AuthorizationUser authorizationUser;

    /** The parent authorization groups to maintain. */
    private NabuccoList<AuthorizationGroup> authorizationGroupList;

    /** Constructs a new AuthorizationUserMaintainMsg instance. */
    public AuthorizationUserMaintainMsg() {
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
        propertyMap.put(AUTHORIZATIONUSER, PropertyDescriptorSupport.createDatatype(AUTHORIZATIONUSER,
                AuthorizationUser.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONGROUPLIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONGROUPLIST,
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
        properties.add(super.createProperty(AuthorizationUserMaintainMsg.getPropertyDescriptor(AUTHORIZATIONUSER),
                this.getAuthorizationUser()));
        properties.add(super.createProperty(AuthorizationUserMaintainMsg.getPropertyDescriptor(AUTHORIZATIONGROUPLIST),
                this.authorizationGroupList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(AUTHORIZATIONUSER) && (property.getType() == AuthorizationUser.class))) {
            this.setAuthorizationUser(((AuthorizationUser) property.getInstance()));
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONGROUPLIST) && (property.getType() == AuthorizationGroup.class))) {
            this.authorizationGroupList = ((NabuccoList<AuthorizationGroup>) property.getInstance());
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
        final AuthorizationUserMaintainMsg other = ((AuthorizationUserMaintainMsg) obj);
        if ((this.authorizationUser == null)) {
            if ((other.authorizationUser != null))
                return false;
        } else if ((!this.authorizationUser.equals(other.authorizationUser)))
            return false;
        if ((this.authorizationGroupList == null)) {
            if ((other.authorizationGroupList != null))
                return false;
        } else if ((!this.authorizationGroupList.equals(other.authorizationGroupList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationUser == null) ? 0 : this.authorizationUser.hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0 : this.authorizationGroupList
                .hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The authorization user to maintain.
     *
     * @return the AuthorizationUser.
     */
    public AuthorizationUser getAuthorizationUser() {
        return this.authorizationUser;
    }

    /**
     * The authorization user to maintain.
     *
     * @param authorizationUser the AuthorizationUser.
     */
    public void setAuthorizationUser(AuthorizationUser authorizationUser) {
        this.authorizationUser = authorizationUser;
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
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationUserMaintainMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationUserMaintainMsg.class).getAllProperties();
    }
}
