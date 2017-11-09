package org.gso.leka;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gso.leka.data.schoolClass.SchoolClass;
import org.gso.leka.data.schoolClass.SchoolClassListHandler;
import org.gso.leka.data.schoolClass.SchoolClassReadHandler;
import org.gso.leka.http.HttpRouter;
import org.gso.leka.http.HttpServer;

public class Main {
	static HttpServer server;
	
	public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("leka");
	
	public static void main(String[] args) throws NamingException, IOException {
		AppConfig.initialise();
		
		server = new HttpServer(AppConfig.getConfig().getIntFromPath("httpServer/port"));
		
		HttpRouter ClassRouter = new HttpRouter("schoolclass");
		ClassRouter.registerHandler(new SchoolClassReadHandler());
		ClassRouter.registerHandler(new SchoolClassListHandler());
		
		server.getRouter().registerHandler(ClassRouter);
		System.out.println(server.getListeningPort());
		
		List<String> classes = SchoolClass.getIDs();
		statusCheck();
		server.start();
	}

	public static void statusCheck() {
		Logger log = LogManager.getRootLogger();
		try {
			Persistence.createEntityManagerFactory("leka").createEntityManager().createNativeQuery("SELECT 1 from dual");
		} catch (Exception e) {
			log.info("Database Connection: ERROR");
		}
		
		log.info("Database Connection: Ok");
		
		if (server.isAlive()) {
			log.info("Http Server: Ok");
		} else {
			log.info("Http Server: ERROR");
		}
		
	}
}
