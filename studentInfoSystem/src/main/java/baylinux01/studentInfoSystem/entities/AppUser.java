package baylinux01.studentInfoSystem.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique=true,nullable=false)
	private String username;
	@NonNull
	@JsonIgnore
	private String password;
	private String roles;
	private double gpa;
	private String department;
	private String program;
	private LocalDate registration_date;
	private int grade;
	@ManyToOne
	private AppUser supervisor;
	@OneToMany
	private List<AppUser> students;
	@JsonIgnore
	@OneToMany
	private List<Term> terms;
	
	public List<AppUser> getStudents() {
		return students;
	}
	public void setStudents(List<AppUser> students) {
		this.students = students;
	}
	public List<Term> getTerms() {
		return terms;
	}
	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}
	public AppUser getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(AppUser supervisor) {
		this.supervisor = supervisor;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public LocalDate getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(LocalDate registration_date) {
		this.registration_date = registration_date;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
