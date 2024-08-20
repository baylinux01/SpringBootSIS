package baylinux01.webApiDeneme2.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique =true, nullable = false)
	private Long studentNumber;
	@Column(unique=true,nullable = false)
	private Long tc;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@JsonIgnore
	private String password;
	@JsonIgnore
	@OneToMany
	private List<Term> terms;
	@JsonIgnore
	@OneToMany
	private List<Lesson> lessons;
	private Double gpa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Long studentNumber) {
		this.studentNumber = studentNumber;
	}
	public Long getTc() {
		return tc;
	}
	public void setTc(Long tc) {
		this.tc = tc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Term> getTerms() {
		return terms;
	}
	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getGpa() {
		return gpa;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
	
	
	
	
}
