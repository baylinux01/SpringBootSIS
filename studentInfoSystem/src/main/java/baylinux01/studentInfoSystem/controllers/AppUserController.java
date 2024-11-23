package baylinux01.studentInfoSystem.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.services.AppUserService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/appUser")
public class AppUserController {
	
	AppUserService appUserService;

	@Autowired
	public AppUserController(AppUserService appUserService) {
		super();
		this.appUserService = appUserService;
	}
	
	@GetMapping("/getAppUserByUsername")
	public AppUser getAppUserByUsername(String username)
	{
		return appUserService.getAppUserByUsername(username);
	}
	
	@GetMapping("/getAppUserById")
	public AppUser getAppUserById(long id)
	{
		return appUserService.getAppUserById(id);
	}
	
	@GetMapping("/getAllAppUsers")
	public List<AppUser> getAllAppUsers()
	{
		return appUserService.getAllAppUsers();
	}
	@PostMapping("/createAppUser")
	public String createAppUser
	(
			HttpServletRequest request,String username,String password,long departmentToChooseId
	)
	{
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String departmentToChooseIdStr=request.getParameter("departmentToChooseId");
//		long departmentToChooseId=Long.valueOf(departmentToChooseIdStr);
		return appUserService.createAppUser(request,username,password,departmentToChooseId);
		
	}
	
	@PostMapping("/createStudent")
	public String createStudent
	(
			HttpServletRequest request,String username,String password,long departmentToChooseId
	)
	{
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String departmentToChooseIdStr=request.getParameter("departmentToChooseId");
//		long departmentToChooseId=Long.valueOf(departmentToChooseIdStr);
		return appUserService.createStudent(request,username,password,departmentToChooseId);
		
	}
	
	@PostMapping("/createTeacher")
	public String createTeacher
	(
			HttpServletRequest request,String username,String password,long departmentToChooseId
	)
	{
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String departmentToChooseIdStr=request.getParameter("departmentToChooseId");
//		long departmentToChooseId=Long.valueOf(departmentToChooseIdStr);
		return appUserService.createTeacher(request,username,password,departmentToChooseId);
		
	}
	
	@PostMapping("/login")
	public AppUser login(@RequestParam("username") String username
			,@RequestParam("password") String password) 
					throws NoSuchAlgorithmException
	{
		return appUserService.login(username,password);
	}
	
	@PostMapping("/adminLogin")
	public AppUser adminLogin(@RequestParam("username") String username
			,@RequestParam("password") String password) 
					throws NoSuchAlgorithmException
	{
		return appUserService.adminLogin(username,password);
	}
	
	@PostMapping("/teacherLogin")
	public AppUser teacherLogin(@RequestParam("username") String username
			,@RequestParam("password") String password) 
					throws NoSuchAlgorithmException
	{
		return appUserService.teacherLogin(username,password);
	}
	
	@PostMapping("/studentLogin")
	public AppUser studentLogin(@RequestParam("username") String username
			,@RequestParam("password") String password) 
					throws NoSuchAlgorithmException
	{
		return appUserService.studentLogin(username,password);
	}
	
	@PutMapping("/updateAppUserPassword")
	public String updateAppUserPassword( HttpServletRequest request
			,@RequestParam("newPassword") String newPassword)
	{
		return appUserService.updateAppUserPassword(request,newPassword);
	}
	@PutMapping("/updateAppUserUsername")
	public String updateAppUserUsername( HttpServletRequest request
			,@RequestParam("newUsername") String newUsername)
	{
		return appUserService.updateAppUserUsername(request,newUsername);
	}
	
	
	
	
	
	

}
