package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetings;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetings) {
        this.meetings = meetings;
    }

    @Override
    public Set<Meeting> getMeetings() {
        return null;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.save(meeting);
    }

    @Override
    public void delMeeting(Meeting meeting) {

    }

    @Override
    public void editMeeting(Meeting meeting) {

    }

    @Override
    public void signUpForMeeting(Student student, Meeting meeting) {

    }
}
