package org.gso.leka.data;

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
	
	
}
