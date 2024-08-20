package baylinux01.webApiDeneme2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.webApiDeneme2.constants.Notes;
import baylinux01.webApiDeneme2.constants.Seasons;
import baylinux01.webApiDeneme2.entities.Lesson;
import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.entities.Teacher;
import baylinux01.webApiDeneme2.entities.Term;
import baylinux01.webApiDeneme2.repositories.LessonRepository;
import baylinux01.webApiDeneme2.repositories.StudentRepository;
import baylinux01.webApiDeneme2.repositories.TeacherRepository;
import baylinux01.webApiDeneme2.repositories.TermRepository;

@Service
public class LessonService {

	LessonRepository lessonRepository;
	StudentRepository studentRepository;
	TermRepository termRepository;
	TeacherRepository teacherRepository;
	
	@Autowired
	public LessonService(LessonRepository lessonRepository, StudentRepository studentRepository,
			TermRepository termRepository,TeacherRepository teacherRepository) {
		super();
		this.lessonRepository = lessonRepository;
		this.studentRepository = studentRepository;
		this.termRepository = termRepository;
		this.teacherRepository=teacherRepository;
	}


	public String createLesson(Long semiYear, String code, String name,Long acts 
			,Long studentNumber, Long termId,Long teacherId) {
		Student student=studentRepository.findByStudentNumber(studentNumber);
		Teacher teacher=teacherRepository.findById(teacherId).orElse(null);
		Term term=termRepository.findById(termId).orElse(null);
		if(student!=null&&term!=null&&term.getStudent()==student&&teacher!=null)
		{
			if((term.getSeason().equalsIgnoreCase(Seasons.GUZ)&&semiYear%2!=0)
					||(term.getSeason().equalsIgnoreCase(Seasons.BAHAR)&&semiYear%2==0)
					||term.getSeason().equalsIgnoreCase(Seasons.MUAF))
			{
				Lesson lesson=new Lesson();
				lesson.setSemiYear(semiYear);
				lesson.setCode(code);
				lesson.setName(name);
				lesson.setActs(acts);
				lesson.setStudent(student);
				lesson.setTerm(term);
				lesson.setTeacher(teacher);
				lessonRepository.save(lesson);
				return "success";
			}
			else return "fail";
		}else return "fail";
	}


	public String giveMidTermScoreToLesson(Long lessonId, Double score) {
		Lesson lesson=lessonRepository.findById(lessonId).orElse(null);
		if(lesson!=null) 
		{
			lesson.setMidTermNote(score);
			return "success";
		}
		return "fail";
		
	}


	public String giveFinalScoreToLesson(Long lessonId, Double score) {
		Lesson lesson=lessonRepository.findById(lessonId).orElse(null);
		if(lesson!=null) 
		{
			lesson.setFinalNote(score);
			return "success";
		}
		return "fail";
	}


	public List<Lesson> getAllLessons() {
		List<Lesson> lessons=lessonRepository.findAll();
		return lessons;
	}


	public List<Lesson> getLessonsOfAStudent(Long studentNumber) {
		List<Lesson> lessons=lessonRepository.findByStudentStudentNumber(studentNumber);
		return lessons;
	}


	public List<Lesson> getSpecificLessonsOfSpecificTerms(String lessonCode, Long termStartYear, Long termEndYear,
			String termSeason) {
		
		List<Lesson> lessons1=lessonRepository.findByCode(lessonCode);
		List<Term> terms=termRepository.findByStartYearAndEndYearAndSeason(termStartYear,termEndYear,termSeason);
		
		List<Lesson> lessons3=new ArrayList<Lesson>();
		for(Lesson l: lessons1)
		{
			for(Term t : terms)
			{
				if(l.getTerm()==t)
					lessons3.add(l);
			}
		}
		return lessons3;
	}


	public void calculateLowerBoundsAndDetermineNotesOfSpecificLessons(String lessonCode
			, Long termStartYear,
			Long termEndYear, String termSeason) {
		// TODO Auto-generated method stub
		List<Lesson> lessons=getSpecificLessonsOfSpecificTerms(lessonCode,termStartYear
				,termEndYear,termSeason);
		Double total=0.0;
		Long count=0L;
		for(Lesson l: lessons)
		{
			if(l.getMidTermNote()!=null &&l.getFinalNote()!=null)
			{
				total=l.getMidTermNote()*0.4+l.getFinalNote()*0.6;
				count+=1;
			}
			else
			{
				
			}
			
		}
		for(Lesson les:lessons)
		{
			
			les.setLowerBoundforDD((total/(count*3))*0.2);
			les.setLowerBoundforDC((total/(count*2))*0.2);
			les.setLowerBoundforCC((total/(count))*0.2);
			les.setLowerBoundforCB(total/(((double)count/2))*0.2);
			les.setLowerBoundforBB(total/(((double)count/3))*0.2);
			les.setLowerBoundforBA(total/(((double)count/4))*0.2);
			les.setLowerBoundforAA(total/(((double)count/5))*0.2);
			lessonRepository.save(les);
		}
		for(Lesson less: lessons)
		{
			if(less.getMidTermNote()!=null &&less.getFinalNote()!=null)
			{
				Double score=less.getMidTermNote()*0.4+less.getFinalNote()*0.6;
				if(score>less.getLowerBoundforAA()) less.setNote(Notes.AA);
				else if (score>less.getLowerBoundforBA()) less.setNote(Notes.BA);
				else if (score>less.getLowerBoundforBB()) less.setNote(Notes.BB);
				else if (score>less.getLowerBoundforCB()) less.setNote(Notes.CB);
				else if (score>less.getLowerBoundforCC()) less.setNote(Notes.CC);
				else if (score>less.getLowerBoundforDC()) less.setNote(Notes.DC);
				else if (score>less.getLowerBoundforDD()) less.setNote(Notes.DD);
				else if (score<less.getLowerBoundforDD()) less.setNote(Notes.FF);
				lessonRepository.save(less);
			}
			else
			{
				
			}
		}
	}


	public Lesson getSpecificLesson(String lessonCode, Long termStartYear, Long termEndYear, String termSeason,
			Long studentNumber) {
		// TODO Auto-generated method stub
		List<Lesson> lessons=getSpecificLessonsOfSpecificTerms(lessonCode, termStartYear
				, termEndYear, termSeason);
		Student student= studentRepository.findByStudentNumber(studentNumber);
		Lesson lesson=null;
		for(Lesson l: lessons)
		{
			if(l.getStudent()==student)
			{
				lesson=l;
			}
		}
		return lesson;
		
	}


	public String putMidTermScoreToSpecificLesson(String lessonCode, Long termStartYear
			, Long termEndYear,
			String termSeason, Long studentNumber,Double midTermScore) {
		// TODO Auto-generated method stub
		Lesson lesson=getSpecificLesson(lessonCode, termStartYear, termEndYear, termSeason,
				studentNumber);
		if(lesson!=null)
		{
			lesson.setMidTermNote(midTermScore);
			lessonRepository.save(lesson);
			return "success";
		}
		return "fail";
	}


	public String putFinalScoreToSpecificLesson(String lessonCode, Long termStartYear, Long termEndYear,
			String termSeason, Long studentNumber, Double finalScore) {
		// TODO Auto-generated method stub
		Lesson lesson=getSpecificLesson(lessonCode, termStartYear, termEndYear, termSeason,
				studentNumber);
		if(lesson!=null)
		{
			lesson.setFinalNote(finalScore);
			lessonRepository.save(lesson);
			return "success";
		}
		return "fail";
	}


	public List<Lesson> getLessonsOfATerm(Long termId) {
		// TODO Auto-generated method stub
		Term term=termRepository.findById(termId).orElse(null);
		List<Lesson> lessons=null;
		if(term!=null)
		{
			lessons=lessonRepository.findByTerm(term);
		}
		return lessons;
	}


	public String putButScoreToSpecificLesson(String lessonCode, Long termStartYear, Long termEndYear,
			String termSeason, Long studentNumber, Double butScore) {
		// TODO Auto-generated method stub
		
		Lesson lesson=getSpecificLesson(lessonCode, termStartYear, termEndYear, termSeason,
				studentNumber);
		if(lesson!=null)
		{
			lesson.setButNote(butScore);
			lessonRepository.save(lesson);
			return "success";
		}
		return "fail";
	}


	public void determineNotesAfterButOfSpecificLessons(String lessonCode, Long termStartYear, Long termEndYear,
			String termSeason) {
		// TODO Auto-generated method stub
		List<Lesson> lessons=getSpecificLessonsOfSpecificTerms(lessonCode,termStartYear
				,termEndYear,termSeason);
		
		for(Lesson less: lessons)
		{
			if(less.getMidTermNote()!=null &&less.getButNote()!=null&&less.getNote().equals(Notes.FF))
			{
				Double score=less.getMidTermNote()*0.4+less.getButNote()*0.6;
				if(score>less.getLowerBoundforAA()) less.setNote(Notes.AA);
				else if (score>less.getLowerBoundforBA()) less.setNote(Notes.BA);
				else if (score>less.getLowerBoundforBB()) less.setNote(Notes.BB);
				else if (score>less.getLowerBoundforCB()) less.setNote(Notes.CB);
				else if (score>less.getLowerBoundforCC()) less.setNote(Notes.CC);
				else if (score>less.getLowerBoundforDC()) less.setNote(Notes.DC);
				else if (score>less.getLowerBoundforDD()) less.setNote(Notes.DD);
				else if (score<less.getLowerBoundforDD()) less.setNote(Notes.FF);
				lessonRepository.save(less);
			}
			else
			{
				
			}
		}
	}


	



}
