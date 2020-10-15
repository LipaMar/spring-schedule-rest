package lipamar.schedule.rest;

import lipamar.schedule.exception.ValidationFailedException;
import lipamar.schedule.model.Meeting;
import lipamar.schedule.model.Student;
import lipamar.schedule.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<Meeting> addMeeting(@RequestBody @Valid Meeting meeting, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException(errors.getAllErrors());
        }
        return new ResponseEntity<>(meetingService.addMeeting(meeting), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Meeting>> getMeetings() {
        return new ResponseEntity<>(meetingService.getMeetings(), HttpStatus.OK);
    }
    @PutMapping("/{meetingId}")
    public ResponseEntity<Meeting> editMeeting(@PathVariable int meetingId,@RequestBody @Valid Meeting newMeeting, BindingResult errors){
        Meeting meeting = meetingService.getMeeting(meetingId);
        if(meeting == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        if(errors.hasErrors()){
            throw new ValidationFailedException(errors.getAllErrors());
        }
        return new ResponseEntity<>(meetingService.editMeeting(newMeeting),HttpStatus.OK);

    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable int meetingId) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        return meeting != null ? new ResponseEntity<>(meeting, HttpStatus.FOUND) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{meetingId}/students")
    public ResponseEntity<Set<Student>> getSignedUpStudents(@PathVariable int meetingId) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        return meeting != null ? new ResponseEntity<>(meeting.getSignedUpStudents(), HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
