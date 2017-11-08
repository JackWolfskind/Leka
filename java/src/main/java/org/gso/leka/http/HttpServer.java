package org.gso.leka.http;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class HttpServer extends NanoHTTPD {
	
	public final char URI_SEPERATOR = '\\';
	
	private HttpRouter root = new HttpRouter();

	public HttpServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void start() throws IOException {
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
	}

	@Override
	public Response serve(IHTTPSession session) {
		Response response;
		if (session.getRemoteIpAddress().equals("127.0.0.1")) {
			response = root.Handle(session, session.getUri().substring(1));
		} else {
			response = newFixedLengthResponse(Status.UNAUTHORIZED.getDescription());
			response.setStatus(Status.UNAUTHORIZED);
		}
		return response;
	}
	
	public HttpRouter getRouter() {
		return root;
	}

}
