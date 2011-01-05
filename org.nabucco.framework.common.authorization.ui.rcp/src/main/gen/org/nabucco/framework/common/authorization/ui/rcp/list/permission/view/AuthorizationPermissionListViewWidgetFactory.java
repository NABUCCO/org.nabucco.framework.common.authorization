/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view;

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.model.ListViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * AuthorizationPermissionListViewWidgetFactory<p/>ListView for AuthorizationPermission<p/>
 *
 * @author Stefanie Feld, PRODYNA AG, 2010-03-15
 */
public class AuthorizationPermissionListViewWidgetFactory extends WidgetFactory {

    /**
     * Constructs a new AuthorizationPermissionListViewWidgetFactory instance.
     *
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public AuthorizationPermissionListViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit) {
        super(nabuccoFormToolKit);
    }

    /**
     * CreateTable.
     *
     * @param model the ListViewModel<?>.
     * @param parameter the NabuccoTableParameter.
     * @param parent the Composite.
     * @return the NabuccoTableViewer.
     */
    public NabuccoTableViewer createTable(Composite parent, NabuccoTableParameter parameter,
            ListViewModel<?> model) {
        return nabuccoFormToolKit.createNabuccoTable(parent, parameter, model);
    }
}
