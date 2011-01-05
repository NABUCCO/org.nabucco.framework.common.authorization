/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.facade.datatype;

import java.util.List;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.property.ListProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
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

    private static final String[] PROPERTY_NAMES = { "childGroupList", "userList", "roleList",
            "permissionList" };

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,n;", "m0,n;", "m0,n;", "m0,n;" };

    private List<AuthorizationGroup> childGroupList;

    private List<AuthorizationGroupUserRelation> userList;

    private List<AuthorizationGroupRoleRelation> roleList;

    private List<AuthorizationGroupPermissionRelation> permissionList;

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
        if ((this.childGroupList instanceof NabuccoList<?>)) {
            clone.childGroupList = ((NabuccoList<AuthorizationGroup>) this.childGroupList)
                    .cloneCollection();
        }
        if ((this.userList instanceof NabuccoList<?>)) {
            clone.userList = ((NabuccoList<AuthorizationGroupUserRelation>) this.userList)
                    .cloneCollection();
        }
        if ((this.roleList instanceof NabuccoList<?>)) {
            clone.roleList = ((NabuccoList<AuthorizationGroupRoleRelation>) this.roleList)
                    .cloneCollection();
        }
        if ((this.permissionList instanceof NabuccoList<?>)) {
            clone.permissionList = ((NabuccoList<AuthorizationGroupPermissionRelation>) this.permissionList)
                    .cloneCollection();
        }
    }

    /**
     * Getter for the ChildGroupListJPA.
     *
     * @return the List<AuthorizationGroup>.
     */
    List<AuthorizationGroup> getChildGroupListJPA() {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoList<AuthorizationGroup>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationGroup>) this.childGroupList).getDelegate();
    }

    /**
     * Setter for the ChildGroupListJPA.
     *
     * @param childGroupList the List<AuthorizationGroup>.
     */
    void setChildGroupListJPA(List<AuthorizationGroup> childGroupList) {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoList<AuthorizationGroup>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationGroup>) this.childGroupList).setDelegate(childGroupList);
    }

    /**
     * Getter for the UserListJPA.
     *
     * @return the List<AuthorizationGroupUserRelation>.
     */
    List<AuthorizationGroupUserRelation> getUserListJPA() {
        if ((this.userList == null)) {
            this.userList = new NabuccoList<AuthorizationGroupUserRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationGroupUserRelation>) this.userList).getDelegate();
    }

    /**
     * Setter for the UserListJPA.
     *
     * @param userList the List<AuthorizationGroupUserRelation>.
     */
    void setUserListJPA(List<AuthorizationGroupUserRelation> userList) {
        if ((this.userList == null)) {
            this.userList = new NabuccoList<AuthorizationGroupUserRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationGroupUserRelation>) this.userList).setDelegate(userList);
    }

    /**
     * Getter for the RoleListJPA.
     *
     * @return the List<AuthorizationGroupRoleRelation>.
     */
    List<AuthorizationGroupRoleRelation> getRoleListJPA() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationGroupRoleRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationGroupRoleRelation>) this.roleList).getDelegate();
    }

    /**
     * Setter for the RoleListJPA.
     *
     * @param roleList the List<AuthorizationGroupRoleRelation>.
     */
    void setRoleListJPA(List<AuthorizationGroupRoleRelation> roleList) {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationGroupRoleRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationGroupRoleRelation>) this.roleList).setDelegate(roleList);
    }

    /**
     * Getter for the PermissionListJPA.
     *
     * @return the List<AuthorizationGroupPermissionRelation>.
     */
    List<AuthorizationGroupPermissionRelation> getPermissionListJPA() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationGroupPermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        return ((NabuccoList<AuthorizationGroupPermissionRelation>) this.permissionList)
                .getDelegate();
    }

    /**
     * Setter for the PermissionListJPA.
     *
     * @param permissionList the List<AuthorizationGroupPermissionRelation>.
     */
    void setPermissionListJPA(List<AuthorizationGroupPermissionRelation> permissionList) {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationGroupPermissionRelation>(
                    NabuccoCollectionState.LAZY);
        }
        ((NabuccoList<AuthorizationGroupPermissionRelation>) this.permissionList)
                .setDelegate(permissionList);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public List<NabuccoProperty<?>> getProperties() {
        List<NabuccoProperty<?>> properties = super.getProperties();
        properties.add(new ListProperty<AuthorizationGroup>(PROPERTY_NAMES[0],
                AuthorizationGroup.class, PROPERTY_CONSTRAINTS[0], this.childGroupList));
        properties.add(new ListProperty<AuthorizationGroupUserRelation>(PROPERTY_NAMES[1],
                AuthorizationGroupUserRelation.class, PROPERTY_CONSTRAINTS[1], this.userList));
        properties.add(new ListProperty<AuthorizationGroupRoleRelation>(PROPERTY_NAMES[2],
                AuthorizationGroupRoleRelation.class, PROPERTY_CONSTRAINTS[2], this.roleList));
        properties.add(new ListProperty<AuthorizationGroupPermissionRelation>(PROPERTY_NAMES[3],
                AuthorizationGroupPermissionRelation.class, PROPERTY_CONSTRAINTS[3],
                this.permissionList));
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder appendable = new StringBuilder();
        appendable.append(super.toString());
        return appendable.toString();
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
     * @return the List<AuthorizationGroup>.
     */
    public List<AuthorizationGroup> getChildGroupList() {
        if ((this.childGroupList == null)) {
            this.childGroupList = new NabuccoList<AuthorizationGroup>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.childGroupList;
    }

    /**
     * Missing description at method getUserList.
     *
     * @return the List<AuthorizationGroupUserRelation>.
     */
    public List<AuthorizationGroupUserRelation> getUserList() {
        if ((this.userList == null)) {
            this.userList = new NabuccoList<AuthorizationGroupUserRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.userList;
    }

    /**
     * Missing description at method getRoleList.
     *
     * @return the List<AuthorizationGroupRoleRelation>.
     */
    public List<AuthorizationGroupRoleRelation> getRoleList() {
        if ((this.roleList == null)) {
            this.roleList = new NabuccoList<AuthorizationGroupRoleRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.roleList;
    }

    /**
     * Missing description at method getPermissionList.
     *
     * @return the List<AuthorizationGroupPermissionRelation>.
     */
    public List<AuthorizationGroupPermissionRelation> getPermissionList() {
        if ((this.permissionList == null)) {
            this.permissionList = new NabuccoList<AuthorizationGroupPermissionRelation>(
                    NabuccoCollectionState.INITIALIZED);
        }
        return this.permissionList;
    }
}
