package uz.bob.school_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.school_app.entity.School;
import uz.bob.school_app.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
