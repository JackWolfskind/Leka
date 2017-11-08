package org.gso.leka.http;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public abstract class HttpHandler implements IHttpHandler {
	
	private String route;
	
	public HttpHandler(String route) {
		this.route = route;
	}

	public abstract Response Handle(IHTTPSession session, String route);

	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}

}
