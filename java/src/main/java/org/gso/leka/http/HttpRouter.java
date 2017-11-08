package org.gso.leka.http;

import java.util.HashMap;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class HttpRouter implements IHttpHandler {
	
	HashMap<String, IHttpHandler> handler = new HashMap<String, IHttpHandler>();
	
	public Response Handle(IHTTPSession session, String route) {
		
		IHttpHandler current = handler.get(route);
		if (current == null) {
			Response resp = NanoHTTPD.newFixedLengthResponse("Route not found");
			resp.setStatus(Status.NOT_FOUND);
			return resp;
		}
		return current.Handle(session, route);
	}

	public String getRoute() {
		return null;
	}
	
	public void registerHandler(IHttpHandler handler) {
		this.handler.put(handler.getRoute(), handler);
	}
	
	public void deregisterHandler(IHttpHandler handler) {
		this.handler.remove(handler.getRoute());
	}

}
