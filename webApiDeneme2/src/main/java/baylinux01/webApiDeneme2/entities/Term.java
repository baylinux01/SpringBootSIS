package baylinux01.webApiDeneme2.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(name="generalInfo",columnNames = {
//		"startYear","endYear","season","student"
//})})
public class Term {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long startYear;
	@Column(nullable = false)
	private Long endYear;
	@Column(nullable = false)
	private String season;
	@JsonIgnore
	@OneToMany
	private List<Lesson> lessons;
	@ManyToOne
	private Student student;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStartYear() {
		return startYear;
	}
	public void setStartYear(Long startYear) {
		this.startYear = startYear;
	}
	public Long getEndYear() {
		return endYear;
	}
	public void setEndYear(Long endYear) {
		this.endYear = endYear;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	

	
}
