/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p1cycles;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;

/**
 * A cycle breaker that responds to user interaction by respecting the direction of
 * edges as given in the original drawing.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>none</dd>
 *   <dt>Postcondition:</dt><dd>the graph has no cycles
 * </dl>
 * 
 * @author msp
 */
public class InteractiveCycleBreaker extends AbstractAlgorithm implements ILayoutPhase {

    /** intermediate processing strategy. */
    private static final IntermediateProcessingStrategy INTERMEDIATE_PROCESSING_STRATEGY =
        new IntermediateProcessingStrategy(
                IntermediateProcessingStrategy.AFTER_PHASE_5,
                EnumSet.of(IntermediateLayoutProcessor.REVERSED_EDGE_RESTORER));

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        return INTERMEDIATE_PROCESSING_STRATEGY;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Interactive cycle breaking", 1);
        
        // gather edges that point to the wrong direction
        LinkedList<LEdge> revEdges = new LinkedList<LEdge>();
        for (LNode source : layeredGraph.getLayerlessNodes()) {
            source.id = 1;
            double sourcex = source.getPosition().x + source.getSize().x / 2;
            for (LPort port : source.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getOutgoingEdges()) {
                    LNode target = edge.getTarget().getNode();
                    if (target != source) {
                        double targetx = target.getPosition().x + target.getSize().x / 2;
                        if (targetx < sourcex) {
                            revEdges.add(edge);
                        }
                    }
                }
            }
        }
        // reverse the gathered edges
        for (LEdge edge : revEdges) {
            edge.reverse();
        }
        
        // perform an additional check for cycles - maybe we missed something
        // (could happen if some nodes have the same horizontal position)
        revEdges.clear();
        for (LNode node : layeredGraph.getLayerlessNodes()) {
            if (node.id > 0) {
                findCycles(node, revEdges);
            }
        }
        // again, reverse the edges that were marked
        for (LEdge edge : revEdges) {
            edge.reverse();
        }
        
        revEdges.clear();
        getMonitor().done();
    }
    
    /**
     * Perform a DFS starting on the given node and mark back edges in order to break cycles.
     * 
     * @param node1 a node
     * @param revEdges list of edges that will be reversed
     */
    private void findCycles(final LNode node1, final List<LEdge> revEdges) {
        node1.id = -1;
        for (LPort port : node1.getPorts(PortType.OUTPUT)) {
            for (LEdge edge : port.getOutgoingEdges()) {
                LNode node2 = edge.getTarget().getNode();
                if (node1 != node2) {
                    if (node2.id < 0) {
                        revEdges.add(edge);
                    } else if (node2.id > 0) {
                        findCycles(node2, revEdges);
                    }
                }
            }
        }
        node1.id = 0;
    }

}
