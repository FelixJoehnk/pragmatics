/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.ptolemy.klighd.transformation;

/**
 * Contains constants used during the transformations. This class is not to be instantiated.
 * 
 * <p><em>Note:</em> FindBugs has problems with the arrays defined herein. However, we don't care
 * too much about that.</p>
 * 
 * @author cds
 */
final class TransformationConstants {
    
    // PORT NAMES
    
    /**
     * Possible names for input ports. Used to infer port types during the transformation.
     */
    public static final String[] PORT_NAMES_INPUT = {"in", "input", "incomingPort"};
    
    /**
     * Possible names for output ports. Used to infer port types during the transformation.
     */
    public static final String[] PORT_NAMES_OUTPUT = {"out", "output"};
    
    /**
     * Regular expression for the separator character used in port names.
     */
    public static final String PORT_NAME_SEPARATOR_REGEX = "\\.";
    
    
    // PORT TYPES
    
    /**
     * Name for an annotation that marks a port as being a multiport.
     */
    public static final String IS_MULTIPORT = "_multiport";
    
    /**
     * Name of an annotation that marks a port as being a ParameterPort instance.
     */
    public static final String IS_PARAMETER_PORT = "_parameterPort";
    
    /**
     * Name of an annotation that marks a port as being an IOPort instance.
     */
    public static final String IS_IO_PORT = "_ioPort";
    
    
    // ANNOTATION TYPES
    
    /**
     * Trype of attributes.
     */
    public static final String TYPE_ATTRIBUTE = "ptolemy.kernel.util.Attribute";
    
    /**
     * Type of annotations that describe a comment.
     */
    public static final String TYPE_TEXT_ATTRIBUTE = "ptolemy.vergil.kernel.attributes.TextAttribute";
    
    /**
     * Type of annotations that hold the text of comments.
     */
    public static final String TYPE_STRING_ATTRIBUTE = "ptolemy.kernel.util.StringAttribute";
    
    /**
     * Type of annotations that define parameters of models.
     */
    public static final String TYPE_PARAMETER = "ptolemy.data.expr.Parameter";
    
    
    // ANNOTATION CONSTANTS
    
    /**
     * Name for an annotation describing where a model element originally came from if it was
     * transformed from another model.
     */
    public static final String ANNOTATION_LANGUAGE = "_language";
    
    /**
     * Value of the {@link #ANNOTATION_LANGUAGE} annotation identifying elements transformed from a
     * Ptolemy model.
     */
    public static final String ANNOTATION_LANGUAGE_PTOLEMY = "ptolemy";
    
    /**
     * Name for an annotation describing the original class name of an element imported from a
     * Ptolemy model.
     */
    public static final String ANNOTATION_PTOLEMY_CLASS = "_ptolemyClass";
    
    /**
     * Name of the annotation that specifies the name of the element a comment is explicitly attached to.
     */
    public static final String ANNOTATION_RELATIVE_TO ="relativeTo";
    
    /**
     * Name of the annotation that specifies the type of the element a comment is explicitly attached to.
     */
    public static final String ANNOTATION_RELATIVE_TO_ELEMENT_NAME = "relativeToElementName";
    
    /**
     * Name of the annotation that holds the text of a comment.
     */
    public static final String ANNOTATION_COMMENT_TEXT = "text";
    
    /**
     * Name of the annotation that holds an element's location.
     */
    public static final String ANNOTATION_LOCATION = "_location";
    
    
    /**
     * This class is not meant to be instantiated.
     */
    private TransformationConstants() {
        // This space intentionally left mostly blank
    }
    
}
