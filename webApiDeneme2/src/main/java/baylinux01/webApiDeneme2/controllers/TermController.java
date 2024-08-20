package baylinux01.webApiDeneme2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baylinux01.webApiDeneme2.entities.Term;
import baylinux01.webApiDeneme2.services.TermService;

@CrossOrigin
@RestController
@RequestMapping("/terms")
public class TermController {
	
	TermService termService;
	
	@Autowired
	public TermController(TermService termService) {
		super();
		this.termService = termService;
	}


	@GetMapping("gettermsofastudent")
	List<Term> getTermsOfAStudent(Long studentNumber)
	{
		return termService.getTermsOfAStudent(studentNumber);
	}

}
