package lipamar.schedule.service;

import lipamar.schedule.model.Student;
import lipamar.schedule.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl service;
    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        student = new Student();
    }

    @Test
    void addStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        service.addStudent(student);
        assertEquals(student, service.addStudent(student));
    }

    @Test
    void editStudent() {
        when(studentRepository.findById(any())).thenReturn(java.util.Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        student.setLastName("Doe");
        assertThat(student, equalTo(service.editStudent(student)));
    }
}