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
 * 
 */
package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.model.Category;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.ExampleResource.Type;
import de.cau.cs.kieler.kex.model.SourceType;
import de.cau.cs.kieler.kex.model.plugin.PluginExampleCreator;

/**
 * A collection of example export functions.
 * 
 * @author pkl
 * 
 */
public final class ExampleExport {

    private static final String PROJECT_CLASS = "org.eclipse.core.internal.resources.Project";
    private static final String FOLDER_CLASS = "org.eclipse.core.internal.resources.Folder";
    private static final String FILE_CLASS = "org.eclipse.core.internal.resources.File";
    private static final int EXAMPLE_TITLE_MIN = 4;
    private static final int AUTHOR_MIN = 3;
    private static final int DESCRIPTION_MIN = 10;
    private static final int CONTACT_MIN = 5;

    private ExampleExport() {
        // should not called
    }

    /**
     * Method for validating given map elements. This contains minimumlengths- and duplicate-checks.
     * 
     * @param map
     *            , Map of {@link ExampleElement} and an arbitrary {@link Object}.
     * @param collectors
     *            , {@link ExampleCollector}s
     */
    @SuppressWarnings("unchecked")
    public static void validate(final Map<ExampleElement, Object> map,
            final ExampleCollector... collectors) {
        checkAttributes(map);

        Object sourceType = map.get(ExampleElement.SOURCETYPE);
        if (!(sourceType instanceof SourceType)) {
            throw new RuntimeException("No source type has been defined.");
        }

        String destLocation = (String) map.get(ExampleElement.DEST_LOCATION);
        validateField(destLocation, 2, "Destination Location");

        // TODO ueber validator nachdenken
        // List<String> categories = (List<String>) map.get(ExampleElement.CATEGORY);
        // validateElement(categories, 1, "Categories");

        List<ExportResource> exportedResources = (List<ExportResource>) map
                .get(ExampleElement.RESOURCES);
        validateElement(exportedResources, 1, "Exported Resources");

        // first example duplicate check
        ExampleExport.checkDuplicate((String) map.get(ExampleElement.ID), collectors);

    }

    private static void checkAttributes(final Map<ExampleElement, Object> map) {
        String exampleTitle = (String) map.get(ExampleElement.TITLE);
        validateField(exampleTitle, EXAMPLE_TITLE_MIN, "Example Title");

        String author = (String) map.get(ExampleElement.AUTHOR);
        // min. uni abbreviations like pkl
        validateField(author, AUTHOR_MIN, "Author");

        String exampleDescription = (String) map.get(ExampleElement.DESCRIPTION);
        validateField(exampleDescription, DESCRIPTION_MIN, "Example Description");

        String exampleContact = (String) map.get(ExampleElement.CONTACT);
        // min 5 chars a@b.c
        validateField(exampleContact, CONTACT_MIN, "Example Contact");
    }

    private static void validateElement(final List<?> list, final int minLength,
            final String listName) {
        if (list == null || list.size() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("No ").append(listName).append(" has been selected.\n")
                    .append("Please choose at least ").append(String.valueOf(minLength));
            throw new RuntimeException(errorMsg.toString());
        }
    }

    private static void validateField(final String checkable, final int minLength,
            final String checkableName) {
        if (checkable == null || checkable.length() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("The field ").append(checkableName)
                    .append(" has to be set with at least ").append(String.valueOf(minLength))
                    .append(" characters.");
            throw new RuntimeException(errorMsg.toString());
        }
    }

    /**
     * checks the collectors for given exampleTitle. If exists, a {@link RuntimeException} will
     * thrown.
     * 
     * @param exampleTitle
     *            , String
     * @param collectors
     *            , {@link ExampleCollector}...
     * 
     */
    public static void checkDuplicate(final String exampleTitle,
            final ExampleCollector... collectors) {
        if (exampleTitle == null) {
            throw new RuntimeException("Title of an example could not be null.");
        }
        for (ExampleCollector collector : collectors) {
            if (collector.getExamplePool().containsKey(exampleTitle)) {
                throw new RuntimeException("Duplicate example title. Please choose an other one!");
            }
        }
    }

    /**
     * extends a plugin with a new example.
     * 
     * @param properties
     *            , {@link Map} with {@link ExampleElement} as key and an {@link Object} as value.
     * @param extensionCreator
     *            , {@link PluginExampleCreator}
     */
    @SuppressWarnings("unchecked")
    public static void exportInPlugin(final Map<ExampleElement, Object> properties,
            final PluginExampleCreator extensionCreator) {

        Example mappedExample = ExampleExport.mapToExample(properties);

        // TODO hier the example image in images ordner einbauen!
        File destFile = new File(mappedExample.getRootDir());
        if (!destFile.exists()) {
            throw new RuntimeException(ErrorMessage.DESTFILE_NOT_EXIST + mappedExample.getRootDir());
        }

        List<ExportResource> exportResources = (List<ExportResource>) properties
                .get(ExampleElement.RESOURCES);
        List<IPath> finishedResources = new ArrayList<IPath>();
        try {
            extensionCreator.copyResources(destFile, exportResources, finishedResources);
            mappedExample.addResources(ExampleExport.mapToExampleResource(exportResources));

            // TODO image in den "images" ordner des jeweiligen plugins kopieren, daf�r vlt.
            // namespace id herausfiltern.
            String absOverviewPic = copyOverviewPic(
                    (String) properties.get(ExampleElement.OVERVIEW_PIC), extensionCreator,
                    destFile, finishedResources);

            extensionCreator.addExtension(destFile, mappedExample,
                    (List<Category>) properties.get(ExampleElement.CREATE_CATEGORIES),
                    absOverviewPic);
        } catch (RuntimeException e) {
            extensionCreator.deleteExampleResources(finishedResources);
            throw e;
        }
    }

    private static String copyOverviewPic(final String overviewPic,
            final PluginExampleCreator extensionCreator, final File destFile,
            final List<IPath> finishedResources) {
        if (overviewPic != null && overviewPic.length() > 1) {
            String absolutePath = extensionCreator.copyOverviewPic(destFile.getPath(), overviewPic,
                    finishedResources);
            return absolutePath;
        }
        return null;
    }

    /**
     * mapping of properties onto an example.
     * 
     * @param properties
     *            , Map<String, Object>
     * @param rootResource
     * @return Example
     */
    public static Example mapToExample(final Map<ExampleElement, Object> properties) {
        String title = (String) properties.get(ExampleElement.TITLE);
        String category = (String) properties.get(ExampleElement.CATEGORY);
        Example result = new Example(ExampleExport.exampleIDCreator(category, title), category,
                title, SourceType.KIELER);
        result.setDescription((String) properties.get(ExampleElement.DESCRIPTION));
        result.setContact((String) properties.get(ExampleElement.CONTACT));
        result.setAuthor((String) properties.get(ExampleElement.AUTHOR));
        result.setRootDir((String) properties.get(ExampleElement.DEST_LOCATION));
        result.setOverviewPic((String) properties.get(ExampleElement.OVERVIEW_PIC));
        return result;
    }

    private static String exampleIDCreator(final String category, final String title) {
        return category + title.trim().replace(' ', '_');
    }

    private static List<ExampleResource> mapToExampleResource(
            final List<ExportResource> exportResources) {
        List<ExampleResource> result = new ArrayList<ExampleResource>();
        for (ExportResource exRe : exportResources) {
            ExampleResource.Type st = filterSourceType(exRe.getResource().getClass().getName());
            ExampleResource resultItem = new ExampleResource(
                    exRe.getLocalPath().toPortableString(), st);
            resultItem.setDirectOpen(exRe.isDirectOpen());
            result.add(resultItem);
        }
        return result;
    }

    private static ExampleResource.Type filterSourceType(final String name) {
        if (PROJECT_CLASS.equals(name)) {
            return Type.PROJECT;
        }
        if (FOLDER_CLASS.equals(name)) {
            return Type.FOLDER;
        }
        if (FILE_CLASS.equals(name)) {
            return Type.FILE;
        }
        return null;
    }
}
