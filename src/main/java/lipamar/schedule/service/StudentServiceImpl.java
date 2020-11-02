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
@Transactional
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
    public Student delStudent(int studentId) {
        Student student = students.findById(studentId).orElse(null);
        if (student != null) {
            students.delete(student);
            return student;
        }
        return null;
    }

    @Override
    public Student editStudent(Student student) {
        Student studentToUpdate = students.findById(student.getId()).orElse(null);
        if (studentToUpdate == null) throw new RuntimeException("Student who was tried to edit does not exist");
        updateStudent(student,studentToUpdate);
        return students.save(studentToUpdate);
    }

    private void updateStudent(Student src, Student dst) {
        dst.setStudyCourse(src.getStudyCourse());
        dst.setSemester(src.getSemester());
        dst.setRole(src.getRole());
        dst.setPassword(src.getPassword());
        dst.setIndex(src.getIndex());
        dst.setEmail(src.getEmail());
        dst.setName(src.getName());
        dst.setLastName(src.getLastName());
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
