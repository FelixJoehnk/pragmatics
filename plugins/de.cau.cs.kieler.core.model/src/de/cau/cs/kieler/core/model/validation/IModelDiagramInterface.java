/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.model.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorPart;

/**
 * The Interface IModelDiagramInterface.
 * 
 * @author soh
 * @kieler.ignore We'd like to get rid of this.
 */
public interface IModelDiagramInterface {

    /**
     * Gets the model of the editor part to check.
     * 
     * @param editorPart
     *            the editor part
     * @return the model
     */
    EObject getModel(IEditorPart editorPart);
    
}
