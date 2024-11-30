package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.LessonRegistration;
import baylinux01.studentInfoSystem.entities.LessonRegistrationToChoose;
import baylinux01.studentInfoSystem.entities.Term;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.LessonRegistrationRepository;
import baylinux01.studentInfoSystem.repositories.LessonRegistrationToChooseRepository;
import baylinux01.studentInfoSystem.repositories.LessonRepository;
import baylinux01.studentInfoSystem.repositories.LessonToChooseRepository;
import baylinux01.studentInfoSystem.repositories.TermRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class LessonRegistrationService {
	
	LessonRegistrationRepository lessonRegistrationRepository;
	LessonRegistrationToChooseRepository lessonRegistrationToChooseRepository;
	LessonRepository lessonRepository;
	LessonToChooseRepository lessonToChooseRepository;
	AppUserRepository appUserRepository;
	TermRepository termRepository;
	
	@Autowired
	public LessonRegistrationService(LessonRegistrationRepository lessonRegistrationRepository,
			LessonRegistrationToChooseRepository lessonRegistrationToChooseRepository,
			LessonRepository lessonRepository, LessonToChooseRepository lessonToChooseRepository,
			AppUserRepository appUserRepository,TermRepository termRepository) {
		super();
		this.lessonRegistrationRepository = lessonRegistrationRepository;
		this.lessonRegistrationToChooseRepository = lessonRegistrationToChooseRepository;
		this.lessonRepository = lessonRepository;
		this.lessonToChooseRepository = lessonToChooseRepository;
		this.appUserRepository = appUserRepository;
		this.termRepository=termRepository;
	}

	@Transactional
	public String createLessonRegistrationsForCertainTerm(HttpServletRequest request
			,long lessonRegistrationToChooseId) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			
			LessonRegistrationToChoose lessonRegistrationToChoose=
					lessonRegistrationToChooseRepository.findById(lessonRegistrationToChooseId)
					.orElse(null);
			List<Term> terms=termRepository.findByName(lessonRegistrationToChoose.getName());
			int i=0;
			while(i<terms.size())
			{
				LessonRegistration lessonRegistration=new LessonRegistration();
				lessonRegistration.setName(lessonRegistrationToChoose.getName());
				lessonRegistration.setTerm(terms.get(i));
				lessonRegistration.setAbsolutized(false);
				lessonRegistration.setApproval(false);
				lessonRegistrationRepository.save(lessonRegistration);
				i++;
			}
			return "lessonRegistrations successfully created";
			
			
		}
		else
		{
			return "error";
		}
	}

	
	
	

}
