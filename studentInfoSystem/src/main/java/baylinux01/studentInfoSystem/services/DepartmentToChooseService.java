package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.DepartmentToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.DepartmentToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class DepartmentToChooseService {
	
	DepartmentToChooseRepository departmentToChooseRepository;
	AppUserRepository appUserRepository;
	@Autowired
	public DepartmentToChooseService
	(
			DepartmentToChooseRepository departmentToChooseRepository
			,AppUserRepository appUserRepository
	) 
	{
		super();
		this.departmentToChooseRepository = departmentToChooseRepository;
		this.appUserRepository=appUserRepository;
	}

	public String createDepartmentToChoose(HttpServletRequest request, String departmentName,int year) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser.getRoles().contains("ADMIN"))
		{
			DepartmentToChoose departmentToChoose= new DepartmentToChoose();
			departmentToChoose.setDepartment_name(departmentName);
			departmentToChoose.setYear(year);
			departmentToChooseRepository.save(departmentToChoose);
			return "departmentToChoose successfuly saved";
		}else
		return "fail";
	}

	public List<DepartmentToChoose> getAllDepartmentsToChoose(HttpServletRequest request) {
		List<DepartmentToChoose> departmentsToChoose=departmentToChooseRepository.findAll();
		return departmentsToChoose;
	}
	
	

}
