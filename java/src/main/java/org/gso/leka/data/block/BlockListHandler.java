package org.gso.leka.data.block;

import java.util.Date;
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

public class BlockListHandler extends HttpHandler {

	public BlockListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getInputStream());
		List<Block> blocks;

		if (parameters == null) {
			blocks = Block.loadAll(Main.entityManagerFactory.createEntityManager(), null, null, null);
		} else {
			Date start = parameters.containsKey("start") ? new Date(parameters.get("start").getAsLong()) : null;
			Date end = parameters.containsKey("end") ? new Date(parameters.get("end").getAsLong()) : null;
			
			blocks = Block.loadAll(Main.entityManagerFactory.createEntityManager(), start, end,
					parameters.get("name").getAsString());
		}

		Response reply = HttpServer.newFixedLengthResponse(new Gson().toJson(blocks));
		reply.setStatus(Status.OK);

		return reply;
	}

}
