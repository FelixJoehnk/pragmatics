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
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Restores the direction of reversed edges. (edges with the property
 * {@link de.cau.cs.kieler.klay.layered.Properties#REVERSED} set to {@code true})
 * 
 * <p>
 * All edges are traversed to look for reversed edges. If such edges are found,
 * they are restored, the ports they are connected to being restored as well.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; may contain reversed edges.</dd>
 *   <dt>Postcondition:</dt><dd>a layered graph without reversed edges.</dd>
 * </dl>
 *
 * @author cds
 */
public class ReversedEdgeRestorer extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Restoring reversed edges", 1);
        
        // Iterate through the layers
        for (Layer layer : layeredGraph.getLayers()) {
            // Iterate through the nodes
            for (LNode node : layer.getNodes()) {
                // Iterate through the node's OUTPUT ports (which only makes a slight
                // difference, because when we actually find reversed edges, the port
                // type is reversed as well, the other ports becoming output ports too
                // and possibly being iterated over again)
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    // Iterate through the edges
                    for (LEdge edge : port.getEdges()) {
                        if (edge.getProperty(Properties.REVERSED)) {
                            reverseEdge(edge);
                        }
                    }
                }
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Reverses the given edge. Sets its former source port's type to {@code INPUT},
     * sets its former target port's type to {@code OUTPUT} and sets the edge's
     * {@code REVERSED} property to {@code false}.
     * 
     * @param edge the edge to reverse.
     */
    private void reverseEdge(final LEdge edge) {
        LPort formerSourcePort = edge.getSource();
        LPort formerTargetPort = edge.getTarget();
        
        // Reverse edge and remove REVERSED property
        edge.setSource(formerTargetPort);
        edge.setTarget(formerSourcePort);
        edge.setProperty(Properties.REVERSED, false);
        
        // Set port types
        formerSourcePort.setType(PortType.INPUT);
        formerTargetPort.setType(PortType.OUTPUT);
    }

}
