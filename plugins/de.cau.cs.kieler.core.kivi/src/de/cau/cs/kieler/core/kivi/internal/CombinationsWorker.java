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
package de.cau.cs.kieler.core.kivi.internal;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.kivi.UnlockEffect;
import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;

/**
 * Worker thread that handles the evaluation and execution of all combinations.
 * 
 * @author mmu
 * 
 */
public class CombinationsWorker extends Thread {

    /**
     * Queue of triggers containing events that need to be handled.
     */
    private BlockingQueue<ITriggerState> triggerStates = new LinkedBlockingQueue<ITriggerState>();

    /**
     * Default constructor, sets thread name as combinations.
     */
    public CombinationsWorker() {
        super("Combinations");
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                ITriggerState triggerState = triggerStates.take();
                try {
                    KiVi.getInstance().distributeTriggerState(triggerState);
                    // schedule an UnlockEffect, that calls notifyAll on the given
                    // triggerState. Any Triggers that wait on that state will be
                    // awakened. This way triggers can wait for the execution of effects
                    // before they continue triggering.
                    if (!(triggerState instanceof EffectTriggerState)) {
                        // dont unlock if the trigger is reaction to an effect -> endless loop
                        KiVi.getInstance().executeEffect(new UnlockEffect(triggerState));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // got interrupted
            }
        }
    }

    /**
     * Insert a new trigger state into the queue.
     * 
     * @param triggerState
     *            the trigger state to be inserted
     */
    public void trigger(final ITriggerState triggerState) {
        try {
            triggerStates.put(triggerState);
        } catch (InterruptedException e) {
            // got interrupted
        }
    }

}
