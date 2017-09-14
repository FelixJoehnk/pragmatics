/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KImage;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Handler to store the view model KGraph (or a subgraph) of a KlighD view to a file. This class is
 * mainly intended to support debugging purposes.
 * 
 * For "chunky", we export 4 types of diagrams:
 *      flat_           Every compound node with its children and none of the children being expanded
 *      expanded_       Every compound node with all of its children being expanded
 *      expanded_flat   Every compound node with its children of size as if they were expanded but all
 *                      further children removed 
 *      inchierarchy_   Incrementally expanded diagrams, starting with the top-level compound node and 
 *                      zero expanded children. Afterwards compound nodes are expanded one after another 
 *                      in a breath-first order.
 * 
 * @author uru
 */
@SuppressWarnings("restriction")
public class KlighdSaveKGraphHandler extends AbstractHandler {

    private static final String TYPE_PARAMETER_ID =
            "de.cau.cs.kieler.klighd.ui.view.savekgraphParameter";
    private static final String EXPORT_TYPE_SINGLE = "single";
    private static final String EXPORT_TYPE_CHUNKY = "chunky";
    
    private static final String KGRAPH_FILE_EXTENSION = ".kgx";
    
    private static final Set<Class<?>> EMPTY = Collections.emptySet();
    
    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final KlighdTreeSelection selection;
        
        final IWorkbenchPart part = HandlerUtil.getActivePart(event);
        
        final IViewer viewer;
        if (part instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) part).getViewer();
        } else {
            return null;
        }

        // in case this handler is invoked via a context menu,
        // the activeMenuSelection (ISources#ACTIVE_MENU_SELECTION_NAME) is available
        ISelection s = HandlerUtil.getActiveMenuSelection(event);

        if (s instanceof KlighdTreeSelection) {
            // if it's a KLighD selection (it is supposed to be, we're fine :-)
            selection = (KlighdTreeSelection) s;

        } else if (s == null) {
            // if no activeMenuSelection is set, the handler may be called by the main menu,
            // toolbar, or a key binding; refer to the global selection in that case
            s = HandlerUtil.getCurrentSelectionChecked(event);

            if (s instanceof KlighdTreeSelection) {
                // again if it's a KLighD selection (it is supposed to be, we're fine :-)
                selection = (KlighdTreeSelection) s;

            } else {
                // something really strange must have happened
                return null;
            }
        } else {
            // something really strange must have happened
            return null;
        }

        KNode subgraph = null;
        if (selection.isEmpty()) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Please select something to export."), StatusManager.SHOW);
            return null;
        } else if (selection.size() == 1 && selection.getFirstElement() instanceof KNode) {
            // If exactly one element is selected and it is a knode
            // we suppose the user wanted to select that subgraph
            subgraph = (KNode) selection.getFirstElement();
        } 
        
        // Otherwise we export the whole view model graph
        final ViewContext viewContext = selection.getViewContext();
        if (viewContext == null) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Could not determine ViewContext."), StatusManager.SHOW);
        }

        final Shell shell = Display.getCurrent().getActiveShell();

        
        final String exportType = event.getParameter(TYPE_PARAMETER_ID);
        if (exportType.equals(EXPORT_TYPE_SINGLE)) {
            // ---------------------------------------------------
            //     Default export of the selected (sub-)graph
            // ---------------------------------------------------
            final KlighdSaveKGraphDialog fd = 
                    new KlighdSaveKGraphDialog(viewer.getControl().getShell());
            // show a KLighd save KGraph dialog
            int success = fd.open();
            
            if (success == Dialog.OK) {
                // retrieve selected
                IPath file = fd.getFilePath();
                
                // if file extension is missing, add it 
                if (!file.toOSString().contains(KGRAPH_FILE_EXTENSION)) {
                    file.addFileExtension(KGRAPH_FILE_EXTENSION);
                }
                
                // create uri to save file to the right place
                URI uri = null;
                if (fd.isWorkspacePath()) {
                    uri = URI.createPlatformResourceURI(file.toOSString(), true);
                } else {
                    uri = URI.createFileURI(file.toOSString());
                }

                export(subgraph, uri, EMPTY, false);
                
                // return a confirmation that everything works fine
                final String title = "Diagram export successful.";
                final String msg = "KLighD diagram export finished successfully.";

                MessageDialog.openInformation(shell, title, msg);
            }
        } else if (exportType.equals(EXPORT_TYPE_CHUNKY)) {
            // ---------------------------------------------------
            //      Export the diagram by recursively 
            //      exporting hierarchy levels, once flat
            //      and once with internal hierarchy expanded
            // ---------------------------------------------------
            ContainerSelectionDialog csd =
                    new ContainerSelectionDialog(shell, ResourcesPlugin.getWorkspace().getRoot(),
                            false, "");
            int success = csd.open();
            if (success == ContainerSelectionDialog.OK) {
                IPath container = (IPath) csd.getResult()[0];
                String destination = container.toOSString().toString();
    
                exportRecursivelyChunky(subgraph, destination, viewContext);
            }
        }
        
        return null;
    }
            
    
    private void exportRecursivelyChunky(final KNode subgraph, final String destination,
            final ViewContext viewContext) {

        // collect all subgraph s
        final Queue<KNode> flatNodes = Lists.newLinkedList();
        final Queue<KNode> expandedNodes = Lists.newLinkedList();
        String path = destination + File.separator;
        
        flatNodes.add(subgraph);
        expandedNodes.add(subgraph);

        // --------
        // #1 flat_
        // expand the flat versions
        while (!flatNodes.isEmpty()) {
            final KNode n = flatNodes.poll();

            if (n.getChildren().size() > 1) {
                String filename = "flat_" + getModelPathName(n, viewContext) + KGRAPH_FILE_EXTENSION;
                System.out.println("Exporting: " + filename);
                URI uri = URI.createPlatformResourceURI(path + filename, true);
                export(n, uri, EMPTY, true);
            }

            // expand and collect the children
            for (final KNode child : n.getChildren()) {

                // TODO application specific expansion goes here
                if (!child.getChildren().isEmpty()) {
                    viewContext.getViewer().expand(child);

                    flatNodes.add(child);
                    expandedNodes.add(child);
                }
            }

            // if we expanded some nodes, perform layout
            if (!n.getChildren().isEmpty()) {
                new LightDiagramLayoutConfig(viewContext)
                    .animate(false)
                    .performLayout();
            }
        }

        // ----------------
        // #2 inchierarchy_
        // collapse everything again
        for (KNode node : expandedNodes) {
            if (node != subgraph) {
                viewContext.getViewer().collapse(node);
            }
        }
        final Queue<KNode> increaseHierarchy = Lists.newLinkedList();
        increaseHierarchy.add(subgraph);
        new LightDiagramLayoutConfig(viewContext)
            .animate(false)
            .performLayout();
        int i = 0;
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        int requiredPaddingZeroes = (int) Math.ceil(Math.log(expandedNodes.size()) / Math.log(10));
        do {
            
            KNode toExpand = increaseHierarchy.poll();
            viewContext.getViewer().expand(toExpand);
            new LightDiagramLayoutConfig(viewContext)
                .animate(false)
                .performLayout();
            
            String filename =
                    "inchierarchy_" + getModelPathName(subgraph, viewContext) + "_"
                            + String.format("%0" + requiredPaddingZeroes + "d", i++)
                            + KGRAPH_FILE_EXTENSION;
            System.out.println("Exporting: " + filename);
            URI uri = URI.createPlatformResourceURI(path + filename, true);
            export(subgraph, uri, EMPTY, false);

            for (KNode child : toExpand.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    increaseHierarchy.add(child);
                }
            }

        } while (!increaseHierarchy.isEmpty());

        // export the expanded versions
        while (!expandedNodes.isEmpty()) {
            final KNode n = expandedNodes.poll();

            if (n.getChildren().size() > 1) {
                String filename = getModelPathName(n, viewContext) + KGRAPH_FILE_EXTENSION;
                System.out.println("Exporting: " + filename);
                URI uri = URI.createPlatformResourceURI(path + "expanded_" + filename, true);

                // ----------------
                // #3 expanded_
                export(n, uri, EMPTY, false);
                
                uri = URI.createPlatformResourceURI(path + "expanded_flat_" + filename, true);
                
                // ----------------
                // #4 expanded_flat_
                // remove children of compound nodes and export again
                export(n, uri, EMPTY, true);
            }
        }
    }
    
    private String getModelPathName(final KNode node, final ViewContext vc) {
        String s = "";
        KNode parent = node;
        while (parent != null) {
            String name = parent.toString().replace("\"", "").replace(" ", "").replace("KNode", "");
            name = name.replace("de.cau.cs.kieler.klighd.kgraph.impl.Impl", "Node");
            s = "-" + name + s;
            parent = parent.getParent();
        }
        s = s.substring(1);
        if (vc != null && vc.getDiagramWorkbenchPart() != null) {
            s = vc.getDiagramWorkbenchPart().getTitle() + "-" + s;
        }
        return s;
    }
    
    private void export(final KNode subgraph, final URI fileOutputURI,
            final Set<Class<?>> removeRenderingsOnClasses, final boolean removeChildren) {
        try {
            ResourceSet rs = new ResourceSetImpl();
            Resource r = rs.createResource(fileOutputURI);

            // We do not want to tamper with the original model, thus create a copy.
            // Also, we copy the whole graph because we must be able to
            // collect rendering libraries that might exist on higher levels
            KNode root = subgraph;
            while (root.getParent() != null) {
                root = root.getParent();
            }
            Copier copier = new Copier();
            KNode copy = (KNode) copier.copy(root);
            copier.copyReferences();

            KNode exportGraph = copy;
            if (subgraph != null) {

                // the new root's position is at 0,0 now
                KNode subgraphCopy = (KNode) copier.get(subgraph);
                subgraphCopy.setPos(0, 0);

                // move all rendering libraries that we can find to the newly promoted root
                Iterable<KRenderingLibrary> libs =
                        Iterables2.toIterable(ModelingUtil.eAllContentsOfType(copy,
                                KRenderingLibrary.class));

                for (KRenderingLibrary lib : libs) {
                    // move the libs to the new root
                    subgraphCopy.getData().add(lib);
                }

                // we wrap the subgraph into a new psuedo root node
                // to retain external ports
                KNode newRoot = KGraphUtil.createInitializedNode();
                newRoot.getChildren().add(subgraphCopy);
                // give the root a proper dimension
                newRoot.setWidth(subgraphCopy.getWidth());
                newRoot.setHeight(subgraphCopy.getHeight());

                // expand the subgraph by default
                subgraphCopy.setProperty(KlighdInternalProperties.POPULATED, true);
                subgraphCopy.setProperty(KlighdProperties.EXPAND, true);

                exportGraph = newRoot;
            }

            try {
                Set<KNode> populatedNodes = Sets.newHashSet();
                // remove transient klighd state
                // care: do not iterate over the elements of the 'copy' as the subgraph
                // was already removed from its original containment
                EObject copyRoot = copier.get(subgraph);
                Iterator<KGraphElement> kgeIt = ModelingUtil.selfAndEAllContentsOfType2(copyRoot,
                                KGraphElement.class);

                while (kgeIt.hasNext()) {
                    KGraphElement kge = kgeIt.next();
                    Iterator<KGraphData> dataIt = kge.getData().iterator();
                    while (dataIt.hasNext()) {
                        KGraphData d = dataIt.next();
                        
                        // RenderingContextData (explicit type information not available here)
                        if (d.getClass().equals(KGraphDataImpl.class)) {
                            // remember whether a node was expanded / collapsed
                            if (kge instanceof KNode && kge != exportGraph) {
                                KNode currentNode = (KNode) kge;
                                boolean isPopulated = d.getProperty(KlighdInternalProperties.POPULATED);
                                kge.setProperty(KlighdProperties.EXPAND, isPopulated);
                                
                                // since the populated state is only attached to the transient 
                                // rendering context that we throw away here, we have to remember 
                                // it for later usage
                                if (isPopulated) {
                                    populatedNodes.add(currentNode);
                                }

                                // klighd supports to change layout options based on the expansion
                                // state of a node
                                // here we want to persist the options based on the current state.
                                ExpansionAwareLayoutOptionData ealo =
                                        kge.getProperty(ExpansionAwareLayoutOption.OPTION);
                                if (ealo != null) {
                                    kge.copyProperties(ealo.getValues(isPopulated));
                                }
                                
                                // we make an exception if children of expanded compound
                                //  nodes are remove. In such cases the port constraints of the 
                                //  compound node are fixed after internal layout, 
                                //  which we want to retain after export
                                // but don't fix them for the currently exported compound node 
                                //  because that's the one we wanna layout and hence be able 
                                //  to move external ports
                                if (removeChildren && isPopulated && currentNode != copyRoot) {
                                    kge.setProperty(CoreOptions.PORT_CONSTRAINTS,
                                            PortConstraints.FIXED_POS);
                                    Direction dir = currentNode.getParent().getProperty(
                                            CoreOptions.DIRECTION);
                                    // due to a previous FREE, ports might have been moved to
                                    // different sides
                                    for (KPort p : currentNode.getPorts()) {
                                        p.setProperty(CoreOptions.PORT_SIDE,
                                                KGraphUtil.calcPortSide(p, dir));
                                    }
                                }

                            }
                            dataIt.remove();
                        }

                        // remove rendering information if desired. this might be useful for diagram
                        // types that use confidential renderings
                        if (!removeRenderingsOnClasses.isEmpty() && d instanceof KRendering) {
                            for (Class<?> clazz : removeRenderingsOnClasses) {
                                if (clazz.isInstance(kge)) {
                                    dataIt.remove();
                                }
                            }
                        }
                        
                        // remove some images
                        if (d instanceof KContainerRendering) {
                            Iterator<KRendering> renIt =
                                    ((KContainerRendering) d).getChildren().iterator();
                            while (renIt.hasNext()) {
                                KRendering ren = renIt.next();
                                if (ren instanceof KRenderingRef) {
                                    if (((KRenderingRef) ren).getRendering() instanceof KImage) {
                                        dataIt.remove();
                                        break;
                                    }
                                }
                                if (d instanceof KImage) {
                                    dataIt.remove();
                                    break;
                                }
                            }
                        }
                 
                    }
                }


                // persist layout options and friends
                KGraphUtil.persistDataElements(copy);
    
                // remove children if requested. Do this after the previous removal
                // happening to make sure all rendering libraries are considered etc
                if (removeChildren) {
                    Iterator<KNode> childrenIterator =
                            ((KNode) copier.get(subgraph)).getChildren().iterator();
                    while (childrenIterator.hasNext()) {
                        KNode child = childrenIterator.next();
                        child.getChildren().clear();
                        child.setProperty(KlighdProperties.EXPAND, false);
                        child.setProperty(KlighdProperties.MINIMAL_NODE_SIZE, null);

                        // remove the collapsed rendering
                        Iterator<KGraphData> dataIt = child.getData().iterator();
                        while (dataIt.hasNext()) {
                            KGraphData d = dataIt.next();
                            // either remove the collapsed or the expanded rendering
                            if (d instanceof KRendering && populatedNodes.contains(child)) {
                                if (d.getProperty(KlighdProperties.COLLAPSED_RENDERING)) {
                                    dataIt.remove();
                                }
                            } else if (d instanceof KRendering) {
                                if (d.getProperty(KlighdProperties.EXPANDED_RENDERING)) {
                                    dataIt.remove();
                                }
                            }
                        }
                        
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
            
            r.getContents().add(exportGraph);

            // remove any edges that do not have a target anymore
            recursivelyRemoveInvalidEdges((KNode) copier.get(subgraph));
            
            Map<String, Object> saveOpts = Maps.newHashMap();
            if (subgraph != null) {
                // we have to drop several elements if only a subgraph
                // is exported ... just let the emf deal with it.
                // FIXME use constants
                saveOpts.put("PROCESS_DANGLING_HREF", "DISCARD");
            }
            r.save(saveOpts);

        } catch (IOException e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID,
                            "Could not write the KGraph to selected resource.", e),
                    StatusManager.SHOW);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID, "Error occurred.", e),
                    StatusManager.SHOW);
        }
    }
    
    private void recursivelyRemoveInvalidEdges(final KNode parent) {
        final Iterator<KEdge> edgeIt = parent.getOutgoingEdges().iterator();
        while (edgeIt.hasNext()) {
            KEdge edge = edgeIt.next();
            if (edge.getTarget() == null) {
                edgeIt.remove();
            } else if (!edge.getSource().eResource().equals(edge.getTarget().eResource())) {
                edgeIt.remove();
            }
        }        
        
        for (KNode child : parent.getChildren()) {
            recursivelyRemoveInvalidEdges(child);
        }
    }
}
