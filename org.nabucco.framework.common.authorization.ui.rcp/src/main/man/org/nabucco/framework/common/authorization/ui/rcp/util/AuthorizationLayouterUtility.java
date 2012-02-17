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
package org.nabucco.framework.common.authorization.ui.rcp.util;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * AuthorizationLayouterUtility
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class AuthorizationLayouterUtility {

    /**
     * Private constructor
     */
    private AuthorizationLayouterUtility() {
    }

    public static void layoutDefault(Text text) {
        GridData data = new GridData();
        data.widthHint = 180;
        text.setLayoutData(data);
    }

    public static void layoutDefault(Label label) {
        GridData data = new GridData();
        data.widthHint = 100;
        label.setLayoutData(data);
    }

    public static void layoutDefault(Combo combo) {
        GridData data = new GridData();
        data.widthHint = 165;
        combo.setLayoutData(data);
    }
}
