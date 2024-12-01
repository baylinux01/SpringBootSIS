package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.Lesson;
import baylinux01.studentInfoSystem.entities.LessonRegistration;
import baylinux01.studentInfoSystem.entities.LessonToChoose;
import baylinux01.studentInfoSystem.entities.Term;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.LessonRegistrationRepository;
import baylinux01.studentInfoSystem.repositories.LessonRepository;
import baylinux01.studentInfoSystem.repositories.LessonToChooseRepository;
import baylinux01.studentInfoSystem.repositories.TermRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class LessonService {
	
	LessonRepository lessonRepository;
	AppUserRepository appUserRepository;
	LessonToChooseRepository lessonToChooseRepository;
	LessonRegistrationRepository lessonRegistrationRepository;
	TermRepository termRepository;
	
	@Autowired
	public LessonService(LessonRepository lessonRepository
			,AppUserRepository appUserRepository
			,LessonToChooseRepository lessonToChooseRepository
			,LessonRegistrationRepository lessonRegistrationRepository
			,TermRepository termRepository) {
		super();
		this.lessonRepository = lessonRepository;
		this.appUserRepository=appUserRepository;
		this.lessonToChooseRepository=lessonToChooseRepository;
		this.lessonRegistrationRepository=lessonRegistrationRepository;
		this.termRepository=termRepository;
	}
	@Transactional
	public String addLessonToLessonRegistration(HttpServletRequest request, long lessonToChooseId,
			long lessonRegistrationId,String teacherUsername) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		AppUser teacher=appUserRepository.findByUsername(teacherUsername);
		LessonRegistration lessonRegistration
		=lessonRegistrationRepository.findById(lessonRegistrationId).orElse(null);
		LessonToChoose lessonToChoose=lessonToChooseRepository
				.findById(lessonToChooseId).orElse(null);
		if(requestingUser!=null&& requestingUser.getRoles().contains("ADMIN")
				&&lessonRegistration.getTerm().getStudent().getProgram().equalsIgnoreCase(lessonToChoose.getProgram())
				&&teacher!=null
				&&teacher.getRoles().contains("TEACHER"))		
		{
			if((lessonRegistration.getName().contains("GÜZ")
					&&lessonToChoose.getSemi_year()%2==1)
					||(lessonRegistration.getName().contains("BAHAR")
							&&lessonToChoose.getSemi_year()%2==0))
			{
				if(lessonRegistration.getTerm().getStudent().getGrade()*2>=lessonToChoose.getSemi_year()
						||lessonRegistration.getTerm().getStudent().getGpa()>=3.5)
				{
					Lesson lesson=new Lesson();
					lesson.setCode(lessonToChoose.getCode());
					lesson.setName(lessonToChoose.getName());
					lesson.setProgram(lessonToChoose.getProgram());
					lesson.setSemi_year(lessonToChoose.getSemi_year());
					lesson.setTeacher(teacher);
					
					List<Lesson> lessons=lessonRegistration.getLessons();
					int i=0;
					boolean previouslyAdded=false;
					while(i<lessons.size())
					{
						if(lessons.get(i).getCode().equalsIgnoreCase(lesson.getCode()))
						previouslyAdded=true;
						i++;
					}
					if(previouslyAdded==false)
					{
						lessons.add(lesson);
						lesson.setLesson_registration(lessonRegistration);
						lessonRegistration.setLessons(lessons);
						appUserRepository.save(teacher);
						lessonRepository.save(lesson);
						lessonRegistrationRepository.save(lessonRegistration);
						return "lesson successfully added to lesson registration";
					}
					else
					{
						return "This lesson is already added to lesson registration";
					}
				}
				else
				{
					return "to get lesson from above your gpa should be at least 3.5";
				}
				
			}
			else
			{
				return "This lesson does not belong this term";
			}
		
			
		}
		else if(requestingUser.getRoles().contains("STUDENT")
				&&lessonRegistration.getTerm().getStudent()==requestingUser
				&&requestingUser.getProgram().equalsIgnoreCase(lessonToChoose.getProgram())
				&&lessonRegistration.isAbsolutized()==false
				&&lessonRegistration.isApproval()==false
				&&teacher!=null
				&&teacher.getRoles().contains("TEACHER"))
		{
			if((lessonRegistration.getName().contains("GÜZ")
					&&lessonToChoose.getSemi_year()%2==1)
					||(lessonRegistration.getName().contains("BAHAR")
							&&lessonToChoose.getSemi_year()%2==0))
			{
				if(lessonRegistration.getTerm().getStudent().getGrade()*2>=lessonToChoose.getSemi_year()
						||lessonRegistration.getTerm().getStudent().getGpa()>=3.5)
				{
					Lesson lesson=new Lesson();
					lesson.setCode(lessonToChoose.getCode());
					lesson.setName(lessonToChoose.getName());
					lesson.setProgram(lessonToChoose.getProgram());
					lesson.setSemi_year(lessonToChoose.getSemi_year());
					lesson.setTeacher(teacher);
					
					List<Lesson> lessons=lessonRegistration.getLessons();
					int i=0;
					boolean previouslyAdded=false;
					while(i<lessons.size())
					{
						if(lessons.get(i).getCode().equalsIgnoreCase(lesson.getCode()))
						previouslyAdded=true;
						i++;
					}
					if(previouslyAdded==false)
					{
						lessons.add(lesson);
						lesson.setLesson_registration(lessonRegistration);
						lessonRegistration.setLessons(lessons);
						appUserRepository.save(teacher);
						lessonRepository.save(lesson);
						lessonRegistrationRepository.save(lessonRegistration);
						return "lesson successfully added to lesson registration";
					}
					else
					{
						return "This lesson is already added to lesson registration";
					}
				}
				else
				{
					return "to get lesson from above your gpa should be at least 3.5";
				}
				
			}
			else
			{
				return "This lesson does not belong this term";
			}
			
		}
		else if(requestingUser.getRoles().contains("TEACHER")
				&&requestingUser.getStudents().contains(lessonRegistration.getTerm().getStudent())
				&&lessonRegistration.getTerm().getStudent().getProgram().equalsIgnoreCase(lessonToChoose.getProgram())
				&&lessonRegistration.isApproval()==false
				&&teacher!=null
				&&teacher.getRoles().contains("TEACHER"))
		{
			if((lessonRegistration.getName().contains("GÜZ")
					&&lessonToChoose.getSemi_year()%2==1)
					||(lessonRegistration.getName().contains("BAHAR")
							&&lessonToChoose.getSemi_year()%2==0))
			{
				if(lessonRegistration.getTerm().getStudent().getGrade()*2>=lessonToChoose.getSemi_year()
						||lessonRegistration.getTerm().getStudent().getGpa()>=3.5)
				{
					Lesson lesson=new Lesson();
					lesson.setCode(lessonToChoose.getCode());
					lesson.setName(lessonToChoose.getName());
					lesson.setProgram(lessonToChoose.getProgram());
					lesson.setSemi_year(lessonToChoose.getSemi_year());
					lesson.setTeacher(teacher);
					
					List<Lesson> lessons=lessonRegistration.getLessons();
					int i=0;
					boolean previouslyAdded=false;
					while(i<lessons.size())
					{
						if(lessons.get(i).getCode().equalsIgnoreCase(lesson.getCode()))
						previouslyAdded=true;
						i++;
					}
					if(previouslyAdded==false)
					{
						lessons.add(lesson);
						lesson.setLesson_registration(lessonRegistration);
						lessonRegistration.setLessons(lessons);
						appUserRepository.save(teacher);
						lessonRepository.save(lesson);
						lessonRegistrationRepository.save(lessonRegistration);
						return "lesson successfully added to lesson registration";
					}
					else
					{
						return "This lesson is already added to lesson registration";
					}
				}
				else
				{
					return "to get lesson from above your gpa should be at least 3.5";
				}
				
			}
			else
			{
				return "This lesson does not belong this term";
			}
			
		}
		else
		{
			return "fail";
		}
		
	}
	public String giveMidtermNoteToLesson(HttpServletRequest request,long lessonId, double midtermNote) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		Lesson lesson=lessonRepository.findById(lessonId).orElse(null);
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			lesson.setMidterm_note(midtermNote);
			lessonRepository.save(lesson);
			return "midterm note successfully saved";
		}
		else if(requestingUser!=null
				&&requestingUser.getRoles().contains("TEACHER")
				&&lesson!=null
				&&lesson.getTeacher()==requestingUser)
		{
			lesson.setMidterm_note(midtermNote);
			lessonRepository.save(lesson);
			return "midterm note successfully saved";
		}
		else
		{
			return "fail";
		}
	}
	public String giveFinalNoteToLesson(HttpServletRequest request, long lessonId, double finalNote) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		Lesson lesson=lessonRepository.findById(lessonId).orElse(null);
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			lesson.setFinal_note(finalNote);
			lessonRepository.save(lesson);
			return "final note successfully saved";
		}
		else if(requestingUser!=null
				&&requestingUser.getRoles().contains("TEACHER")
				&&lesson!=null
				&&lesson.getTeacher()==requestingUser)
		{
			lesson.setFinal_note(finalNote);
			lessonRepository.save(lesson);
			return "final note successfully saved";
		}
		else
		{
			return "fail";
		}
	}
	public List<Lesson> getLessonsOfAStudentForACertainTerm(HttpServletRequest request, long termId,
			String studentUsername) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		Term term=termRepository.findById(termId).orElse(null);
		AppUser student=appUserRepository.findByUsername(studentUsername);
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&term!=null&&student!=null&&student.getRoles().contains("STUDENT")
				&&(requestingUser.getRoles().contains("ADMIN")||requestingUser.getRoles().contains("TEACHER")))
		{
			LessonRegistration lessonRegistration=term.getLessonRegistration();
			if(lessonRegistration!=null)
			{
				List<Lesson> lessons=lessonRegistration.getLessons();
				return lessons;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	public List<Lesson> getAllLessons(HttpServletRequest request) {
		return lessonRepository.findAll();
		
	}
	public String determineNotesByLesson(HttpServletRequest request,String lessonCode) {
		
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			
				List<Lesson> lessons=lessonRepository.findByCode(lessonCode);
				if(lessons!=null)
				{
					int n=0;
					int sum=0;
					int i=0;
					while(i<lessons.size())
					{
						if(lessons.get(i).getMidterm_note()>=0
								&&lessons.get(i).getFinal_note()>=0)
						{
							lessons.get(i).setNote
							(0.4*lessons.get(i).getMidterm_note()
									+0.6*lessons.get(i).getFinal_note());
							lessonRepository.save(lessons.get(i));
							sum+=lessons.get(i).getNote();
							n++;
						}
						i++;
						
					}
					double average=Double.valueOf(sum)/n;
					if(average>=50)
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=80)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=70)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=60)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=50)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=40)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=30)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<30&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getFinal_note()<30&&lessons.get(i).getFinal_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					else
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=average+30)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=average+20)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=average+10)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=average)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=average-10)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=average-20)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<average-20&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getFinal_note()<30&&lessons.get(i).getFinal_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					i=0;
					while(i<lessons.size())
					{
						
						if(lessons.get(i).getBut_note()>=0
								&&lessons.get(i).getMidterm_note()>=0)
						{
							lessons.get(i).setNote
							(0.4*lessons.get(i).getMidterm_note()
									+0.6*lessons.get(i).getBut_note());
							lessonRepository.save(lessons.get(i));
						}
						i++;
					}
					if(average>=50)
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=80)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=70)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=60)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=50)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=40)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=30)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<30&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getBut_note()<30&&lessons.get(i).getBut_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					else
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=average+30)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=average+20)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=average+10)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=average)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=average-10)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=average-20)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<average-20&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getBut_note()<30&&lessons.get(i).getBut_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					return "notes successfully calculated";
				
			}
			else
			{
				return "fail";
			}
		}
		else
		{
			return "fail";
		}
		
	}
	public String determineNotesForAllLessons(HttpServletRequest request) {
		List<LessonToChoose> lessonsToChoose=lessonToChooseRepository.findAll();
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN")
				&&lessonsToChoose!=null)
		{
			
			for(LessonToChoose lessonToChoose:lessonsToChoose)
			{
				List<Lesson> lessons=lessonRepository.findByCode(lessonToChoose.getCode());
				if(lessons!=null)
				{
					int n=0;
					int sum=0;
					int i=0;
					while(i<lessons.size())
					{
						if(lessons.get(i).getMidterm_note()>=0
								&&lessons.get(i).getFinal_note()>=0)
						{
							lessons.get(i).setNote
							(0.4*lessons.get(i).getMidterm_note()
									+0.6*lessons.get(i).getFinal_note());
							lessonRepository.save(lessons.get(i));
							sum+=lessons.get(i).getNote();
							n++;
						}
						i++;
						
					}
					double average=Double.valueOf(sum)/n;
					if(average>=50)
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=80)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=70)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=60)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=50)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=40)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=30)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<30&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getFinal_note()<30&&lessons.get(i).getFinal_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					else
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=average+30)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=average+20)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=average+10)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=average)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=average-10)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=average-20)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<average-20&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getFinal_note()<30&&lessons.get(i).getFinal_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					i=0;
					while(i<lessons.size())
					{
						
						if(lessons.get(i).getBut_note()>=0
								&&lessons.get(i).getMidterm_note()>=0)
						{
							lessons.get(i).setNote
							(0.4*lessons.get(i).getMidterm_note()
									+0.6*lessons.get(i).getBut_note());
							lessonRepository.save(lessons.get(i));
						}
						i++;
					}
					if(average>=50)
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=80)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=70)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=60)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=50)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=40)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=30)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<30&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getBut_note()<30&&lessons.get(i).getBut_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					else
					{
						i=0;
						while(i<lessons.size())
						{
							if(lessons.get(i).getNote()>=average+30)
							{
								lessons.get(i).setLetter_grade("AA");
							}
							else if(lessons.get(i).getNote()>=average+20)
							{
								lessons.get(i).setLetter_grade("BA");
							}
							else if(lessons.get(i).getNote()>=average+10)
							{
								lessons.get(i).setLetter_grade("BB");
							}
							else if(lessons.get(i).getNote()>=average)
							{
								lessons.get(i).setLetter_grade("CC");
							}
							else if(lessons.get(i).getNote()>=average-10)
							{
								lessons.get(i).setLetter_grade("DC");
							}
							else if(lessons.get(i).getNote()>=average-20)
							{
								lessons.get(i).setLetter_grade("DD");
							}
							else if(lessons.get(i).getNote()<average-20&&lessons.get(i).getNote()>0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							if(lessons.get(i).getBut_note()<30&&lessons.get(i).getBut_note()>=0)
							{
								lessons.get(i).setLetter_grade("FF");
							}
							lessonRepository.save(lessons.get(i));
							i++;
							
						}
					}
					
				
			}
			else
			{
				
			}
			}return "notes successfully calculated";
			
		}
		else
		{
			return "fail";
		}
		
		
	}

	

	
	
	

}
