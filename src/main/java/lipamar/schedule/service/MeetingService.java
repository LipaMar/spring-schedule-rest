package lipamar.schedule.service;
import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import java.util.Set;
public interface MeetingService {

    Set<Meeting> getMeetings();

    void addMeeting(Meeting meeting);

    void delMeeting(Meeting meeting);

    void editMeeting(Meeting meeting);

    void signUpForMeeting(Student student, Meeting meeting);
}
