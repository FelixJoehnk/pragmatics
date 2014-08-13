/*
* generated by Xtext
*/
grammar InternalGrana;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;

}

@parser::members {
 
 	private GranaGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(GranaGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRuleGrana
entryRuleGrana 
:
{ before(grammarAccess.getGranaRule()); }
	 ruleGrana
{ after(grammarAccess.getGranaRule()); } 
	 EOF 
;

// Rule Grana
ruleGrana
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getGranaAccess().getJobsAssignment()); }
(rule__Grana__JobsAssignment)*
{ after(grammarAccess.getGranaAccess().getJobsAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleJob
entryRuleJob 
:
{ before(grammarAccess.getJobRule()); }
	 ruleJob
{ after(grammarAccess.getJobRule()); } 
	 EOF 
;

// Rule Job
ruleJob
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getJobAccess().getGroup()); }
(rule__Job__Group__0)
{ after(grammarAccess.getJobAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleResource
entryRuleResource 
:
{ before(grammarAccess.getResourceRule()); }
	 ruleResource
{ after(grammarAccess.getResourceRule()); } 
	 EOF 
;

// Rule Resource
ruleResource
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getResourceAccess().getGroup()); }
(rule__Resource__Group__0)
{ after(grammarAccess.getResourceAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleAnalysis
entryRuleAnalysis 
:
{ before(grammarAccess.getAnalysisRule()); }
	 ruleAnalysis
{ after(grammarAccess.getAnalysisRule()); } 
	 EOF 
;

// Rule Analysis
ruleAnalysis
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getAnalysisAccess().getNameAssignment()); }
(rule__Analysis__NameAssignment)
{ after(grammarAccess.getAnalysisAccess().getNameAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleKIdentifier
entryRuleKIdentifier 
:
{ before(grammarAccess.getKIdentifierRule()); }
	 ruleKIdentifier
{ after(grammarAccess.getKIdentifierRule()); } 
	 EOF 
;

// Rule KIdentifier
ruleKIdentifier
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getKIdentifierAccess().getGroup()); }
(rule__KIdentifier__Group__0)
{ after(grammarAccess.getKIdentifierAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRulePersistentEntry
entryRulePersistentEntry 
:
{ before(grammarAccess.getPersistentEntryRule()); }
	 rulePersistentEntry
{ after(grammarAccess.getPersistentEntryRule()); } 
	 EOF 
;

// Rule PersistentEntry
rulePersistentEntry
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getPersistentEntryAccess().getGroup()); }
(rule__PersistentEntry__Group__0)
{ after(grammarAccess.getPersistentEntryAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleQualifiedID
entryRuleQualifiedID 
:
{ before(grammarAccess.getQualifiedIDRule()); }
	 ruleQualifiedID
{ after(grammarAccess.getQualifiedIDRule()); } 
	 EOF 
;

// Rule QualifiedID
ruleQualifiedID
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getQualifiedIDAccess().getGroup()); }
(rule__QualifiedID__Group__0)
{ after(grammarAccess.getQualifiedIDAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRulePropertyValue
entryRulePropertyValue 
:
{ before(grammarAccess.getPropertyValueRule()); }
	 rulePropertyValue
{ after(grammarAccess.getPropertyValueRule()); } 
	 EOF 
;

// Rule PropertyValue
rulePropertyValue
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getPropertyValueAccess().getAlternatives()); }
(rule__PropertyValue__Alternatives)
{ after(grammarAccess.getPropertyValueAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleFloat
entryRuleFloat 
:
{ before(grammarAccess.getFloatRule()); }
	 ruleFloat
{ after(grammarAccess.getFloatRule()); } 
	 EOF 
;

// Rule Float
ruleFloat
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getFloatAccess().getAlternatives()); }
(rule__Float__Alternatives)
{ after(grammarAccess.getFloatAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__PropertyValue__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); }
	RULE_BOOLEAN
{ after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); }
)

    |(
{ before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); }
	RULE_STRING
{ after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); }
)

    |(
{ before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); }
	ruleFloat
{ after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); }
)

    |(
{ before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); }
	ruleQualifiedID
{ after(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); }
	RULE_TFLOAT
{ after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); }
)

    |(
{ before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); }
	RULE_NATURAL
{ after(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__Job__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__0__Impl
	rule__Job__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getJobAction_0()); }
(

)
{ after(grammarAccess.getJobAccess().getJobAction_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__1__Impl
	rule__Job__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getJobKeyword_1()); }

	'job' 

{ after(grammarAccess.getJobAccess().getJobKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__2__Impl
	rule__Job__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getNameAssignment_2()); }
(rule__Job__NameAssignment_2)?
{ after(grammarAccess.getJobAccess().getNameAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__3__Impl
	rule__Job__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); }
(rule__Job__LayoutBeforeAnalysisAssignment_3)?
{ after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__4__Impl
	rule__Job__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getResourcesKeyword_4()); }

	'resources' 

{ after(grammarAccess.getJobAccess().getResourcesKeyword_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__5
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__5__Impl
	rule__Job__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__5__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getResourcesAssignment_5()); }
(rule__Job__ResourcesAssignment_5)*
{ after(grammarAccess.getJobAccess().getResourcesAssignment_5()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__6
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__6__Impl
	rule__Job__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__6__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getLayoutoptionsKeyword_6()); }

	'layoutoptions' 

{ after(grammarAccess.getJobAccess().getLayoutoptionsKeyword_6()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__7
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__7__Impl
	rule__Job__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__7__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getLayoutOptionsAssignment_7()); }
(rule__Job__LayoutOptionsAssignment_7)
{ after(grammarAccess.getJobAccess().getLayoutOptionsAssignment_7()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__8
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__8__Impl
	rule__Job__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__8__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getAnalysesKeyword_8()); }

	'analyses' 

{ after(grammarAccess.getJobAccess().getAnalysesKeyword_8()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__9
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__9__Impl
	rule__Job__Group__10
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__9__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getAnalysesAssignment_9()); }
(rule__Job__AnalysesAssignment_9)*
{ after(grammarAccess.getJobAccess().getAnalysesAssignment_9()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__10
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__10__Impl
	rule__Job__Group__11
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__10__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getOutputKeyword_10()); }

	'output' 

{ after(grammarAccess.getJobAccess().getOutputKeyword_10()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Job__Group__11
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Job__Group__11__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Job__Group__11__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getOutputAssignment_11()); }
(rule__Job__OutputAssignment_11)
{ after(grammarAccess.getJobAccess().getOutputAssignment_11()); }
)

;
finally {
	restoreStackSize(stackSize);
}


























rule__Resource__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Resource__Group__0__Impl
	rule__Resource__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getPathAssignment_0()); }
(rule__Resource__PathAssignment_0)
{ after(grammarAccess.getResourceAccess().getPathAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Resource__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Resource__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getGroup_1()); }
(rule__Resource__Group_1__0)
{ after(grammarAccess.getResourceAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__Resource__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Resource__Group_1__0__Impl
	rule__Resource__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getFilterKeyword_1_0()); }

	'filter' 

{ after(grammarAccess.getResourceAccess().getFilterKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Resource__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Resource__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getFilterAssignment_1_1()); }
(rule__Resource__FilterAssignment_1_1)
{ after(grammarAccess.getResourceAccess().getFilterAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__KIdentifier__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group__0__Impl
	rule__KIdentifier__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); }
(

)
{ after(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__KIdentifier__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group__1__Impl
	rule__KIdentifier__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); }
(rule__KIdentifier__IdAssignment_1)
{ after(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__KIdentifier__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group__2__Impl
	rule__KIdentifier__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); }

	'{' 

{ after(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__KIdentifier__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group__3__Impl
	rule__KIdentifier__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getGroup_3()); }
(rule__KIdentifier__Group_3__0)?
{ after(grammarAccess.getKIdentifierAccess().getGroup_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__KIdentifier__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); }

	'}' 

{ after(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}












rule__KIdentifier__Group_3__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group_3__0__Impl
	rule__KIdentifier__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group_3__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); }
(rule__KIdentifier__PersistentEntriesAssignment_3_0)
{ after(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__KIdentifier__Group_3__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__KIdentifier__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__Group_3__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); }
(rule__KIdentifier__PersistentEntriesAssignment_3_1)*
{ after(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__PersistentEntry__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PersistentEntry__Group__0__Impl
	rule__PersistentEntry__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PersistentEntry__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); }
(rule__PersistentEntry__KeyAssignment_0)
{ after(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PersistentEntry__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PersistentEntry__Group__1__Impl
	rule__PersistentEntry__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PersistentEntry__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); }

	':' 

{ after(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PersistentEntry__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PersistentEntry__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PersistentEntry__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); }
(rule__PersistentEntry__ValueAssignment_2)
{ after(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__QualifiedID__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedID__Group__0__Impl
	rule__QualifiedID__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedID__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); }
	RULE_ID
{ after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedID__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedID__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedID__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedIDAccess().getGroup_1()); }
(rule__QualifiedID__Group_1__0)*
{ after(grammarAccess.getQualifiedIDAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__QualifiedID__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedID__Group_1__0__Impl
	rule__QualifiedID__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedID__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); }

	'.' 

{ after(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedID__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedID__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedID__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); }
	RULE_ID
{ after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}







rule__Grana__JobsAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_0()); }
	ruleJob{ after(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__NameAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); }
	RULE_ID{ after(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__LayoutBeforeAnalysisAssignment_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); }
(
{ before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); }

	'layoutBeforeAnalysis' 

{ after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); }
)

{ after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__ResourcesAssignment_5
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_5_0()); }
	ruleResource{ after(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_5_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__LayoutOptionsAssignment_7
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); }
	ruleKIdentifier{ after(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__AnalysesAssignment_9
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); }
	ruleAnalysis{ after(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Job__OutputAssignment_11
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_11_0()); }
	RULE_STRING{ after(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_11_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__PathAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); }
	RULE_STRING{ after(grammarAccess.getResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Resource__FilterAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); }
	RULE_STRING{ after(grammarAccess.getResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Analysis__NameAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); }
	ruleQualifiedID{ after(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__IdAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); }
	RULE_ID{ after(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__PersistentEntriesAssignment_3_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); }
	rulePersistentEntry{ after(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__KIdentifier__PersistentEntriesAssignment_3_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); }
	rulePersistentEntry{ after(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PersistentEntry__KeyAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); }
	ruleQualifiedID{ after(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PersistentEntry__ValueAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); }
	rulePropertyValue{ after(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_TFLOAT : (('+'|'-') (('0'..'9')+ ('.' ('0'..'9')*)? (('e'|'E') ('+'|'-')? ('0'..'9')+)?|'.' ('0'..'9')+ (('e'|'E') ('+'|'-')? ('0'..'9')+)?)|('0'..'9')+ '.' ('0'..'9')* (('e'|'E') ('+'|'-')? ('0'..'9')+)?|'.' ('0'..'9')+ (('e'|'E') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('e'|'E') ('+'|'-')? ('0'..'9')+);

RULE_NATURAL : ('0'..'9')+;

RULE_BOOLEAN : ('true'|'false');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;


