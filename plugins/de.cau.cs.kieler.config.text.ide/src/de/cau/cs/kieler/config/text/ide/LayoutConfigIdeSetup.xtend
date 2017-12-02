/*
 * generated by Xtext 2.12.0
 */
package de.cau.cs.kieler.config.text.ide

import com.google.inject.Guice
import de.cau.cs.kieler.config.text.LayoutConfigRuntimeModule
import de.cau.cs.kieler.config.text.LayoutConfigStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class LayoutConfigIdeSetup extends LayoutConfigStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new LayoutConfigRuntimeModule, new LayoutConfigIdeModule))
	}
	
}
