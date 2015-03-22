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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.PortOrder;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Counts exact number of all crossings caused by in-layer edges.
 * 
 * @author alan
 *
 */
class InLayerEdgeAllCrossingsCounter extends InLayerEdgeCrossingsCounter {
    /**
     * Create in-layer edge crossingCounter with current order of nodes.
     * 
     * @param nodeOrder
     *            current node order.
     */
    public InLayerEdgeAllCrossingsCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
    }

    /**
     * Counts all in-layer crossings.
     * 
     * @return in-layer crossings
     */
    public int countCrossings() {
        int crossings = 0;
        crossings = iterateEdgesTopDownAndCountCrossingsOnSide(PortSide.WEST);
        crossings += iterateEdgesTopDownAndCountCrossingsOnSide(PortSide.EAST);
        return crossings;
    }

    private int iterateEdgesTopDownAndCountCrossingsOnSide(final PortSide side) {
        int crossings = 0;
        for (LNode node : getNodeOrder()) {
            Iterable<LPort> ports = node.getPorts(side, PortOrder.NORTHSOUTH_EASTWEST);
            for (LPort port : ports) {
                for (LEdge edge : port.getConnectedEdges()) {
                    if (!edge.isSelfLoop()) {
                        crossings += super.countCrossingsOn(edge, port);
                    }
                }
            }
        }

        return crossings;
    }
}
