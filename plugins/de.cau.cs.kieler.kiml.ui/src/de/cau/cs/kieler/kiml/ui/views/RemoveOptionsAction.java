/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that removes all layout options from the current diagram.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class RemoveOptionsAction extends Action {
    
    /** the layout view that created this action. */
    private LayoutViewPart layoutView;

    /**
     * Creates an apply option action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     */
    public RemoveOptionsAction(final LayoutViewPart thelayoutView, final String text) {
        super(text);
        this.layoutView = thelayoutView;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        IWorkbenchPart workbenchPart = layoutView.getCurrentEditor();
        DiagramLayoutManager manager = layoutView.getCurrentManager();
        if (manager != null) {
            EditPart diagram = manager.getBridge().getEditPart(workbenchPart);
            if (diagram != null) {
                // show a dialog to confirm the removal of all layout options
                String diagramName = workbenchPart.getTitle();
                boolean userResponse = MessageDialog.openQuestion(layoutView.getSite().getShell(),
                        Messages.getString("kiml.ui.31"), Messages.getString("kiml.ui.32")
                        + " " + diagramName + "?");
                if (userResponse) {
                    final ILayoutConfig config = manager.getLayoutConfig(diagram);
                    Runnable runnable = new Runnable() {
                        public void run() {
                            config.clearProperties();
                            LayoutViewPart.findView().refresh();
                        }
                    };
                    EditingDomain editingDomain = manager.getBridge().getEditingDomain(diagram);
                    KimlUiUtil.runModelChange(runnable,
                            (TransactionalEditingDomain) editingDomain,
                            Messages.getString("kiml.ui.30"));
                }
            }
        }
    }
    
}
