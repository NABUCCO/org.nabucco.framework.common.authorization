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
import org.nabucco.framework.base.facade.datatype.security.Group;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupRoleRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;

/**
 * AuthorizationGroup<p/>Represents a group within the authorization component<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2010-11-30
 */
public class AuthorizationGroup extends Group implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;", "m0,n;", "m0,n;", "m0,n;" };

    public static final String CHILDGROUPLIST = "childGroupList";

    public static final String USERLIST = "userList";

    public static final String ROLELIST = "roleList";

    public static final String PERMISSIONLIST = "permissionList";

    private NabuccoList<AuthorizationGroup> childGroupList;

    private NabuccoList<AuthorizationGroupUserRelation> userList;

    private NabuccoList<AuthorizationGroupRoleRelation> roleList;

    private NabuccoList<AuthorizationGroupPermissionRelation> permissionList;

    private Long groupTypeRefId;

    /** Constructs a new AuthorizationGroup instance. */
    public AuthorizationGroup() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationGroup.
     */
    protected void cloneObject(AuthorizationGroup clone) {
        super.cloneObject(clone);
        if ((this.childGroupList != null)) {
            clone.childGroupList = this.childGroupList.cloneCollection();
        }
        if ((this.userList != null)) {
            clone.userList = this.userList.cloneCollection();
        }
        if ((this.roleList != null)) {
            clone.roleList = this.roleList.cloneCollection();
        }
        if ((this.permissionList != null)) {
            clone.permissionList = this.permissionList.cloneCollection();
        }
    }

    /**
     * Getter for the ChildGroupListJPA.
     *
     * @return the List<AuthorizationGroup>.
     */
    List<AuthorizationGroup> getChildGroupListJPA() {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoListImpl<AuthorizationGroup>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationGroup>) this.childGroupList).getDelegate();
    }

    /**
     * Setter for the ChildGroupListJPA.
     *
     * @param childGroupList the List<AuthorizationGroup>.
     */
    void setChildGroupListJPA(List<AuthorizationGroup> childGroupList) {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoListImpl<AuthorizationGroup>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationGroup>) this.childGroupList).setDelegate(childGroupList);
    }

    /**
     * Getter for the UserListJPA.
     *
     * @return the List<AuthorizationGroupUserRelation>.
     */
    List<AuthorizationGroupUserRelation> getUserListJPA() {
        if ((this.userList == null)) {
            this.userList = new NabuccoListImpl<AuthorizationGroupUserRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationGroupUserRelation>) this.userList).getDelegate();
    }

    /**
     * Setter for the UserListJPA.
     *
     * @param userList the List<AuthorizationGroupUserRelation>.
     */
    void setUserListJPA(List<AuthorizationGroupUserRelation> userList) {
        if ((this.userList == null)) {
            this.userList = new NabuccoListImpl<AuthorizationGroupUserRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationGroupUserRelation>) this.userList).setDelegate(userList);
    }

    /**
     * Getter for the RoleListJPA.
     *
     * @return the List<AuthorizationGroupRoleRelation>.
     */
    List<AuthorizationGroupRoleRelation> getRoleListJPA() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationGroupRoleRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationGroupRoleRelation>) this.roleList).getDelegate();
    }

    /**
     * Setter for the RoleListJPA.
     *
     * @param roleList the List<AuthorizationGroupRoleRelation>.
     */
    void setRoleListJPA(List<AuthorizationGroupRoleRelation> roleList) {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationGroupRoleRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationGroupRoleRelation>) this.roleList).setDelegate(roleList);
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationGroupPermissionRelation>.
     */
    List<AuthorizationGroupPermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationGroupPermissionRelation>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<AuthorizationGroupPermissionRelation>) this.permissionList).getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationGroupPermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationGroupPermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationGroupPermissionRelation>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<AuthorizationGroupPermissionRelation>) this.permissionList).setDelegate(permissionList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(Group.class).getPropertyMap());
        propertyMap.put(CHILDGROUPLIST, PropertyDescriptorSupport.createCollection(CHILDGROUPLIST,
                AuthorizationGroup.class, 8, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(USERLIST, PropertyDescriptorSupport.createCollection(USERLIST,
                AuthorizationGroupUserRelation.class, 9, PROPERTY_CONSTRAINTS[1], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(ROLELIST, PropertyDescriptorSupport.createCollection(ROLELIST,
                AuthorizationGroupRoleRelation.class, 10, PROPERTY_CONSTRAINTS[2], false,
                PropertyAssociationType.COMPOSITION));
        propertyMap.put(PERMISSIONLIST, PropertyDescriptorSupport.createCollection(PERMISSIONLIST,
                AuthorizationGroupPermissionRelation.class, 11, PROPERTY_CONSTRAINTS[3], false,
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
        properties.add(super.createProperty(AuthorizationGroup.getPropertyDescriptor(CHILDGROUPLIST),
                this.childGroupList, null));
        properties.add(super.createProperty(AuthorizationGroup.getPropertyDescriptor(USERLIST), this.userList, null));
        properties.add(super.createProperty(AuthorizationGroup.getPropertyDescriptor(ROLELIST), this.roleList, null));
        properties.add(super.createProperty(AuthorizationGroup.getPropertyDescriptor(PERMISSIONLIST),
                this.permissionList, null));
        properties.add(super.createProperty(AuthorizationGroup.getPropertyDescriptor(GROUPTYPE), this.getGroupType(),
                this.groupTypeRefId));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(CHILDGROUPLIST) && (property.getType() == AuthorizationGroup.class))) {
            this.childGroupList = ((NabuccoList<AuthorizationGroup>) property.getInstance());
            return true;
        } else if ((property.getName().equals(USERLIST) && (property.getType() == AuthorizationGroupUserRelation.class))) {
            this.userList = ((NabuccoList<AuthorizationGroupUserRelation>) property.getInstance());
            return true;
        } else if ((property.getName().equals(ROLELIST) && (property.getType() == AuthorizationGroupRoleRelation.class))) {
            this.roleList = ((NabuccoList<AuthorizationGroupRoleRelation>) property.getInstance());
            return true;
        } else if ((property.getName().equals(PERMISSIONLIST) && (property.getType() == AuthorizationGroupPermissionRelation.class))) {
            this.permissionList = ((NabuccoList<AuthorizationGroupPermissionRelation>) property.getInstance());
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
        final AuthorizationGroup other = ((AuthorizationGroup) obj);
        if ((this.groupTypeRefId == null)) {
            if ((other.groupTypeRefId != null))
                return false;
        } else if ((!this.groupTypeRefId.equals(other.groupTypeRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.groupTypeRefId == null) ? 0 : this.groupTypeRefId.hashCode()));
        return result;
    }

    @Override
    public AuthorizationGroup cloneObject() {
        AuthorizationGroup clone = new AuthorizationGroup();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Missing description at method getChildGroupList.
     *
     * @return the NabuccoList<AuthorizationGroup>.
     */
    public NabuccoList<AuthorizationGroup> getChildGroupList() {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoListImpl<AuthorizationGroup>(NabuccoCollectionState.INITIALIZED);
        }
        return this.childGroupList;
    }

    /**
     * Missing description at method getUserList.
     *
     * @return the NabuccoList<AuthorizationGroupUserRelation>.
     */
    public NabuccoList<AuthorizationGroupUserRelation> getUserList() {
        if ((this.userList == null)) {
            this.userList = new NabuccoListImpl<AuthorizationGroupUserRelation>(NabuccoCollectionState.INITIALIZED);
        }
        return this.userList;
    }

    /**
     * Missing description at method getRoleList.
     *
     * @return the NabuccoList<AuthorizationGroupRoleRelation>.
     */
    public NabuccoList<AuthorizationGroupRoleRelation> getRoleList() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoListImpl<AuthorizationGroupRoleRelation>(NabuccoCollectionState.INITIALIZED);
        }
        return this.roleList;
    }

    /**
     * Missing description at method getPermissionList.
     *
     * @return the NabuccoList<AuthorizationGroupPermissionRelation>.
     */
    public NabuccoList<AuthorizationGroupPermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoListImpl<AuthorizationGroupPermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }

    /**
     * Getter for the GroupTypeRefId.
     *
     * @return the Long.
     */
    public Long getGroupTypeRefId() {
        return this.groupTypeRefId;
    }

    /**
     * Setter for the GroupTypeRefId.
     *
     * @param groupTypeRefId the Long.
     */
    public void setGroupTypeRefId(Long groupTypeRefId) {
        this.groupTypeRefId = groupTypeRefId;
    }

    /**
     * Setter for the GroupType.
     *
     * @param groupType the Code.
     */
    public void setGroupType(Code groupType) {
        super.setGroupType(groupType);
        if ((groupType != null)) {
            this.setGroupTypeRefId(groupType.getId());
        } else {
            this.setGroupTypeRefId(null);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationGroup.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationGroup.class).getAllProperties();
    }
}
