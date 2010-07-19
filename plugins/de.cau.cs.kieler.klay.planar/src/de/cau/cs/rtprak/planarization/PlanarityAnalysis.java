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
package de.cau.cs.rtprak.planarization;

import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.rtprak.planarization.graph.IGraph;
import de.cau.cs.rtprak.planarization.graph.InconsistentGraphModelException;
import de.cau.cs.rtprak.planarization.graph.impl.PGraph;
import de.cau.cs.rtprak.planarization.impl.BoyerMyrvoldPlanarityTester;

/**
 * A graph analysis, that uses planarity testing algorithms to check if a graph is planar.
 * 
 * @author ocl
 */
public class PlanarityAnalysis implements IAnalysis {

    // ======================== Attributes =========================================================

    /** Algorithm for planar testing. */
    private IPlanarityTester tester = new BoyerMyrvoldPlanarityTester();

    // ======================== Analysis ===========================================================

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        progressMonitor.begin("Planarity testing", 1);
        boolean planar;

        try {
            // KGraph -> PGraph conversion
            IGraph graph = new PGraph(parentNode);

            // Planarity Testing
            planar = this.tester.testPlanarity(graph);

        } catch (InconsistentGraphModelException e) {
            throw new KielerException("Inconsistent Graph Model: " + e.getMessage());
        }

        progressMonitor.done();
        return planar;
    }

}
