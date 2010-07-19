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
package de.cau.cs.kieler.kiml.evol.grana;

import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * Calculates a normalized "crosslessness" metric, based upon the number of
 * crossings.
 *
 * @author bdu
 *
 */
public class EdgeCrossingsMetric implements IAnalysis {
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("Edge crossings metric analysis", 1);
        final Float result;
        final Object crossingsResult = results.get("de.cau.cs.kieler.kiml.grana.edgeCrossings");
        final Object edgesResult = results.get("de.cau.cs.kieler.kiml.grana.edgeCount");
        final Object bendsResult = results.get("de.cau.cs.kieler.kiml.grana.bendpointCount");
        final int edgesCount = (Integer) edgesResult;
        final int bendsCount = (Integer) bendsResult;
        final int crossingsCount = (Integer) crossingsResult;

        final int edgesAuxCount = edgesCount + bendsCount;
        // FIXME: this is an overestimate. Substract # of impossible crossings.
        final int maxCrossingsCount = (edgesAuxCount * (edgesAuxCount - 1)) / 2;

        result = (float) ((double) crossingsCount / maxCrossingsCount);

        return result;

    }
}
