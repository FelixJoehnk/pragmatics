/*
* generated by Xtext
*/
package de.cau.cs.kieler.kiml.formats.gml.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class GMLAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.tokens");
	}
}
