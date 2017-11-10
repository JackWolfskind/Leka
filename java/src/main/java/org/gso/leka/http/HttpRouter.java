package org.gso.leka.http;

import java.util.HashMap;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

/**
 * An HTTP Router used to forward requests to underlying routers of handlers
 * @author Lukas Prediger
 */
public class HttpRouter extends HttpHandler {

	public HttpRouter() {
		super("\\");
	}

	public HttpRouter(String route) {
		super(route);
	}

	HashMap<String, IHttpHandler> handler = new HashMap<String, IHttpHandler>();

	@Override
	public Response Handle(IHTTPSession session, String route) {
		if (route.isEmpty()) {
			Response resp = NanoHTTPD.newFixedLengthResponse("Route not found");
			resp.setStatus(Status.NOT_FOUND);
			return resp;
		}
		String[] routen = route.split("/", 2);
		IHttpHandler current = handler.get(routen[0]);
		if (current == null) {
			Response resp = NanoHTTPD.newFixedLengthResponse("Route not found");
			resp.setStatus(Status.NOT_FOUND);
			return resp;
		}
		
		return current.Handle(session, routen[routen.length-1]);
	}

	/**
	 * Registers a handler for the route defined by the handler.getRoute() method
	 * @param handler a class implementing the IHttpHandler interface
	 */
	public void registerHandler(IHttpHandler handler) {
		this.handler.put(handler.getRoute(), handler);
	}

	/**
	 * Unregisters a handler
	 * @param handler the handler to be removed
	 */
	public void deregisterHandler(IHttpHandler handler) {
		this.handler.remove(handler.getRoute());
	}

}
