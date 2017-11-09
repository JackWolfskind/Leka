package org.gso.leka.data;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.gso.leka.data.schoolClass.SchoolClass;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "schulstunde")
public class Lesson {
	@Id
	@Column(name = "s_id")
	private int LessonID;

	@Column(name = "s_datum")
	private Date date;
	
	@Column(name = "s_thema", columnDefinition="text")
	private String topic;

	@Column(name = "s_lehrer")
	private String teacherID;
	@Transient
	private Teacher teacher;

	@Column(name = "s_klasse")
	private String classID;
	@Transient
	private SchoolClass schoolclass;

	@Column(name = "adm_st_id")
	private int blockID;
	@Transient
	private Block block;
	
	@Column(name = "s_freigabe")
	private String permission;
	
	public void save(EntityManager manager) {
		manager.getTransaction().begin();
		try {
			manager.persist(this);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public Lesson load(EntityManager manager, int ID) {
		return manager.find(Lesson.class, ID);
	}
	
	public Lesson load(EntityManager manager, Date date, int TeacherID, int BlockID) {
		String tableName = this.getClass().getAnnotation(Table.class).name();
		try {
			String teacherIDColumn = this.getClass().getField("TeacherID").getAnnotation(Column.class).name();
			String blockIDColumn = this.getClass().getField("BlockID").getAnnotation(Column.class).name();
			String dateColumn = this.getClass().getField("date").getAnnotation(Column.class).name();
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> from = query.from(Lesson.class);
		return null;
	}
	
	public static List<Lesson> loadAll(EntityManager manager) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> from = query.from(Lesson.class);
		return manager.createQuery(query).getResultList();
	}
}
