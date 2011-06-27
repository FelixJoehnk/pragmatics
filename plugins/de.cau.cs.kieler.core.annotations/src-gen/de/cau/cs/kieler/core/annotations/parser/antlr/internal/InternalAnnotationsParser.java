package de.cau.cs.kieler.core.annotations.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import de.cau.cs.kieler.core.annotations.services.AnnotationsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalAnnotationsParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_COMMENT_ANNOTATION", "RULE_BOOLEAN", "RULE_INT", "RULE_FLOAT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'@'", "'('", "')'", "'['", "']'", "'.'"
    };
    public static final int RULE_BOOLEAN=5;
    public static final int RULE_ID=9;
    public static final int RULE_STRING=8;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=6;
    public static final int RULE_WS=12;
    public static final int RULE_FLOAT=7;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_COMMENT_ANNOTATION=4;
    public static final int RULE_ML_COMMENT=10;

        public InternalAnnotationsParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[40+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private AnnotationsGrammarAccess grammarAccess;
     	
        public InternalAnnotationsParser(TokenStream input, IAstFactory factory, AnnotationsGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Annotation";	
       	}
       	
       	@Override
       	protected AnnotationsGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:83:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:84:2: (iv_ruleAnnotation= ruleAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:85:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation81);
            iv_ruleAnnotation=ruleAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation91); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAnnotation


    // $ANTLR start ruleAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:92:1: ruleAnnotation returns [EObject current=null] : (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject this_CommentAnnotation_0 = null;

        EObject this_TagAnnotation_1 = null;

        EObject this_KeyStringValueAnnotation_2 = null;

        EObject this_TypedKeyStringValueAnnotation_3 = null;

        EObject this_KeyBooleanValueAnnotation_4 = null;

        EObject this_KeyIntValueAnnotation_5 = null;

        EObject this_KeyFloatValueAnnotation_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:97:6: ( (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:98:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:98:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            int alt1=7;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:99:2: this_CommentAnnotation_0= ruleCommentAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_ruleAnnotation141);
                    this_CommentAnnotation_0=ruleCommentAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_CommentAnnotation_0; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:112:2: this_TagAnnotation_1= ruleTagAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_ruleAnnotation171);
                    this_TagAnnotation_1=ruleTagAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_TagAnnotation_1; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:125:2: this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation201);
                    this_KeyStringValueAnnotation_2=ruleKeyStringValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyStringValueAnnotation_2; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:138:2: this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTypedKeyStringValueAnnotationParserRuleCall_3(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation231);
                    this_TypedKeyStringValueAnnotation_3=ruleTypedKeyStringValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_TypedKeyStringValueAnnotation_3; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:151:2: this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation261);
                    this_KeyBooleanValueAnnotation_4=ruleKeyBooleanValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyBooleanValueAnnotation_4; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:164:2: this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation291);
                    this_KeyIntValueAnnotation_5=ruleKeyIntValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyIntValueAnnotation_5; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:177:2: this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation321);
                    this_KeyFloatValueAnnotation_6=ruleKeyFloatValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyFloatValueAnnotation_6; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAnnotation


    // $ANTLR start entryRuleCommentAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:196:1: entryRuleCommentAnnotation returns [EObject current=null] : iv_ruleCommentAnnotation= ruleCommentAnnotation EOF ;
    public final EObject entryRuleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommentAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:197:2: (iv_ruleCommentAnnotation= ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:198:2: iv_ruleCommentAnnotation= ruleCommentAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getCommentAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation356);
            iv_ruleCommentAnnotation=ruleCommentAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleCommentAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation366); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCommentAnnotation


    // $ANTLR start ruleCommentAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:205:1: ruleCommentAnnotation returns [EObject current=null] : ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) ;
    public final EObject ruleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:210:6: ( ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:211:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:211:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:212:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:212:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:213:3: lv_value_0_0= RULE_COMMENT_ANNOTATION
            {
            lv_value_0_0=(Token)input.LT(1);
            match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation407); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getCommentAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_0_0, 
              	        		"COMMENT_ANNOTATION", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCommentAnnotation


    // $ANTLR start entryRuleTagAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:243:1: entryRuleTagAnnotation returns [EObject current=null] : iv_ruleTagAnnotation= ruleTagAnnotation EOF ;
    public final EObject entryRuleTagAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTagAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:244:2: (iv_ruleTagAnnotation= ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:245:2: iv_ruleTagAnnotation= ruleTagAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getTagAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation447);
            iv_ruleTagAnnotation=ruleTagAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleTagAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation457); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleTagAnnotation


    // $ANTLR start ruleTagAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:252:1: ruleTagAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleTagAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:257:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:258:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:258:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:258:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleTagAnnotation492); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:262:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:263:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:263:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:264:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTagAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTagAnnotation513);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:286:2: ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:286:4: '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleTagAnnotation524); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:290:1: ( (lv_annotations_3_0= ruleAnnotation ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_COMMENT_ANNOTATION||LA2_0==14) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:291:1: (lv_annotations_3_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:291:1: (lv_annotations_3_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:292:3: lv_annotations_3_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTagAnnotation545);
                    	    lv_annotations_3_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_3_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleTagAnnotation556); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleTagAnnotation


    // $ANTLR start entryRuleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:326:1: entryRuleKeyStringValueAnnotation returns [EObject current=null] : iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF ;
    public final EObject entryRuleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:327:2: (iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:328:2: iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyStringValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation594);
            iv_ruleKeyStringValueAnnotation=ruleKeyStringValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation604); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleKeyStringValueAnnotation


    // $ANTLR start ruleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:335:1: ruleKeyStringValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:340:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:341:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:341:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:341:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleKeyStringValueAnnotation639); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:345:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:346:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:346:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:347:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation660);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:369:2: ( (lv_value_2_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:370:1: (lv_value_2_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:370:1: (lv_value_2_0= ruleEString )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:371:3: lv_value_2_0= ruleEString
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation681);
            lv_value_2_0=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"EString", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:393:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:393:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleKeyStringValueAnnotation692); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:397:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==RULE_COMMENT_ANNOTATION||LA4_0==14) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:398:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:398:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:399:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation713);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleKeyStringValueAnnotation724); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleKeyStringValueAnnotation


    // $ANTLR start entryRuleTypedKeyStringValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:433:1: entryRuleTypedKeyStringValueAnnotation returns [EObject current=null] : iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF ;
    public final EObject entryRuleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:434:2: (iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:435:2: iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation762);
            iv_ruleTypedKeyStringValueAnnotation=ruleTypedKeyStringValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleTypedKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation772); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleTypedKeyStringValueAnnotation


    // $ANTLR start ruleTypedKeyStringValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:442:1: ruleTypedKeyStringValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_type_3_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        EObject lv_annotations_7_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:447:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:448:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:448:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:448:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleTypedKeyStringValueAnnotation807); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:452:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:453:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:453:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:454:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation828);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleTypedKeyStringValueAnnotation838); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftSquareBracketKeyword_2(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:480:1: ( (lv_type_3_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:481:1: (lv_type_3_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:481:1: (lv_type_3_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:482:3: lv_type_3_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeExtendedIDParserRuleCall_3_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation859);
            lv_type_3_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"type",
              	        		lv_type_3_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            match(input,18,FollowSets000.FOLLOW_18_in_ruleTypedKeyStringValueAnnotation869); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightSquareBracketKeyword_4(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:508:1: ( (lv_value_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:509:1: (lv_value_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:509:1: (lv_value_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:510:3: lv_value_5_0= ruleEString
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_5_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation890);
            lv_value_5_0=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_5_0, 
              	        		"EString", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:532:2: ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:532:4: '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleTypedKeyStringValueAnnotation901); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_6_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:536:1: ( (lv_annotations_7_0= ruleAnnotation ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==RULE_COMMENT_ANNOTATION||LA6_0==14) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:537:1: (lv_annotations_7_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:537:1: (lv_annotations_7_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:538:3: lv_annotations_7_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_6_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation922);
                    	    lv_annotations_7_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_7_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleTypedKeyStringValueAnnotation933); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightParenthesisKeyword_6_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleTypedKeyStringValueAnnotation


    // $ANTLR start entryRuleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:572:1: entryRuleKeyBooleanValueAnnotation returns [EObject current=null] : iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF ;
    public final EObject entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyBooleanValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:573:2: (iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:574:2: iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation971);
            iv_ruleKeyBooleanValueAnnotation=ruleKeyBooleanValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyBooleanValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation981); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleKeyBooleanValueAnnotation


    // $ANTLR start ruleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:581:1: ruleKeyBooleanValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:586:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:587:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:587:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:587:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleKeyBooleanValueAnnotation1016); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:591:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:592:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:592:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:593:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation1037);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:615:2: ( (lv_value_2_0= RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:616:1: (lv_value_2_0= RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:616:1: (lv_value_2_0= RULE_BOOLEAN )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:617:3: lv_value_2_0= RULE_BOOLEAN
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation1054); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"Boolean", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:639:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==15) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:639:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleKeyBooleanValueAnnotation1070); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:643:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==RULE_COMMENT_ANNOTATION||LA8_0==14) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:644:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:644:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:645:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation1091);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleKeyBooleanValueAnnotation1102); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleKeyBooleanValueAnnotation


    // $ANTLR start entryRuleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:679:1: entryRuleKeyIntValueAnnotation returns [EObject current=null] : iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF ;
    public final EObject entryRuleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyIntValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:680:2: (iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:681:2: iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyIntValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation1140);
            iv_ruleKeyIntValueAnnotation=ruleKeyIntValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyIntValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation1150); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleKeyIntValueAnnotation


    // $ANTLR start ruleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:688:1: ruleKeyIntValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:693:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:694:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:694:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:694:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleKeyIntValueAnnotation1185); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:698:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:699:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:699:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:700:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation1206);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:722:2: ( (lv_value_2_0= RULE_INT ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:723:1: (lv_value_2_0= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:723:1: (lv_value_2_0= RULE_INT )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:724:3: lv_value_2_0= RULE_INT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation1223); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"INT", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:746:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:746:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleKeyIntValueAnnotation1239); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:750:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==RULE_COMMENT_ANNOTATION||LA10_0==14) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:751:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:751:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:752:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation1260);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleKeyIntValueAnnotation1271); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleKeyIntValueAnnotation


    // $ANTLR start entryRuleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:786:1: entryRuleKeyFloatValueAnnotation returns [EObject current=null] : iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF ;
    public final EObject entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyFloatValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:787:2: (iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:788:2: iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyFloatValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation1309);
            iv_ruleKeyFloatValueAnnotation=ruleKeyFloatValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyFloatValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation1319); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleKeyFloatValueAnnotation


    // $ANTLR start ruleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:795:1: ruleKeyFloatValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:800:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:801:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:801:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:801:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleKeyFloatValueAnnotation1354); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:805:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:806:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:806:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:807:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation1375);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:829:2: ( (lv_value_2_0= RULE_FLOAT ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:830:1: (lv_value_2_0= RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:830:1: (lv_value_2_0= RULE_FLOAT )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:831:3: lv_value_2_0= RULE_FLOAT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation1392); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"Float", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:853:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==15) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:853:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleKeyFloatValueAnnotation1408); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:857:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==RULE_COMMENT_ANNOTATION||LA12_0==14) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:858:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:858:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:859:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation1429);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleKeyFloatValueAnnotation1440); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleKeyFloatValueAnnotation


    // $ANTLR start entryRuleEString
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:895:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:896:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:897:2: iv_ruleEString= ruleEString EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString1481);
            iv_ruleEString=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleEString.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString1492); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:904:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:909:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:910:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:910:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_STRING) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_ID) ) {
                alt14=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("910:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:910:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString1532); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_STRING_0);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:918:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString1558); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_ID_1);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                          
                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
              	    lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEString


    // $ANTLR start entryRuleExtendedID
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:933:1: entryRuleExtendedID returns [String current=null] : iv_ruleExtendedID= ruleExtendedID EOF ;
    public final String entryRuleExtendedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleExtendedID = null;


        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:934:2: (iv_ruleExtendedID= ruleExtendedID EOF )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:935:2: iv_ruleExtendedID= ruleExtendedID EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getExtendedIDRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_entryRuleExtendedID1604);
            iv_ruleExtendedID=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleExtendedID.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExtendedID1615); if (failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExtendedID


    // $ANTLR start ruleExtendedID
    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:942:1: ruleExtendedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleExtendedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:947:6: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:948:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:948:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:948:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID1655); if (failed) return current;
            if ( backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( backtracking==0 ) {
               
                  createLeafNode(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:955:1: (kw= '.' this_ID_2= RULE_ID )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==19) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.annotations/src-gen/de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.g:956:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,19,FollowSets000.FOLLOW_19_in_ruleExtendedID1674); if (failed) return current;
            	    if ( backtracking==0 ) {

            	              current.merge(kw);
            	              createLeafNode(grammarAccess.getExtendedIDAccess().getFullStopKeyword_1_0(), null); 
            	          
            	    }
            	    this_ID_2=(Token)input.LT(1);
            	    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID1689); if (failed) return current;
            	    if ( backtracking==0 ) {

            	      		current.merge(this_ID_2);
            	          
            	    }
            	    if ( backtracking==0 ) {
            	       
            	          createLeafNode(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_1_1(), null); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
              	    lastConsumedNode = currentNode;
                  
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExtendedID


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\14\uffff";
    static final String DFA1_eofS =
        "\3\uffff\1\11\7\uffff\1\11";
    static final String DFA1_minS =
        "\1\4\1\uffff\1\11\1\4\1\11\6\uffff\1\4";
    static final String DFA1_maxS =
        "\1\16\1\uffff\1\11\1\23\1\11\6\uffff\1\23";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\3\uffff\1\3\1\4\1\5\1\7\1\2\1\6\1\uffff";
    static final String DFA1_specialS =
        "\14\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1\11\uffff\1\2",
            "",
            "\1\3",
            "\1\11\1\7\1\12\1\10\2\5\4\uffff\3\11\1\6\1\uffff\1\4",
            "\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\11\1\7\1\12\1\10\2\5\4\uffff\3\11\1\6\1\uffff\1\4"
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "98:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation81 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation91 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_ruleAnnotation141 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_ruleAnnotation171 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation201 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation231 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation291 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation321 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation356 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation366 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation407 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation447 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation457 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleTagAnnotation492 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTagAnnotation513 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleTagAnnotation524 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTagAnnotation545 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleTagAnnotation556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation594 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation604 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleKeyStringValueAnnotation639 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation660 = new BitSet(new long[]{0x0000000000000300L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation681 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleKeyStringValueAnnotation692 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation713 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleKeyStringValueAnnotation724 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation762 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation772 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleTypedKeyStringValueAnnotation807 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation828 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleTypedKeyStringValueAnnotation838 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation859 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleTypedKeyStringValueAnnotation869 = new BitSet(new long[]{0x0000000000000300L});
        public static final BitSet FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation890 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleTypedKeyStringValueAnnotation901 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation922 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleTypedKeyStringValueAnnotation933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation971 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation981 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleKeyBooleanValueAnnotation1016 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation1037 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation1054 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleKeyBooleanValueAnnotation1070 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation1091 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleKeyBooleanValueAnnotation1102 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation1140 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation1150 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleKeyIntValueAnnotation1185 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation1206 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation1223 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleKeyIntValueAnnotation1239 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation1260 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleKeyIntValueAnnotation1271 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation1309 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation1319 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_ruleKeyFloatValueAnnotation1354 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation1375 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation1392 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleKeyFloatValueAnnotation1408 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation1429 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_16_in_ruleKeyFloatValueAnnotation1440 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString1481 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString1492 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString1532 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString1558 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_entryRuleExtendedID1604 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExtendedID1615 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID1655 = new BitSet(new long[]{0x0000000000080002L});
        public static final BitSet FOLLOW_19_in_ruleExtendedID1674 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID1689 = new BitSet(new long[]{0x0000000000080002L});
    }


}