package baylinux01.studentInfoSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.studentInfoSystem.entities.DepartmentToChoose;

@Repository
public interface DepartmentToChooseRepository extends JpaRepository<DepartmentToChoose, Long>{
	
	

}
