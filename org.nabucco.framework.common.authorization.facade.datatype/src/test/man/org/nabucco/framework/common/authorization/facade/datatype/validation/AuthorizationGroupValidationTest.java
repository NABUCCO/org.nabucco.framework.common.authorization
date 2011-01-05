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
package org.nabucco.framework.common.authorization.facade.datatype.validation;

import org.junit.Test;
import org.nabucco.framework.base.facade.datatype.validation.ValidationError;
import org.nabucco.framework.base.facade.datatype.validation.ValidationException;
import org.nabucco.framework.base.facade.datatype.validation.ValidationResult;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * AuthorizationGroupValidationTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationGroupValidationTest {

    @Test
    public void testAuthorization() throws ValidationException {

        AuthorizationGroup group = new AuthorizationGroup();
        group.setOwner("A");
        group.setGroupType("");
        group.setGroupname("AdminGroup");

        ValidationResult result = new ValidationResult();
        // group.validate(result);

        for (ValidationError error : result.getErrorList()) {
            System.out.println(error.getMessage());
        }
    }

}
