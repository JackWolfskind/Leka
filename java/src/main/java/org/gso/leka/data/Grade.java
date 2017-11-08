package org.gso.leka.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="noten")
public class Grade {
	
	@Id
	@Column(name="n_id")
	private int ID;
	
	@Column(name="n_note")
	private String grade;
	
	@Column(name="n_bemerkung")
	private String comment;

	@Column(name="s_id")
	private String LessonID;
	@Transient
	private Lesson Lesson;
	
	@Column(name="n_schueler")
	private String StudentID;
	@Transient
	private Student student;
	
	
}
