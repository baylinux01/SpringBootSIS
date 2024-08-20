package baylinux01.webApiDeneme2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baylinux01.webApiDeneme2.entities.Teacher;
import baylinux01.webApiDeneme2.repositories.TeacherRepository;

@Service
public class TeacherService {

	TeacherRepository teacherRepository;
	
	
	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}



	public String createTeacher(String name, String surname, Long tc, String password) {
		Teacher teacher=new Teacher();
		teacher.setName(name);
		teacher.setSurname(surname);
		teacher.setTc(tc);
		teacher.setPassword(password);
		Teacher teacher2=teacherRepository.save(teacher);
		if(teacher2!=null) return "success";
		else return "fail";
		
		
	}

}
