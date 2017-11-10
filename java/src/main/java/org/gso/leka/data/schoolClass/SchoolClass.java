package org.gso.leka.data.schoolClass;

import java.util.ArrayList;
import java.util.List;

import org.gso.leka.LDAPHelper;
import org.gso.leka.data.student.Student;

import com.unboundid.ldap.sdk.LDAPException;

public class SchoolClass {
	private String ID;
	private List<Student> students;

	public SchoolClass() {
	};

	public SchoolClass(String iD) {
		super();
		ID = iD;
	}

	public SchoolClass(String iD, List<Student> students) {
		super();
		ID = iD;
		this.students = students;
	}

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
		List<Student> students = new ArrayList<>();
		try {
			students = new LDAPHelper().studentsOfClass(ID);
		} catch (LDAPException e) {
			e.printStackTrace();
		}

		return new SchoolClass(ID, students);
	}

	public static List<String> getIDs() {
		List<String> reply = new ArrayList<>();
		try {
			reply.addAll(new LDAPHelper().getKlassenAbteilungen().keySet());
		} catch (LDAPException e) {
			e.printStackTrace();
		}

		return reply;
	}
}
