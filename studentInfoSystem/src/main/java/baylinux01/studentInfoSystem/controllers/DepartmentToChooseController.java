package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.DepartmentToChoose;
import baylinux01.studentInfoSystem.services.DepartmentToChooseService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/departmentToChoose")
public class DepartmentToChooseController {
	
	DepartmentToChooseService departmentToChooseService;

	@Autowired
	public DepartmentToChooseController(DepartmentToChooseService departmentToChooseService) {
		super();
		this.departmentToChooseService = departmentToChooseService;
	}
	
	@PostMapping("/createDepartmentToChoose")
	public String createDepartmentToChoose(HttpServletRequest request,String departmentName,int year)
	{
		return departmentToChooseService.createDepartmentToChoose(request,departmentName,year);
	}
	
	@GetMapping("/getAllDepartmentsToChoose")
	public List<DepartmentToChoose> getAllDepartmentsToChoose(HttpServletRequest request)
	{
		return departmentToChooseService.getAllDepartmentsToChoose(request);
	}
	
	

}
