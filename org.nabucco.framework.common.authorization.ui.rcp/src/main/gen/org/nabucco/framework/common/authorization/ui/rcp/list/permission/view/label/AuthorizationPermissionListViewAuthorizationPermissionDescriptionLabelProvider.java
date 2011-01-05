/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationPermission;

/**
 * AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider
 *
 * @author Undefined
 */
public class AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider
        implements ILabelProvider {

    /** Constructs a new AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider instance. */
    public AuthorizationPermissionListViewAuthorizationPermissionDescriptionLabelProvider() {
        super();
    }

    @Override
    public Image getImage(Object arg0) {
        return null;
    }

    @Override
    public String getText(Object arg0) {
        String result = "";
        if ((arg0 instanceof AuthorizationPermission)) {
            AuthorizationPermission permission = ((AuthorizationPermission) arg0);
            result = ((permission.getDescription() != null) ? permission.getDescription()
                    .toString() : "");
        }
        return result;
    }

    @Override
    public void addListener(ILabelProviderListener arg0) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object arg0, String arg1) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener arg0) {
    }
}
