/*
 * Copyright 2010 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco-source.org/nabucco-license.html
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
import org.nabucco.framework.base.facade.datatype.security.Subject;
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

    private List<ConnectionSpecification> connectionSpecifications;

    private ConnectionSpecification selectedConnectionSpecification;

    private Subject subject;

    public static final String PROPERTY_USER_NAME = "userName";

    public static final String PROPERTY_PASSWORD = "password";

    public static final String PROPERTY_USER_ID = "userId";

    public static final String PROPERTY_CONNECTION_SPECIFICATION = "connectionSpecifications";

    public static final String PROPERTY_SELECTED_CONNECTION_SPECIFICATION = "selectedConnectionSpecification";

    /**
     * Getter for the username string.
     * 
     * @return the username
     */
    public String getUserName() {
        return userName;
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
        return password;
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

    public List<ConnectionSpecification> getConnectionSpecifications() {
        return connectionSpecifications;
    }

    public void setConnectionSpecifications(List<ConnectionSpecification> connectionSpecifications) {
        this.connectionSpecifications = connectionSpecifications;
    }

    public ConnectionSpecification getSelectedConnectionSpecification() {
        return selectedConnectionSpecification;
    }

    public void setSelectedConnectionSpecification(
            final ConnectionSpecification selectedConnectionSpecification) {
        Activator.getDefault().setCurrentConnectionSpecification(selectedConnectionSpecification);
        updateProperty(PROPERTY_SELECTED_CONNECTION_SPECIFICATION,
                this.selectedConnectionSpecification,
                this.selectedConnectionSpecification = selectedConnectionSpecification);
    }

    public Subject getAuthenticatedSubject() {
        return this.subject;
    }

    public void setAuthenticatedSubject(Subject subject) {
        Subject oldSubject = this.subject;
        updateProperty(PROPERTY_USER_NAME, oldSubject, subject);
        this.subject = subject;
    }
}
