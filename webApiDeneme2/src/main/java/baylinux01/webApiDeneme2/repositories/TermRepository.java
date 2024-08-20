package baylinux01.webApiDeneme2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baylinux01.webApiDeneme2.entities.Student;
import baylinux01.webApiDeneme2.entities.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long>{

	List<Term> findByStartYearAndEndYearAndSeason(Long termStartYear, Long termEndYear, String termSeason);

	List<Term> findByStudent(Student student);

}
