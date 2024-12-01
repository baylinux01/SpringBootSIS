package baylinux01.studentInfoSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.services.LessonService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/lesson")
public class LessonController {
	
	LessonService lessonService;

	@Autowired
	public LessonController(LessonService lessonService) {
		super();
		this.lessonService = lessonService;
	}
	
	@PostMapping("/addLessonToLessonRegistration")
	public String addLessonToLessonRegistration(HttpServletRequest request
			,long lessonToChooseId,long lessonRegistrationId,String teacherUsername)
	{
		return lessonService
				.addLessonToLessonRegistration(request,lessonToChooseId,lessonRegistrationId,teacherUsername);
	}
	
	
}
