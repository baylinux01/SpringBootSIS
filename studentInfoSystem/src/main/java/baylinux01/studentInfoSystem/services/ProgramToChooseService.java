package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.DepartmentToChoose;
import baylinux01.studentInfoSystem.entities.ProgramToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.DepartmentToChooseRepository;
import baylinux01.studentInfoSystem.repositories.ProgramToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProgramToChooseService {
	
	ProgramToChooseRepository programToChooseRepository;
	DepartmentToChooseRepository departmentToChooseRepository;
	AppUserRepository appUserRepository;
	@Autowired
	public ProgramToChooseService(ProgramToChooseRepository programToChooseRepository,
			DepartmentToChooseRepository departmentToChooseRepository
			,AppUserRepository appUserRepository) {
		super();
		this.programToChooseRepository = programToChooseRepository;
		this.departmentToChooseRepository = departmentToChooseRepository;
		this.appUserRepository=appUserRepository;
	}
	public String createProgramToChoose(HttpServletRequest request, String name, int year, long departmentId,
			boolean hasPreparation) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			ProgramToChoose programToChoose=new ProgramToChoose();
			programToChoose.setHas_preparation(hasPreparation);
			programToChoose.setName(name);
			programToChoose.setYear(year);
			DepartmentToChoose departmentToChoose=departmentToChooseRepository
					.findById(departmentId).orElse(null);
			if(departmentToChoose!=null) 
			programToChoose
			.setDepartment(departmentToChoose.getDepartment_name());
			else return "department for the program not found";
			programToChooseRepository.save(programToChoose);
			return "programToChoose successfully created";
		}
		return "fail";
	}
	public List<ProgramToChoose> getAllProgramsToChoose(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return programToChooseRepository.findAll();
	}
	
	

}
