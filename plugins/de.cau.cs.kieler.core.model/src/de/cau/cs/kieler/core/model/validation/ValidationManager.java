/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.model.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.xtend.typesystem.emf.check.CheckRegistry;

import de.cau.cs.kieler.core.model.CoreModelPlugin;

/**
 * Handler for managing check files and validate actions.
 * 
 * @author soh
 * @kieler.rating 2010-06-11 proposed yellow soh
 */
public final class ValidationManager {

    /**
     * Contains all registered packages. Also contains the default validators.
     */
    private static Map<EPackage, EValidator> packages = new HashMap<EPackage, EValidator>();

    /** Maps the id of a check file to the internal checkfile objects. */
    private static Map<String, CheckFile> checkFiles = new HashMap<String, CheckFile>();

    /** The list of listeners to be notified of visibility changes. */
    private static Set<IPropertyChangeListener> listeners = new HashSet<IPropertyChangeListener>();

    /** Prefix for the preference store. */
    public static final String PREFERENCE_PREFIX = "_Checkfile_";

    /**
     * Hide the default constructor.
     */
    private ValidationManager() {
        // does nothing
    }

    /**
     * Get all registered check files.
     * 
     * @return the set of registered files
     */
    public static Set<String> getRegisteredFiles() {
        return checkFiles.keySet();
    }

    /**
     * Get all files registered for a specific ePackage.
     * 
     * @param ePackage
     *            the package
     * @return the files
     */
    public static Set<String> getRegisteredFiles(final EPackage ePackage) {
        Set<String> result = new HashSet<String>();
        for (CheckFile file : checkFiles.values()) {
            if (file.ePackage == ePackage) {
                result.add(file.file);
            }
        }
        return result;
    }

    /**
     * Get the Epackage of the file.
     * 
     * @param id
     *            the file
     * @return the package
     */
    public static EPackage getEPackage(final String id) {
        EPackage result = null;
        CheckFile checkFile = checkFiles.get(id);
        if (checkFile != null) {
            result = checkFile.ePackage;
        }
        return result;
    }

    // /**
    // * Get the ePackage for the currently active editor.
    // *
    // * @return the package
    // */
    // public static EPackage getEPackageOfActiveEditor() {
    // EPackage ePackage = null;
    //
    // IEditorPart editorPart = EditorUtils.getLastActiveEditor();
    // if (editorPart != null) {
    // EObject eObj = null;
    // if (editorPart instanceof DiagramEditor) {
    // DiagramEditor diagEd = (DiagramEditor) editorPart;
    // Object obj = diagEd.getDiagramEditPart().getModel();
    // if (obj != null && obj instanceof View) {
    // eObj = ((View) obj).getElement();
    // }
    // } else if (editorPart instanceof XtextEditor) {
    // XtextEditor xEd = (XtextEditor) editorPart;
    // eObj = ModelingUtil.getModelFromXtextEditor(xEd);
    // } else {
    // // now we have to ask the extension point for a suitable class
    // IModelDiagramInterface modelDiagramInterface =
    // ValidationInformationCollector
    // .getModelDiagramInterface(editorPart.getClass()
    // .getName());
    // if (modelDiagramInterface != null) {
    // eObj = modelDiagramInterface.getModel(editorPart);
    // } else {
    // // FIXME: Ignored for now ... fix this! E.g., when changing
    // // the editor to a
    // // supported one, partOpened is fired BUT the active editor
    // // is still the old
    // // one!
    // // String message =
    // // "Cannot find validation extension point definition for editor "
    // // + editorPart.getClass().getName();
    // // throw new RuntimeException(message);
    // }
    // }
    // if (eObj != null) {
    // ePackage = eObj.eClass().getEPackage();
    // }
    // }
    // return ePackage;
    // }

    /**
     * Determine whether or not a file is enabled.
     * 
     * @param id
     *            the file
     * @return true if enabled, false if not, null if unknown.
     */
    public static Boolean isEnabled(final String id) {
        CheckFile check = checkFiles.get(id);
        if (check == null) {
            return null;
        }
        return check.isEnabled();
    }

    /**
     * Show all checks.
     */
    public static void enableAll() {
        setAllEnabled(true);
    }

    /**
     * Hide all checks.
     */
    public static void disableAll() {
        setAllEnabled(false);
    }

    /**
     * Show or hide checks.
     * 
     * @param enabled
     *            true if checks should be shown
     */
    private static void setAllEnabled(final boolean enabled) {
        for (CheckFile file : checkFiles.values()) {
            file.setEnabled(enabled);
        }
        refreshChecks();
    }

    /**
     * Register a new check file.
     * 
     * @param id
     *            the id of the check file
     * @param ePackage
     *            the package
     * @param file
     *            the file
     * @param isWrapExistingValidator
     *            True if the checkfile wraps around another checkfile and thus
     *            has to be added after it.
     * @param referencedEPackageNsURIs
     *            ???
     * @param name
     *            the name to display
     * @param tooltip
     *            the tooltip to display
     */
    public static void registerCheckFile(final String id,
            final EPackage ePackage, final String file,
            final boolean isWrapExistingValidator,
            final List<String> referencedEPackageNsURIs, final String name,
            final String tooltip, final boolean isEnabledByDefault) {
        if (!packages.containsKey(ePackage)) {
            packages.put(ePackage,
                    EValidator.Registry.INSTANCE.getEValidator(ePackage));
        }

        // if nothing can be found user the extension point setting to
        // determine whether the validation is enabled or not
        // --> isEnabledByDefault

        // determine whether or the file should be allowed to show its markers
        IPreferenceStore store = CoreModelPlugin.getDefault()
                .getPreferenceStore();
        boolean value = true;
        String key = PREFERENCE_PREFIX + id;
        if (store.contains(key)) {
            // try the preference store
            value = store.getBoolean(key);
        } else {
            // if value not found try accessing the persistent memory on disc
            IEclipsePreferences prefs = new InstanceScope()
                    .getNode(CoreModelPlugin.PLUGIN_ID);
            value = prefs.getBoolean(key, isEnabledByDefault);
            store.setValue(key, value);
        }

        CheckFile checkFile = new CheckFile();
        checkFiles.put(id, checkFile);

        checkFile.id = id;
        checkFile.ePackage = ePackage;
        checkFile.file = file;
        checkFile.isWrapExistingValidator = isWrapExistingValidator;
        if (referencedEPackageNsURIs != null) {
            checkFile.referencedEPackageNsURIs = referencedEPackageNsURIs;
        } else {
            checkFile.referencedEPackageNsURIs = new LinkedList<String>();
        }
        checkFile.name = name;
        checkFile.tooltip = tooltip;

        register(checkFile);
        checkFile.setEnabled(value);
        refreshChecks();
    }

    /**
     * Get the tooltip for the file.
     * 
     * @param id
     *            the file
     * @return the tooltip
     */
    public static String getTooltip(final String id) {
        CheckFile checkfile = checkFiles.get(id);
        if (checkfile != null) {
            return checkfile.tooltip;
        }
        return "File not found";
    }

    /**
     * Get the displayed name for a given checkfile.
     * 
     * @param id
     *            the id of the checkfile
     * @return the displayed name
     */
    public static String getDisplay(final String id) {
        CheckFile checkfile = checkFiles.get(id);
        if (checkfile != null) {
            return checkfile.name;
        }
        return "File not found";
    }

    /**
     * Run the validate action of the currently active editor.
     */
    public static void validateActiveEditor() {
        // EPackage ePackage = getEPackageOfActiveEditor();
        ValidationInformationCollector.validateActiveEditor();
    }

    /**
     * Register a new check file.
     * 
     * @param checkFile
     *            the file
     */
    private static void register(final CheckFile checkFile) {
        if (checkFile.isEnabled()) {
            CheckRegistry.getInstance().registerCheckFile(checkFile.ePackage,
                    checkFile.file, checkFile.isWrapExistingValidator,
                    checkFile.referencedEPackageNsURIs);
        }
    }

    /**
     * Deregisters all checkfiles on all editors editor.
     */
    public static void deregisterChecks() {
        for (EPackage ePackage : packages.keySet()) {
            removeChecksOfPackage(ePackage);
        }
    }

    /**
     * Remove a checkfile from the list.
     * 
     * @param id
     *            the id
     */
    public static void removeCheck(final String id) {
        CheckFile checkFile = checkFiles.remove(id);
        if (checkFile != null) {
            removeChecksOfPackage(checkFile.ePackage);
            restoreChecks(checkFile.ePackage);
        }
    }

    /**
     * Remove the checks for a given package retaining the default checks.
     * 
     * @param ePackage
     *            the package
     */
    private static void removeChecksOfPackage(final EPackage ePackage) {
        EValidator.Registry registry = EValidator.Registry.INSTANCE;

        registry.remove(ePackage);
        registry.put(ePackage, packages.get(ePackage));
    }

    /**
     * Restore the checks for a certain epackage.
     * 
     * @param ePackage
     *            the package.
     */
    public static void restoreChecks(final EPackage ePackage) {
        // make sure all check files that don't wrap around an
        // existing check file are added first. Otherwise
        // the wrapping checks can't be added
        for (CheckFile file : checkFiles.values()) {
            if ((ePackage == null || file.ePackage == ePackage)
                    && !file.isWrapExistingValidator) {
                register(file);
            }
        }
        for (CheckFile file : checkFiles.values()) {
            if ((ePackage == null || file.ePackage == ePackage)
                    && file.isWrapExistingValidator) {
                register(file);
            }
        }
    }

    /**
     * Restore all checks.
     */
    public static void restoreChecks() {
        restoreChecks(null);
    }

    /**
     * Refreshes all checks by deregistering all of them and registering them
     * again.
     */
    public static void refreshChecks() {
        deregisterChecks();
        restoreChecks();
    }

    /**
     * Enable the given check.
     * 
     * @param id
     *            the file
     */
    public static void enableCheck(final String id) {
        setEnabled(id, true);
    }

    /**
     * Disable a check file.
     * 
     * @param id
     *            the file
     */
    public static void disableCheck(final String id) {
        setEnabled(id, false);
    }

    /**
     * Set the enablement of a file.
     * 
     * @param id
     *            the file
     * @param enabled
     *            true if visible
     */
    public static void setEnabled(final String id, final boolean enabled) {
        CheckFile check = checkFiles.get(id);
        if (check != null) {
            check.setEnabled(enabled);
            refreshChecks();
        }
    }

    /**
     * Notify listeners of a property change.
     * 
     * @param id
     *            the file
     * @param oldValue
     *            the old value
     * @param newValue
     *            the new value
     */
    private static void firePropertyChangedEvent(final String id,
            final boolean oldValue, final boolean newValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(
                CoreModelPlugin.PLUGIN_ID, PREFERENCE_PREFIX + id, oldValue,
                newValue);

        for (IPropertyChangeListener listener : listeners) {
            listener.propertyChange(event);
        }
    }

    /**
     * Registers a new listener on the manager.
     * 
     * @param listener
     *            the listener
     */
    public static void addListener(final IPropertyChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the manager.
     * 
     * @param listener
     *            the listener
     */
    public static void removeListener(final IPropertyChangeListener listener) {
        listeners.remove(listener);
    }

    /**
     * Determine whether there is a validate action present for the given
     * editor.
     * 
     * @return true if there is a validate action
     */
    public static boolean hasValidateActionsForActiveEditor() {
        return ValidationInformationCollector
                .hasValidateActionForActiveEditor();
    }

    /**
     * Stores all the information about a checkfile.
     * 
     * @author soh
     * @kieler.rating 2010-06-11 proposed yellow soh
     */
    private static final class CheckFile {

        /**
         * 
         * Does nothing.
         * 
         */
        private CheckFile() {
            // does nothing
        }

        /**
         * Set the visibility of the file.
         * 
         * @param enabledParam
         *            true if it should be visible
         */
        public void setEnabled(final boolean enabledParam) {
            String pref = PREFERENCE_PREFIX + id;
            boolean oldValue = enabled;
            enabled = enabledParam;
            new InstanceScope().getNode(CoreModelPlugin.PLUGIN_ID).putBoolean(
                    pref, enabled);
            IPreferenceStore store = CoreModelPlugin.getDefault()
                    .getPreferenceStore();
            store.setValue(pref, enabled);
            firePropertyChangedEvent(file, oldValue, enabled);
        }

        /**
         * Getter.
         * 
         * @return true if the file is enabled
         */
        public boolean isEnabled() {
            return enabled;
        }

        /** The package. */
        private EPackage ePackage;

        /** The path to the file. */
        private String file;

        /**
         * True if the checkfile wraps around another checkfile and thus has to
         * be added after it.
         */
        private boolean isWrapExistingValidator;

        /** ???. */
        private List<String> referencedEPackageNsURIs;

        /** A tooltip for display. */
        private String tooltip;

        /** True if the file should be visible. */
        private boolean enabled = true;

        /** The displayed name of the file. */
        private String name;

        /** The identifier for the file. */
        private String id;
    }

    /**
     * Determine whether a file should be visible.
     * 
     * @param id
     *            the id of the file
     * @return true if it should be visible
     */
    public static boolean isVisible(final String id) {
        return ValidationInformationCollector.isVisible(id);
    }
}
