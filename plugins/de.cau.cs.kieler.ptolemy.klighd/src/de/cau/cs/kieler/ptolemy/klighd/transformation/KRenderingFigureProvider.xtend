/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.klighd.KlighdConstants

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*
import static de.cau.cs.kieler.ptolemy.klighd.transformation.TransformationConstants.*

/**
 * Creates concrete KRendering information for Ptolemy diagram elements.
 * 
 * @author ckru
 * @author cds
 */
class KRenderingFigureProvider {
    
    /** Marking nodes. */
    @Inject extension AnnotationExtensions
    /** Extensions used during the transformation. To make things easier. And stuff. */
    @Inject extension MiscellaneousExtensions
    /** Create KRenderings from Ptolemy figures. */
    @Inject extension PtolemyFigureInterface
    /** Color stuff. */
    @Inject extension KColorExtensions
    /** Rendering stuff. */
    @Inject extension KRenderingExtensions
    
    /** Rendering factory used to instantiate KRendering instances. */
    val renderingFactory = KRenderingFactory::eINSTANCE
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Node Renderings
    
    
    /**
     * Renders the given node in a default way, that is, as a simple rectangle.
     * 
     * @return the created rendering.
     */
    def KRendering createDefaultRendering(KNode node) {
        val rendering = renderingFactory.createKRectangle() => [rect |
            rect.setBackgroundColor(255, 255, 255)
        ]
        
        return rendering
    }
    
    /**
     * Creates a rendering for an expanded compound node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createExpandedCompoundNodeRendering(KNode node) {
        // This is the code for representing expanded compound nodes as rounded rectangles with
        // progressively darker grey backgrounds
        val rendering = renderingFactory.createKRoundedRectangle() => [rect |
            rect.cornerHeight = 15
            rect.cornerWidth = 15
            rect.setLineWidth(0)
            rect.styles += renderingFactory.createKBackground() => [bg |
                bg.alpha = 10
                bg.color = renderingFactory.createKColor() => [col |
                    col.red = 16
                    col.green = 78
                    col.blue = 139
                ]
            ]
        ]

        // This in turn is the code for representing expanded compound nodes as regular rectangles
        // with drop shadows
//        val rendering = renderingFactory.createKRectangle() => [rect |
//            rect.styles += renderingFactory.createKBackground() => [bg |
//                bg.alpha = 10
//                bg.color = renderingFactory.createKColor() => [col |
//                    col.red = 16
//                    col.green = 78
//                    col.blue = 139
//                ]
//            ]
//            rect.setShadow(GraphicsUtils::lookupColor("darkgrey"))
//        ]
        
        return rendering
    }
    
    /**
     * Creates a rendering for a relation node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRelationNodeRendering(KNode node) {
        return renderingFactory.createKPolygon() => [poly |
            poly.points += createKPosition(5, 0)
            poly.points += createKPosition(10, 5)
            poly.points += createKPosition(5, 10)
            poly.points += createKPosition(0, 5)
            poly.points += createKPosition(5, 0)
            poly.setBackgroundColor(0, 0, 0)
        ]
    }
    
    /**
     * Creates a rendering for a director node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createDirectorNodeRendering(KNode node) {
        return renderingFactory.createKRectangle() => [rec |
            rec.background = "green".color
            rec.foreground = "black".color
            rec.setAreaPlacementData(
                createKPosition(LEFT, 0, 0, TOP, 0, 0),
                createKPosition(LEFT, 100, 0, TOP, 30, 0)
            )
        ]
    }
    
    /**
     * Creates a rendering for a comment node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createCommentNodeRendering(KNode node) {
        val rectangle = renderingFactory.createKRectangle() => [rec |
            rec.background = renderingFactory.createKColor() => [col |
                col.red = 251
                col.green = 255
                col.blue = 222
            ]
            rec.setLineWidth(0)
        ]
        
        // Add the comment's text
        rectangle.children += renderingFactory.createKText() => [text |
            text.text = node.layout.getProperty(COMMENT_TEXT)
            text.setSurroundingSpace(5, 0)
            text.setFontSize(KlighdConstants::DEFAULT_FONT_SIZE - 2)
        ]
        
        return rectangle
    }
    
    /**
     * Creates a rendering for a state node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createStateNodeRendering(KNode node) {
        val isFinal = node.hasAnnotation("isFinalState")
        val isInitial = node.hasAnnotation("isInitialState")
        val lineWidth = if (isInitial) 4 else 1
        
        // Create the outer circle (which may remain the only one)
        val outerCircle = renderingFactory.createKRoundedRectangle() => [rec |
            rec.cornerHeight = 30
            rec.cornerWidth = 30
            rec.setAreaPlacementData(
                createKPosition(LEFT, 0, 0, TOP, 0, 0),
                createKPosition(LEFT, 30, 0, TOP, 30, 0)
            )
            rec.lineWidth = lineWidth
        ]
        
        // If this is a final state, we need to add an inner circle as well
        if (isFinal) {
            val innerCircle = renderingFactory.createKRoundedRectangle() => [rec |
                rec.cornerHeight = 22
                rec.cornerWidth = 22
                rec.setAreaPlacementData(
                    createKPosition(LEFT, 3, 0, TOP, 3, 0),
                    createKPosition(LEFT, 27, 0, TOP, 27, 0)
                )
                rec.lineWidth = lineWidth
            ]
            outerCircle.children += innerCircle
        }
        
        return outerCircle
    }
    
    /**
     * Creates a rendering for a node that displays a value.
     * 
     * @param node the node to create the rendering information for.
     * @param value the value the node should display.
     * @return the rendering.
     */
    def KRendering createValueDisplayingNodeRendering(KNode node, String value) {
        val nodeRendering = createDefaultRendering(node) as KContainerRendering
        
        // Add a text field to the default rendering
        nodeRendering.children += renderingFactory.createKText() => [text |
            text.text = value
            text.setSurroundingSpace(5, 0)
            text.setForeground(GraphicsUtils::lookupColor("darkgrey"))
        ]
        
        return nodeRendering
    }
    
    /**
     * Creates a rendering for a regular node.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createRegularNodeRendering(KNode node) {
        val ptRendering = createPtolemyFigureRendering(node.getAnnotationValue(ANNOTATION_PTOLEMY_CLASS))
        return ptRendering ?: createDefaultRendering(node)
    }
    
    /**
     * Creates a rendering for an accumulator node. This needs to be a separate case because the SVG
     * description for accumulator nodes in Ptolemy is broken.
     * 
     * @param node the node to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createAccumulatorNodeRendering(KNode node) {
        val accumulatorSvg = "<svg height=\"41\" width=\"41\" >"
                + "<rect height=\"40\" style=\"fill:white;stroke:black;stroke-width:1\" "
                + "width=\"40\" x=\"0.0\" y=\"0.0\" />"
                + "<path d=\"m 29.126953,6.2304687 0,3.0410157 -13.833984,0 8.789062,9.3515626 "
                + "-8.789062,10.335937 14.554687,0 0,3.041016 -18.246093,0 "
                + "0,-3.550781 8.419921,-9.826172 -8.419921,-8.9648439 0,-3.4277344 z\" />"
                + "</svg>"
        return GraphicsUtils::createFigureFromSvg(accumulatorSvg)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Port Renderings
    
    /** Width / height of a port. */
    private val PORT_SIZE = 7f
    
    /** Half the width / height of a port. */
    private val PORT_SIZE_HALF = 3.5f
    
    
    /**
     * Creates a rendering for a port.
     * 
     * @param port the port to create the rendering information for.
     * @return the rendering.
     */
    def KRendering createPortRendering(KPort port) {
        val layout = port.getData(typeof(KShapeLayout))
        
        // Determine the port color
        val portFillColor = if (port.hasAnnotation(IS_PARAMETER_PORT)) {
            // Parameter ports are gray
            GraphicsUtils::lookupColor("gray")
        } else if (port.hasAnnotation(IS_IO_PORT) && port.hasAnnotation(IS_MULTIPORT)) {
            // IO Multiports are white
            GraphicsUtils::lookupColor("white")
        } else {
            // All other ports are black
            GraphicsUtils::lookupColor("black")
        }
        
        // Create the triangle (depending on the port size)
        val polygon = port.addPolygon()
        switch layout.getProperty(LayoutOptions::PORT_SIDE) {
            case PortSide::NORTH: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE_HALF, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
            }
            case PortSide::SOUTH: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE_HALF, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
            }
            default: {
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, 0, 0)
                polygon.points += createKPosition(
                    LEFT, PORT_SIZE, 0, TOP, PORT_SIZE_HALF, 0)
                polygon.points += createKPosition(
                    LEFT, 0, 0, TOP, PORT_SIZE, 0)
            }
        }
        
        // Set color properties
        polygon.setBackground(portFillColor)
        polygon.setForeground(GraphicsUtils::lookupColor("black"))
        
        return polygon
    }
    
}