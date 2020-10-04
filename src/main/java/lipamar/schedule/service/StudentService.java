package lipamar.schedule.service;

import lipamar.schedule.model.Student;

import java.util.Set;

public interface StudentService {

    Set<Student> getStudents();

    Student addStudent(Student student);

    Student delStudent(Student student);

    Student editStudent(Student student);

}
