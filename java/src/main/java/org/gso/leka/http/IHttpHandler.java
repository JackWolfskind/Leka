package org.gso.leka.http;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

/**
 * Interface for all HTTP Handlers.
 * @author Lukas Prediger
 *
 */
public interface IHttpHandler {
	/**
	 * Handles a HTTP Request
	 * @param session the HTTP Request session object
	 * @param route The remaining route, either being the Route of the Handler itself or the still to be routed to
	 * @return the Response object
	 */
	public Response Handle(IHTTPSession session, String route);
	
	/**
	 * Return the Route this handler responses to
	 * @return the Route
	 */
	public String getRoute();
}
