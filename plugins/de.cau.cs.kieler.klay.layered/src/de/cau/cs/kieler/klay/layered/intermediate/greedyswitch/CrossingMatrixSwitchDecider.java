/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
abstract class CrossingMatrixSwitchDecider extends SwitchDecider {

    private final InLayerEdgeCrossingCounter inLayerCounter;
    private final InBetweenLayerEdgeTwoNodeCrossingCounter inBetweenLayerCrossCounter;

    /**
     * @param freeLayerIndex
     * @param graph
     */
    public CrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        initializeNodeIds(graph);
        inBetweenLayerCrossCounter =
                new InBetweenLayerEdgeTwoNodeCrossingCounter(graph, freeLayerIndex);
        inLayerCounter = new InLayerEdgeCrossingCounter(super.getFreeLayer());
    }

    private void initializeNodeIds(final LNode[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            int id = 0;
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j].id = id++;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesSwitchReduceCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        if (super.constraintsPreventSwitch(upperNodeIndex, lowerNodeIndex)) {
            return false;
        }

        LNode upperNode = super.getFreeLayer()[upperNodeIndex];
        LNode lowerNode = super.getFreeLayer()[lowerNodeIndex];
        inLayerCounter.countCrossingsBetweenNeighbouringNodes(upperNode, lowerNode);

        int upperLowerCrossings =
                getCrossingMatrixEntry(upperNode, lowerNode)
                        + inLayerCounter.getUpperLowerCrossings();
        int lowerUpperCrossings =
                getCrossingMatrixEntry(lowerNode, upperNode)
                        + inLayerCounter.getLowerUpperCrossings();

        return upperLowerCrossings > lowerUpperCrossings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyOfSwitch(final int upperNodeIndex, final int lowerNodeIndex) {
        inLayerCounter.notifyNodeSwitch(super.getFreeLayer()[upperNodeIndex],
                super.getFreeLayer()[lowerNodeIndex]);
    }

    abstract int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode);

    protected InBetweenLayerEdgeTwoNodeCrossingCounter getTwoLayerCrossCounter() {
        return inBetweenLayerCrossCounter;
    }

}
