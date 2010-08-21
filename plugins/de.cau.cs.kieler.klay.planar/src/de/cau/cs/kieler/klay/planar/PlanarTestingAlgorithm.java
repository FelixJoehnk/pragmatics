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
package de.cau.cs.kieler.klay.planar;

/**
 * An enum for the possible planar testing algorithms used by the layouter.
 * 
 * @author ocl
 */
public enum PlanarTestingAlgorithm {

    /** The algorithm by Boyer and Myrvold. */
    BOYER_MYRVOLD_ALGORITHM,

    /** The algorithm based on the left-right-planarity test. */
    LEFT_RIGHT_PLANARITY_ALGORITHM;

}
