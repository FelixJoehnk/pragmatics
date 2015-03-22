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

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Superclass for SwitchDeciders that calculate the crossing matrix on demand.
 * 
 * @author alan
 *
 */
abstract class OnDemandCrossingMatrixSwitchDecider extends CrossingMatrixSwitchDecider {
    private final boolean[][] isCrossingMatrixFilled;
    private final int[][] crossingMatrix;

    public OnDemandCrossingMatrixSwitchDecider(final int freeLayerIndex, final LNode[][] graph) {
        super(freeLayerIndex, graph);
        isCrossingMatrixFilled =
                new boolean[super.getFreeLayer().length][super.getFreeLayer().length];
        crossingMatrix = new int[super.getFreeLayer().length][super.getFreeLayer().length];
    }

    @Override
    int getCrossingMatrixEntry(final LNode upperNode, final LNode lowerNode) {
        if (!isCrossingMatrixFilled[positionOf(upperNode)][positionOf(lowerNode)]) {
            fillCrossingMatrix(upperNode, lowerNode);
            isCrossingMatrixFilled[positionOf(upperNode)][positionOf(lowerNode)] = true;
            isCrossingMatrixFilled[positionOf(lowerNode)][positionOf(upperNode)] = true;
        }
        return crossingMatrix[positionOf(upperNode)][positionOf(lowerNode)];
    }

    abstract void fillCrossingMatrix(LNode upperNode, LNode lowerNode);

    protected void setCrossingMatrixEntriesFromCounter(final LNode upperNode, final LNode lowerNode) {
        crossingMatrix[positionOf(upperNode)][positionOf(lowerNode)] =
                super.getTwoLayerCrossCounter().getUpperLowerCrossings();
        crossingMatrix[positionOf(lowerNode)][positionOf(upperNode)] =
                super.getTwoLayerCrossCounter().getLowerUpperCrossings();
    }

}
