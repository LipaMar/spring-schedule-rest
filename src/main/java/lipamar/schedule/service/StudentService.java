package lipamar.schedule.service;

import lipamar.schedule.model.Student;

import java.util.Set;

public interface StudentService {

    Set<Student> getStudents();

    void addStudent(Student student);

    void delStudent(Student student);

    void editStudent(Student student);

}
