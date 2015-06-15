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
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

/**
 * A batch result serializer which saves the information as CSV.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class CSVResultSerializer implements IBatchResultSerializer {

    /** the visualization type used in the serialization. */
    private static final String VISUALIZATION_TYPE = "csv";

    /**
     * {@inheritDoc}
     */
    public void serialize(final OutputStream outputStream,
            final BatchResult batchResult, final IKielerProgressMonitor monitor)
            throws Exception {
        monitor.begin("Serialize batch result as CSV", 1);
        // create a stream writer
        OutputStreamWriter writer =
                new OutputStreamWriter(outputStream, "UTF-8");
        // write the header
        writer.write("File");
        for (AnalysisData analysis : batchResult.getAnalyses()) {
            if (analysis.getComponents().size() > 0) {
                for (Pair<String, String> component : analysis.getComponents()) {
                    writer.write(";" + analysis.getName() + " ("
                            + component.getFirst() + ")");
                }
            } else {
                writer.write(";" + analysis.getName());
            }
        }
        
        // possibly write range analysis headers
        Batch batch = batchResult.getBatch();
        if (batch.getRangeAnalysis() != null) {
            for (Number n : batch.getRangeValues()) {
                writer.write(";" + n);
            }
        }
        
        // headers for execution time
        List<String> executionTimePhases = Lists.newArrayList(batchResult.getExecutionTimePhases());
        // sort them lexicographically 
        Collections.sort(executionTimePhases);
        for (String phase : executionTimePhases) {
            writer.write(";" + phase);
        }
        writer.write("\n");
        
        // write the job results
        for (BatchJobResult<?> jobResult : batchResult.getJobResults()) {
            writer.write(jobResult.getJob().getParameter().toString());
            Map<String, Object> results = jobResult.getResults();
            // basic analyses
            for (AnalysisData analysis : batchResult.getAnalyses()) {
                Object result = results.get(analysis.getId());
                Visualization visualization =
                        VisualizationService.getInstance().getVisualization(
                                VISUALIZATION_TYPE, result);
                String s = visualization.get(analysis, result);
                writer.write(";" + s);
            }
            
            // possibly append range batch results
            if (jobResult.getJob() instanceof BatchRangeJob<?>) {
                for (Object result : jobResult.getRangeResults()) {
                    Object component = result;
                    if (result instanceof Object[]) {
                        component = ((Object[]) result)[batch.getRangeAnalysisComponent()];
                    }
                    Visualization visualization =
                            VisualizationService.getInstance().getVisualization("text", result);
                    String text = visualization.get(batch.getRangeAnalysis(), component);
                    writer.write(";" + text);
                }
            }

            // execution time results
            Map<String, Double> execTimes = jobResult.getExecTimeResults();
            if (execTimes != null) {
                for (String phase : executionTimePhases) {
                    Double time = execTimes.get(phase);
                    writer.write(";" + (time != null ? time + "" : ""));
                }
            }
            
            writer.write("\n");
        }
        writer.close();
        monitor.done();
    }

}
