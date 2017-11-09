package org.gso.leka.data.schoolClass;

import java.util.List;

import org.gso.leka.data.student.Student;

public class SchoolClass {
	private String ID;
	private List<Student> students;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public static SchoolClass getClass(String ID) {
		return null;
	}
	
	public static List<SchoolClass> getAll() {
		return null;
	}
}
