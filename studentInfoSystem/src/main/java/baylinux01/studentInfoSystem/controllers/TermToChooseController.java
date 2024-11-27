package baylinux01.studentInfoSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.studentInfoSystem.entities.TermToChoose;
import baylinux01.studentInfoSystem.services.TermToChooseService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/termToChoose")
public class TermToChooseController {
	
	TermToChooseService termToChooseService;

	@Autowired
	public TermToChooseController(TermToChooseService termToChooseService) {
		super();
		this.termToChooseService = termToChooseService;
	}
	
	@PostMapping("/createTermToChoose")
	public String createTermToChoose(HttpServletRequest request, String name)
	{
		return termToChooseService.createTermToChoose(request,name);
	}
	
	@GetMapping("/getAllTermsToChoose")
	public List<TermToChoose> getAllTermsToChoose(HttpServletRequest request)
	{
		return termToChooseService.getAllTermsToChoose(request);
	}
	
	@DeleteMapping("/deleteTermToChoose")
	public String deleteTermToChoose(HttpServletRequest request,long termToChooseId)
	{
		return termToChooseService.deleteTermToChoose(request,termToChooseId);
	}

}
