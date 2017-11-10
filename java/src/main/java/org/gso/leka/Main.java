package org.gso.leka;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.gso.leka.data.block.BlockListHandler;
import org.gso.leka.data.block.BlockReadHandler;
import org.gso.leka.data.grade.GradeListHandler;
import org.gso.leka.data.grade.GradeReadHandler;
import org.gso.leka.data.lesson.LessonListHandler;
import org.gso.leka.data.lesson.LessonReadHandler;
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

		//registering router
		server.registerRouter("schoolclass", new SchoolClassReadHandler(), new SchoolClassListHandler());
		server.registerRouter("block", new BlockReadHandler(), new BlockListHandler());
		server.registerRouter("grade", new GradeReadHandler(), new GradeListHandler());
		server.registerRouter("lesson", new LessonReadHandler(), new LessonListHandler());
		
		server.start();
	}

}
