lexer grammar InternalAnnotations;
@header {
package de.cau.cs.kieler.core.annotations.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T14 : '@' ;

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 951
RULE_COMMENT_ANNOTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 953
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 955
RULE_INT : '-'? ('0'..'9')+;

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 957
RULE_FLOAT : ('-'? ('0'..'9')+ '.' ('0'..'9')* (('e'|'E') ('+'|'-')? ('0'..'9')+)? 'f'?|'-'? ('0'..'9')+ 'f');

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 959
RULE_BOOLEAN : ('true'|'false');

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 961
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 963
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 965
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 967
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.cau.cs.kieler.core.annotations.ui/src-gen/de/cau/cs/kieler/core/annotations/ui/contentassist/antlr/internal/InternalAnnotations.g" 969
RULE_ANY_OTHER : .;


