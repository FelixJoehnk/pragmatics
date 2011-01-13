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
package de.cau.cs.kieler.core.model.graphiti.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorFactory;
import org.eclipse.graphiti.ui.internal.parts.DiagramEditPart;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.model.graphiti.KielerGraphitiPlugin;
import de.cau.cs.kieler.core.ui.commands.AbstractReInitDiagramCommand;
import de.cau.cs.kieler.core.util.Maybe;

/**
 * Abstract super class for commands that initialize any graphiti diagram.
 * 
 * @author soh
 */
@SuppressWarnings("restriction")
public abstract class AbstractReInitGraphitiDiagramCommand extends
        AbstractReInitDiagramCommand {

    /**
     * Command for performing the reinitialization process.
     * 
     * @author soh
     */
    private class ReInitCommand extends AbstractCommand {

        /** The provider for creating the add features. */
        private IFeatureProvider provider;
        /** The model root element. */
        private EObject modelRoot;
        /** The diagram root element. */
        private PictogramElement elem;
        /** The editor. */
        private DiagramEditor editor;

        /**
         * 
         * Creates a new ReinitCommand.
         * 
         * @param providerParam
         *            the provider
         * @param modelRootParam
         *            the model root
         * @param elemParam
         *            the diagram root
         * @param editorParam
         *            the editor
         */
        public ReInitCommand(final IFeatureProvider providerParam,
                final EObject modelRootParam, final PictogramElement elemParam,
                final DiagramEditor editorParam) {
            provider = providerParam;
            modelRoot = modelRootParam;
            elem = elemParam;
            editor = editorParam;
        }

        @Override
        public boolean canExecute() {
            return true;
        }

        public void execute() {
            doExecute();
        }

        /**
         * 
         */
        protected void doExecute() {
            // process children of the root element
            for (EObject eObj : modelRoot.eContents()) {
                ContainerShape contShape = (ContainerShape) elem;
                linkToDiagram(eObj, provider, contShape, editor);
            }
            // deal with connections after finished
            processConnections(provider);
        }

        @Override
        public boolean canUndo() {
            return false;
        }

        public void redo() {
            execute();
        }
    }

    /** The name of the diagram type. */
    private String diagramTypeName;
    /** the grid size. */
    private int gridSize;
    /** true if snap to grid should be on by default. */
    private boolean snapToGrid;

    /**
     * Creates a new AbstractReInitGraphitiDiagramCommand.
     * 
     * @param diagramTypeNameParam
     *            the name
     * @param gridSizeParam
     *            the grid size
     * @param snapToGridParam
     *            true if it should snap to grid
     */
    public AbstractReInitGraphitiDiagramCommand(
            final String diagramTypeNameParam, final int gridSizeParam,
            final boolean snapToGridParam) {
        super();
        this.diagramTypeName = diagramTypeNameParam;
        this.gridSize = gridSizeParam;
        this.snapToGrid = snapToGridParam;
    }

    /**
     * Getter for the editor id.
     * 
     * @return the id
     */
    protected abstract String getEditorId();

    /**
     * Getter for the container.
     * 
     * @return the container
     */
    private IWorkbenchWindow getContainer() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    }

    @Override
    protected TransactionalEditingDomain createEditingDomain() {
        return DiagramEditorFactory.createResourceSetAndEditingDomain();
    }

    @Override
    public EObject getEObjectFromEditPart(final EditPart editPart) {
        if (editPart instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart part = (IPictogramElementEditPart) editPart;
            return part.getPictogramElement();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createNewDiagram(final EObject modelRootParam,
            final TransactionalEditingDomain editingDomain,
            final IFile diagramPath, final IProgressMonitor monitor) {
        // taken from the new wizard and adapted
        Resource diagramResource = createDiagram(diagramPath, modelRootParam,
                editingDomain, monitor);
        if (diagramResource != null && getEditorId() != null) {
            try {
                openDiagram(diagramResource);
            } catch (PartInitException exception) {
                ErrorDialog.openError(getContainer().getShell(),
                        "Error opening diagram editor", null,
                        exception.getStatus());
            }
        }

        return diagramResource != null;
    }

    /**
     * Create a diagram with given URIs.
     * 
     * @param diagramFile
     *            URI for the diagram file
     * @param modelRootParam
     *            URI for the model file
     * @param editingDomain
     *            the editing domain
     * @param progressMonitor
     *            progress monitor
     * @return a resource for the new diagram file
     */
    private Resource createDiagram(final IFile diagramFile,
            final EObject modelRootParam,
            final TransactionalEditingDomain editingDomain,
            final IProgressMonitor progressMonitor) {
        // taken from the new wizard and adapted
        progressMonitor.beginTask("Creating diagram and model files", 2);
        // create a resource set
        ResourceSet resourceSet = editingDomain.getResourceSet();
        CommandStack commandStack = editingDomain.getCommandStack();

        if (!diagramFile.exists()) {
            InputStream input = new ByteArrayInputStream("".getBytes());
            try {
                diagramFile.create(input, IResource.NONE,
                        new NullProgressMonitor());
            } catch (CoreException e0) {
                e0.printStackTrace();
            }
        }
        // create resource for the diagram
        String path = diagramFile.getFullPath().toString();
        final Resource diagramResource = resourceSet.createResource(URI
                .createPlatformResourceURI(path, true));
        if (diagramResource != null/*&& modelResource != null*/) {
            commandStack.execute(new RecordingCommand(editingDomain) {
                @Override
                protected void doExecute() {
                    createModel(diagramResource, diagramFile.getName(),
                            modelRootParam);
                }
            });
            progressMonitor.worked(1);

            try {
                diagramResource.save(GraphitiNewWizard.createSaveOptions());
            } catch (IOException exception) {
                IStatus status = new Status(IStatus.ERROR,
                        KielerGraphitiPlugin.PLUGIN_ID,
                        "Unable to store model and diagram resources",
                        exception);
                StatusManager.getManager().handle(status);
            }
            GraphitiNewWizard.setCharset(WorkspaceSynchronizer
                    .getFile(diagramResource));
        }
        progressMonitor.done();
        return diagramResource;
    }

    /**
     * Link the domain model root to the diagram.
     * 
     * @param diagramResource
     *            resource for the diagram model
     * @param diagramName
     *            name of the diagram model
     * @param modelRootParam
     *            the root element
     */
    private void createModel(final Resource diagramResource,
            final String diagramName, final EObject modelRootParam) {
        diagramResource.setTrackingModification(true);
        Diagram newDiagram = Graphiti.getPeCreateService().createDiagram(
                diagramTypeName, diagramName, gridSize, snapToGrid);
        PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
        link.setPictogramElement(newDiagram);
        link.getBusinessObjects().add(modelRootParam);
        diagramResource.getContents().add(newDiagram);
    }

    /**
     * List of connections to be processed after the other elements are
     * finished.
     */
    private List<EObject> connections;
    /** Store the elements that are already added and linked. */
    private Map<EObject, PictogramElement> elements;

    /**
     * Link the model to the newly created diagram inside the editor.
     * 
     * @param editor
     *            the editor
     */
    protected void linkModelToDiagram(final DiagramEditor editor) {
        connections = new LinkedList<EObject>();
        elements = new HashMap<EObject, PictogramElement>();
        // get the feature provider for getting the AddFeatures
        IDiagramTypeProvider dtp = editor.getDiagramTypeProvider();
        IFeatureProvider provider = dtp.getFeatureProvider();

        // get the root edit part for the diagram
        EditPart part = editor.getGraphicalViewer().getContents();

        if (part instanceof DiagramEditPart) {
            DiagramEditPart dep = (DiagramEditPart) part;
            PictogramElement elem = dep.getPictogramElement();
            List<EObject> list = elem.getLink().getBusinessObjects();
            EObject modelRoot = list.get(0);
            if (elem instanceof ContainerShape) {
                TransactionalEditingDomain domain = editor.getEditingDomain();
                CommandStack cs = domain.getCommandStack();
                cs.execute(new ReInitCommand(provider, modelRoot, elem, editor));
            }
        }
    }

    /**
     * The given domain model element represents a connection which should be
     * dealt with after all other elements are added. This is necessary since
     * connection require their source and target to be already present while
     * adding.
     * 
     * @param eObj
     *            the domain model element
     * @return true if the element represents a connection
     */
    protected abstract boolean isConnection(EObject eObj);

    /**
     * Get the source element of a connection.
     * 
     * @param connection
     *            the connection
     * @return the source of the connection
     */
    protected abstract EObject getConnectionSource(EObject connection);

    /**
     * Get the target element for a connection.
     * 
     * @param connection
     *            the connection
     * @return the target of the connection
     */
    protected abstract EObject getConnectionTarget(EObject connection);

    /**
     * Process the list of connections after the rest is finished.
     * 
     * @param provider
     *            the feature provider for getting the AddConnectionFeatures
     */
    private void processConnections(final IFeatureProvider provider) {
        for (EObject connection : connections) {
            AddConnectionContext context = processConnection(elements,
                    connection);

            if (context != null) {
                addAndLinkIfPossible(provider, context);
            }
        }
    }

    /**
     * Process a single connection. This method should be overridden if the
     * standard implementation doesn't produce the desired results. When the
     * method is called it can be guaranteed that all other elements have
     * already been added to the diagram.
     * 
     * @param elementsParam
     *            the map of already added elements.
     * @param connection
     *            the domain model element of the connection to be added
     * @return the context to execute for adding
     */
    protected AddConnectionContext processConnection(
            final Map<EObject, PictogramElement> elementsParam,
            final EObject connection) {
        // the domain model source and target
        EObject src = getConnectionSource(connection);
        EObject target = getConnectionTarget(connection);

        // the already added graphical elements
        PictogramElement srcElement = elementsParam.get(src);
        PictogramElement targetElement = elementsParam.get(target);

        // Determine the source anchor and container
        Anchor srcAnchor = null;
        ContainerShape srcContainer = null;
        if (srcElement instanceof ContainerShape) {
            // The source is a simple node (e.g. KAOM Entity)
            srcContainer = (ContainerShape) srcElement;
            srcAnchor = srcContainer.getAnchors().get(0);
        } else if (srcElement instanceof Anchor) {
            // The source is an anchor itself (e.g. KAOM Port)
            srcAnchor = (Anchor) srcElement;
            AnchorContainer container = srcAnchor.getParent();
            if (container instanceof ContainerShape) {
                srcContainer = (ContainerShape) container;
            }
        } else if (srcElement instanceof Shape) {
            // The source is a generic shape (e.g. KAOM Relation)
            Shape s = (Shape) srcElement;
            srcAnchor = s.getAnchors().get(0);
            srcContainer = s.getContainer();
        }

        // Determine the target anchor
        Anchor targetAnchor = null;
        ContainerShape targetContainer = null;
        if (targetElement instanceof ContainerShape) {
            targetContainer = (ContainerShape) targetElement;
            targetAnchor = targetContainer.getAnchors().get(0);
        } else if (targetElement instanceof Anchor) {
            targetAnchor = (Anchor) targetElement;
            AnchorContainer container = targetAnchor.getParent();
            if (container instanceof ContainerShape) {
                targetContainer = (ContainerShape) container;
            }
        } else if (targetElement instanceof Shape) {
            Shape s = (Shape) targetElement;
            targetAnchor = s.getAnchors().get(0);
            targetContainer = s.getContainer();
        }

        // create the context
        AddConnectionContext context = new AddConnectionContext(srcAnchor,
                targetAnchor);
        context.setNewObject(connection);
        context.setTargetContainer(srcContainer);
        return context;
    }

    /**
     * Add the eObject to the diagram.
     * 
     * @param provider
     *            feature provider for the features
     * @param context
     *            the context (must be AddConnectionContext if the domain model
     *            element is a connection)
     * @param eObj
     *            the domain model element to add
     * @return the added diagram element or null
     */
    private PictogramElement addAndLinkIfPossible(
            final IFeatureProvider provider, final AddContext context) {
        PictogramElement result = null;
        IAddFeature feature = provider.getAddFeature(context);
        if (feature != null) {
            // only do something if the element has a graphical representation
            result = feature.add(context);
        }
        return result;
    }

    /**
     * Link the given eObject to newly created elements in the diagram.
     * 
     * @param eObj
     *            the domain model element to add
     * @param provider
     *            the feature provider for getting the AddFeatures
     * @param container
     *            the parent container
     * @param editor
     *            the editor
     */
    private void linkToDiagram(final EObject eObj,
            final IFeatureProvider provider, final ContainerShape container,
            final DiagramEditor editor) {
        if (isConnection(eObj)) {
            // connections should be dealt with after
            // all other elements are present
            connections.add(eObj);
            return;
        }
        // create a context for adding the element
        AddContext context = new AddContext();
        context.setNewObject(eObj);
        context.setTargetContainer(container);

        // add the element
        PictogramElement element = addAndLinkIfPossible(provider, context);

        if (element != null) {
            // only do something if the element has a graphical representation
            elements.put(eObj, element);

            if (element instanceof ContainerShape) {
                // element may contain visible children
                ContainerShape cs = (ContainerShape) element;
                // recursively add children of the domain model element
                for (EObject child : eObj.eContents()) {
                    linkToDiagram(child, provider, cs, editor);
                }
            }
        }

    }

    /**
     * Open the diagram from the given resource.
     * 
     * @param diagramResource
     *            a resource for a diagram file
     * @throws PartInitException
     *             if the diagram could not be opened
     */
    private void openDiagram(final Resource diagramResource)
            throws PartInitException {
        String path = diagramResource.getURI().toPlatformString(true);
        final IResource workspaceResource = ResourcesPlugin.getWorkspace()
                .getRoot().findMember(new Path(path));
        if (workspaceResource instanceof IFile) {
            final Maybe<IEditorPart> editor = new Maybe<IEditorPart>();
            final Maybe<PartInitException> except = new Maybe<PartInitException>();
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                public void run() {
                    IWorkbenchPage page = getContainer().getActivePage();
                    try {
                        editor.set(page.openEditor(new FileEditorInput(
                                (IFile) workspaceResource), getEditorId()));
                    } catch (PartInitException e) {
                        except.set(e);
                    }
                }
            });
            if (except.get() != null) {
                throw except.get();
            }
            IEditorPart theEditor = editor.get();
            if (theEditor instanceof DiagramEditor) {
                // editor is open, can add the rest of the elements now
                linkModelToDiagram((DiagramEditor) theEditor);
            }
        }
    }
}
