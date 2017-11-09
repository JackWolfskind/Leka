package org.gso.leka;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.gso.leka.data.Lesson;
import org.gso.leka.http.HttpServer;

public class Main {
	public static void main(String[] args) throws NamingException, IOException {
		AppConfig.initialise();
		//new LDAPHelper();
		List<Lesson> lesson = Lesson.loadAll(Persistence.createEntityManagerFactory("leka").createEntityManager());
		try {
			new HttpServer(8080).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
