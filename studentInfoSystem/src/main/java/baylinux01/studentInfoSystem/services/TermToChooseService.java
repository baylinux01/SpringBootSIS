package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.TermToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.TermToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TermToChooseService {
	
	TermToChooseRepository termToChooseRepository;
	AppUserRepository appUserRepository;
	
	@Autowired
	public TermToChooseService(TermToChooseRepository termToChooseRepository, AppUserRepository appUserRepository) {
		super();
		this.termToChooseRepository = termToChooseRepository;
		this.appUserRepository = appUserRepository;
	}

	public String createTermToChoose(HttpServletRequest request, String name) {
		// TODO Auto-generated method stub
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			TermToChoose termToChoose=new TermToChoose();
			termToChoose.setName(name);
			termToChooseRepository.save(termToChoose);
			return "termToChoose successfully created";
		}
		else
		{
			return "fail";
		}
	}

	public List<TermToChoose> getAllTermsToChoose(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return termToChooseRepository.findAll();
	}

	public String deleteTermToChoose(HttpServletRequest request, long termToChooseId) {
		// TODO Auto-generated method stub
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			try{
				termToChooseRepository.deleteById(termToChooseId);
				return "TermToChoose successfully deleted";
				
			}catch(Exception ex)
			{
				return "An error occurred. Make sure that termToChoose with id you sent exists";
			}
		}
		else return "you don't have the authority to do that";
	}
	
	

}
