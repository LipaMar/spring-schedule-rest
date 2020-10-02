package lipamar.schedule.rest;

import lipamar.schedule.model.Student;
import lipamar.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Set<Student>> listStudents(){
        Set<Student> students = service.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
