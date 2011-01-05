/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.list.permission.view;

import java.io.Serializable;
import java.util.Map;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.common.authorization.ui.rcp.list.permission.model.AuthorizationPermissionListViewModel;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoComponentListView;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoCompositeTextFilter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;

/**
 * AuthorizationPermissionListView<p/>ListView for AuthorizationPermission<p/>
 *
 * @author Stefanie Feld, PRODYNA AG, 2010-03-15
 */
public class AuthorizationPermissionListView extends
        NabuccoComponentListView<AuthorizationPermissionListViewModel> {

    public static final String ID = "org.nabucco.framework.common.authorization.ui.list.permission.AuthorizationPermissionListView";

    public static final String TAB_TITLE = (ID + ".tabTitle");

    public static final String TITLE = (ID + ".title");

    /** Constructs a new AuthorizationPermissionListView instance. */
    public AuthorizationPermissionListView() {
        super();
        model = new AuthorizationPermissionListViewModel();
    }

    @Override
    protected void createFormControl(Form form) {
        Composite o = this.getLayouter().layout(form.getBody(), this.getMessageManager(), model,
                this);
        if ((o instanceof NabuccoTableViewer)) {
            tableViewer = ((NabuccoTableViewer) o);
        }
    }

    @Override
    protected NabuccoCompositeTextFilter createFilter(final Composite parent) {
        return new NabuccoCompositeTextFilter(parent);
    }

    @Override
    public void setFocus() {
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        return model.getValues();
    }

    @Override
    public String getNewPartName() {
        return I18N.i18n(TAB_TITLE, this.getValues());
    }

    @Override
    public String getManagedFormTitle() {
        return I18N.i18n(TITLE);
    }

    @Override
    public String getId() {
        return AuthorizationPermissionListView.ID;
    }
}
