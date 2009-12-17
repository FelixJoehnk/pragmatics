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
package de.cau.cs.kieler.core.ui.util;

/**
 * Interface for conditions that can be evaluated on a specific object.
 *
 * @author msp
 */
public interface ICondition {

    /**
     * Evaluate this condition on the given object.
     * 
     * @param object a target object
     * @return true if the condition is met for the object
     */
    boolean evaluate(Object object);
    
}
