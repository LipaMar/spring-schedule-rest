package lipamar.schedule.repository;

import lipamar.schedule.model.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
}
