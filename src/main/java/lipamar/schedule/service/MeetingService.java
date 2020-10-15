package lipamar.schedule.service;
import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import java.util.Set;
public interface MeetingService {

    Set<Meeting> getMeetings();

    Meeting getMeeting(int meetingId);

    Meeting addMeeting(Meeting meeting);

    Meeting delMeeting(Meeting meeting);

    Meeting editMeeting(Meeting meeting);

}
