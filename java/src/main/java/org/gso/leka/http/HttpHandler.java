package org.gso.leka.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public abstract class HttpHandler implements IHttpHandler {
	
	private String route;
	
	public static Map<String, JsonElement> parseParameters(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		try {
			StringBuilder sb = new StringBuilder();
			while (isr.ready()) {
				sb.append((char) isr.read());
			}
			if (!sb.toString().isEmpty()) return parseParameters(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, JsonElement> parseParameters(String json) {
		Map<String, JsonElement> reply = new HashMap<>();
		JsonParser parser = new JsonParser();
		JsonObject parameters = parser.parse(json).getAsJsonObject();
		Set<Entry<String, JsonElement>> entry = parameters.entrySet();
		
		Iterator<Entry<String, JsonElement>> iterator = entry.iterator();
		
		while(iterator.hasNext()) {
			Entry<String, JsonElement> ent = iterator.next();
			reply.put(ent.getKey(), ent.getValue());
		}
		return reply;
	} 
	
	public HttpHandler(String route) {
		this.route = route;
	}

	public abstract Response Handle(IHTTPSession session, String route);

	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}

}
