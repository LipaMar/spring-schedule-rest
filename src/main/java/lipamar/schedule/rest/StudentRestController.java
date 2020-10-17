package lipamar.schedule.rest;

import lipamar.schedule.exception.ValidationFailedException;
import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.service.MeetingService;
import lipamar.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final StudentService studentService;
    private final MeetingService meetingService;

    @Autowired
    public StudentRestController(StudentService studentService, MeetingService meetingService) {
        this.studentService = studentService;
        this.meetingService = meetingService;
    }

    @GetMapping
    public ResponseEntity<Set<Student>> listStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student, BindingResult errors) {
        if (!errors.hasErrors()) {
            Student response = studentService.addStudent(student);
            if (response != null)
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Void> signUpStudent(@PathVariable int studentId, @RequestParam int meetingId) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        studentService.signUpForMeeting(studentId, meeting);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId) {
        Student student = studentService.getStudent(studentId);
        if (student == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> editStudent(@RequestBody @Valid Student newStudentInfo, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException(errors.getAllErrors());
        }
        return new ResponseEntity<>(studentService.editStudent(newStudentInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> delStudent(@PathVariable int studentId) throws Exception {
        Student student = studentService.delStudent(studentId);
        if(student == null) throw new Exception("Cannot delete student who is not existing");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/meetings")
    public ResponseEntity<Set<Meeting>> listStudentsMeetings(@PathVariable int studentId) {
        Student student = studentService.getStudent(studentId);
        if (student == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student.getMeetings(), HttpStatus.OK);
    }
}
