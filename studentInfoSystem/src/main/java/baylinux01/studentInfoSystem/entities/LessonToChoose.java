package baylinux01.studentInfoSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LessonToChoose {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String department;
	@Column(unique=true,nullable=false)
	private String code;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private int semi_year;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public int getSemi_year() {
		return semi_year;
	}
	public void setSemi_year(int semi_year) {
		this.semi_year = semi_year;
	}
	
	
	
}
