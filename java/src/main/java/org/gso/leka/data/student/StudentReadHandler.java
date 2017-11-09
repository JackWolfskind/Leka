package org.gso.leka.data.student;

import java.util.Map;
import org.gso.leka.Main;
import org.gso.leka.data.schoolClass.SchoolClass;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class StudentReadHandler extends HttpHandler {
	
	public StudentReadHandler() {
		super("read");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply;
		
		if (parameters.containsKey("id")) {
			Student student = Student.get(parameters.get("id").getAsString());

			reply = HttpServer.newFixedLengthResponse(new Gson().toJson(student));
			reply.setStatus(Status.OK);
		} else {
			reply = HttpServer.newFixedLengthResponse(Status.BAD_REQUEST.getDescription() + " - No ID Provided");
			reply.setStatus(Status.BAD_REQUEST);
		}

		return reply;
	}

}
