package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.Lesson;
import baylinux01.studentInfoSystem.entities.LessonRegistration;
import baylinux01.studentInfoSystem.entities.LessonToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.LessonRegistrationRepository;
import baylinux01.studentInfoSystem.repositories.LessonRepository;
import baylinux01.studentInfoSystem.repositories.LessonToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class LessonService {
	
	LessonRepository lessonRepository;
	AppUserRepository appUserRepository;
	LessonToChooseRepository lessonToChooseRepository;
	LessonRegistrationRepository lessonRegistrationRepository;
	
	@Autowired
	public LessonService(LessonRepository lessonRepository
			,AppUserRepository appUserRepository
			,LessonToChooseRepository lessonToChooseRepository
			,LessonRegistrationRepository lessonRegistrationRepository) {
		super();
		this.lessonRepository = lessonRepository;
		this.appUserRepository=appUserRepository;
		this.lessonToChooseRepository=lessonToChooseRepository;
		this.lessonRegistrationRepository=lessonRegistrationRepository;
	}
	@Transactional
	public String addLessonToLessonRegistration(HttpServletRequest request, long lessonToChooseId,
			long lessonRegistrationId) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		LessonRegistration lessonRegistration
		=lessonRegistrationRepository.findById(lessonRegistrationId).orElse(null);
		LessonToChoose lessonToChoose=lessonToChooseRepository
				.findById(lessonToChooseId).orElse(null);
		if(requestingUser!=null&& requestingUser.getRoles().contains("ADMIN")
				&&lessonRegistration.getTerm().getStudent().getProgram().equalsIgnoreCase(lessonToChoose.getProgram()))		
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
				&&lessonRegistration.isApproval()==false)
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
				&&lessonRegistration.isApproval()==false)
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

	
	
	

}
