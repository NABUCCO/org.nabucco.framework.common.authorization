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
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationInformationRs<p/>Message holding authorization information of a user.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-05-20
 */
public class AuthorizationInformationRs extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;", "m0,n;", "m0,n;" };

    public static final String GROUPLIST = "groupList";

    public static final String ROLELIST = "roleList";

    public static final String PERMISSIONLIST = "permissionList";

    /** The list of contained authorization groups. */
    private NabuccoList<AuthorizationGroup> groupList;

    /** The list of hold authorization roles. */
    private NabuccoList<AuthorizationRole> roleList;

    /** The list of hold authorization permissions. */
    private NabuccoList<AuthorizationPermission> permissionList;

    /** Constructs a new AuthorizationInformationRs instance. */
    public AuthorizationInformationRs() {
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
        propertyMap.put(GROUPLIST, PropertyDescriptorSupport.createCollection(GROUPLIST, AuthorizationGroup.class, 0,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(ROLELIST, PropertyDescriptorSupport.createCollection(ROLELIST, AuthorizationRole.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(PERMISSIONLIST, PropertyDescriptorSupport.createCollection(PERMISSIONLIST,
                AuthorizationPermission.class, 2, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties
                .add(super.createProperty(AuthorizationInformationRs.getPropertyDescriptor(GROUPLIST), this.groupList));
        properties.add(super.createProperty(AuthorizationInformationRs.getPropertyDescriptor(ROLELIST), this.roleList));
        properties.add(super.createProperty(AuthorizationInformationRs.getPropertyDescriptor(PERMISSIONLIST),
                this.permissionList));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(GROUPLIST) && (property.getType() == AuthorizationGroup.class))) {
            this.groupList = ((NabuccoList<AuthorizationGroup>) property.getInstance());
            return true;
        } else if ((property.getName().equals(ROLELIST) && (property.getType() == AuthorizationRole.class))) {
            this.roleList = ((NabuccoList<AuthorizationRole>) property.getInstance());
            return true;
        } else if ((property.getName().equals(PERMISSIONLIST) && (property.getType() == AuthorizationPermission.class))) {
            this.permissionList = ((NabuccoList<AuthorizationPermission>) property.getInstance());
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
        final AuthorizationInformationRs other = ((AuthorizationInformationRs) obj);
        if ((this.groupList == null)) {
            if ((other.groupList != null))
                return false;
        } else if ((!this.groupList.equals(other.groupList)))
            return false;
        if ((this.roleList == null)) {
            if ((other.roleList != null))
                return false;
        } else if ((!this.roleList.equals(other.roleList)))
            return false;
        if ((this.permissionList == null)) {
            if ((other.permissionList != null))
                return false;
        } else if ((!this.permissionList.equals(other.permissionList)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.groupList == null) ? 0 : this.groupList.hashCode()));
        result = ((PRIME * result) + ((this.roleList == null) ? 0 : this.roleList.hashCode()));
        result = ((PRIME * result) + ((this.permissionList == null) ? 0 : this.permissionList.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The list of contained authorization groups.
     *
     * @return the NabuccoList<AuthorizationGroup>.
     */
    public NabuccoList<AuthorizationGroup> getGroupList() {
        if ((this.groupList == null)) {
            this.groupList = new NabuccoListImpl<AuthorizationGroup>(NabuccoCollectionState.INITIALIZED);
        }
        return this.groupList;
    }

    /**
     * The list of hold authorization roles.
     *
     * @return the NabuccoList<AuthorizationRole>.
     */
    public NabuccoList<AuthorizationRole> getRoleList() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationRole>(NabuccoCollectionState.INITIALIZED);
        }
        return this.roleList;
    }

    /**
     * The list of hold authorization permissions.
     *
     * @return the NabuccoList<AuthorizationPermission>.
     */
    public NabuccoList<AuthorizationPermission> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationPermission>(NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationInformationRs.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationInformationRs.class).getAllProperties();
    }
}
