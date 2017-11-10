package org.gso.leka.data.lesson;

import java.sql.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.gso.leka.Main;
import org.gso.leka.data.block.Block;
import org.gso.leka.data.grade.Grade;
import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class LessonCreateHandler extends HttpHandler {

	public LessonCreateHandler() {
		super("create");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer.newFixedLengthResponse("");

		EntityManager manager = Main.entityManagerFactory.createEntityManager();

		if (parameters.containsKey("date") && parameters.containsKey("block") && parameters.containsKey("teacher")) {
			if (Block.load(manager, parameters.get("blockID").getAsInt()) == null) {
				reply = HttpServer.newFixedLengthResponse(Status.BAD_REQUEST.getDescription() + " - Invalid Block ID");
				reply.setStatus(Status.BAD_REQUEST);
			} else {
				Lesson lesson = new Lesson();
				lesson.setBlockID(parameters.get("blockID").getAsInt());
				lesson.setDate(new Date(parameters.get("date").getAsLong()));
				lesson.setTeacherID(parameters.get("teacher").getAsString());
			}
		} else {
			reply = HttpServer.newFixedLengthResponse(
					Status.BAD_REQUEST.getDescription() + " - Not all manditory Fields Provided");
			reply.setStatus(Status.BAD_REQUEST);
		}

		reply.setStatus(Status.OK);
		return reply;
	}

}
