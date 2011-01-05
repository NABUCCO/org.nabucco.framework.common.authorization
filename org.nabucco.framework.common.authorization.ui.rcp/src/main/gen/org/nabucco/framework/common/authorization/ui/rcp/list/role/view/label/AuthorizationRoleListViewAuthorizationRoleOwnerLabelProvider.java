/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.role.view.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationRole;

/**
 * AuthorizationRoleListViewAuthorizationRoleOwnerLabelProvider
 *
 * @author Undefined
 */
public class AuthorizationRoleListViewAuthorizationRoleOwnerLabelProvider implements ILabelProvider {

    /** Constructs a new AuthorizationRoleListViewAuthorizationRoleOwnerLabelProvider instance. */
    public AuthorizationRoleListViewAuthorizationRoleOwnerLabelProvider() {
        super();
    }

    @Override
    public Image getImage(Object arg0) {
        return null;
    }

    @Override
    public String getText(Object arg0) {
        String result = "";
        if ((arg0 instanceof AuthorizationRole)) {
            AuthorizationRole role = ((AuthorizationRole) arg0);
            result = ((role.getOwner() != null) ? role.getOwner().toString() : "");
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
