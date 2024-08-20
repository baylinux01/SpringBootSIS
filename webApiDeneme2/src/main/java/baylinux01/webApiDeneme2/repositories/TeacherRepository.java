package baylinux01.webApiDeneme2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.webApiDeneme2.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
