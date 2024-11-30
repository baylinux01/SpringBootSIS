package baylinux01.studentInfoSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.services.TermService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/term")
public class TermController {
	
	TermService termService;

	@Autowired
	public TermController(TermService termService) {
		super();
		this.termService = termService;
	}
	
	@PostMapping("/createTermForAParticularStudent")
	public String createTermForAParticularStudent(HttpServletRequest request
			,long termToChooseId
			,String studentUsername)
	{
		return termService.createTermForAParticularStudent(request,termToChooseId,studentUsername);
	}
	
	@PostMapping("/createTermForAllStudents")
	public String createTermForAllStudents(HttpServletRequest request
			,long termToChooseId
			)
	{
		return termService.createTermForAllStudents(request,termToChooseId);
	}
	
	

}
