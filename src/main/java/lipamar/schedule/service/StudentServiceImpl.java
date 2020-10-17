package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Presence;
import lipamar.schedule.model.Student;
import lipamar.schedule.repository.PresenceRepository;
import lipamar.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository students;
    private final PresenceRepository presenceRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository students, PresenceRepository presenceRepository) {
        this.students = students;
        this.presenceRepository = presenceRepository;
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
        if (meeting == null) {
            throw new RuntimeException("Meeting");
        } else if (student == null) {
            throw new RuntimeException("Student");
        } else {
            try {
                Presence presence = new Presence();
                presence.setMeeting(meeting);
                presence.setStudent(student);
                presenceRepository.save(presence);
            } catch (Exception e) {
                throw new RuntimeException("The same student cannot be signed up multiple times for one meeting");
            }
        }

    }
}
