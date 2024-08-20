package baylinux01.webApiDeneme2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.webApiDeneme2.entities.Teacher;
import baylinux01.webApiDeneme2.services.TeacherService;

@CrossOrigin
@RestController
@RequestMapping("/teachers")
public class TeacherController {
	TeacherService teacherService;
	
	@Autowired
	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}


	@PostMapping("/createteacher")
	String createTeacher(String name, String surname,Long tc,String password)
	{
		return teacherService.createTeacher(name,surname,tc,password);
		
	}

}
