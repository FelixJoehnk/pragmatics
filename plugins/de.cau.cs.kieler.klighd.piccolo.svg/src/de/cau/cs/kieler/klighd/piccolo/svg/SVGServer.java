/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.svg;

/**
 * @author uru
 *
 */
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

public class SVGServer extends Server {

    private boolean verbose;

    private ConcurrentLinkedQueue<SVGSendingWebSocket> broadcast =
            new ConcurrentLinkedQueue<SVGSendingWebSocket>();
    private KlighdSVGGraphicsImpl svgGenerator;

    public SVGServer(int port, KlighdSVGGraphicsImpl theSvgGenerator) {
        svgGenerator = theSvgGenerator;

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        addConnector(connector);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
                WebSocket ws = null;
                if ("protocol.svgws".equals(protocol)) {
                    ws = new SVGSendingWebSocket();
                }
                return ws;
            }
        };
        setHandler(wsHandler);

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        rHandler.setResourceBase("svg/");
        wsHandler.setHandler(rHandler);

        verbose = true; 

    }

    class SVGSendingWebSocket implements WebSocket, WebSocket.OnTextMessage {

        private Connection connection;

        /**
         * @return the connection
         */
        public Connection getConnection() {
            return connection;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onOpen(Connection connection) {
            if (verbose) {
                System.err.printf("%s#onOpen %s\n", this.getClass().getSimpleName(), connection);
            }
            this.connection = connection;
            broadcast.add(this);

            broadcastSVG();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMessage(String data) {
            if (verbose) {
                System.err.printf("%s#onMessage     %s\n", this.getClass().getSimpleName(), data);
            }

            broadcastSVG();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onClose(int code, String message) {
            if (verbose) {
                System.err.printf("%s#onDisonnect %d %s\n", this.getClass().getSimpleName(), code,
                        message);
            }
            broadcast.remove(this);
        }

    }

    public void broadcastSVG() {

        String data = svgGenerator.getSVG();
        //System.out.println("sending data " + data.length());

        if (data.length() < 1000) {
            return;
        }

        for (SVGSendingWebSocket ws : broadcast) {
            try {
                ws.getConnection().sendMessage(data);
            } catch (IOException e) {
                broadcast.remove(ws);
                e.printStackTrace();
            }
        }
    }

}
