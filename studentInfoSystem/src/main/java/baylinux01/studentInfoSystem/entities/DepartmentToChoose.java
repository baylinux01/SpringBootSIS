package baylinux01.studentInfoSystem.entities;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DepartmentToChoose {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false,unique = true)
	private String department_name;
	@Column(nullable = false)
	private int year;
	@Column(nullable=false)
	private boolean has_preparation;
	
	public boolean isHas_preparation() {
		return has_preparation;
	}
	public void setHas_preparation(boolean has_preparation) {
		this.has_preparation = has_preparation;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	

}
