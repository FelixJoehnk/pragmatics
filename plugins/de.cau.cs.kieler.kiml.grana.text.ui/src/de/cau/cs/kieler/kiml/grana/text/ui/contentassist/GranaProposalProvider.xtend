/*
 * generated by Xtext
 */
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.PersistentEntry
import de.cau.cs.kieler.kiml.LayoutMetaDataService
import de.cau.cs.kieler.kiml.LayoutOptionData.Type
import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.ui.LayoutOptionLabelProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 * 
 * @author uru
 */
class GranaProposalProvider extends AbstractGranaProposalProvider {
    
    @Inject
    private GranaGrammarAccess grammarAccess;
    
    /**
     * Proposals for graph analyses. 
     */
    override completeAnalysis_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        
        super.completeAnalysis_Name(model, assignment, context, acceptor)

        // propose all known analyses that match the current prefix
        for (a : AnalysisService.instance.analyses) {
            if (isValidProposal(a.id, context.prefix, context)) {
                val displayString = new StyledString(a.name)
                acceptor.accept(doCreateProposal(a.id, displayString, null, priorityHelper.defaultPriority + 1, context))
            }
        }

    }
    
    /**
     * Proposals for layout option keys.
     */
    override completePersistentEntry_Key(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        
        // create and register the completion proposal for every element in the list
        for (optionData : LayoutMetaDataService.instance.optionData) {
            val displayString = new StyledString(optionData.toString(),
                            if (optionData.isAdvanced()) StyledString.COUNTER_STYLER else null)
                            
            displayString.append(" (" + optionData.getId() + ")", StyledString.QUALIFIER_STYLER)

            val proposal = getValueConverter().toString(optionData.getId(),
                                grammarAccess.getQualifiedIDRule().getName())

            val labelProvider = new LayoutOptionLabelProvider(optionData)
            val image = labelProvider.getImage(optionData.getDefault())

            handleKeyProposal(context, acceptor, optionData.getId(), proposal, displayString, image);
        }
    }
    
    /**
     * Copied from {@link KGraphProposalProvider}.
     */
    private def handleKeyProposal(ContentAssistContext context,
            ICompletionProposalAcceptor acceptor, String id, String proposal,
            StyledString displayString, Image image) {
        
        if (isValidProposal(proposal, context.getPrefix(), context)) {
            // accept the proposal with unmodified prefix
            acceptor.accept(doCreateProposal(proposal, displayString, image, getPriorityHelper()
                    .getDefaultPriority(), context));
        } else {
            val lastDotIndex = id.lastIndexOf('.');
            if (lastDotIndex >= 0) {
                // accept the proposal with enhanced prefix
                val prefix =
                        new StringBuilder(id.substring(0, lastDotIndex + 1))
                prefix.append(context.getPrefix())
                // add escape characters as required
                var i = 0
                while (i < proposal.length && i < prefix.length) {
                    
                    if (proposal.charAt(i) != prefix.charAt(i)) {
                        if (proposal.charAt(i) == '^') {
                            prefix.insert(i, '^');
                        } else {
                            // break
                            i = proposal.length
                        }
                    }
                    
                    i = i + 1
                }
                if (isValidProposal(proposal, prefix.toString(), context)) {
                    // accept the proposal with unmodified prefix
                    acceptor.accept(doCreateProposal(proposal, displayString, image,
                            getPriorityHelper().getDefaultPriority(), context));
                }
            }
        }
    }
    
    override completePersistentEntry_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.completePersistentEntry_Value(model, assignment, context, acceptor)
        
        valueProposal(context, acceptor)
    }
    
    /**
     * Copied from {@link KGraphProposalProvider}.
     * 
     * Computes the annotation value proposals based on a foregoing layout parameter.
     * 
     * @param context Xtext API
     * @param acceptor Xtext API
     * 
     * @author sgu, chsch
     */
    private def valueProposal(ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        // check if the prefix is a KIELER annotation
        if (context.getCurrentModel() instanceof PersistentEntry) {

            val annotationName = (context.getCurrentModel() as PersistentEntry).getKey();
            if (!annotationName.nullOrEmpty) {

                // get the option list
                val layoutServices = LayoutMetaDataService.getInstance();

                // find the specific option an display all possible values
                var optionData = layoutServices.getOptionData(annotationName);
                
                // if option data is null, try to add the kieler prefix
                if (optionData == null) {
                    optionData = layoutServices.getOptionData("de.cau.cs.kieler." + annotationName);
                }
                
                val theType = if (optionData != null) optionData.getType() else Type.UNDEFINED;
                var String proposal = null;
                
                switch (theType) {
                // show the available choices for boolean and enumeration/
                case Type.BOOLEAN,
                case Type.ENUM,
                case Type.ENUMSET:
                    for ( j : 0..< optionData.getChoices().length) {
                        proposal = optionData.choices.get(j)
                        acceptor.accept(createCompletionProposal(proposal, context));
                    }

                // for string, float, integer and object show the type of the value and give a
                //  corresponding default value
                case theType == Type.STRING && annotationName.equals(LayoutOptions.ALGORITHM.getId()): {
                    var String displayString = null;
                    for (data : layoutServices.getAlgorithmData()) {
                        proposal = '"' + data.getId() + '"';
                        displayString = data.getName();
                        acceptor.accept(createCompletionProposal(proposal, displayString, null,
                                context));
                    }
                }

                // fall through if STRING but above predicate is false

                //case Type.FLOAT:
                //case Type.INT:
                //case Type.OBJECT:
                case Type.STRING,
                case Type.FLOAT,
                case Type.INT,
                case Type.OBJECT: {
                    // chose the corresponding default value
                    switch (theType) {
                        case Type.STRING:
                            proposal = "\"\""
                        case Type.FLOAT:
                            proposal = "0.0"
                        case Type.INT:
                            proposal = "0"
                        case Type.OBJECT: {
                            try {
                                proposal = "\""
                                        + optionData.getOptionClass().newInstance().toString()
                                        + "\"";
                            } catch (InstantiationException e) {
                                proposal = "\"\"";
                            } catch (IllegalAccessException e) {
                                proposal = "\"\"";
                            }
                        }
                        default: 
                        	proposal = ""
                    }
                    acceptor.accept(createCompletionProposal(proposal, optionData.getType()
                            .toString(), null, context));
                }
                }
            }
        }
    }
}
