/*
* generated by Xtext
*/

package de.cau.cs.kieler.core.annotations.text.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class AnnotationsGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class AnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Annotation");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cCommentAnnotationParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cTagAnnotationParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cKeyStringValueAnnotationParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cTypedKeyStringValueAnnotationParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cKeyBooleanValueAnnotationParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cKeyIntValueAnnotationParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cKeyFloatValueAnnotationParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);
		
		//// --------------------------
		////
		////   ANNOTATIONS
		////
		//// --------------------------
		//Annotation:
		//	CommentAnnotation | TagAnnotation | KeyStringValueAnnotation | TypedKeyStringValueAnnotation |
		//	KeyBooleanValueAnnotation | KeyIntValueAnnotation | KeyFloatValueAnnotation;
		public ParserRule getRule() { return rule; }

		//CommentAnnotation | TagAnnotation | KeyStringValueAnnotation | TypedKeyStringValueAnnotation | KeyBooleanValueAnnotation
		//| KeyIntValueAnnotation | KeyFloatValueAnnotation
		public Alternatives getAlternatives() { return cAlternatives; }

		//CommentAnnotation
		public RuleCall getCommentAnnotationParserRuleCall_0() { return cCommentAnnotationParserRuleCall_0; }

		//TagAnnotation
		public RuleCall getTagAnnotationParserRuleCall_1() { return cTagAnnotationParserRuleCall_1; }

		//KeyStringValueAnnotation
		public RuleCall getKeyStringValueAnnotationParserRuleCall_2() { return cKeyStringValueAnnotationParserRuleCall_2; }

		//TypedKeyStringValueAnnotation
		public RuleCall getTypedKeyStringValueAnnotationParserRuleCall_3() { return cTypedKeyStringValueAnnotationParserRuleCall_3; }

		//KeyBooleanValueAnnotation
		public RuleCall getKeyBooleanValueAnnotationParserRuleCall_4() { return cKeyBooleanValueAnnotationParserRuleCall_4; }

		//KeyIntValueAnnotation
		public RuleCall getKeyIntValueAnnotationParserRuleCall_5() { return cKeyIntValueAnnotationParserRuleCall_5; }

		//KeyFloatValueAnnotation
		public RuleCall getKeyFloatValueAnnotationParserRuleCall_6() { return cKeyFloatValueAnnotationParserRuleCall_6; }
	}

	public class CommentAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "CommentAnnotation");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cValueCOMMENT_ANNOTATIONTerminalRuleCall_0 = (RuleCall)cValueAssignment.eContents().get(0);
		
		//// e.g.: / ** semantic comment * /
		//CommentAnnotation returns StringAnnotation:
		//	value=COMMENT_ANNOTATION;
		public ParserRule getRule() { return rule; }

		//value=COMMENT_ANNOTATION
		public Assignment getValueAssignment() { return cValueAssignment; }

		//COMMENT_ANNOTATION
		public RuleCall getValueCOMMENT_ANNOTATIONTerminalRuleCall_0() { return cValueCOMMENT_ANNOTATIONTerminalRuleCall_0; }
	}

	public class TagAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "TagAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cLeftParenthesisKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cAnnotationsAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_2_1_0 = (RuleCall)cAnnotationsAssignment_2_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_2_2 = (Keyword)cGroup_2.eContents().get(2);
		
		//// e.g.: @HVlayout
		//TagAnnotation returns Annotation:
		//	"@" name=ExtendedID ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_2() { return cGroup_2; }

		//"("
		public Keyword getLeftParenthesisKeyword_2_0() { return cLeftParenthesisKeyword_2_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_2_1() { return cAnnotationsAssignment_2_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_2_1_0() { return cAnnotationsAnnotationParserRuleCall_2_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_2_2() { return cRightParenthesisKeyword_2_2; }
	}

	public class KeyStringValueAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KeyStringValueAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueEStringParserRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cAnnotationsAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_3_1_0 = (RuleCall)cAnnotationsAssignment_3_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3_2 = (Keyword)cGroup_3.eContents().get(2);
		
		//// e.g.: @layouter dot;   
		//KeyStringValueAnnotation returns StringAnnotation:
		//	"@" name=ExtendedID value=EString ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID value=EString ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//value=EString
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		//EString
		public RuleCall getValueEStringParserRuleCall_2_0() { return cValueEStringParserRuleCall_2_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_3() { return cGroup_3; }

		//"("
		public Keyword getLeftParenthesisKeyword_3_0() { return cLeftParenthesisKeyword_3_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_3_1() { return cAnnotationsAssignment_3_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_3_1_0() { return cAnnotationsAnnotationParserRuleCall_3_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3_2() { return cRightParenthesisKeyword_3_2; }
	}

	public class TypedKeyStringValueAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "TypedKeyStringValueAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cTypeAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cTypeExtendedIDParserRuleCall_3_0 = (RuleCall)cTypeAssignment_3.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cValueAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cValueEStringParserRuleCall_5_0 = (RuleCall)cValueAssignment_5.eContents().get(0);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cLeftParenthesisKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cAnnotationsAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_6_1_0 = (RuleCall)cAnnotationsAssignment_6_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_6_2 = (Keyword)cGroup_6.eContents().get(2);
		
		//// e.g.: @position[de.cau.cs.kieler.core.math.KVector] "(3,2)"
		//TypedKeyStringValueAnnotation returns TypedStringAnnotation:
		//	"@" name=ExtendedID "[" type=ExtendedID "]" value=EString ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID "[" type=ExtendedID "]" value=EString ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//"["
		public Keyword getLeftSquareBracketKeyword_2() { return cLeftSquareBracketKeyword_2; }

		//type=ExtendedID
		public Assignment getTypeAssignment_3() { return cTypeAssignment_3; }

		//ExtendedID
		public RuleCall getTypeExtendedIDParserRuleCall_3_0() { return cTypeExtendedIDParserRuleCall_3_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_4() { return cRightSquareBracketKeyword_4; }

		//value=EString
		public Assignment getValueAssignment_5() { return cValueAssignment_5; }

		//EString
		public RuleCall getValueEStringParserRuleCall_5_0() { return cValueEStringParserRuleCall_5_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_6() { return cGroup_6; }

		//"("
		public Keyword getLeftParenthesisKeyword_6_0() { return cLeftParenthesisKeyword_6_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_6_1() { return cAnnotationsAssignment_6_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_6_1_0() { return cAnnotationsAnnotationParserRuleCall_6_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_6_2() { return cRightParenthesisKeyword_6_2; }
	}

	public class KeyBooleanValueAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KeyBooleanValueAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueBooleanTerminalRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cAnnotationsAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_3_1_0 = (RuleCall)cAnnotationsAssignment_3_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3_2 = (Keyword)cGroup_3.eContents().get(2);
		
		//// e.g.: @visible true;
		//KeyBooleanValueAnnotation returns BooleanAnnotation:
		//	"@" name=ExtendedID value=Boolean ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID value=Boolean ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//value=Boolean
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		//Boolean
		public RuleCall getValueBooleanTerminalRuleCall_2_0() { return cValueBooleanTerminalRuleCall_2_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_3() { return cGroup_3; }

		//"("
		public Keyword getLeftParenthesisKeyword_3_0() { return cLeftParenthesisKeyword_3_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_3_1() { return cAnnotationsAssignment_3_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_3_1_0() { return cAnnotationsAnnotationParserRuleCall_3_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3_2() { return cRightParenthesisKeyword_3_2; }
	}

	public class KeyIntValueAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KeyIntValueAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueINTTerminalRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cAnnotationsAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_3_1_0 = (RuleCall)cAnnotationsAssignment_3_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3_2 = (Keyword)cGroup_3.eContents().get(2);
		
		//// e.g.: @minSpace 10;    
		//KeyIntValueAnnotation returns IntAnnotation:
		//	"@" name=ExtendedID value=INT ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID value=INT ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//value=INT
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		//INT
		public RuleCall getValueINTTerminalRuleCall_2_0() { return cValueINTTerminalRuleCall_2_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_3() { return cGroup_3; }

		//"("
		public Keyword getLeftParenthesisKeyword_3_0() { return cLeftParenthesisKeyword_3_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_3_1() { return cAnnotationsAssignment_3_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_3_1_0() { return cAnnotationsAnnotationParserRuleCall_3_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3_2() { return cRightParenthesisKeyword_3_2; }
	}

	public class KeyFloatValueAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KeyFloatValueAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameExtendedIDParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueFloatTerminalRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cAnnotationsAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cAnnotationsAnnotationParserRuleCall_3_1_0 = (RuleCall)cAnnotationsAssignment_3_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3_2 = (Keyword)cGroup_3.eContents().get(2);
		
		//// e.g.: @minSpace 10.0;    
		//KeyFloatValueAnnotation returns FloatAnnotation:
		//	"@" name=ExtendedID value=Float ("(" annotations+=Annotation* ")")?;
		public ParserRule getRule() { return rule; }

		//"@" name=ExtendedID value=Float ("(" annotations+=Annotation* ")")?
		public Group getGroup() { return cGroup; }

		//"@"
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }

		//name=ExtendedID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ExtendedID
		public RuleCall getNameExtendedIDParserRuleCall_1_0() { return cNameExtendedIDParserRuleCall_1_0; }

		//value=Float
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		//Float
		public RuleCall getValueFloatTerminalRuleCall_2_0() { return cValueFloatTerminalRuleCall_2_0; }

		//("(" annotations+=Annotation* ")")?
		public Group getGroup_3() { return cGroup_3; }

		//"("
		public Keyword getLeftParenthesisKeyword_3_0() { return cLeftParenthesisKeyword_3_0; }

		//annotations+=Annotation*
		public Assignment getAnnotationsAssignment_3_1() { return cAnnotationsAssignment_3_1; }

		//Annotation
		public RuleCall getAnnotationsAnnotationParserRuleCall_3_1_0() { return cAnnotationsAnnotationParserRuleCall_3_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3_2() { return cRightParenthesisKeyword_3_2; }
	}

	public class ImportAnnotationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ImportAnnotation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cImportKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cImportURIAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cImportURISTRINGTerminalRuleCall_1_0 = (RuleCall)cImportURIAssignment_1.eContents().get(0);
		
		//// needed for importing other resources
		//ImportAnnotation:
		//	"import" importURI=STRING;
		public ParserRule getRule() { return rule; }

		//"import" importURI=STRING
		public Group getGroup() { return cGroup; }

		//"import"
		public Keyword getImportKeyword_0() { return cImportKeyword_0; }

		//importURI=STRING
		public Assignment getImportURIAssignment_1() { return cImportURIAssignment_1; }

		//STRING
		public RuleCall getImportURISTRINGTerminalRuleCall_1_0() { return cImportURISTRINGTerminalRuleCall_1_0; }
	}

	public class EStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "EString");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cSTRINGTerminalRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//// allow strings without quotes as they don'c contain spaces
		//EString returns ecore::EString:
		//	STRING | ID;
		public ParserRule getRule() { return rule; }

		//STRING | ID
		public Alternatives getAlternatives() { return cAlternatives; }

		//STRING
		public RuleCall getSTRINGTerminalRuleCall_0() { return cSTRINGTerminalRuleCall_0; }

		//ID
		public RuleCall getIDTerminalRuleCall_1() { return cIDTerminalRuleCall_1; }
	}

	public class ExtendedIDElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ExtendedID");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//ExtendedID returns ecore::EString:
		//	ID ("." ID)*;
		public ParserRule getRule() { return rule; }

		//ID ("." ID)*
		public Group getGroup() { return cGroup; }

		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }

		//("." ID)*
		public Group getGroup_1() { return cGroup_1; }

		//"."
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }

		//ID
		public RuleCall getIDTerminalRuleCall_1_1() { return cIDTerminalRuleCall_1_1; }
	}
	
	
	private AnnotationElements pAnnotation;
	private CommentAnnotationElements pCommentAnnotation;
	private TagAnnotationElements pTagAnnotation;
	private KeyStringValueAnnotationElements pKeyStringValueAnnotation;
	private TypedKeyStringValueAnnotationElements pTypedKeyStringValueAnnotation;
	private KeyBooleanValueAnnotationElements pKeyBooleanValueAnnotation;
	private KeyIntValueAnnotationElements pKeyIntValueAnnotation;
	private KeyFloatValueAnnotationElements pKeyFloatValueAnnotation;
	private ImportAnnotationElements pImportAnnotation;
	private EStringElements pEString;
	private ExtendedIDElements pExtendedID;
	private TerminalRule tCOMMENT_ANNOTATION;
	private TerminalRule tML_COMMENT;
	private TerminalRule tINT;
	private TerminalRule tFloat;
	private TerminalRule tBoolean;
	private TerminalRule tSTRING;
	
	private final GrammarProvider grammarProvider;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public AnnotationsGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammarProvider = grammarProvider;
		this.gaTerminals = gaTerminals;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//// --------------------------
	////
	////   ANNOTATIONS
	////
	//// --------------------------
	//Annotation:
	//	CommentAnnotation | TagAnnotation | KeyStringValueAnnotation | TypedKeyStringValueAnnotation |
	//	KeyBooleanValueAnnotation | KeyIntValueAnnotation | KeyFloatValueAnnotation;
	public AnnotationElements getAnnotationAccess() {
		return (pAnnotation != null) ? pAnnotation : (pAnnotation = new AnnotationElements());
	}
	
	public ParserRule getAnnotationRule() {
		return getAnnotationAccess().getRule();
	}

	//// e.g.: / ** semantic comment * /
	//CommentAnnotation returns StringAnnotation:
	//	value=COMMENT_ANNOTATION;
	public CommentAnnotationElements getCommentAnnotationAccess() {
		return (pCommentAnnotation != null) ? pCommentAnnotation : (pCommentAnnotation = new CommentAnnotationElements());
	}
	
	public ParserRule getCommentAnnotationRule() {
		return getCommentAnnotationAccess().getRule();
	}

	//// e.g.: @HVlayout
	//TagAnnotation returns Annotation:
	//	"@" name=ExtendedID ("(" annotations+=Annotation* ")")?;
	public TagAnnotationElements getTagAnnotationAccess() {
		return (pTagAnnotation != null) ? pTagAnnotation : (pTagAnnotation = new TagAnnotationElements());
	}
	
	public ParserRule getTagAnnotationRule() {
		return getTagAnnotationAccess().getRule();
	}

	//// e.g.: @layouter dot;   
	//KeyStringValueAnnotation returns StringAnnotation:
	//	"@" name=ExtendedID value=EString ("(" annotations+=Annotation* ")")?;
	public KeyStringValueAnnotationElements getKeyStringValueAnnotationAccess() {
		return (pKeyStringValueAnnotation != null) ? pKeyStringValueAnnotation : (pKeyStringValueAnnotation = new KeyStringValueAnnotationElements());
	}
	
	public ParserRule getKeyStringValueAnnotationRule() {
		return getKeyStringValueAnnotationAccess().getRule();
	}

	//// e.g.: @position[de.cau.cs.kieler.core.math.KVector] "(3,2)"
	//TypedKeyStringValueAnnotation returns TypedStringAnnotation:
	//	"@" name=ExtendedID "[" type=ExtendedID "]" value=EString ("(" annotations+=Annotation* ")")?;
	public TypedKeyStringValueAnnotationElements getTypedKeyStringValueAnnotationAccess() {
		return (pTypedKeyStringValueAnnotation != null) ? pTypedKeyStringValueAnnotation : (pTypedKeyStringValueAnnotation = new TypedKeyStringValueAnnotationElements());
	}
	
	public ParserRule getTypedKeyStringValueAnnotationRule() {
		return getTypedKeyStringValueAnnotationAccess().getRule();
	}

	//// e.g.: @visible true;
	//KeyBooleanValueAnnotation returns BooleanAnnotation:
	//	"@" name=ExtendedID value=Boolean ("(" annotations+=Annotation* ")")?;
	public KeyBooleanValueAnnotationElements getKeyBooleanValueAnnotationAccess() {
		return (pKeyBooleanValueAnnotation != null) ? pKeyBooleanValueAnnotation : (pKeyBooleanValueAnnotation = new KeyBooleanValueAnnotationElements());
	}
	
	public ParserRule getKeyBooleanValueAnnotationRule() {
		return getKeyBooleanValueAnnotationAccess().getRule();
	}

	//// e.g.: @minSpace 10;    
	//KeyIntValueAnnotation returns IntAnnotation:
	//	"@" name=ExtendedID value=INT ("(" annotations+=Annotation* ")")?;
	public KeyIntValueAnnotationElements getKeyIntValueAnnotationAccess() {
		return (pKeyIntValueAnnotation != null) ? pKeyIntValueAnnotation : (pKeyIntValueAnnotation = new KeyIntValueAnnotationElements());
	}
	
	public ParserRule getKeyIntValueAnnotationRule() {
		return getKeyIntValueAnnotationAccess().getRule();
	}

	//// e.g.: @minSpace 10.0;    
	//KeyFloatValueAnnotation returns FloatAnnotation:
	//	"@" name=ExtendedID value=Float ("(" annotations+=Annotation* ")")?;
	public KeyFloatValueAnnotationElements getKeyFloatValueAnnotationAccess() {
		return (pKeyFloatValueAnnotation != null) ? pKeyFloatValueAnnotation : (pKeyFloatValueAnnotation = new KeyFloatValueAnnotationElements());
	}
	
	public ParserRule getKeyFloatValueAnnotationRule() {
		return getKeyFloatValueAnnotationAccess().getRule();
	}

	//// needed for importing other resources
	//ImportAnnotation:
	//	"import" importURI=STRING;
	public ImportAnnotationElements getImportAnnotationAccess() {
		return (pImportAnnotation != null) ? pImportAnnotation : (pImportAnnotation = new ImportAnnotationElements());
	}
	
	public ParserRule getImportAnnotationRule() {
		return getImportAnnotationAccess().getRule();
	}

	//// allow strings without quotes as they don'c contain spaces
	//EString returns ecore::EString:
	//	STRING | ID;
	public EStringElements getEStringAccess() {
		return (pEString != null) ? pEString : (pEString = new EStringElements());
	}
	
	public ParserRule getEStringRule() {
		return getEStringAccess().getRule();
	}

	//ExtendedID returns ecore::EString:
	//	ID ("." ID)*;
	public ExtendedIDElements getExtendedIDAccess() {
		return (pExtendedID != null) ? pExtendedID : (pExtendedID = new ExtendedIDElements());
	}
	
	public ParserRule getExtendedIDRule() {
		return getExtendedIDAccess().getRule();
	}

	//// --------------------------
	////
	////  Terminals...
	////
	//// --------------------------
	//// custom terminals
	//// custom terminal rule introducing semantic comments
	//terminal COMMENT_ANNOTATION:
	//	"/ **"->"* /";
	public TerminalRule getCOMMENT_ANNOTATIONRule() {
		return (tCOMMENT_ANNOTATION != null) ? tCOMMENT_ANNOTATION : (tCOMMENT_ANNOTATION = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "COMMENT_ANNOTATION"));
	} 

	//// modified version of Terminals.ML_COMMENT as
	//// COMMENT_ANNOTATION is not recognized correctly with original one 
	//terminal ML_COMMENT:
	//	"/ *" !"*"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return (tML_COMMENT != null) ? tML_COMMENT : (tML_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ML_COMMENT"));
	} 

	//// generic terminals
	//// redefine INT terminal to allow negative numbers
	//terminal INT returns ecore::EInt:
	//	"-"? "0".."9"+;
	public TerminalRule getINTRule() {
		return (tINT != null) ? tINT : (tINT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "INT"));
	} 

	//// make sure the Float rule does not shadow the INT rule
	//terminal Float returns ecore::EFloatObject:
	//	"-"? "0".."9"+ ("." "0".."9"*) (("e" | "E") ("+" | "-")? "0".."9"+)? "f"? | "-"? "0".."9"+ "f";
	public TerminalRule getFloatRule() {
		return (tFloat != null) ? tFloat : (tFloat = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "Float"));
	} 

	//// introduce boolean values
	//terminal Boolean returns ecore::EBooleanObject:
	//	"true" | "false";
	public TerminalRule getBooleanRule() {
		return (tBoolean != null) ? tBoolean : (tBoolean = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "Boolean"));
	} 

	//// custom terminal rule for strings
	//// type identifiers can reference Java classes
	////terminal TypeId returns ecore::EString:
	////    '[' ('a'..'z'|'A'..'Z'|'_'|'.') ('a'..'z'|'A'..'Z'|'_'|'.'|'0'..'9')* ']';
	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"";
	public TerminalRule getSTRINGRule() {
		return (tSTRING != null) ? tSTRING : (tSTRING = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "STRING"));
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
