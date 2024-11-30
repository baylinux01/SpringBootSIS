package baylinux01.studentInfoSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import baylinux01.studentInfoSystem.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByUsername(String username);

	List<AppUser> findByProgram(String name);
	
	

}
