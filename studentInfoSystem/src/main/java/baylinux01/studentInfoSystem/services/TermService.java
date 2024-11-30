package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.Term;
import baylinux01.studentInfoSystem.entities.TermToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.TermRepository;
import baylinux01.studentInfoSystem.repositories.TermToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TermService {
	
	TermRepository termRepository;
	TermToChooseRepository termToChooseRepository;
	AppUserRepository appUserRepository;
	
	@Autowired
	public TermService(TermRepository termRepository, TermToChooseRepository termToChooseRepository,
			AppUserRepository appUserRepository) {
		super();
		this.termRepository = termRepository;
		this.termToChooseRepository = termToChooseRepository;
		this.appUserRepository = appUserRepository;
	}

	public String createTermForAParticularStudent(HttpServletRequest request
			, long termToChooseId, String studentUsername) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			TermToChoose termToChoose=
					termToChooseRepository.findById(termToChooseId).orElse(null);
			AppUser student=appUserRepository.findByUsername(studentUsername);
			if(termToChoose!=null&&student!=null)
			{
				Term term=new Term();
				term.setName(termToChoose.getName());
				term.setStudent(student);
				List<Term> terms=student.getTerms();
				terms.add(term);
				student.setTerms(terms);
				termRepository.save(term);
				appUserRepository.save(student);
				return "term successfully created";
				
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

	public String createTermForAllStudents(HttpServletRequest request, long termToChooseId) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			TermToChoose termToChoose=
					termToChooseRepository.findById(termToChooseId).orElse(null);
			List<AppUser> students=appUserRepository.findAll();
			if(termToChoose!=null&&students.size()>0)
			{
				int i=0;
				while(i<students.size())
				{ 
					if(students.get(i).getRoles().contains("STUDENT"))
					{
						Term term=new Term();
						term.setName(termToChoose.getName());
						term.setStudent(students.get(i));
						List<Term> terms=students.get(i).getTerms();
						terms.add(term);
						students.get(i).setTerms(terms);
						termRepository.save(term);
						appUserRepository.save(students.get(i));
					}
					
					i++;
				}
				return "success";
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

	
	
	
	
	
	
	

}
