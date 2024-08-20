package baylinux01.webApiDeneme2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.webApiDeneme2.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByStudentNumber(Long studentNumber);

}
