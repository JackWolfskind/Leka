package org.gso.leka.data.block;

import java.util.Map;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class BlockReadHandler extends HttpHandler {
	
	public BlockReadHandler() {
		super("read");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer.newFixedLengthResponse(Status.NOT_IMPLEMENTED.getDescription());
		
		reply.setStatus(Status.NOT_IMPLEMENTED);
		return reply;
	}

}