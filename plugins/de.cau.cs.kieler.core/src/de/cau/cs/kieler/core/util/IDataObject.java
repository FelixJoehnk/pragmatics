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
package de.cau.cs.kieler.core.util;

import de.cau.cs.kieler.core.KielerException;

/**
 * An interface for data types, which should be serializable using {@link #toString()} and
 * parsable using {@link #parse(String)}. The default constructor should always be
 * accessible and create an instance with default content.
 *
 * @author msp
 */
public interface IDataObject {
    
    /**
     * Parse the given string and set the content of this data object.
     * 
     * @param string a string
     * @throws KielerException if the string does not have the expected format
     */
    void parse(String string) throws KielerException;

}
