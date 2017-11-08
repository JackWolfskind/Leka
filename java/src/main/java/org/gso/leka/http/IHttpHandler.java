package org.gso.leka.http;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public interface IHttpHandler {
	public Response Handle(IHTTPSession session, String route);
	
	public String getRoute();
}
