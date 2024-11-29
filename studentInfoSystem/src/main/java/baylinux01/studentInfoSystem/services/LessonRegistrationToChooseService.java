package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.LessonRegistrationToChoose;
import baylinux01.studentInfoSystem.entities.TermToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.LessonRegistrationToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LessonRegistrationToChooseService {
	
	AppUserRepository appUserRepository;
	LessonRegistrationToChooseRepository lessonRegistrationToChooseRepository;
	
	@Autowired
	public LessonRegistrationToChooseService(AppUserRepository appUserRepository,
			LessonRegistrationToChooseRepository lessonRegistrationToChooseRepository) {
		super();
		this.appUserRepository = appUserRepository;
		this.lessonRegistrationToChooseRepository = lessonRegistrationToChooseRepository;
	}

	public String createLessonRegistrationToChoose(HttpServletRequest request, String name) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			LessonRegistrationToChoose lessonRegistrationToChoose=new LessonRegistrationToChoose();
			lessonRegistrationToChoose.setName(name);
			lessonRegistrationToChooseRepository.save(lessonRegistrationToChoose);
			return "lessonRegistrationToChoose successfully created";
		}
		else
		{
			return "fail";
		}
	}

	public List<LessonRegistrationToChoose> getAllLessonRegistrationsToChoose(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return lessonRegistrationToChooseRepository.findAll();
	}
	
	

}
