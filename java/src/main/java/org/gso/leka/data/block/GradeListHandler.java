package org.gso.leka.data.block;

import java.util.Date;
import java.util.Map;

import org.gso.leka.Main;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class GradeListHandler extends HttpHandler {
	
	public GradeListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer.newFixedLengthResponse(
				new Gson().toJson(Block.loadAll(Main.entityManagerFactory.createEntityManager(),
						new Date(parameters.get("start").getAsLong()),
						new Date(parameters.get("end").getAsLong()),
						parameters.get("name").getAsString())));
		reply.setStatus(Status.OK);
		
		return reply;
	}

}
