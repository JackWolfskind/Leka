package org.gso.leka.data.student;

import java.util.ArrayList;
import java.util.List;

import org.gso.leka.LDAPHelper;
import org.gso.leka.data.schoolClass.SchoolClass;

import com.unboundid.ldap.sdk.LDAPException;

public class Student {
	private String ID;
	private String Name;
	
	public Student() {};
	
	public Student(String iD) {
		this.ID = iD;
	}
	
	public Student(String iD, String name) {
		this.ID = iD;
		this.Name = name;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public static List<Student> getAll() {
		return null;
	}

	public static Student get(String asString) {
		return null;
	}
	
	public static List<Student> getAll(String ClassID) throws LDAPException {
		return new LDAPHelper().studentsOfClass(ClassID);
	}
	
	
}
