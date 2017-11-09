package org.gso.leka.data.grade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.gso.leka.data.lesson.Lesson;
import org.gso.leka.data.student.Student;

@Entity
@Table(name="noten")
public class Grade {
	
	@Id
	@Column(name="n_id")
	private int ID;
	
	@Column(name="n_note")
	private String grade;
	
	@Column(name="n_bemerkung", columnDefinition="text")
	private String comment;

	@Column(name="s_id")
	private int LessonID;
	@Transient
	private Lesson Lesson;
	
	@Column(name="n_schueler")
	private String StudentID;
	@Transient
	private Student student;
	
	
	
}
