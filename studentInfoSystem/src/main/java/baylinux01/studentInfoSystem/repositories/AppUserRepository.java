package baylinux01.studentInfoSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import baylinux01.studentInfoSystem.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByUsername(String username);
	
	

}
