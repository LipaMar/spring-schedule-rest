package lipamar.schedule.rest;

import lipamar.schedule.model.Meeting;
import lipamar.schedule.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    private final MeetingService service;

    @Autowired
    public MeetingRestController(MeetingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Meeting> addMeeting(@RequestBody @Valid Meeting meeting, Errors errors){
        if (errors.hasErrors()){
            // TODO: create error response
            return new ResponseEntity<>(meeting,HttpStatus.BAD_REQUEST);
        }
        service.addMeeting(meeting);
        return new ResponseEntity<>(meeting, HttpStatus.CREATED);
    }
}
