/*
 * NABUCCO Generator, Copyright (c) 2010, PRODYNA AG, Germany. All rights reserved.
 */
package org.nabucco.framework.common.authorization.ui.rcp.command.group;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingGroupListViewCommand<p/>@TODO<p/>
 *
 * @author Michael Krausse, PRODYNA AG, 2010-03-16
 */
public class OpenCorrespondingGroupListViewCommand implements NabuccoCommand {

    private OpenCorrespondingGroupListViewHandler openCorrespondingGroupListViewHandler = NabuccoInjector
            .getInstance(OpenCorrespondingGroupListViewCommand.class).inject(
                    OpenCorrespondingGroupListViewHandler.class);

    public static final String ID = "org.nabucco.framework.common.authorization.ui.command.group.OpenCorrespondingGroupListViewCommand";

    /** Constructs a new OpenCorrespondingGroupListViewCommand instance. */
    public OpenCorrespondingGroupListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openCorrespondingGroupListViewHandler.openCorrespondingGroupListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingGroupListViewCommand.ID;
    }
}
