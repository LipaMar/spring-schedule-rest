package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository students;

    @Autowired
    public StudentServiceImpl(StudentRepository students) {
        this.students = students;
    }

    @Override
    public Set<Student> getStudents() {
        return StreamSupport.stream(students.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Student getStudent(int id) {
        return students.findById(id).orElse(null);
    }

    @Override
    public Student addStudent(Student student) {
        if (students.findByIndexOrEmail(student.getIndex(), student.getEmail()) == null)
            return students.save(student);
        else
            return null;
    }

    @Override
    public Student delStudent(Student student) {
        students.delete(student);
        return student;
    }

    @Override
    public Student editStudent(Student student) {
        if (!exists(student)) return null;
        return students.save(student);
    }

    private boolean exists(Student student) {
        try {
            students.findById(student.getId()).orElseThrow(Exception::new);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void signUpForMeeting(int studentId, Meeting meeting) throws RuntimeException {
        Student student = students.findById(studentId).orElse(null);
        if (meeting != null && student != null) {
            student.getMeetings().add(meeting);
            students.save(student);
        } else
            throw new RuntimeException("Could not sign up student to the meeting");

    }
}
