package org.gso.leka.data.schoolClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class SchoolClassListHandler extends HttpHandler {
	
	public SchoolClassListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		//Map<String, JsonElement> parameters = parseParameters(session.getParameters().get("parameters").get(0));
		List<String> classIDs = SchoolClass.getIDs();
		
		Response reply = HttpServer.newFixedLengthResponse(new Gson().toJson(classIDs));
		
		reply.setStatus(Status.OK);
		return reply;
	}

}
