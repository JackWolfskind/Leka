package org.gso.leka.data.schoolClass;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		List<String> classIDs = SchoolClass.getIDs();

		if (parameters != null && parameters.containsKey("filter")) {
			String filter = parameters.get("filter").getAsString();

			classIDs = classIDs.stream().filter(new Predicate<String>() {

				@Override
				public boolean test(String id) {
					return id.matches(filter.replace("?", ".?").replace("*", ".*?"));
				}
			}).collect(Collectors.toList());
		}

		Response reply = HttpServer.newFixedLengthResponse(new Gson().toJson(classIDs));

		reply.setStatus(Status.OK);
		return reply;
	}

}
