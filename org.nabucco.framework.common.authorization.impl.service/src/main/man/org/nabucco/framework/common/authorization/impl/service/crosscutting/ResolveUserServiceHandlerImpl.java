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
package org.nabucco.framework.common.authorization.impl.service.crosscutting;

import org.nabucco.framework.base.facade.datatype.Identifier;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.security.UserId;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.authorization.UserRq;
import org.nabucco.framework.base.facade.message.authorization.UserRs;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * ResolveUserServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveUserServiceHandlerImpl extends ResolveUserServiceHandler {

    private static final String QUERY = "select u from AuthorizationUser u where u.username = :username";

    private static final long serialVersionUID = 1L;

    @Override
    protected UserRs resolveUser(UserRq rq) throws SearchException {

        UserId userId = rq.getUserId();
        Identifier id = rq.getId();

        if ((userId == null || userId.getValue() == null)) {

            if ((id == null || id.getValue() == null)) {
                throw new SearchException("Cannot resolve user for id [null].");
            }

            try {
                AuthorizationUser user = super.getPersistenceManager().find(AuthorizationUser.class, id.getValue());

                UserRs rs = new UserRs();
                rs.setUser(user);

                return rs;

            } catch (PersistenceException pe) {
                throw new SearchException("Cannot resolve User with id [" + id + "].", pe);
            }
        }

        try {
            NabuccoQuery<AuthorizationUser> query = this.getPersistenceManager().createQuery(QUERY);
            query.setParameter("username", new Name(userId.getValue()));

            AuthorizationUser user = query.getSingleResult();

            UserRs rs = new UserRs();
            rs.setUser(user);

            return rs;
        } catch (PersistenceException pe) {
            throw new SearchException("Cannot resolve User with user-id [" + userId + "].", pe);
        }

    }

}
