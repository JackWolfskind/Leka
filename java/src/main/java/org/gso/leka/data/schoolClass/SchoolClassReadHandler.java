package org.gso.leka.data.schoolClass;

import java.util.Map;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class SchoolClassReadHandler extends HttpHandler {

	public SchoolClassReadHandler() {
		super("read");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Response reply;
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());

		if (parameters.containsKey("id")) {
			SchoolClass schoolClass = SchoolClass.getClass(parameters.get("id").getAsString());

			reply = HttpServer.newFixedLengthResponse(new Gson().toJson(schoolClass));
			reply.setStatus(Status.OK);
		} else {
			reply = HttpServer.newFixedLengthResponse(Status.BAD_REQUEST.getDescription() + " - No ID Provided");
			reply.setStatus(Status.BAD_REQUEST);
		}

		return reply;
	}

}
