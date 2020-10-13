package lipamar.schedule.rest;

import lipamar.schedule.model.Student;
import lipamar.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final StudentService service;

    @Autowired
    public StudentRestController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<Student>> listStudents() {
        return new ResponseEntity<>(service.getStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (!errors.hasErrors()) {
            Student response = service.addStudent(student);
            if (response != null)
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
