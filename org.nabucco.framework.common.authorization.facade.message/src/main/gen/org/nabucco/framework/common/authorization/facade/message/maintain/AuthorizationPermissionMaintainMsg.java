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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationPermissionMaintainMsg<p/>Message for persisting an AuthorizationPermission with its AuthorizationGroup, AuthorizationUser and AuthorizationRole<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-05-24
 */
public class AuthorizationPermissionMaintainMsg extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m0,n;", "m0,n;", "m0,n;" };

    public static final String AUTHORIZATIONPERMISSION = "authorizationPermission";

    public static final String AUTHORIZATIONGROUPLIST = "authorizationGroupList";

    public static final String AUTHORIZATIONUSERLIST = "authorizationUserList";

    public static final String AUTHORIZATIONROLELIST = "authorizationRoleList";

    /** The authorization permission to maintain. */
    private AuthorizationPermission authorizationPermission;

    /** The parent authorization groups to maintain. */
    private NabuccoList<AuthorizationGroup> authorizationGroupList;

    /** The parent authorization users to maintain. */
    private NabuccoList<AuthorizationUser> authorizationUserList;

    /** The parent authorization roles to maintain. */
    private NabuccoList<AuthorizationRole> authorizationRoleList;

    /** Constructs a new AuthorizationPermissionMaintainMsg instance. */
    public AuthorizationPermissionMaintainMsg() {
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
        propertyMap.put(AUTHORIZATIONPERMISSION, PropertyDescriptorSupport.createDatatype(AUTHORIZATIONPERMISSION,
                AuthorizationPermission.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONGROUPLIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONGROUPLIST,
                AuthorizationGroup.class, 1, PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONUSERLIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONUSERLIST,
                AuthorizationUser.class, 2, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(AUTHORIZATIONROLELIST, PropertyDescriptorSupport.createCollection(AUTHORIZATIONROLELIST,
                AuthorizationRole.class, 3, PROPERTY_CONSTRAINTS[3], false, PropertyAssociationType.COMPOSITION));
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
                AuthorizationPermissionMaintainMsg.getPropertyDescriptor(AUTHORIZATIONPERMISSION),
                this.getAuthorizationPermission()));
        properties.add(super.createProperty(
                AuthorizationPermissionMaintainMsg.getPropertyDescriptor(AUTHORIZATIONGROUPLIST),
                this.authorizationGroupList));
        properties.add(super.createProperty(
                AuthorizationPermissionMaintainMsg.getPropertyDescriptor(AUTHORIZATIONUSERLIST),
                this.authorizationUserList));
        properties.add(super.createProperty(
                AuthorizationPermissionMaintainMsg.getPropertyDescriptor(AUTHORIZATIONROLELIST),
                this.authorizationRoleList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(AUTHORIZATIONPERMISSION) && (property.getType() == AuthorizationPermission.class))) {
            this.setAuthorizationPermission(((AuthorizationPermission) property.getInstance()));
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONGROUPLIST) && (property.getType() == AuthorizationGroup.class))) {
            this.authorizationGroupList = ((NabuccoList<AuthorizationGroup>) property.getInstance());
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONUSERLIST) && (property.getType() == AuthorizationUser.class))) {
            this.authorizationUserList = ((NabuccoList<AuthorizationUser>) property.getInstance());
            return true;
        } else if ((property.getName().equals(AUTHORIZATIONROLELIST) && (property.getType() == AuthorizationRole.class))) {
            this.authorizationRoleList = ((NabuccoList<AuthorizationRole>) property.getInstance());
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
        final AuthorizationPermissionMaintainMsg other = ((AuthorizationPermissionMaintainMsg) obj);
        if ((this.authorizationPermission == null)) {
            if ((other.authorizationPermission != null))
                return false;
        } else if ((!this.authorizationPermission.equals(other.authorizationPermission)))
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
        if ((this.authorizationRoleList == null)) {
            if ((other.authorizationRoleList != null))
                return false;
        } else if ((!this.authorizationRoleList.equals(other.authorizationRoleList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.authorizationPermission == null) ? 0 : this.authorizationPermission
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationGroupList == null) ? 0 : this.authorizationGroupList
                .hashCode()));
        result = ((PRIME * result) + ((this.authorizationUserList == null) ? 0 : this.authorizationUserList.hashCode()));
        result = ((PRIME * result) + ((this.authorizationRoleList == null) ? 0 : this.authorizationRoleList.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The authorization permission to maintain.
     *
     * @return the AuthorizationPermission.
     */
    public AuthorizationPermission getAuthorizationPermission() {
        return this.authorizationPermission;
    }

    /**
     * The authorization permission to maintain.
     *
     * @param authorizationPermission the AuthorizationPermission.
     */
    public void setAuthorizationPermission(AuthorizationPermission authorizationPermission) {
        this.authorizationPermission = authorizationPermission;
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
     * The parent authorization users to maintain.
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
     * The parent authorization roles to maintain.
     *
     * @return the NabuccoList<AuthorizationRole>.
     */
    public NabuccoList<AuthorizationRole> getAuthorizationRoleList() {
        if ((this.authorizationRoleList == null)) {
            this.authorizationRoleList = new NabuccoListImpl<AuthorizationRole>(NabuccoCollectionState.INITIALIZED);
        }
        return this.authorizationRoleList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationPermissionMaintainMsg.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationPermissionMaintainMsg.class).getAllProperties();
    }
}
