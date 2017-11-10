package org.gso.leka.data.lesson;

import java.sql.Date;
import java.util.ArrayList;
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

	@Column(name = "s_thema", columnDefinition = "text")
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

	public static List<Lesson> load(EntityManager manager, Date date, String TeacherID, int BlockID) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> from = query.from(Lesson.class);
		List<Predicate> predicates = new ArrayList<>();
		if (date != null) {
			predicates.add(builder.equal(from.get("date"), date));
		}
		if (TeacherID != null) {
			predicates.add(builder.equal(from.get("teacherID"), TeacherID));
		}
		if (BlockID != 0) {
			predicates.add(builder.equal(from.get("blockID"), BlockID));
		}

		Predicate where = builder.and(predicates.toArray(new Predicate[predicates.size()]));
		query = query.select(from).where(where);
		List<Lesson> result = manager.createQuery(query).getResultList();
		return result;
	}

	public static List<Lesson> loadAll(EntityManager manager) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> from = query.from(Lesson.class);
		return manager.createQuery(query).getResultList();
	}

	public int getLessonID() {
		return LessonID;
	}

	public void setLessonID(int lessonID) {
		LessonID = lessonID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public SchoolClass getSchoolclass() {
		return schoolclass;
	}

	public void setSchoolclass(SchoolClass schoolclass) {
		this.schoolclass = schoolclass;
	}

	public int getBlockID() {
		return blockID;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
}
