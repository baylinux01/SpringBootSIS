package baylinux01.webApiDeneme2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.entities.Term;
import baylinux01.webApiDeneme2.repositories.StudentRepository;
import baylinux01.webApiDeneme2.repositories.TermRepository;

@Service
public class TermService {

	TermRepository termRepository;
	StudentRepository studentRepository;
	
	

	@Autowired
	public TermService(TermRepository termRepository, StudentRepository studentRepository) {
		super();
		this.termRepository = termRepository;
		this.studentRepository = studentRepository;
	}




	public List<Term> getTermsOfAStudent(Long studentNumber) {
		// TODO Auto-generated method stub
		Student student=studentRepository.findByStudentNumber(studentNumber);
		List<Term> terms=null;
		if(student!=null)
		{
			terms=termRepository.findByStudent(student);
		}
		return terms;
	}

}
