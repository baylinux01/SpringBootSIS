package baylinux01.studentInfoSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.services.LessonRegistrationService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/lessonRegistration")
public class LessonRegistrationController {
	
	LessonRegistrationService lessonRegistrationService;

	@Autowired
	public LessonRegistrationController(LessonRegistrationService lessonRegistrationService) {
		super();
		this.lessonRegistrationService = lessonRegistrationService;
	}
	
	@PostMapping("/createLessonRegistrationsForCertainTerm")
	public String createLessonRegistrationsForCertainTerm(HttpServletRequest request,
			long lessonRegistrationToChooseId)
	{
		return lessonRegistrationService
				.createLessonRegistrationsForCertainTerm(request,lessonRegistrationToChooseId);
	}
	
	

}
