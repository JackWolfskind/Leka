package org.gso.leka.data.schoolClass;

import org.gso.leka.http.HttpHandler;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;


public class SchoolClassListHandler extends HttpHandler {
	
	SchoolClassListHandler() {
		super("read");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		return null;
	}

}
