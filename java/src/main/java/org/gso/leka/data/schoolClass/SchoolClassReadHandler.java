package org.gso.leka.data.schoolClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(session.getInputStream()));
		try {
			String body = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (session.getParameters().containsKey("parameters")) {
			Map<String, JsonElement> parameters = parseParameters(session.getParameters().get("parameters").get(0));
			SchoolClass schoolClass = SchoolClass.getClass(parameters.get("id").getAsString());

			reply = HttpServer.newFixedLengthResponse(new Gson().toJson(schoolClass));
			reply.setStatus(Status.OK);
		} else {
			reply = HttpServer.newFixedLengthResponse(Status.BAD_REQUEST.getDescription());
			reply.setStatus(Status.BAD_REQUEST);
		}
		return reply;
	}

}
