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

public class LessonListHandler extends HttpHandler {

	public LessonListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer
				.newFixedLengthResponse(new Gson().toJson(
						Lesson.load(Main.entityManagerFactory.createEntityManager(),
						new Date(parameters.get("date").getAsLong()), 
						parameters.get("teacherID").getAsString(),
						parameters.get("blockID").getAsInt())));
		reply.setStatus(Status.OK);
		return reply;
	}

}
