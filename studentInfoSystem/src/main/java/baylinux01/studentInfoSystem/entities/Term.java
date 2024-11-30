package baylinux01.studentInfoSystem.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManyToOne
	private AppUser student;
	private double term_point_average;
	@OneToOne
	private LessonRegistration lessonRegistration;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AppUser getStudent() {
		return student;
	}
	public void setStudent(AppUser student) {
		this.student = student;
	}
	public double getTerm_point_average() {
		return term_point_average;
	}
	public void setTerm_point_average(double term_point_average) {
		this.term_point_average = term_point_average;
	}
	public LessonRegistration getLessonRegistration() {
		return lessonRegistration;
	}
	public void setLessonRegistration(LessonRegistration lessonRegistration) {
		this.lessonRegistration = lessonRegistration;
	}
	
	
}
