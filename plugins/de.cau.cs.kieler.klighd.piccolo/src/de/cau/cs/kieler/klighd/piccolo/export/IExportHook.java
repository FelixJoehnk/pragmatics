/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;


/**
 * Interface to be implemented when using the extensionpoint
 * de.cau.cs.kieler.klighd.piccolo.exportHooks. Registered hooks are being called before and after
 * the diagram is drawn.
 *
 * @author csp
 */
public interface IExportHook {
    /**
     * This function is called before the diagram is drawn. The bounds have the form
     * <code>(0,0,width,heigh)</code> where width and height are the overall size of the drawn
     * diagram. The returned {@link AffineTransform} gets applied after all pre hooks are executed
     * but before the diagram is drawn. For each hook, the initial transform is reset and the
     * returned transform is saved until all hooks are finished.</br>
     * </br>
     * You can use the returned transform e.g. to resize the diagram and draw something onto the freed
     * space. See {@link AuthorConfidentialExportHook} for more details and example code.
     *
     * @param graphics
     *            the graphics to draw on
     * @param bounds
     *            the size of drawing area
     * @return the affine transform to apply before drawing the diagram. May be <code>null</code>.
     */
    AffineTransform drawPreDiagram(KlighdSWTGraphicsEx graphics, Rectangle2D bounds);

    /**
     * This method is called after the diagram has been drawn. The bounds have the form
     * <code>(0,0,width,heigh)</code> where width and height are the overall size of the drawn
     * diagram.
     *
     * @param graphics
     *            the graphics to draw on
     * @param bounds
     *            the size of drawing area
     */
    void drawPostDiagram(KlighdSWTGraphicsEx graphics, Rectangle2D bounds);

}
