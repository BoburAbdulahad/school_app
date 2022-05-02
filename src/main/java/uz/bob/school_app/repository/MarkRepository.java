package uz.bob.school_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.school_app.entity.Mark;
import uz.bob.school_app.entity.School;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Integer> {
}
