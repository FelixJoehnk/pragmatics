/*
* generated by Xtext
*/
grammar InternalSequence;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package de.cau.cs.kieler.uml.sequence.text.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.cau.cs.kieler.uml.sequence.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.uml.sequence.text.services.SequenceGrammarAccess;

}

@parser::members {

 	private SequenceGrammarAccess grammarAccess;
 	
    public InternalSequenceParser(TokenStream input, SequenceGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "SequenceDiagram";	
   	}
   	
   	@Override
   	protected SequenceGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleSequenceDiagram
entryRuleSequenceDiagram returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSequenceDiagramRule()); }
	 iv_ruleSequenceDiagram=ruleSequenceDiagram 
	 { $current=$iv_ruleSequenceDiagram.current; } 
	 EOF 
;

// Rule SequenceDiagram
ruleSequenceDiagram returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0(),
            $current);
    }
)	otherlv_1='sequenceDiagram' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1());
    }
(
(
		lv_DiagramName_2_0=RULE_STRING
		{
			newLeafNode(lv_DiagramName_2_0, grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getSequenceDiagramRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"DiagramName",
        		lv_DiagramName_2_0, 
        		"STRING");
	    }

)
)(	otherlv_3='{' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_1_0()); 
	    }
		lv_locals_4_0=ruleLocalVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
	        }
       		add(
       			$current, 
       			"locals",
        		lv_locals_4_0, 
        		"LocalVariable");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_5='}' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_2());
    }
)?(
(
		{ 
	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 
	    }
		lv_lifelines_6_0=ruleLifeline		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
	        }
       		add(
       			$current, 
       			"lifelines",
        		lv_lifelines_6_0, 
        		"Lifeline");
	        afterParserOrEnumRuleCall();
	    }

)
)*(
(
		{ 
	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 
	    }
		lv_interactions_7_0=ruleInteraction		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
	        }
       		add(
       			$current, 
       			"interactions",
        		lv_interactions_7_0, 
        		"Interaction");
	        afterParserOrEnumRuleCall();
	    }

)
)*)
;





// Entry rule entryRuleLocalVariable
entryRuleLocalVariable returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLocalVariableRule()); }
	 iv_ruleLocalVariable=ruleLocalVariable 
	 { $current=$iv_ruleLocalVariable.current; } 
	 EOF 
;

// Rule LocalVariable
ruleLocalVariable returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='Char' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getLocalVariableAccess().getCharKeyword_0_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLocalVariableRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
))
    |(	otherlv_2='Boolean' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getLocalVariableAccess().getBooleanKeyword_1_0());
    }
(
(
		lv_name_3_0=RULE_ID
		{
			newLeafNode(lv_name_3_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLocalVariableRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_3_0, 
        		"ID");
	    }

)
))
    |(	otherlv_4='Integer' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getLocalVariableAccess().getIntegerKeyword_2_0());
    }
(
(
		lv_name_5_0=RULE_ID
		{
			newLeafNode(lv_name_5_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLocalVariableRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_5_0, 
        		"ID");
	    }

)
))
    |(	otherlv_6='Float' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getLocalVariableAccess().getFloatKeyword_3_0());
    }
(
(
		lv_name_7_0=RULE_ID
		{
			newLeafNode(lv_name_7_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_3_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLocalVariableRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_7_0, 
        		"ID");
	    }

)
)))
;





// Entry rule entryRuleLifeline
entryRuleLifeline returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLifelineRule()); }
	 iv_ruleLifeline=ruleLifeline 
	 { $current=$iv_ruleLifeline.current; } 
	 EOF 
;

// Rule Lifeline
ruleLifeline returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='lifeline' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getLifelineAccess().getLifelineKeyword_0());
    }
(
(
		lv_caption_1_0=RULE_STRING
		{
			newLeafNode(lv_caption_1_0, grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLifelineRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"caption",
        		lv_caption_1_0, 
        		"STRING");
	    }

)
)	otherlv_2='as' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getLifelineAccess().getAsKeyword_2());
    }
(
(
		lv_name_3_0=RULE_ID
		{
			newLeafNode(lv_name_3_0, grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLifelineRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_3_0, 
        		"ID");
	    }

)
))
;





// Entry rule entryRuleInteraction
entryRuleInteraction returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getInteractionRule()); }
	 iv_ruleInteraction=ruleInteraction 
	 { $current=$iv_ruleInteraction.current; } 
	 EOF 
;

// Rule Interaction
ruleInteraction returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
    }
    this_TwoLifelineMessage_0=ruleTwoLifelineMessage
    { 
        $current = $this_TwoLifelineMessage_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
    }
    this_OneLifelineMessage_1=ruleOneLifelineMessage
    { 
        $current = $this_OneLifelineMessage_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
    }
    this_Fragment_2=ruleFragment
    { 
        $current = $this_Fragment_2.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 
    }
    this_OneLifelineEndBlock_3=ruleOneLifelineEndBlock
    { 
        $current = $this_OneLifelineEndBlock_3.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 
    }
    this_OneLifelineNote_4=ruleOneLifelineNote
    { 
        $current = $this_OneLifelineNote_4.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getDestroyParserRuleCall_5()); 
    }
    this_Destroy_5=ruleDestroy
    { 
        $current = $this_Destroy_5.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
    }
    this_Refinement_6=ruleRefinement
    { 
        $current = $this_Refinement_6.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleTwoLifelineMessage
entryRuleTwoLifelineMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTwoLifelineMessageRule()); }
	 iv_ruleTwoLifelineMessage=ruleTwoLifelineMessage 
	 { $current=$iv_ruleTwoLifelineMessage.current; } 
	 EOF 
;

// Rule TwoLifelineMessage
ruleTwoLifelineMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
	}

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
	    }
		lv_transitionType_1_0=ruleTransitionType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTwoLifelineMessageRule());
	        }
       		set(
       			$current, 
       			"transitionType",
        		lv_transitionType_1_0, 
        		"TransitionType");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		lv_caption_2_0=RULE_STRING
		{
			newLeafNode(lv_caption_2_0, grammarAccess.getTwoLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"caption",
        		lv_caption_2_0, 
        		"STRING");
	    }

)
)	otherlv_3='to' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
        }
	otherlv_4=RULE_ID
	{
		newLeafNode(otherlv_4, grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
	}

)
)((
(
		lv_startBlockLeft_5_0=	'sourceStartBlock' 
    {
        newLeafNode(lv_startBlockLeft_5_0, grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "startBlockLeft", true, "sourceStartBlock");
	    }

)
)
    |((
(
		lv_endBlockLeft_6_0=	'sourceEndBlock' 
    {
        newLeafNode(lv_endBlockLeft_6_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "endBlockLeft", true, "sourceEndBlock");
	    }

)
)(
(
		lv_endBlockLeftCount_7_0=RULE_INT_GREATER_ZERO
		{
			newLeafNode(lv_endBlockLeftCount_7_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"endBlockLeftCount",
        		lv_endBlockLeftCount_7_0, 
        		"INT_GREATER_ZERO");
	    }

)
)?))?((
(
		lv_startBlockRight_8_0=	'targetStartBlock' 
    {
        newLeafNode(lv_startBlockRight_8_0, grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "startBlockRight", true, "targetStartBlock");
	    }

)
)
    |((
(
		lv_endBlockRight_9_0=	'targetEndBlock' 
    {
        newLeafNode(lv_endBlockRight_9_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "endBlockRight", true, "targetEndBlock");
	    }

)
)(
(
		lv_endBlockRightCount_10_0=RULE_INT_GREATER_ZERO
		{
			newLeafNode(lv_endBlockRightCount_10_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"endBlockRightCount",
        		lv_endBlockRightCount_10_0, 
        		"INT_GREATER_ZERO");
	    }

)
)?))?(	otherlv_11='sourceNote' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0());
    }
(
(
		lv_sourceNote_12_0=RULE_STRING
		{
			newLeafNode(lv_sourceNote_12_0, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"sourceNote",
        		lv_sourceNote_12_0, 
        		"STRING");
	    }

)
))?(	otherlv_13='targetNote' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0());
    }
(
(
		lv_targetNote_14_0=RULE_STRING
		{
			newLeafNode(lv_targetNote_14_0, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"targetNote",
        		lv_targetNote_14_0, 
        		"STRING");
	    }

)
))?)
;





// Entry rule entryRuleOneLifelineMessage
entryRuleOneLifelineMessage returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOneLifelineMessageRule()); }
	 iv_ruleOneLifelineMessage=ruleOneLifelineMessage 
	 { $current=$iv_ruleOneLifelineMessage.current; } 
	 EOF 
;

// Rule OneLifelineMessage
ruleOneLifelineMessage returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
	}

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
	    }
		lv_transitionType_1_0=ruleTransitionType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getOneLifelineMessageRule());
	        }
       		set(
       			$current, 
       			"transitionType",
        		lv_transitionType_1_0, 
        		"TransitionType");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2='lost' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getOneLifelineMessageAccess().getLostKeyword_2_0());
    }

    |	otherlv_3='found' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getOneLifelineMessageAccess().getFoundKeyword_2_1());
    }
)(
(
		lv_caption_4_0=RULE_STRING
		{
			newLeafNode(lv_caption_4_0, grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"caption",
        		lv_caption_4_0, 
        		"STRING");
	    }

)
)((
(
		lv_startBlock_5_0=	'startBlock' 
    {
        newLeafNode(lv_startBlock_5_0, grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "startBlock", true, "startBlock");
	    }

)
)
    |((
(
		lv_endBlock_6_0=	'endBlock' 
    {
        newLeafNode(lv_endBlock_6_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
       		setWithLastConsumed($current, "endBlock", true, "endBlock");
	    }

)
)(
(
		lv_endBlockCount_7_0=RULE_INT_GREATER_ZERO
		{
			newLeafNode(lv_endBlockCount_7_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_4_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"endBlockCount",
        		lv_endBlockCount_7_0, 
        		"INT_GREATER_ZERO");
	    }

)
)?))?(	otherlv_8='note' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0());
    }
(
(
		lv_note_9_0=RULE_STRING
		{
			newLeafNode(lv_note_9_0, grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineMessageRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"note",
        		lv_note_9_0, 
        		"STRING");
	    }

)
))?)
;





// Entry rule entryRuleOneLifelineEndBlock
entryRuleOneLifelineEndBlock returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOneLifelineEndBlockRule()); }
	 iv_ruleOneLifelineEndBlock=ruleOneLifelineEndBlock 
	 { $current=$iv_ruleOneLifelineEndBlock.current; } 
	 EOF 
;

// Rule OneLifelineEndBlock
ruleOneLifelineEndBlock returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 
	}

)
)(
(
		lv_endBlock_1_0=	'endBlock' 
    {
        newLeafNode(lv_endBlock_1_0, grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
	        }
       		setWithLastConsumed($current, "endBlock", true, "endBlock");
	    }

)
)(
(
		lv_endBlockCount_2_0=RULE_INT_GREATER_ZERO
		{
			newLeafNode(lv_endBlockCount_2_0, grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"endBlockCount",
        		lv_endBlockCount_2_0, 
        		"INT_GREATER_ZERO");
	    }

)
)?)
;





// Entry rule entryRuleOneLifelineNote
entryRuleOneLifelineNote returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOneLifelineNoteRule()); }
	 iv_ruleOneLifelineNote=ruleOneLifelineNote 
	 { $current=$iv_ruleOneLifelineNote.current; } 
	 EOF 
;

// Rule OneLifelineNote
ruleOneLifelineNote returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineNoteRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
	}

)
)	otherlv_1='note' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1());
    }
(
(
		lv_note_2_0=RULE_STRING
		{
			newLeafNode(lv_note_2_0, grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getOneLifelineNoteRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"note",
        		lv_note_2_0, 
        		"STRING");
	    }

)
))
;





// Entry rule entryRuleDestroy
entryRuleDestroy returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getDestroyRule()); }
	 iv_ruleDestroy=ruleDestroy 
	 { $current=$iv_ruleDestroy.current; } 
	 EOF 
;

// Rule Destroy
ruleDestroy returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getDestroyRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getDestroyAccess().getLifelineLifelineCrossReference_0_0()); 
	}

)
)(
(
		lv_destroy_1_0=	'destroy' 
    {
        newLeafNode(lv_destroy_1_0, grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getDestroyRule());
	        }
       		setWithLastConsumed($current, "destroy", true, "destroy");
	    }

)
))
;





// Entry rule entryRuleFragment
entryRuleFragment returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getFragmentRule()); }
	 iv_ruleFragment=ruleFragment 
	 { $current=$iv_ruleFragment.current; } 
	 EOF 
;

// Rule Fragment
ruleFragment returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='fragment' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getFragmentAccess().getFragmentKeyword_0());
    }
(
(
		lv_name_1_0=RULE_STRING
		{
			newLeafNode(lv_name_1_0, grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getFragmentRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"STRING");
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_2_0()); 
	    }
		lv_fragmentContents_2_0=ruleFragmentContent		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getFragmentRule());
	        }
       		add(
       			$current, 
       			"fragmentContents",
        		lv_fragmentContents_2_0, 
        		"FragmentContent");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_3_0()); 
	    }
		lv_fragmentContents_3_0=ruleFragmentContent		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getFragmentRule());
	        }
       		add(
       			$current, 
       			"fragmentContents",
        		lv_fragmentContents_3_0, 
        		"FragmentContent");
	        afterParserOrEnumRuleCall();
	    }

)
)*)
;





// Entry rule entryRuleFragmentContent
entryRuleFragmentContent returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getFragmentContentRule()); }
	 iv_ruleFragmentContent=ruleFragmentContent 
	 { $current=$iv_ruleFragmentContent.current; } 
	 EOF 
;

// Rule FragmentContent
ruleFragmentContent returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='{' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getFragmentContentAccess().getLeftCurlyBracketKeyword_0());
    }
(	otherlv_1='label' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getFragmentContentAccess().getLabelKeyword_1_0());
    }
(
(
		lv_label_2_0=RULE_STRING
		{
			newLeafNode(lv_label_2_0, grammarAccess.getFragmentContentAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getFragmentContentRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"label",
        		lv_label_2_0, 
        		"STRING");
	    }

)
))?(
(
		{ 
	        newCompositeNode(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_2_0()); 
	    }
		lv_interactions_3_0=ruleInteraction		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getFragmentContentRule());
	        }
       		add(
       			$current, 
       			"interactions",
        		lv_interactions_3_0, 
        		"Interaction");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_3_0()); 
	    }
		lv_interactions_4_0=ruleInteraction		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getFragmentContentRule());
	        }
       		add(
       			$current, 
       			"interactions",
        		lv_interactions_4_0, 
        		"Interaction");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_5='}' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getFragmentContentAccess().getRightCurlyBracketKeyword_4());
    }
)
;





// Entry rule entryRuleRefinement
entryRuleRefinement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRefinementRule()); }
	 iv_ruleRefinement=ruleRefinement 
	 { $current=$iv_ruleRefinement.current; } 
	 EOF 
;

// Rule Refinement
ruleRefinement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='refinement' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getRefinementAccess().getRefinementKeyword_0());
    }
	otherlv_1='{' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getRefinementAccess().getLeftCurlyBracketKeyword_1());
    }
	otherlv_2='lifelines' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getRefinementAccess().getLifelinesKeyword_2());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRefinementRule());
	        }
        }
	otherlv_3=RULE_ID
	{
		newLeafNode(otherlv_3, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_3_0()); 
	}

)
)(	otherlv_4=',' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getRefinementAccess().getCommaKeyword_4_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRefinementRule());
	        }
        }
	otherlv_5=RULE_ID
	{
		newLeafNode(otherlv_5, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_1_0()); 
	}

)
))*	otherlv_6='label' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getRefinementAccess().getLabelKeyword_5());
    }
(
(
		lv_label_7_0=RULE_STRING
		{
			newLeafNode(lv_label_7_0, grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_6_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRefinementRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"label",
        		lv_label_7_0, 
        		"STRING");
	    }

)
)	otherlv_8='}' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getRefinementAccess().getRightCurlyBracketKeyword_7());
    }
)
;





// Rule TransitionType
ruleTransitionType returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='async' 
	{
        $current = grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='create' 
	{
        $current = grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='response' 
	{
        $current = grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='sync' 
	{
        $current = grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3()); 
    }
));



RULE_INT_GREATER_ZERO : ('1'..'9' ('0'..'9')*|'all');

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


