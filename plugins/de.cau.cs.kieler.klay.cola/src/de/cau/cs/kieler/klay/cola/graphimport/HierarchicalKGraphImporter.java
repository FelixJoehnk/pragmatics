/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.graphimport;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.adaptagrams.Cluster;
import org.adaptagrams.Rectangle;
import org.adaptagrams.RectangularCluster;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;
import de.cau.cs.kieler.klay.cola.util.ColaUtil;

/**
 * 
 * @author uru
 */
public class HierarchicalKGraphImporter implements IGraphImporter<KNode, CGraph> {

    private Map<KNode, CNode> nodeMap = Maps.newHashMap();
    private Map<KEdge, CEdge> edgeMap = Maps.newHashMap();
    private Map<KPort, CPort> portMap = Maps.newHashMap();
    /** Maps compound nodes to the cluster by which they are represented. */
    private BiMap<KNode, Cluster> clusterMap = HashBiMap.create();

    private CGraph graph;
    private boolean portDummies = false;
    private boolean repositionHierarchicalPorts = false;

    /**
     * {@inheritDoc}
     */
    public CGraph importGraph(final KNode rootNode) {

        // create a new adaptagrams graph
        graph = new CGraph();
        graph.copyProperties(rootNode.getData(KLayoutData.class));
        graph.setProperty(InternalColaProperties.ORIGIN, rootNode);

        portDummies = graph.getProperty(ColaProperties.PORT_DUMMIES);
        repositionHierarchicalPorts =
                graph.getProperty(ColaProperties.REPOSITION_HIERARCHICAL_PORTS);

        // transform the nodes
        recImportNodes(rootNode, null);

        // transform edges
        recImportEdges(rootNode);

        // after all graph elements were created we can initialize the cola elements of the graph
        graph.init();

        return graph;
    }

    private void recImportNodes(final KNode parentNode, final Cluster parentCluster) {

        for (KNode node : parentNode.getChildren()) {

            if (node.getIncomingEdges().isEmpty() && node.getOutgoingEdges().isEmpty()) {
                continue;
            }

            // if this is an atomic node, ie it has no children
            if (node.getChildren().isEmpty()) {
                // create an adaptagrams node
                CNode cnode = new CNode(graph);
                // dimensions
                ColaUtil.setPosAndSizeAbsolute(cnode, node, node.getParent());
                // properties
                cnode.copyProperties(node.getData(KLayoutData.class));
                cnode.setProperty(InternalColaProperties.ORIGIN, node);
                // remember it
                nodeMap.put(node, cnode);
                graph.getChildren().add(cnode);
                cnode.setParent(graph);
                cnode.init();

                // add to the cluster
                // note that top level nodes are not added to any cluster
                if (parentCluster != null) {
                    parentCluster.addChildNode(cnode.cIndex);
                }

                if (portDummies) {
                    // create ports
                    for (KPort p : node.getPorts()) {
                        CPort port = new CPort(graph, cnode);
                        ColaUtil.setPosAndSizeAbsolute(port, p, p.getNode());
                        port.copyProperties(p.getData(KLayoutData.class));
                        port.setProperty(InternalColaProperties.ORIGIN, p);
                        portMap.put(p, port);
                        cnode.getPorts().add(port);
                        port.init();
                        PortSide ps =
                                p.getData(KLayoutData.class).getProperty(LayoutOptions.PORT_SIDE);
                        if (ps != PortSide.UNDEFINED) {
                            port.side = ps;
                        } else {
                            // calculate a useful side
                            port.side =
                                    KimlUtil.calcPortSide(p,
                                            graph.getProperty(LayoutOptions.DIRECTION)); // FIXME
                        }

                        // add an edge from the port to the node's center
                        port.withCenterEdge();

                        // put the dummy port in the same cluster as the node
                        if (parentCluster != null) {
                            parentCluster.addChildNode(port.cIndex);
                        }
                    }
                }

            } else {
                // this is a compound node, create a cluster for it
                Cluster compoundCluster = new RectangularCluster();
                KShapeLayout compoundLayout = node.getData(KShapeLayout.class); 
                KVector absolute = KimlUtil.toAbsolute(compoundLayout.createVector(), parentNode);
                compoundCluster.setBounds(new Rectangle(absolute.x, absolute.x
                        + compoundLayout.getWidth(), absolute.y, absolute.y
                        + compoundLayout.getHeight()));

                // the top level nodes are not cumulated within a cluster
                if (parentCluster == null) {
                    graph.rootCluster.addChildCluster(compoundCluster);
                } else {
                    parentCluster.addChildCluster(compoundCluster);
                }
                clusterMap.put(node, compoundCluster);

                // recursively convert the children
                recImportNodes(node, compoundCluster);
            }
        }

    }

    private void recImportEdges(final KNode parentNode) {

        for (KNode node : parentNode.getChildren()) {

            // we start a atomic nodes
            if (node.getChildren().isEmpty()) {
                for (KEdge edge : node.getOutgoingEdges()) {
                    List<KEdge> edgeChain = Lists.newLinkedList();
                    List<Pair<KPort, KVector>> checkpoints = Lists.newLinkedList();
                    introduceEdge(edge, edge.getSource(), edge, edge.getTarget(), edgeChain,
                            checkpoints);
                }
            } else {

                // recursively import edges of this compound node
                recImportEdges(node);
            }
        }
    }

    private void introduceEdge(final KEdge edge, final KNode startNode, final KEdge startEdge,
            final KNode currentTarget, final List<KEdge> edgeChain, 
            final List<Pair<KPort, KVector>> checkpoints) {

        edgeChain.add(edge);

        // if the target or the target ports node are atomic, finish the edge
        if (edge.getTarget().getChildren().isEmpty()) {
            CNode src = nodeMap.get(startNode);
            CNode tgt = nodeMap.get(edge.getTarget());
            CPort srcPort = portMap.get(startEdge.getSourcePort());
            CPort tgtPort = portMap.get(edge.getTargetPort());

            CEdge cedge = new CEdge(graph, src, srcPort, tgt, tgtPort);
            edgeMap.put(edge, cedge);
            cedge.copyProperties(edge.getData(KLayoutData.class));
            cedge.setProperty(InternalColaProperties.ORIGIN, edge);
            cedge.init();

            // spans the edge hierarchy levels?
            if (!startNode.getParent().equals(currentTarget.getParent())) {
                cedge.crossHierarchy = true;
                cedge.setProperty(InternalColaProperties.EDGE_CHAIN, edgeChain);
                cedge.setProperty(InternalColaProperties.EDGE_CHECKPOINTS, checkpoints);
            }

            src.getOutgoingEdges().add(cedge);
            if (srcPort != null) {
                srcPort.getOutgoingEdges().add(cedge);
            }
            tgt.getIncomingEdges().add(cedge);
            if (tgtPort != null) {
                tgtPort.getIncomingEdges().add(cedge);
            }

        }

        // if the target is a hierarchical port, follow that port!
        if (edge.getTargetPort() != null) {

            // and add the port's position to a list of "checkpoints"
            KShapeLayout portLayout = edge.getTargetPort().getData(KShapeLayout.class);
            KVector cp = portLayout.createVector();
            cp.translate(portLayout.getWidth() / 2f, portLayout.getHeight() / 2f);
            // convert it to a global position
            cp = KimlUtil.toAbsolute(cp, edge.getTarget());
            checkpoints.add(Pair.of(edge.getTargetPort(), cp));
            
            for (KEdge portEdge : getOutgoingEdges(edge.getTargetPort())) {
                // make sure to copy the list, as we might follow multiple edges
                introduceEdge(portEdge, startNode, startEdge, portEdge.getTarget(),
                        Lists.newArrayList(edgeChain), Lists.newArrayList(checkpoints));
            }

        }

    }

    private Iterable<KEdge> getOutgoingEdges(final KPort port) {
        return Iterables.filter(port.getEdges(), new Predicate<KEdge>() {
            public boolean apply(final KEdge edge) {
                return edge.getSource().equals(port.getNode());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final CGraph constrainedGraph) {

        double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

        // calculate the offset from border spacing and node distribution
        double minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY =
                Float.MIN_VALUE;

        // find the minimal and maximal positions of the contained nodes
        for (CNode n : graph.getChildren()) {
            final KVector pos = n.getRectPosRaw();
            final KVector size = n.getRectSizeRaw();
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
            maxX = Math.max(maxX, pos.x + size.x);
            maxY = Math.max(maxY, pos.y + size.y);
            for (CPort p : n.getPorts()) {
                final KVector ppos = p.getRectPosRaw();
                final KVector psize = p.getRectSizeRaw();
                minX = Math.min(minX, ppos.x);
                minY = Math.min(minY, ppos.y);
                maxX = Math.max(maxX, ppos.x + psize.x);
                maxY = Math.max(maxY, ppos.y + psize.y);
            }
        }
        // also consider the clusters here
        for (Cluster cluster : clusterMap.values()) {
            RectangularCluster c = (RectangularCluster) cluster;
            minX = Math.min(minX, c.getBounds().getMinX());
            minY = Math.min(minY, c.getBounds().getMinY());
            maxX = Math.max(maxX, c.getBounds().getMaxX());
            maxY = Math.max(maxY, c.getBounds().getMaxY());
        }

        // the global offset by which the whole diagram is shifted
        final KVector offset = new KVector(borderSpacing - minX, borderSpacing - minY);

        /*
         * Clusters
         */
        for (Entry<KNode, Cluster> clusterEnty : clusterMap.entrySet()) {
            KNode knode = clusterEnty.getKey();
            KShapeLayout layout = knode.getData(KShapeLayout.class);
            RectangularCluster c = (RectangularCluster) clusterEnty.getValue();

            // clusters in clusters have to be offset properly
            KVector relative = relativeToCluster(c, offset);

            layout.setXpos((float) (relative.x));
            layout.setYpos((float) (relative.y));
            layout.setWidth((float) (c.getBounds().width()));
            layout.setHeight((float) (c.getBounds().height()));
        }

        /*
         * Nodes
         */
        for (CNode n : graph.getChildren()) {
            KShapeLayout layout =
                    n.getProperty(InternalColaProperties.ORIGIN).getData(KShapeLayout.class);

            // if the node is contained in a cluster we have to offset it
            KVector relative = relativeToCluster(n, offset);
            layout.setXpos((float) relative.x);
            layout.setYpos((float) relative.y);

            // ports
            if (!n.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
                for (CPort p : n.getPorts()) {
                    if (p.cEdgeIndex != -1) {
                        KShapeLayout portLayout =
                                p.getProperty(InternalColaProperties.ORIGIN)
                                        .getData(KShapeLayout.class);
                        // ports are relative to the parent in KGraph
                        KVector relativePort = p.getRelativePos();
                        portLayout.setXpos((float) relativePort.x);
                        portLayout.setYpos((float) relativePort.y);
                    }
                }
            }
        }

        KNode root = (KNode) graph.getProperty(InternalColaProperties.ORIGIN);
        Iterator<EObject> allEdges =
                Iterators.filter(root.eAllContents(), new Predicate<EObject>() {
                    public boolean apply(final EObject obj) {
                        return obj instanceof KEdge;
                    }
                });
        while (allEdges.hasNext()) {
            KEdge edge = ((KEdge) allEdges.next());
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            layout.getBendPoints().clear();
            layout.getSourcePoint().setPos(0, 0);
            layout.getTargetPoint().setPos(0, 0);
        }

        for (CEdge e : edgeMap.values()) {
            if (!e.crossHierarchy) {
                KEdge edge = (KEdge) e.getProperty(InternalColaProperties.ORIGIN);
                KEdgeLayout layout = edge.getData(KEdgeLayout.class);

                // if bendpoints were specified use these
                if (!e.bendpoints.isEmpty()) {
                    KVectorChain chain = new KVectorChain(e.bendpoints);
                    
                    // TODO cleanup below!
                    // offset the bendpoints
                    // note, that we have to consider hierarchy as well
                    // is the source node contained in a cluster?
                    KNode parent = ((KNode) e.getSource().getProperty(
                            InternalColaProperties.ORIGIN)).getParent();
                    Cluster c =
                            clusterMap.get(parent);
                    KVector clusterPos = new KVector();
                    if (c != null) {
                     clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());
                     clusterPos.negate();
                    } else {
                        clusterPos.add(offset);
                    }
                    System.out.println(parent + " " + c);
                    //KVector relativeOffset = offset.differenceCreate(clusterPos);
                    
                    chain.translate(clusterPos);
                    
                    layout.applyVectorChain(chain);
                } else {
                    // convert to relative coordinates (note that both are relative to the source's
                    // parent)
                    KVector relativeSrcPoint =
                            KimlUtil.toRelative(e.getSourcePoint(), edge.getSource().getParent());
                    KVector relativeTgtPoint =
                            KimlUtil.toRelative(e.getTargetPoint(), edge.getSource().getParent());
    
                    // apply new positions
                    layout.getSourcePoint().applyVector(relativeSrcPoint.sumCreate(offset));
                    layout.getTargetPoint().applyVector(relativeTgtPoint.sumCreate(offset));
                }
            } else {

                // assign better positions to hierarchical ports
                if (repositionHierarchicalPorts) {
                    // transfer positions to all edges that are part of this hierarchical edge
                    // FIXME edges may be handled multiple times
                    // this should yield deterministic results, however performance could be improved
                    // however, it might be better to use a median or average value
                    repositionHierarchicalPorts(e, offset);
                }
                
                List<KVectorChain> subRoutes =
                        e.getProperty(InternalColaProperties.EDGE_SUB_ROUTES);
                List<KEdge> edgeChain = e.getProperty(InternalColaProperties.EDGE_CHAIN);
                if (!subRoutes.isEmpty()) {
                    Iterator<KVectorChain> subRoutesIt = subRoutes.iterator();
                    Iterator<KEdge> edgeChainIt = edgeChain.iterator();

                    while (subRoutesIt.hasNext() && edgeChainIt.hasNext()) {
                        final KVectorChain vc = subRoutesIt.next();
                        final KEdge edge = edgeChainIt.next();
                        KVector globalOffset = new KVector();
                        // current coordinates are in global, translate them to relative
                        if (KimlUtil.isDescendant(edge.getTarget(), edge.getSource())) {
                            // relative to the source
                            globalOffset = KimlUtil.toRelative(globalOffset, edge.getSource());
                        } else {
                            // relative to the source's parent
                            globalOffset =
                                    KimlUtil.toRelative(globalOffset, edge.getSource().getParent());
                        }

                        vc.translate(globalOffset);
                        vc.translate(offset);

                        // apply back to the original edge layout
                        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                        edgeLayout.applyVectorChain(vc);
                    }

                }
            }
        }

        // resize the parent node
        KInsets insets = root.getData(KShapeLayout.class).getInsets();
        double width = (maxX - minX) + 2 * borderSpacing + insets.getLeft() + insets.getRight();
        double height = (maxY - minY) + 2 * borderSpacing + insets.getTop() + insets.getBottom();
        KimlUtil.resizeNode(root, (float) width, (float) height, false, true);
    }

    
    /**
     * Every node's position is in global coordinates. We translate this back to the position
     * relative to the parent node.
     */
    private KVector relativeToCluster(final CNode node, final KVector offset) {

        KVector nodePos = node.getRectPos();
        KNode parent = ((KNode) node.getProperty(InternalColaProperties.ORIGIN)).getParent();
        Cluster c = clusterMap.get(parent);
        if (c == null) {
            // no offset required, we are on the root level
            return nodePos.sumCreate(offset);
        } else {
            // if it's a nested cluster we have to offset that
            KVector clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());
            return nodePos.differenceCreate(clusterPos);
        }
    }

    private KVector relativeToCluster(final Cluster c, final KVector offset) {

        KVector clusterPos = new KVector(c.getBounds().getMinX(), c.getBounds().getMinY());

        // retrieve the parent cluster
        KNode node = clusterMap.inverse().get(c);
        Cluster parentCluster = clusterMap.get(node.getParent());

        if (parentCluster == null) {
            return clusterPos.sumCreate(offset);
        } else {
            KVector parentClusterPos =
                    new KVector(parentCluster.getBounds().getMinX(), parentCluster.getBounds()
                            .getMinY());
            return clusterPos.differenceCreate(parentClusterPos);
        }
    }

    private void repositionHierarchicalPorts(final CEdge e, final KVector offset) {

        List<KEdge> edgeChain = e.getProperty(InternalColaProperties.EDGE_CHAIN);
        // initialize the line connecting the centers of the start and end port
        KVector srcPnt;
        KVector tgtPnt;
        if (e.srcPort == null || e.tgtPort == null) {
            // use the nodes' centers
            srcPnt = e.src.getRectCenterRaw();
            tgtPnt = e.tgt.getRectCenterRaw();
        } else {
            srcPnt = e.srcPort.getRectCenterRaw();
            tgtPnt = e.tgtPort.getRectCenterRaw();
        }

        final Line2D line = new Line2D.Double(srcPnt.x, srcPnt.y, tgtPnt.x, tgtPnt.y);

        // iterate through all edge but the last and
        // reposition the target port of all intermediate ports
        for (KEdge kedge : edgeChain.subList(0, edgeChain.size() - 1)) {

            // find the currently surrounding cluster
            Cluster c = clusterMap.get(kedge.getTarget());
            Rectangle bounds = c.getBounds();
            final Rectangle2D rect =
                    new Rectangle2D.Double(bounds.getMinX(), bounds.getMinY(), bounds.width(),
                            bounds.height());

            final Pair<KVector, PortSide> intersection = ColaUtil.getIntersectionPoint(line, rect);

            if (intersection != null) {
                KShapeLayout portLayout = kedge.getTargetPort().getData(KShapeLayout.class);

                // the calculated intersection bound lies exactly on the rectangles boundary,
                // hence we have to shift the port into a specific direction according to its size
                // also, the intersection refers to the center position, so add half the dimension
                KVector positionOffset = new KVector();
                switch (intersection.getSecond()) {
                case NORTH:
                    positionOffset.x = portLayout.getWidth() / 2d;
                    positionOffset.y = portLayout.getHeight();
                    break;
                case EAST:
                    positionOffset.x = 0;
                    positionOffset.y = portLayout.getHeight() / 2d;
                    break;
                case SOUTH:
                    positionOffset.x = portLayout.getWidth() / 2d;
                    positionOffset.y = 0;
                    break;
                case WEST:
                    positionOffset.x = portLayout.getWidth();
                    positionOffset.y = portLayout.getHeight() / 2d;
                    break;
                default:
                    // we dont care
                }

                // assign the new side of the port
                portLayout.setProperty(LayoutOptions.PORT_SIDE, intersection.getSecond());

                // determine the new position, relative to the parent node
                KVector clusterPos = new KVector(bounds.getMinX(), bounds.getMinY());
                KVector newPos = new KVector();
                newPos.add(intersection.getFirst()) // intersection point
                        .sub(clusterPos) // relative to the cluster
                        .sub(positionOffset); // proper port position

                portLayout.applyVector(newPos);
            }
        }

    }
}

