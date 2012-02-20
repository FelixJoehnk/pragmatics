/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext;

import org.eclipse.xtext.resource.XtextResource;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.model.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState; // SUPPRESS CHECKSTYLE LineLength
import de.cau.cs.kieler.core.model.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState.EventType;  // SUPPRESS CHECKSTYLE LineLength
import de.cau.cs.kieler.klighd.effects.KlighdCloseDiagramEffect;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;

/**
 * A combination for initializing/refreshing of KLighD views of Xtext-based models.
 * Views initiated by this combination are shared with {@link VisualizeChosenElementCombination}
 * since they use the compute the (secondary) view ids the same way.
 * 
 * @author chsch
 */
public class UpdateXtextModelKLighDCombination extends AbstractCombination {

    /**
     * The execute method revealed and invoked by KIVi.
     * 
     * @param state
     *            A {@link de.cau.cs.kieler.core.kivi.ITriggerState} carrying the necessary
     *            information.
     */
    public void execute(final XtextModelChangeState state) {
        String id = state.getEditorInputPath().toPortableString().replace(":", "");
          // the replacement is needed since the secondary view ids seem to be required
          //  to be free of ':', which will be violated on windows determining them this way. 
        
// chsch: sucks!!        
//        // not final, because if a project has the same name like 
//        //       a folder in the path it fails
//        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//        IPath path = state.getEditorInputPath();
//        for (String segment : path.segments()) {
//            if (root.getProject(segment).exists()) {
//                id = id.substring(id.indexOf(segment));
//                break;
//            }
//        }
        if (state.getEventType().equals(EventType.CLOSED)) {
            this.schedule(new KlighdCloseDiagramEffect(id));
        } else {
            XtextResource resource = state.getResource();
            if (resource == null || resource.getContents() == null
                    || resource.getContents().isEmpty()) {
                return;
            }
            this.schedule(new KlighdDiagramEffect(id, state.getEditorInputPath().lastSegment(),
                    resource.getContents().get(0)));
        }
    }
}
