package org.gso.leka;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class AppConfig {
	
	private static AppConfig instance;
	
	private JsonObject config;
	
	public  static AppConfig getConfig() {
		return instance;
	}
	
	public static void initialise() throws IOException {
		instance = new AppConfig();
	}
	
	private AppConfig() throws IOException {
		loadConfig();
	}
	
	public void loadConfig(String path) {
		InputStream isr = getClass().getResourceAsStream("/conf.json");
		JsonReader reader = new JsonReader(new InputStreamReader(isr));
		JsonParser parser = new JsonParser();
		config = parser.parse(reader).getAsJsonObject();
	}
	
	public void loadConfig() throws IOException {
		loadConfig("/conf.json");
	}
	
	public String getFromPath(String path) {
		String[] parts = path.split("/");
		String name = parts[parts.length-1];
		parts = Arrays.copyOf(parts, parts.length-1);
		
		JsonObject current = config;
		for(String part : parts) {
			current = current.getAsJsonObject(part);
		}
		return current.get(name).getAsString();
	}
}
