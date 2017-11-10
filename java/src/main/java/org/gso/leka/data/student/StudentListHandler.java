package org.gso.leka.data.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gso.leka.http.HttpHandler;
import org.gso.leka.http.HttpServer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.unboundid.ldap.sdk.LDAPException;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.NanoHTTPD.Response.Status;


public class StudentListHandler extends HttpHandler {
	
	public StudentListHandler() {
		super("list");
	}

	@Override
	public Response Handle(IHTTPSession session, String route) {
		Map<String, JsonElement> parameters = parseParameters(session.getParameters().get("parameters").get(0));
		List<String> classIDs = new ArrayList<>();
		
		if (parameters.containsKey("schoolclass")) {
			
		}
		try {
			for(Student stu : parameters.containsKey("schoolclass")? Student.getAll(parameters.get("schoolclass").getAsString()) : Student.getAll()) {
				classIDs.add(stu.getID());
			}
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response reply = HttpServer.newFixedLengthResponse(new Gson().toJson(classIDs));
		
		reply.setStatus(Status.OK);
		return reply;
	}

}
