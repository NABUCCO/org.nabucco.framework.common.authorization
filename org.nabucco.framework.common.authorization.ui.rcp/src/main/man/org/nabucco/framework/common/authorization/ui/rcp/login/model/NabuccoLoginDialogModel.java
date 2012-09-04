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
package org.nabucco.framework.common.authorization.ui.rcp.login.model;

import java.util.List;

import org.nabucco.framework.base.facade.component.connection.ConnectionSpecification;
import org.nabucco.framework.base.facade.datatype.session.authorization.SecurityContext;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.ViewModel;

/**
 * NabuccoLoginDialogModel
 * 
 * @author Michael Krausse, PRODYNA AG
 */
public class NabuccoLoginDialogModel extends ViewModel {

    private String userName = "";

    private String password = "";

    private String tenant = "";

    private SecurityContext securityContext;

    private List<ConnectionSpecification> connections;

    private ConnectionSpecification selectedConnection;

    public static final String PROPERTY_USER_NAME = "userName";

    public static final String PROPERTY_PASSWORD = "password";

    public static final String PROPERTY_TENANT = "tenant";

    public static final String PROPERTY_USER_ID = "userId";

    public static final String PROPERTY_CONNECTION_SPECIFICATION = "connections";

    public static final String PROPERTY_SELECTED_CONNECTION = "selectedConnection";

    /**
     * Getter for the username string.
     * 
     * @return the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Setter for the username string.
     * 
     * @param password
     *            the username
     */
    public void setUserName(String userName) {
        updateProperty(PROPERTY_USER_NAME, this.userName, this.userName = userName);
    }

    /**
     * Getter for the password string.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for the password string.
     * 
     * @param password
     *            the password
     */
    public void setPassword(String password) {
        updateProperty(PROPERTY_PASSWORD, this.password, this.password = password);
    }

    /**
     * Getter for the tenant.
     * 
     * @return Returns the tenant.
     */
    public String getTenant() {
        return this.tenant;
    }

    /**
     * Setter for the tenant.
     * 
     * @param tenant
     *            The tenant to set.
     */
    public void setTenant(String tenant) {
        updateProperty(PROPERTY_TENANT, this.tenant, this.tenant = tenant);
    }

    /**
     * Getter for the list of connections.
     * 
     * @return the list of connections
     */
    public List<ConnectionSpecification> getConnections() {
        return this.connections;
    }

    /**
     * Setter for the list of connections.
     * 
     * @return the list of connections
     */
    public void setConnections(List<ConnectionSpecification> connectionSpecifications) {
        this.connections = connectionSpecifications;
    }

    /**
     * Getter for the selected connection.
     * 
     * @return the selected connection specification
     */
    public ConnectionSpecification getSelectedConnection() {
        return this.selectedConnection;
    }

    /**
     * Setter for the selected connection.
     * 
     * @param newValue
     *            the selected connection specification
     */
    public void setSelectedConnection(final ConnectionSpecification newValue) {
        ConnectionSpecification oldValue = this.selectedConnection;
        super.updateProperty(PROPERTY_SELECTED_CONNECTION, oldValue, newValue);

        this.selectedConnection = newValue;
        Activator.getDefault().setCurrentConnectionSpecification(newValue);
    }

    /**
     * Getter for the securityContext.
     * 
     * @return Returns the securityContext.
     */
    public SecurityContext getSecurityContext() {
        return this.securityContext;
    }

    /**
     * Setter for the securityContext.
     * 
     * @param securityContext
     *            The securityContext to set.
     */
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

}
