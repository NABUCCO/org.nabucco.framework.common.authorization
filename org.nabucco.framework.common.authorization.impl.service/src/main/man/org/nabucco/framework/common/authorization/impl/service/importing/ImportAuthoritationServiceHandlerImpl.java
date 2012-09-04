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
package org.nabucco.framework.common.authorization.impl.service.importing;

import java.util.List;

import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.importing.ImportContextEntry;
import org.nabucco.framework.base.facade.datatype.serialization.DeserializationData;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationException;
import org.nabucco.framework.base.facade.datatype.serialization.xml.XmlSerializer;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.importing.ImportRq;
import org.nabucco.framework.base.facade.message.importing.ImportRs;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroupUserRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRolePermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserPermissionRelation;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUserRoleRelation;

/**
 * ImportAuthoritationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ImportAuthoritationServiceHandlerImpl extends ImportAuthoritationServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ImportRs importAuthoritation(ImportRq msg) throws ImportException {

        ImportRs result = new ImportRs();
        ImportContext importContext = msg.getImportContext();
        ImportContainer importContainer = msg.getContainer();
        result.setImportContext(importContext);

        try {
            XmlSerializer xmlSerializer = new XmlSerializer();

            DeserializationData data = new DeserializationData(importContainer.getContent().getValue());
            data.setResourceData(importContainer.getResourceData().getValue());
            List<Datatype> deserialized = xmlSerializer.deserialize(data);

            for (Datatype current : deserialized) {
                if (current instanceof NabuccoDatatype) {
                    this.importElement((NabuccoDatatype) current, importContext);
                }
            }

        } catch (SerializationException e) {
            throw new ImportException("Unable to deserialize xml content.", e);
        }

        return result;
    }

    private void importElement(NabuccoDatatype nd, ImportContext importContext) throws ImportException {
        if (nd instanceof AuthorizationGroup) {
            importElement(importContext, (AuthorizationGroup) nd);
        } else if (nd instanceof AuthorizationRole) {
            importElement(importContext, (AuthorizationRole) nd);
        } else if (nd instanceof AuthorizationUser) {
            importElement(importContext, (AuthorizationUser) nd);
        } else if (nd instanceof AuthorizationPermission) {
            importElement(importContext, (AuthorizationPermission) nd);
        } else if (nd instanceof AuthorizationUserRoleRelation) {
            importElement(importContext, (AuthorizationUserRoleRelation) nd);
        } else if (nd instanceof AuthorizationGroupPermissionRelation) {
            importElement(importContext, (AuthorizationGroupPermissionRelation) nd);
        } else if (nd instanceof AuthorizationRolePermissionRelation) {
            importElement(importContext, (AuthorizationRolePermissionRelation) nd);
        } else if (nd instanceof AuthorizationGroupUserRelation) {
            importElement(importContext, (AuthorizationGroupUserRelation) nd);
        } else if (nd instanceof AuthorizationUserPermissionRelation) {
            importElement(importContext, (AuthorizationUserPermissionRelation) nd);
        }
    }

    private ImportContextEntry createEntry(NabuccoDatatype nd) {
        ImportContextEntry entry = new ImportContextEntry();
        entry.setOldId(nd.getId());
        entry.setTypeName(nd.getClass().getName());

        nd.setDatatypeState(DatatypeState.INITIALIZED);
        nd.setId((Long) null);
        return entry;
    }

    private void importElement(ImportContext context, AuthorizationGroup group) throws ImportException {
        ImportContextEntry createdEntry = createEntry(group);
        try {
            AuthorizationGroup persistent = super.getPersistenceManager().persist(group);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationGroup.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationPermission permission) throws ImportException {
        ImportContextEntry createdEntry = createEntry(permission);
        try {
            AuthorizationPermission persistent = super.getPersistenceManager().persist(permission);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationPermission.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationRole role) throws ImportException {
        ImportContextEntry createdEntry = createEntry(role);
        try {
            AuthorizationRole persistent = super.getPersistenceManager().persist(role);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationRole.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationUser user) throws ImportException {
        ImportContextEntry createdEntry = createEntry(user);
        try {
            AuthorizationUser persistent = super.getPersistenceManager().persist(user);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationUser.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationUserRoleRelation relation) throws ImportException {
        ImportContextEntry createdEntry = createEntry(relation);
        try {
            AuthorizationUserRoleRelation persistent = super.getPersistenceManager().persist(relation);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationUserRoleRelation.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationGroupPermissionRelation relation)
            throws ImportException {
        ImportContextEntry createdEntry = createEntry(relation);
        try {
            AuthorizationGroupPermissionRelation persistent = super.getPersistenceManager().persist(relation);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationGroupPermissionRelation.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationRolePermissionRelation relation)
            throws ImportException {
        ImportContextEntry createdEntry = createEntry(relation);
        try {
            AuthorizationRolePermissionRelation persistent = super.getPersistenceManager().persist(relation);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationRolePermissionRelation.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationGroupUserRelation relation) throws ImportException {
        ImportContextEntry createdEntry = createEntry(relation);
        try {
            AuthorizationGroupUserRelation persistent = super.getPersistenceManager().persist(relation);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationGroupUserRelation.", e);
        }
        context.getEntries().add(createdEntry);
    }

    private void importElement(ImportContext context, AuthorizationUserPermissionRelation relation)
            throws ImportException {
        ImportContextEntry createdEntry = createEntry(relation);
        try {
            AuthorizationUserPermissionRelation persistent = super.getPersistenceManager().persist(relation);
            createdEntry.setNewId(persistent.getId());
        } catch (PersistenceException e) {
            throw new ImportException("Unable to import AuthorizationUserPermissionRelation.", e);
        }
        context.getEntries().add(createdEntry);
    }

}
