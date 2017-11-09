package org.gso.leka.data.schoolClass;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.gso.leka.data.Lesson;
import org.gso.leka.data.Student;

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
	
	public void save(EntityManager manager) {
		manager.getTransaction().begin();
		try {
			manager.persist(this);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public static SchoolClass load(EntityManager manager, int ID) {
		return manager.find(SchoolClass.class, ID);
	}
	
	public static List<SchoolClass> loadAll(EntityManager manager) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SchoolClass> query = builder.createQuery(SchoolClass.class);
		Root<SchoolClass> from = query.from(SchoolClass.class);
		return manager.createQuery(query).getResultList();
	}
}
