package org.gso.leka.http;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public class HttpHandler implements IHttpHandler {

	public Response Handle(IHTTPSession session, String route) {
		return null;
	}

	public String getRoute() {
		return null;
	}

}
