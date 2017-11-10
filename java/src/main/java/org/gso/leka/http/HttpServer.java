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
	
	/**
	 * Registers a new Router with the given Handlers
	 * @param route The route the router should respond to
	 * @param handlers The handlers that should be registered with the router
	 */
	public void registerRouter(String route, IHttpHandler...handlers) {
		HttpRouter router = new HttpRouter(route);
		for (IHttpHandler handler : handlers) {
			router.registerHandler(handler);
		}
		root.registerHandler(router);
	}

}
