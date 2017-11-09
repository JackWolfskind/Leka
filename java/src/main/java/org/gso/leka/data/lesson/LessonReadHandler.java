package org.gso.leka.data.lesson;

import java.sql.Date;
import java.util.Map;
import org.gso.leka.Main;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class LessonReadHandler extends HttpHandler {

	public LessonReadHandler() {
		super("read");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer.newFixedLengthResponse("");

		if (parameters.containsKey("date") && parameters.containsKey("block") && parameters.containsKey("teacher")) {
			Lesson lesson = Lesson.load(
					Main.entityManagerFactory.createEntityManager(), 
					new Date(parameters.get("date").getAsLong()),
					parameters.get("teacherID").getAsString(), 
					parameters.get("blockID").getAsInt());
		} else {
			reply = HttpServer.newFixedLengthResponse(
					Status.BAD_REQUEST.getDescription() + " - Not all manditory Fields Provided");
			reply.setStatus(Status.BAD_REQUEST);
		}

		reply.setStatus(Status.OK);
		return reply;
	}

}
