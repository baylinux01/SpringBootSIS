package baylinux01.studentInfoSystem.services;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import baylinux01.studentInfoSystem.entities.AppUser;
import baylinux01.studentInfoSystem.entities.DepartmentToChoose;
import baylinux01.studentInfoSystem.entities.ProgramToChoose;
import baylinux01.studentInfoSystem.repositories.AppUserRepository;
import baylinux01.studentInfoSystem.repositories.DepartmentToChooseRepository;
import baylinux01.studentInfoSystem.repositories.ProgramToChooseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class AppUserService {
	
	AppUserRepository appUserRepository;
	AuthenticationManager authenticationManager;
	PasswordEncoder passwordEncoder;
	DepartmentToChooseRepository departmentToChooseRepository;
	ProgramToChooseRepository programToChooseRepository;
	
	@Autowired
	public AppUserService(
			AppUserRepository appUserRepository
			,AuthenticationManager authenticationManager
			,PasswordEncoder passwordEncoder
			,DepartmentToChooseRepository departmentToChooseRepository
			,ProgramToChooseRepository programToChooseRepository
		) {
		super();
		this.appUserRepository = appUserRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.departmentToChooseRepository=departmentToChooseRepository;
		this.programToChooseRepository=programToChooseRepository;
	}

	public AppUser getAppUserByUsername(String username) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(username);
	}

	public AppUser getAppUserById(long id) {
		// TODO Auto-generated method stub
		return appUserRepository.findById(id).orElse(null);
	}

	public List<AppUser> getAllAppUsers() {
		// TODO Auto-generated method stub
		return appUserRepository.findAll();
	}

	public String createAppUser(HttpServletRequest request,String username
			,String password) {
		// TODO Auto-generated method stub
		List<AppUser> appUsers=getAllAppUsers();
		int i=0;
		while(i<appUsers.size())
		{
			if(appUsers.get(i).getUsername().equalsIgnoreCase(username))
				return "appuser with this username already exists";
			i++;
		}
		AppUser appUser=new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(passwordEncoder.encode(password));
		appUser.setRoles("USER");
		appUser.setRegistration_date(LocalDate.now());
		
		appUserRepository.save(appUser);
		return "success";
	}
	
	public String createStudent(HttpServletRequest request, String username
			,String password, long departmentToChooseId,long programToChooseId) {
		Principal pl=request.getUserPrincipal();
		String requestinUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestinUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			List<AppUser> appUsers=getAllAppUsers();
			int i=0;
			while(i<appUsers.size())
			{
				if(appUsers.get(i).getUsername().equalsIgnoreCase(username))
					return "appuser with this username already exists";
				i++;
			}
			AppUser appUser=new AppUser();
			appUser.setUsername(username);
			appUser.setPassword(passwordEncoder.encode(password));
			appUser.setRoles("USER-STUDENT");
			appUser.setRegistration_date(LocalDate.now());
			DepartmentToChoose departmentToChoose=
					departmentToChooseRepository.findById(departmentToChooseId).orElse(null);
			if(departmentToChoose!=null && departmentToChoose.getDepartment_name()!=null)
			{
				appUser.setDepartment(departmentToChoose.getDepartment_name());
				
			}
			else return "fail";
			ProgramToChoose programToChoose=
					programToChooseRepository.findById(programToChooseId).orElse(null);
			if(programToChoose!=null&&programToChoose.getName()!=null)
			{
				appUser.setProgram(programToChoose.getName());
				if(programToChoose.isHas_preparation()==true) appUser.setGrade(0);
				else appUser.setGrade(1);
			}
			else return "programToChoose not found for student";
			appUserRepository.save(appUser);
			return "success";
		}else return "you don't have the authority to do that";
	}
	
	public String createTeacher(HttpServletRequest request,String username, String password, long departmentToChooseId) {
		Principal pl=request.getUserPrincipal();
		String requestinUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestinUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			List<AppUser> appUsers=getAllAppUsers();
			int i=0;
			while(i<appUsers.size())
			{
				if(appUsers.get(i).getUsername().equalsIgnoreCase(username))
					return "appuser with this username already exists";
				i++;
			}
			AppUser appUser=new AppUser();
			appUser.setUsername(username);
			appUser.setPassword(passwordEncoder.encode(password));
			appUser.setRoles("USER-TEACHER");
			appUser.setRegistration_date(LocalDate.now());
			DepartmentToChoose departmentToChoose=
					departmentToChooseRepository.findById(departmentToChooseId).orElse(null);
			if(departmentToChoose!=null && departmentToChoose.getDepartment_name()!=null)
			{
				appUser.setDepartment(departmentToChoose.getDepartment_name());
				
			}
			else return "fail";
			appUserRepository.save(appUser);
			return "success";
		}else return "you don't have the authority to do that";
	}

	public AppUser login(String username, String password) {
		// TODO Auto-generated method stub
		Authentication authentication =authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		if(authentication.isAuthenticated()) 
		{
			
			AppUser appUser=(AppUser) appUserRepository.findByUsername(username);
			if(appUser!=null
					&&appUser.getRoles().contains("USER")
					&&!appUser.getRoles().contains("ADMIN")
					&&!appUser.getRoles().contains("STUDENT")
					&&!appUser.getRoles().contains("TEACHER"))
			{
				
				return appUser;
			}return null;
			
		}
		else return null;
	}
	
	public AppUser adminLogin(String username, String password) {
		// TODO Auto-generated method stub
				Authentication authentication =authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username,password));
				if(authentication.isAuthenticated()) 
				{
					
					AppUser appUser=(AppUser) appUserRepository.findByUsername(username);
					if(appUser!=null
							&&appUser.getRoles().contains("USER")
							&&appUser.getRoles().contains("ADMIN")
							&&!appUser.getRoles().contains("STUDENT")
							&&!appUser.getRoles().contains("TEACHER"))
					{
						
						return appUser;
					}return null;
					
				}
				else return null;
	}
	
	public AppUser teacherLogin(String username, String password) {
		// TODO Auto-generated method stub
				Authentication authentication =authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username,password));
				if(authentication.isAuthenticated()) 
				{
					
					AppUser appUser=(AppUser) appUserRepository.findByUsername(username);
					if(appUser!=null
							&&appUser.getRoles().contains("USER")
							&&!appUser.getRoles().contains("ADMIN")
							&&!appUser.getRoles().contains("STUDENT")
							&&appUser.getRoles().contains("TEACHER"))
					{
						
						return appUser;
					}return null;
					
				}
				else return null;
	}
	
	public AppUser studentLogin(String username, String password) {
		// TODO Auto-generated method stub
				Authentication authentication =authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username,password));
				if(authentication.isAuthenticated()) 
				{
					
					AppUser appUser=(AppUser) appUserRepository.findByUsername(username);
					if(appUser!=null
							&&appUser.getRoles().contains("USER")
							&&!appUser.getRoles().contains("ADMIN")
							&&appUser.getRoles().contains("STUDENT")
							&&!appUser.getRoles().contains("TEACHER"))
					{
						
						return appUser;
					}return null;
					
				}
				else return null;
	}

	public String updateAppUserPassword(HttpServletRequest request, String newPassword) {
		// TODO Auto-generated method stub
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
		AppUser user=appUserRepository.findByUsername(username);
		user.setPassword(passwordEncoder.encode(newPassword));
		appUserRepository.save(user);
		return "success";
		}
		else return "fail";
		
	}

	public String updateAppUserUsername(HttpServletRequest request, String newUsername) {
		// TODO Auto-generated method stub
		/*jwt olmadan requestten kullanıcı adını alma kodları başlangıcı*/		
		Principal pl=request.getUserPrincipal();
		String username=pl.getName();
		/*jwt olmadan requestten kullanıcı adını alma kodları sonu*/
		if(username!=null)
		{
			List<AppUser> users=appUserRepository.findAll();
			for(AppUser u:users)
			{
				if(u.getUsername().equals(newUsername)) return "username already exists";
			}
			AppUser user=appUserRepository.findByUsername(username);
			user.setUsername(newUsername);
			appUserRepository.save(user);
			return "success";
		}
		else return "fail";
	}

	@Transactional
	public String assignSupervisorToAStudent(HttpServletRequest request, String studentUsername, String supervisorUsername) {
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			AppUser student=appUserRepository.findByUsername(studentUsername);
			AppUser teacher=appUserRepository.findByUsername(supervisorUsername);
			if(student!=null
					&&teacher!=null
					&&student.getRoles().contains("STUDENT")
					&&teacher.getRoles().contains("TEACHER")
					&&student.getDepartment().equalsIgnoreCase(teacher.getDepartment()))
			{
				List<AppUser> students=teacher.getStudents();
				students.add(student);
				teacher.setStudents(students);
				student.setSupervisor(teacher);
				appUserRepository.save(teacher);
				appUserRepository.save(student);
				return "supervisor successfully assigned";
			}
			else
			{
				return "fail";
			}
		}
		else
		{
			return "you do not have the authority to do that";
		}
		
		
		
	}

	@Transactional
	public String assignSupervisorToAGradeOfStudents(HttpServletRequest request, long grade
			, long programToChooseId
			, String supervisorUsername) {
		
		Principal pl=request.getUserPrincipal();
		String requestingUsername=pl.getName();
		AppUser requestingUser=appUserRepository.findByUsername(requestingUsername);
		if(requestingUser!=null&&requestingUser.getRoles().contains("ADMIN"))
		{
			ProgramToChoose programToChoose=programToChooseRepository.findById(programToChooseId).orElse(null);
			
			List<AppUser> users=appUserRepository.findByProgram(programToChoose.getName());
			AppUser teacher=appUserRepository.findByUsername(supervisorUsername);
			if(teacher!=null&&users!=null&&teacher.getRoles().contains("TEACHER"))
			{
				for(AppUser user: users)
				{
					if(user.getRoles().contains("STUDENT")
							&&user.getGrade()==grade
							&&user.getDepartment().equalsIgnoreCase(teacher.getDepartment()))
					{
						List<AppUser> students=teacher.getStudents();
						students.add(user);
						teacher.setStudents(students);
						user.setSupervisor(teacher);
						appUserRepository.save(teacher);
						appUserRepository.save(user);
					}
				}
				return "supervisor is successfully assigned";
			}
			else
			{
				return "fail";
			}
		}
		else
		{
			return "you do not have the authority to do that";
		}
		
	}

	

	

	

	
	
	

}
