package baylinux01.webApiDeneme2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.webApiDeneme2.entities.Lesson;
import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.entities.Term;
import baylinux01.webApiDeneme2.repositories.LessonRepository;
import baylinux01.webApiDeneme2.repositories.StudentRepository;
import baylinux01.webApiDeneme2.repositories.TermRepository;

@Service
public class StudentService {

	StudentRepository  studentRepository;
	LessonRepository lessonRepository;
	TermRepository termRepository;
	@Autowired
	public StudentService(StudentRepository studentRepository
			,LessonRepository lessonRepository, TermRepository termRepository) {
		super();
		this.studentRepository = studentRepository;
		this.lessonRepository=lessonRepository;
		this.termRepository=termRepository;
	}


	public String createStudent(Long studentNumber, Long tc, String name, String surname,String password) {
		Student student=new Student();
		student.setStudentNumber(studentNumber);
		student.setTc(tc);
		student.setName(name);
		student.setSurname(surname);
		student.setPassword(password);
		
		Student student2=studentRepository.save(student);
		if(student2!=null) return "success";
		else return "fail";
		
	}


	public String addNewTermToStudent(Long studentNumber, Long termStartYear
			, Long termEndYear, String termSeason) {
		Student student=studentRepository.findByStudentNumber(studentNumber);
		Term term=new Term();
		term.setStartYear(termStartYear);
		term.setEndYear(termEndYear);
		term.setSeason(termSeason);
		term.setStudent(student);
		Term term2=termRepository.save(term);
		if(term2!=null) return "success";
		else return "fail";
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public String updateGPAOfStudents() {
		// TODO Auto-generated method stub
		List<Student> students=getAllStudents();
		for(Student s: students)
		{
			List<Lesson> lessons=lessonRepository.findByStudentStudentNumber(s.getStudentNumber());
			int i=0;
			
			while(i<lessons.size())
			{
				int j=i+1;
				while(j<lessons.size())
				{
					if(lessons.get(i).getCode().equals(lessons.get(j).getCode()))
					{
						lessons.remove(i);
						i=0;
						j=0;
						break;
					}
					j++;
				}
				i++;
			}
			Double total=0.0;
			Double totalCredit=0.0;
			for(Lesson l:lessons)
			{
				if(l.getNote().equals("AA"))
				{
					total+=4*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("BA"))
				{
					total+=3.5*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("BB"))
				{
					total+=3*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("CB"))
				{
					total+=2.5*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("CC"))
				{
					total+=2*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("DC"))
				{
					total+=1.5*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else if(l.getNote().equals("DD"))
				{
					total+=1*l.getActs();
					totalCredit+=l.getActs();
					
				}
				else
				{
					total+=0;
					totalCredit+=l.getActs();
				}
			}
			s.setGpa(total/totalCredit);
			studentRepository.save(s);
		}
		return "success";
	}


	

}
