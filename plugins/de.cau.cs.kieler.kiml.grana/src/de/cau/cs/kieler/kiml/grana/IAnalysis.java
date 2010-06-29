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
package de.cau.cs.kieler.kiml.grana;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;


/**
 * The interface all graph analysis algorithms have to implement.
 * 
 * @author mri
 */
public interface IAnalysis {

    /**
     * Performs the actual analysis process and returns the results.
     * 
     * @param parentNode the parent node which the analysis is performed on
     * @param progressMonitor progress monitor used to keep track of progress
     * @throws KielerException if the method fails to perform the analysis
     * @return the analysis results
     */
    Object doAnalysis(KNode parentNode, IKielerProgressMonitor progressMonitor)
            throws KielerException;
    
}
