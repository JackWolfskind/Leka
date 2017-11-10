package org.gso.leka.data.lesson;

import java.sql.Date;
import java.util.List;
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

		List<Lesson> lessons;

		if (parameters == null) {
			lessons = Lesson.load(Main.entityManagerFactory.createEntityManager(), null, null, 0);
		} else {
			Date start = parameters.containsKey("date") ? new Date(parameters.get("date").getAsLong()) : null;
			lessons = Lesson.load(Main.entityManagerFactory.createEntityManager(),
					new Date(parameters.get("date").getAsLong()), parameters.get("teacherID").getAsString(),
					parameters.get("blockID").getAsInt());
		}

		Response reply = HttpServer.newFixedLengthResponse(new Gson().toJson(lessons));

		reply.setStatus(Status.OK);
		return reply;
	}

}
