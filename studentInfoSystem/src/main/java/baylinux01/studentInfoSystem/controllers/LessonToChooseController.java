package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.LessonToChoose;
import baylinux01.studentInfoSystem.services.LessonToChooseService;
import jakarta.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/lessonToChoose")
public class LessonToChooseController {
	
	LessonToChooseService lessonToChooseService;

	@Autowired
	public LessonToChooseController(LessonToChooseService lessonToChooseService) {
		super();
		this.lessonToChooseService = lessonToChooseService;
	}
	
	@PostMapping("/createLessonToChoose")
	public String createLessonToChoose
	(
			HttpServletRequest request
			,String code
			, String name
			,long programToChooseId
			,int semiYear
	)
	{
		return lessonToChooseService
				.createLessonToChoose(request,code,name,programToChooseId,semiYear);
	}
	
	@GetMapping("/getAllLessonsToChoose")
	public List<LessonToChoose> getAllLessonsToChoose(HttpServletRequest request)
	{
		return lessonToChooseService.getAllLessonsToChoose(request);
	}
	

}
