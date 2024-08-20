package baylinux01.webApiDeneme2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.webApiDeneme2.constants.Notes;
import baylinux01.webApiDeneme2.entities.Lesson;
import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.repositories.StudentRepository;
import baylinux01.webApiDeneme2.services.LessonService;

@CrossOrigin
@RestController
@RequestMapping("/lessons")
public class LessonController {

	LessonService lessonService;

	@Autowired
	public LessonController(LessonService lessonService) {
		super();
		this.lessonService = lessonService;
	}
	
	@PostMapping("/createlesson")
	String createLesson(Long semiYear,String code
			,String name,Long acts
			,Long studentNumber,Long termId,Long teacherId)
	{
		return lessonService.createLesson(semiYear, code, name, acts
				,studentNumber, termId,teacherId);
	}
	
	@PostMapping("/givemidtermscoretolesson")
	String giveMidTermScoreToLesson(Long lessonId,Double score)
	{
		return lessonService.giveMidTermScoreToLesson(lessonId,score);
	}
	
	@PostMapping("/givefinalscoretolesson")
	String giveFinalScoreToLesson(Long lessonId,Double score)
	{
		return lessonService.giveFinalScoreToLesson(lessonId,score);
	}
	
	@GetMapping("/getalllessons")
	List<Lesson> getAllLessons()
	{
		return lessonService.getAllLessons();
	}
	
	@GetMapping("/getlessonsofastudent")
	List<Lesson> getLessonsOfAStudent(Long studentNumber)
	{
		return lessonService.getLessonsOfAStudent(studentNumber);
	}
	
	@GetMapping("/getspecificlessonsofspecificterms")
	List<Lesson> getSpecificLessonsOfSpecificTerms(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason)
	{
		return lessonService.getSpecificLessonsOfSpecificTerms(lessonCode,termStartYear
				,termEndYear,termSeason);
	}
	@PostMapping("/calculatelowerboundsanddeterminenotesofspecificlessons")
	void calculateLowerBoundsAndDetermineNotesOfSpecificLessons(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason)
	{
		lessonService.calculateLowerBoundsAndDetermineNotesOfSpecificLessons
		(lessonCode
				,termStartYear,termEndYear,termSeason);
		
		
	}
	
	
	@GetMapping("/getspecificlesson")
	Lesson getSpecificLesson(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason,Long studentNumber)
	{
		return lessonService.getSpecificLesson
		(lessonCode
				,termStartYear,termEndYear,termSeason,studentNumber);
		
		
	}
	@PutMapping("putmidtermscoretospecificlesson")
	String putMidTermScoreToSpecificLesson(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason,Long studentNumber,Double midTermScore)
	{
		return lessonService.putMidTermScoreToSpecificLesson(lessonCode
			,termStartYear,termEndYear, termSeason,studentNumber,midTermScore);
	}
	
	@PutMapping("putfinalscoretospecificlesson")
	String putFinalScoreToSpecificLesson(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason,Long studentNumber
			,Double finalScore)
	{
		return lessonService.putFinalScoreToSpecificLesson(lessonCode
			,termStartYear,termEndYear, termSeason,studentNumber,finalScore);
	}
	
	@PutMapping("putbutscoretospecificlesson")
	String putButScoreToSpecificLesson(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason,Long studentNumber
			,Double butScore)
	{
		return lessonService.putButScoreToSpecificLesson(lessonCode
			,termStartYear,termEndYear, termSeason,studentNumber,butScore);
	}
	@GetMapping("/getlessonsofaterm")
	List<Lesson> getLessonsOfATerm(Long termId)
	{
		return lessonService.getLessonsOfATerm(termId);
		
	}
	@PostMapping("/determinenotesafterbutofspecificlessons")
	void determineNotesAfterButOfSpecificLessons(String lessonCode
			,Long termStartYear,Long termEndYear, String termSeason)
	{
		lessonService.determineNotesAfterButOfSpecificLessons
		(lessonCode
				,termStartYear,termEndYear,termSeason);
		
		
	}
	
	
}
