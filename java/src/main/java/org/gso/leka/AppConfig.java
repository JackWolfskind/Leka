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
	
	/**
	 * Gibt die Instanz der Konfiguration zurück. Vorher muss initialise() aufgerufen werden!
	 * @return
	 */
	public  static AppConfig getConfig() {
		return instance;
	}
	
	/**
	 * Initialisiert die Konfiguration und lädt die Konfigdatei.
	 * @throws IOException
	 */
	public static void initialise() throws IOException {
		instance = new AppConfig();
	}
	
	private AppConfig() throws IOException {
		loadConfig();
	}
	
	/**
	 * Lädt die Konfiguration aus dem übergebenen Pfad und speichert sie in der AppConfig.
	 * Muss vor Verwendung der Konfig aufgerrufen werden
	 * Wird von der initialise() aufgerufen
	 * @see initialise
	 * @param path
	 */
	public void loadConfig(String path) {
		InputStream isr = getClass().getResourceAsStream("/conf.json");
		JsonReader reader = new JsonReader(new InputStreamReader(isr));
		JsonParser parser = new JsonParser();
		config = parser.parse(reader).getAsJsonObject();
	}
	
	/**
	 * Lädt die Konfiguration aus dem Standarpfad (/conf.json)
	 * @throws IOException
	 */
	public void loadConfig() throws IOException {
		loadConfig("/conf.json");
	}
	
	/**
	 * Lädt einen Konfigurationswert aus der Konfigurationsdatei. Der Pfad legt eventuelle Verschachtelungen fest
	 * z.B.
	 * {
	 * 	config {
	 * 		host {
	 * 			"hostname": "localhost"
	 * 		}
	 * 	}
	 * }
	 * Entspricht dem Pfad config/host/hostname
	 * 
	 * @param path Durch / getrennter Pfad zum Konfigurationsschlüssel
	 * @return Den Wert des durch path angegeben Schlüssels
	 * @author Lukas Prediger
	 */
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
	
	public int getIntFromPath(String path) {
		return Integer.parseInt(getFromPath(path));
	}
	
	public long getLongFromPath(String path) {
		return Long.parseLong(getFromPath(path));
	}
}
