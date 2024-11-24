package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.ProgramToChoose;
import baylinux01.studentInfoSystem.services.ProgramToChooseService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/programToChoose")
public class ProgramToChooseController {
	
	ProgramToChooseService programToChooseService;
	
	
	@Autowired
	public ProgramToChooseController(ProgramToChooseService programToChooseService) {
		super();
		this.programToChooseService = programToChooseService;
	}



	@PostMapping("/createProgramToChoose")
	public String createProgramToChoose(HttpServletRequest request,String name,int year
			,long departmentId,boolean hasPreparation)
	{
		return programToChooseService.createProgramToChoose(request,name,year,departmentId,hasPreparation);
	}
	
	@GetMapping("/getAllProgramsToChoose")
	public List<ProgramToChoose> getAllProgramsToChoose(HttpServletRequest request)
	{
		return programToChooseService.getAllProgramsToChoose(request);
	}

}
