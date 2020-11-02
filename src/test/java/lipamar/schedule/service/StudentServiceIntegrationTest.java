package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private MeetingService meetingService;

    static Student createValidStudent(){
        Student student = new Student();
        student.setName("test");
        student.setLastName(" ");
        student.setEmail("test");
        student.setPassword("pass");
        student.setIndex("");
        student.setSemester(3);
        student.setStudyCourse("");
        return student;
    }
    @Test
    void whenAddValidStudent_thenExpectGetStudentNotNull() {
        //when
        Student addedStudent = studentService.addStudent(createValidStudent());
        //then
        assertNotNull(addedStudent);
        assertEquals("ROLE_STUDENT",addedStudent.getRole());
        assertTrue(BCrypt.checkpw("pass", addedStudent.getPassword()));
    }

    @Test
    void delStudent() {
        assertNotNull(studentService.getStudent(2));
        studentService.delStudent(2);
        assertNull(studentService.getStudent(2));
    }

    @Test
    void editStudent() {
        Student student = studentService.getStudent(1);
        student.setLastName("Modification Test");
        Student modifiedStudent = studentService.editStudent(student);
        assertEquals(student.getLastName(),modifiedStudent.getLastName());
    }

    @Test
    void signUpForMeeting() {
        Meeting meeting = meetingService.getMeeting(1);
        studentService.signUpForMeeting(1,meeting);

        assertEquals( studentService.getStudent(1).getMeetings().size(), 2);
    }
}