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
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.security.Permission;

/**
 * AuthorizationPermission<p/>Represents a permission within the authorization component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public class AuthorizationPermission extends Permission implements Datatype {

    private static final long serialVersionUID = 1L;

    private Long permissionTypeRefId;

    /** Constructs a new AuthorizationPermission instance. */
    public AuthorizationPermission() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the AuthorizationPermission.
     */
    protected void cloneObject(AuthorizationPermission clone) {
        super.cloneObject(clone);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(Permission.class).getPropertyMap());
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(AuthorizationPermission.getPropertyDescriptor(PERMISSIONTYPE),
                this.getPermissionType(), this.permissionTypeRefId));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
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
        final AuthorizationPermission other = ((AuthorizationPermission) obj);
        if ((this.permissionTypeRefId == null)) {
            if ((other.permissionTypeRefId != null))
                return false;
        } else if ((!this.permissionTypeRefId.equals(other.permissionTypeRefId)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.permissionTypeRefId == null) ? 0 : this.permissionTypeRefId.hashCode()));
        return result;
    }

    @Override
    public AuthorizationPermission cloneObject() {
        AuthorizationPermission clone = new AuthorizationPermission();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Getter for the PermissionTypeRefId.
     *
     * @return the Long.
     */
    public Long getPermissionTypeRefId() {
        return this.permissionTypeRefId;
    }

    /**
     * Setter for the PermissionTypeRefId.
     *
     * @param permissionTypeRefId the Long.
     */
    public void setPermissionTypeRefId(Long permissionTypeRefId) {
        this.permissionTypeRefId = permissionTypeRefId;
    }

    /**
     * Setter for the PermissionType.
     *
     * @param permissionType the Code.
     */
    public void setPermissionType(Code permissionType) {
        super.setPermissionType(permissionType);
        if ((permissionType != null)) {
            this.setPermissionTypeRefId(permissionType.getId());
        } else {
            this.setPermissionTypeRefId(null);
        }
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(AuthorizationPermission.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(AuthorizationPermission.class).getAllProperties();
    }
}
