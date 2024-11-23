package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.DepartmentToChoose;
import baylinux01.studentInfoSystem.entities.LessonToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.DepartmentToChooseRepository;
import baylinux01.studentInfoSystem.repositories.LessonToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LessonToChooseService {

	LessonToChooseRepository lessonToChooseRepository;
	AppUserRepository appUserRepository;
	DepartmentToChooseRepository departmentToChooseRepository;

	@Autowired
	public LessonToChooseService(LessonToChooseRepository lessonToChooseRepository
			,AppUserRepository appUserRepository
			,DepartmentToChooseRepository departmentToChooseRepository) {
		super();
		this.lessonToChooseRepository = lessonToChooseRepository;
		this.appUserRepository=appUserRepository;
		this.departmentToChooseRepository=departmentToChooseRepository;
	}

	public String createLessonToChoose(HttpServletRequest request, String code, String name,
			long departmentToChooseId,int semiYear) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			LessonToChoose lessonToChoose=new LessonToChoose();
			lessonToChoose.setCode(code);
			lessonToChoose.setName(name);
			DepartmentToChoose departmentToChoose=
					departmentToChooseRepository.findById(departmentToChooseId).orElse(null);
			if(departmentToChoose!=null && departmentToChoose.getDepartment_name()!=null)
			{
				lessonToChoose.setDepartment(departmentToChoose.getDepartment_name());
				if(semiYear<=departmentToChoose.getYear()*2)
				{
					lessonToChoose.setSemi_year(semiYear);
				}else return "semiyear is not suitable";
				
			}else 
			{
				return "department not found for the lesson";
			}
			return "lessonToChoose is successfully created";
		}else
		return "fail";
	}

	public List<LessonToChoose> getAllLessonsToChoose(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return lessonToChooseRepository.findAll();
	}
	
	
}
