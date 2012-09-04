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
import org.nabucco.framework.base.facade.datatype.security.User;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPassword;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;

/**
 * AuthorizationUser<p/>Represents a user within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationUser extends User implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,1;", "m0,n;", "m0,n;" };

    public static final String PASSWORD = "password";

    public static final String ROLELIST = "roleList";

    public static final String PERMISSIONLIST = "permissionList";

    /** The user password. */
    private AuthorizationUserPassword password;

    /** The list of relations to AuthorizationRole. */
    private NabuccoList<AuthorizationUserRoleRelation> roleList;

    /** The list of relations to AuthorizationPermission. */
    private NabuccoList<AuthorizationUserPermissionRelation> permissionList;

    private Long userTypeRefId;

    /** Constructs a new AuthorizationUser instance. */
    public AuthorizationUser() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationUser.
     */
    protected void cloneObject(AuthorizationUser clone) {
        super.cloneObject(clone);
        if ((this.getPassword() != null)) {
            clone.setPassword(this.getPassword().cloneObject());
        }
        if ((this.roleList != null)) {
            clone.roleList = this.roleList.cloneCollection();
        }
        if ((this.permissionList != null)) {
            clone.permissionList = this.permissionList.cloneCollection();
        }
    }

    /**
     * Getter for the RoleListJPA.
     *
     * @return the List<AuthorizationUserRoleRelation>.
     */
    List<AuthorizationUserRoleRelation> getRoleListJPA() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationUserRoleRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationUserRoleRelation>) this.roleList).getDelegate();
    }

    /**
     * Setter for the RoleListJPA.
     *
     * @param roleList the List<AuthorizationUserRoleRelation>.
     */
    void setRoleListJPA(List<AuthorizationUserRoleRelation> roleList) {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationUserRoleRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationUserRoleRelation>) this.roleList).setDelegate(roleList);
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationUserPermissionRelation>.
     */
    List<AuthorizationUserPermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationUserPermissionRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationUserPermissionRelation>) this.permissionList).getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationUserPermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationUserPermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationUserPermissionRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationUserPermissionRelation>) this.permissionList).setDelegate(permissionList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(User.class).getPropertyMap());
        propertyMap.put(PASSWORD, PropertyDescriptorSupport.createDatatype(PASSWORD, AuthorizationUserPassword.class,
                8, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(ROLELIST, PropertyDescriptorSupport.createCollection(ROLELIST,
                AuthorizationUserRoleRelation.class, 9, PROPERTY_CONSTRAINTS[1], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(PERMISSIONLIST, PropertyDescriptorSupport.createCollection(PERMISSIONLIST,
                AuthorizationUserPermissionRelation.class, 10, PROPERTY_CONSTRAINTS[2], false,
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
        properties
                .add(super.createProperty(AuthorizationUser.getPropertyDescriptor(PASSWORD), this.getPassword(), null));
        properties.add(super.createProperty(AuthorizationUser.getPropertyDescriptor(ROLELIST), this.roleList, null));
        properties.add(super.createProperty(AuthorizationUser.getPropertyDescriptor(PERMISSIONLIST),
                this.permissionList, null));
        properties.add(super.createProperty(AuthorizationUser.getPropertyDescriptor(USERTYPE), this.getUserType(),
                this.userTypeRefId));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(PASSWORD) && (property.getType() == AuthorizationUserPassword.class))) {
            this.setPassword(((AuthorizationUserPassword) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ROLELIST) && (property.getType() == AuthorizationUserRoleRelation.class))) {
            this.roleList = ((NabuccoList<AuthorizationUserRoleRelation>) property.getInstance());
            return true;
        } else if ((property.getName().equals(PERMISSIONLIST) && (property.getType() == AuthorizationUserPermissionRelation.class))) {
            this.permissionList = ((NabuccoList<AuthorizationUserPermissionRelation>) property.getInstance());
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
        final AuthorizationUser other = ((AuthorizationUser) obj);
        if ((this.password == null)) {
            if ((other.password != null))
                return false;
        } else if ((!this.password.equals(other.password)))
            return false;
        if ((this.userTypeRefId == null)) {
            if ((other.userTypeRefId != null))
                return false;
        } else if ((!this.userTypeRefId.equals(other.userTypeRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.password == null) ? 0 : this.password.hashCode()));
        result = ((PRIME * result) + ((this.userTypeRefId == null) ? 0 : this.userTypeRefId.hashCode()));
        return result;
    }

    @Override
    public AuthorizationUser cloneObject() {
        AuthorizationUser clone = new AuthorizationUser();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The user password.
     *
     * @param password the AuthorizationUserPassword.
     */
    public void setPassword(AuthorizationUserPassword password) {
        this.password = password;
    }

    /**
     * The user password.
     *
     * @return the AuthorizationUserPassword.
     */
    public AuthorizationUserPassword getPassword() {
        return this.password;
    }

    /**
     * The list of relations to AuthorizationRole.
     *
     * @return the NabuccoList<AuthorizationUserRoleRelation>.
     */
    public NabuccoList<AuthorizationUserRoleRelation> getRoleList() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationUserRoleRelation>(NabuccoCollectionState.INITIALIZED);
        }
        return this.roleList;
    }

    /**
     * The list of relations to AuthorizationPermission.
     *
     * @return the NabuccoList<AuthorizationUserPermissionRelation>.
     */
    public NabuccoList<AuthorizationUserPermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationUserPermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }

    /**
     * Getter for the UserTypeRefId.
     *
     * @return the Long.
     */
    public Long getUserTypeRefId() {
        return this.userTypeRefId;
    }

    /**
     * Setter for the UserTypeRefId.
     *
     * @param userTypeRefId the Long.
     */
    public void setUserTypeRefId(Long userTypeRefId) {
        this.userTypeRefId = userTypeRefId;
    }

    /**
     * Setter for the UserType.
     *
     * @param userType the Code.
     */
    public void setUserType(Code userType) {
        super.setUserType(userType);
        if ((userType != null)) {
            this.setUserTypeRefId(userType.getId());
        } else {
            this.setUserTypeRefId(null);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationUser.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationUser.class).getAllProperties();
    }
}
