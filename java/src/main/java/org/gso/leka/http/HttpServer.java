package org.gso.leka.http;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class HttpServer extends NanoHTTPD {
	
	public final char URI_SEPERATOR = '\\';

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
			response = newFixedLengthResponse(session.getUri());
			response.setStatus(Status.OK);
		} else {
			response = newFixedLengthResponse(Status.UNAUTHORIZED.getDescription());
			response.setStatus(Status.UNAUTHORIZED);
		}
		return response;
	}

}
