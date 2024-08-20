package baylinux01.webApiDeneme2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import baylinux01.webApiDeneme2.entities.Lesson;
import baylinux01.webApiDeneme2.entities.Term;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{

	List<Lesson> findByStudentStudentNumber(Long studentNumber);

	List<Lesson> findByCode(String lessonCode);

	List<Lesson> findByTerm(Term term);
	

}
