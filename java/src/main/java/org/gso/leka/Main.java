package org.gso.leka;

import java.io.IOException;

import javax.naming.NamingException;

import org.gso.leka.http.HttpServer;

public class Main {
	public static void main(String[] args) throws NamingException, IOException {
		AppConfig.initialise();
		new LDAPHelper();
		try {
			new HttpServer(8080).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
