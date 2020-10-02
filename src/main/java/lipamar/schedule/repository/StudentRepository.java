package lipamar.schedule.repository;

import lipamar.schedule.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
