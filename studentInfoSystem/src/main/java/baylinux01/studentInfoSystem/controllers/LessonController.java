package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.Lesson;
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
	
	@PutMapping("/giveMidtermNoteToLesson")
	public String giveMidtermNoteToLesson(HttpServletRequest request,long lessonId, double midtermNote)
	{
		return lessonService.giveMidtermNoteToLesson(request,lessonId,midtermNote);
	}
	
	@PutMapping("/giveFinalNoteToLesson")
	public String giveFinalNoteToLesson(HttpServletRequest request,long lessonId, double finalNote)
	{
		return lessonService.giveFinalNoteToLesson(request,lessonId,finalNote);
	}
	@GetMapping("/getLessonsOfAStudentForACertainTerm")
	public List<Lesson> getLessonsOfAStudentForACertainTerm(HttpServletRequest request,long termId,String studentUsername)
	{
		return lessonService.getLessonsOfAStudentForACertainTerm(request,termId,studentUsername);
	}
	@GetMapping("/getAllLessons")
	public List<Lesson> getAllLessons(HttpServletRequest request)
	{
		return lessonService.getAllLessons(request);
	}
	@PutMapping("/determineNotes")
	public String determineNotes(HttpServletRequest request)
	{
		return lessonService.determineNotes(request);
	}
	
}
