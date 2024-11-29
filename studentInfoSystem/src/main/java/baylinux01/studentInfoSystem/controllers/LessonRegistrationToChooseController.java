package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.LessonRegistrationToChoose;
import baylinux01.studentInfoSystem.services.LessonRegistrationToChooseService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/lessonRegistrationToChoose")
public class LessonRegistrationToChooseController {
	
	LessonRegistrationToChooseService lessonRegistrationToChooseService;
	
	
	@Autowired
	public LessonRegistrationToChooseController(LessonRegistrationToChooseService lessonRegistrationToChooseService) {
		super();
		this.lessonRegistrationToChooseService = lessonRegistrationToChooseService;
	}



	@PostMapping("/createLessonRegistrationToChoose")
	public String createLessonRegistrationToChoose(HttpServletRequest request, String name)
	{
		return lessonRegistrationToChooseService.createLessonRegistrationToChoose(request,name);
	}
	
	@GetMapping("/getAllLessonRegistrationToChoose")
	public List<LessonRegistrationToChoose> getAllLessonRegistrationsToChoose(HttpServletRequest request)
	{
		return lessonRegistrationToChooseService.getAllLessonRegistrationsToChoose(request);
	}

}
