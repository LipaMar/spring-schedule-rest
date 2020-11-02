package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MeetingServiceIntegrationTest {

    @Autowired
    private MeetingService service;

    static Meeting createValidMeeting(){
        Meeting meeting = new Meeting();
        meeting.setTitle("Tytuł");
        meeting.setLecturerInfo("Info");
        meeting.setDescription("Opis");
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.DAY_OF_YEAR, 1);
        meeting.setDate(futureDate.getTime());
        return meeting;
    }

    @Test
    void whenAddValidMeeting_expectGetMeetingsContainAddedMeeting() {
        Meeting addedMeeting = service.addMeeting(createValidMeeting());
        assertTrue(service.getMeetings().contains(addedMeeting));
    }
    @Test
    void whenAddInvalidMeeting_expectNull() {
        Meeting meeting = new Meeting();
        meeting.setTitle("   ");
        assertNull(service.addMeeting(meeting));
        meeting.setTitle("test");
        Calendar pastDate = Calendar.getInstance();
        pastDate.add(Calendar.DAY_OF_YEAR, -1);
        meeting.setDate(pastDate.getTime());
        Meeting addedMeeting = service.addMeeting(meeting);
        assertFalse(service.getMeetings().contains(addedMeeting));
        assertNull(addedMeeting);
    }

    @Test
    void whenEditMeeting_expectModified() {
        Meeting meeting = service.getMeeting(1);
        meeting.setTitle("Modified Title");
        service.editMeeting(meeting);
        assertEquals("Modified Title", service.getMeeting(1).getTitle());
    }

    @Test
    void whenDelMeeting_expectGetMeetingNull() {
        service.delMeeting(2);
        assertNull(service.getMeeting(2));
    }
}
