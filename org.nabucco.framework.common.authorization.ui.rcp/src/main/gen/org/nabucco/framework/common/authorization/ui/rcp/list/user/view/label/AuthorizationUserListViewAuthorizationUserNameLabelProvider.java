/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.user.view.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationUser;

/**
 * AuthorizationUserListViewAuthorizationUserNameLabelProvider
 *
 * @author Undefined
 */
public class AuthorizationUserListViewAuthorizationUserNameLabelProvider implements ILabelProvider {

    /** Constructs a new AuthorizationUserListViewAuthorizationUserNameLabelProvider instance. */
    public AuthorizationUserListViewAuthorizationUserNameLabelProvider() {
        super();
    }

    @Override
    public Image getImage(Object arg0) {
        return null;
    }

    @Override
    public String getText(Object arg0) {
        String result = "";
        if ((arg0 instanceof AuthorizationUser)) {
            AuthorizationUser user = ((AuthorizationUser) arg0);
            result = ((user.getUsername() != null) ? user.getUsername().toString() : "");
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
