package lipamar.schedule.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.service.MeetingService;
import lipamar.schedule.service.StudentService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class StudentRestControllerTest {

    @Mock
    private StudentService studentService;
    @Mock
    private MeetingService meetingService;
    @InjectMocks
    private StudentRestController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(studentService.getStudent(eq(1))).thenReturn(new Student());
    }

    @Test
    void listStudents() throws Exception {
        mockMvc.perform(get("/students")).andExpect(status().isOk());
    }

    @Test
    void addStudent() throws Exception {
        when(studentService.addStudent(any(Student.class))).thenReturn(new Student());
        Student student = new Student();
        student.setName("Jan");
        student.setLastName("Kowalski");
        student.setEmail("mail@mail.com");
        student.setIndex("99999");
        student.setPassword("password");
        student.setSemester(2);
        student.setStudyCourse("Stuff");
        ObjectMapper mapper = new ObjectMapper();
        String newStudentAsJSON = mapper.writeValueAsString(student);
        mockMvc.perform(post("/students").content(newStudentAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }

    @Test
    void signUpStudent() throws Exception {
        mockMvc.perform(post("/students/1").param("meetingId","1")).andExpect(status().isOk());
    }

    @Test
    void getStudent() throws Exception {
        mockMvc.perform(get("/students/1")).andExpect(status().isFound());
    }

    @Test
    void listStudentsMeetings() throws Exception {
        mockMvc.perform(get("/students/1/meetings")).andExpect(status().isOk());
    }
}