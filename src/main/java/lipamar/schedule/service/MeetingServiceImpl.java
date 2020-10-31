package lipamar.schedule.service;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetings;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetings) {
        this.meetings = meetings;
    }

    @Override
    public Set<Meeting> getMeetings() {
        return StreamSupport.stream(meetings.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Meeting getMeeting(int meetingId) {
        return meetings.findById(meetingId).orElse(null);
    }

    @Override
    public Meeting addMeeting(Meeting meeting) {
        if (meeting.getDate() != null && new Date().before(meeting.getDate()))
            return meetings.save(meeting);
        return null;
    }

    @Override
    public Meeting delMeeting(int meetingId) {
        Meeting meeting = meetings.findById(meetingId).orElse(null);
        if (meeting != null) {
            meetings.delete(meeting);
            return meeting;
        }
        return null;

    }

    @Override
    public Meeting editMeeting(Meeting meeting) {
        Meeting meetingToUpdate = meetings.findById(meeting.getId()).orElse(null);
        if (meetingToUpdate != null) {
            updateMeeting(meeting,meetingToUpdate);
            return meetings.save(meetingToUpdate);
        } else
            return null;
    }
    private void updateMeeting(Meeting src, Meeting dst){
        dst.setDate(src.getDate());
        dst.setDescription(src.getDescription());
        dst.setLecturerInfo(src.getLecturerInfo());
        dst.setTitle(src.getTitle());
    }
}
