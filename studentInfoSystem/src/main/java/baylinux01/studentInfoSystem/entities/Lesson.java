package baylinux01.studentInfoSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	private String program;
	private String name;
	private int semi_year;
	private double midterm_note=-1;
	private double final_note=-1;
	private double but_note=-1;
	private double note=-1;
	private String letter_grade;
	private LessonStatus status=LessonStatus.SORUMLU;
	
	
	
	
	public LessonStatus getStatus() {
		return status;
	}
	public void setStatus(LessonStatus status) {
		this.status = status;
	}
	@ManyToOne
	private LessonRegistration lesson_registration;
	@ManyToOne
	private AppUser teacher;
	
	
	public double getBut_note() {
		return but_note;
	}
	public void setBut_note(double but_note) {
		this.but_note = but_note;
	}
	public double getMidterm_note() {
		return midterm_note;
	}
	public void setMidterm_note(double midterm_note) {
		this.midterm_note = midterm_note;
	}
	public AppUser getTeacher() {
		return teacher;
	}
	public void setTeacher(AppUser teacher) {
		this.teacher = teacher;
	}
	public LessonRegistration getLesson_registration() {
		return lesson_registration;
	}
	public void setLesson_registration(LessonRegistration lesson_registration) {
		this.lesson_registration = lesson_registration;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSemi_year() {
		return semi_year;
	}
	public void setSemi_year(int semi_year) {
		this.semi_year = semi_year;
	}
	
	public double getFinal_note() {
		return final_note;
	}
	public void setFinal_note(double final_note) {
		this.final_note = final_note;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public String getLetter_grade() {
		return letter_grade;
	}
	public void setLetter_grade(String letter_grade) {
		this.letter_grade = letter_grade;
	}
	
	

}
