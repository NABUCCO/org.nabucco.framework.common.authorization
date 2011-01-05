/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.group.view.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.nabucco.framework.common.authorization.facade.datatype.AuthorizationGroup;

/**
 * AuthorizationGroupListViewAuthorizationGroupOwnerLabelProvider
 *
 * @author Undefined
 */
public class AuthorizationGroupListViewAuthorizationGroupOwnerLabelProvider implements
        ILabelProvider {

    /** Constructs a new AuthorizationGroupListViewAuthorizationGroupOwnerLabelProvider instance. */
    public AuthorizationGroupListViewAuthorizationGroupOwnerLabelProvider() {
        super();
    }

    @Override
    public Image getImage(Object arg0) {
        return null;
    }

    @Override
    public String getText(Object arg0) {
        String result = "";
        if ((arg0 instanceof AuthorizationGroup)) {
            AuthorizationGroup group = ((AuthorizationGroup) arg0);
            result = ((group.getOwner() != null) ? group.getOwner().toString() : "");
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
