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
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.datatype.security.Role;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;

/**
 * AuthorizationRole<p/>Represents a role within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationRole extends Role implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;" };

    public static final String PERMISSIONLIST = "permissionList";

    /** The list of relations to AuthorizationPermission. */
    private NabuccoList<AuthorizationRolePermissionRelation> permissionList;

    private Long roleTypeRefId;

    /** Constructs a new AuthorizationRole instance. */
    public AuthorizationRole() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationRole.
     */
    protected void cloneObject(AuthorizationRole clone) {
        super.cloneObject(clone);
        if ((this.permissionList != null)) {
            clone.permissionList = this.permissionList.cloneCollection();
        }
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationRolePermissionRelation>.
     */
    List<AuthorizationRolePermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationRolePermissionRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationRolePermissionRelation>) this.permissionList).getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationRolePermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationRolePermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationRolePermissionRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationRolePermissionRelation>) this.permissionList).setDelegate(permissionList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(Role.class).getPropertyMap());
        propertyMap.put(PERMISSIONLIST, PropertyDescriptorSupport.createCollection(PERMISSIONLIST,
                AuthorizationRolePermissionRelation.class, 8, PROPERTY_CONSTRAINTS[0], false,
                PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationRole.getPropertyDescriptor(PERMISSIONLIST),
                this.permissionList, null));
        properties.add(super.createProperty(AuthorizationRole.getPropertyDescriptor(ROLETYPE), this.getRoleType(),
                this.roleTypeRefId));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(PERMISSIONLIST) && (property.getType() == AuthorizationRolePermissionRelation.class))) {
            this.permissionList = ((NabuccoList<AuthorizationRolePermissionRelation>) property.getInstance());
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
        final AuthorizationRole other = ((AuthorizationRole) obj);
        if ((this.roleTypeRefId == null)) {
            if ((other.roleTypeRefId != null))
                return false;
        } else if ((!this.roleTypeRefId.equals(other.roleTypeRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.roleTypeRefId == null) ? 0 : this.roleTypeRefId.hashCode()));
        return result;
    }

    @Override
    public AuthorizationRole cloneObject() {
        AuthorizationRole clone = new AuthorizationRole();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The list of relations to AuthorizationPermission.
     *
     * @return the NabuccoList<AuthorizationRolePermissionRelation>.
     */
    public NabuccoList<AuthorizationRolePermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationRolePermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }

    /**
     * Getter for the RoleTypeRefId.
     *
     * @return the Long.
     */
    public Long getRoleTypeRefId() {
        return this.roleTypeRefId;
    }

    /**
     * Setter for the RoleTypeRefId.
     *
     * @param roleTypeRefId the Long.
     */
    public void setRoleTypeRefId(Long roleTypeRefId) {
        this.roleTypeRefId = roleTypeRefId;
    }

    /**
     * Setter for the RoleType.
     *
     * @param roleType the Code.
     */
    public void setRoleType(Code roleType) {
        super.setRoleType(roleType);
        if ((roleType != null)) {
            this.setRoleTypeRefId(roleType.getId());
        } else {
            this.setRoleTypeRefId(null);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationRole.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationRole.class).getAllProperties();
    }
}
