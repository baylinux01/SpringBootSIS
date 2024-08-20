package baylinux01.webApiDeneme2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.services.StudentService;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

	StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@PostMapping("/createstudent")
	String createStudent(Long studentNumber,Long tc,String name
			, String surname,String password)
	{
		return studentService.createStudent(studentNumber,tc,name,surname,password);
	}
	@PostMapping("/addnewtermtostudent")
	String addNewTermToStudent(Long studentNumber, Long termStartYear
			, Long termEndYear, String termSeason)
	{
		return studentService.addNewTermToStudent(studentNumber
				,termStartYear,termEndYear,termSeason);
	}
	
	@GetMapping("/getallstudents")
	List<Student> getAllStudents()
	{
		return studentService.getAllStudents();
	}
	@PutMapping("/updategpaofstudents")
	String updateGPAOfStudents()
	{
		return studentService.updateGPAOfStudents();
	}
}
