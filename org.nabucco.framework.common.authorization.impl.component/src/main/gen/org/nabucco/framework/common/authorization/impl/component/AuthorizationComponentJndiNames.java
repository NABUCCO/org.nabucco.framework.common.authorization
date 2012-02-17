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
package org.nabucco.framework.common.authorization.impl.component;

/**
 * AuthorizationComponentJndiNames<p/>Authorization Component<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-18
 */
public interface AuthorizationComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.component.QueryFilterService/remote";

    final String LOGIN_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.login.Login/local";

    final String LOGIN_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.login.Login/remote";

    final String AUTHORIZATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.AuthorizationService/local";

    final String AUTHORIZATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.AuthorizationService/remote";

    final String MAINTAIN_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization/local";

    final String MAINTAIN_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.maintain.MaintainAuthorization/remote";

    final String SEARCH_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization/local";

    final String SEARCH_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.search.SearchAuthorization/remote";

    final String PRODUCE_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization/local";

    final String PRODUCE_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.produce.ProduceAuthorization/remote";

    final String RESOLVE_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization/local";

    final String RESOLVE_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.resolve.ResolveAuthorization/remote";

    final String EXPORT_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization/local";

    final String EXPORT_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.export.ExportAuthorization/remote";

    final String IMPORT_AUTHORIZATION_LOCAL = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization/local";

    final String IMPORT_AUTHORIZATION_REMOTE = "nabucco/org.nabucco.framework.common.authorization/org.nabucco.framework.common.authorization.facade.service.importing.ImportAuthorization/remote";
}
