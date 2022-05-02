package uz.bob.school_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.school_app.entity.School;
import uz.bob.school_app.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
