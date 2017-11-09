package org.gso.leka.data.lesson;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.gso.leka.data.block.Block;
import org.gso.leka.data.schoolClass.SchoolClass;
import org.gso.leka.data.teacher.Teacher;

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
	
	public static Lesson load(EntityManager manager, Date date, String TeacherID, int BlockID) {
		String tableName = Lesson.class.getAnnotation(Table.class).name();
		try {
			String teacherIDColumn = Lesson.class.getField("teacherID").getAnnotation(Column.class).name();
			String blockIDColumn =Lesson.class.getField("blockID").getAnnotation(Column.class).name();
			String dateColumn = Lesson.class.getField("date").getAnnotation(Column.class).name();
		
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
			Root<Lesson> from = query.from(Lesson.class);
			
			Predicate where = builder.and(
					builder.equal(from.get(teacherIDColumn), TeacherID),
					builder.equal(from.get(blockIDColumn), BlockID),
					builder.equal(from.get(dateColumn), date)
					);
			query = query.select(from).where(where);
			
			manager.createQuery(query).getResultList();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Lesson> loadAll(EntityManager manager) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> from = query.from(Lesson.class);
		return manager.createQuery(query).getResultList();
	}
}
