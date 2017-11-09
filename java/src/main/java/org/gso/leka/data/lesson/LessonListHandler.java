package org.gso.leka.data.lesson;

import java.util.Map;

import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.JsonElement;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;


public class LessonListHandler extends HttpHandler {
	
	public LessonListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		Response reply = HttpServer.newFixedLengthResponse("");
		
		return reply;
	}

}
