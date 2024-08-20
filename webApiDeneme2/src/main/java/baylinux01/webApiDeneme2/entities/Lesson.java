package baylinux01.webApiDeneme2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long semiYear;
	
	private String code;
	private String name;
	@ManyToOne
	private Term term;
	
	@ManyToOne
	private Teacher teacher;
	private Long acts;
	
	@ManyToOne
	private Student student;
	private Double midTermNote;
	private Double finalNote;
	private Double butNote;
	private String note;
	
	private Double lowerBoundforAA;
	private Double lowerBoundforBA;
	private Double lowerBoundforBB;
	private Double lowerBoundforCB;
	private Double lowerBoundforCC;
	private Double lowerBoundforDC;
	private Double lowerBoundforDD;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSemiYear() {
		return semiYear;
	}
	public void setSemiYear(Long semiYear) {
		this.semiYear = semiYear;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Long getActs() {
		return acts;
	}
	public void setActs(Long acts) {
		this.acts = acts;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Double getMidTermNote() {
		return midTermNote;
	}
	public void setMidTermNote(Double midTermNote) {
		this.midTermNote = midTermNote;
	}
	public Double getFinalNote() {
		return finalNote;
	}
	public void setFinalNote(Double finalNote) {
		this.finalNote = finalNote;
	}
	public Double getButNote() {
		return butNote;
	}
	public void setButNote(Double butNote) {
		this.butNote = butNote;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Double getLowerBoundforAA() {
		return lowerBoundforAA;
	}
	public void setLowerBoundforAA(Double lowerBoundforAA) {
		this.lowerBoundforAA = lowerBoundforAA;
	}
	public Double getLowerBoundforBA() {
		return lowerBoundforBA;
	}
	public void setLowerBoundforBA(Double lowerBoundforBA) {
		this.lowerBoundforBA = lowerBoundforBA;
	}
	public Double getLowerBoundforBB() {
		return lowerBoundforBB;
	}
	public void setLowerBoundforBB(Double lowerBoundforBB) {
		this.lowerBoundforBB = lowerBoundforBB;
	}
	public Double getLowerBoundforCB() {
		return lowerBoundforCB;
	}
	public void setLowerBoundforCB(Double lowerBoundforCB) {
		this.lowerBoundforCB = lowerBoundforCB;
	}
	public Double getLowerBoundforCC() {
		return lowerBoundforCC;
	}
	public void setLowerBoundforCC(Double lowerBoundforCC) {
		this.lowerBoundforCC = lowerBoundforCC;
	}
	public Double getLowerBoundforDC() {
		return lowerBoundforDC;
	}
	public void setLowerBoundforDC(Double lowerBoundforDC) {
		this.lowerBoundforDC = lowerBoundforDC;
	}
	public Double getLowerBoundforDD() {
		return lowerBoundforDD;
	}
	public void setLowerBoundforDD(Double lowerBoundforDD) {
		this.lowerBoundforDD = lowerBoundforDD;
	}
	
	
	
	
	
	
}
