package lipamar.schedule.rest;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.service.MeetingService;
import lipamar.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        try {
            Meeting meeting = meetingService.getMeeting(meetingId);
            studentService.signUpForMeeting(studentId, meeting);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId){
        Student student = studentService.getStudent(studentId);
        if(student == null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }
    @GetMapping("/{studentId}/meetings")
    public ResponseEntity<Set<Meeting>> listStudentsMeetings(@PathVariable int studentId){
        Student student = studentService.getStudent(studentId);
        if(student == null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student.getMeetings(),HttpStatus.OK);
    }
}
