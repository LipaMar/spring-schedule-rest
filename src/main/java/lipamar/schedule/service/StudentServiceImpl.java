package lipamar.schedule.service;

import lipamar.schedule.model.Student;
import lipamar.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository students;

    @Autowired
    public StudentServiceImpl(StudentRepository students) {
        this.students = students;
    }

    @Override
    public Set<Student> getStudents() {
        return null;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void delStudent(Student student) {

    }

    @Override
    public void editStudent(Student student) {

    }
}
