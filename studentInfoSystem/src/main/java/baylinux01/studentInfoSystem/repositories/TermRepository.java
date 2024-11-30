package baylinux01.studentInfoSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.studentInfoSystem.entities.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

	List<Term> findByName(String termName);

}
