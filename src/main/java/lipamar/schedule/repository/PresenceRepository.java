package lipamar.schedule.repository;

import lipamar.schedule.model.Presence;
import org.springframework.data.repository.CrudRepository;

public interface PresenceRepository extends CrudRepository<Presence,Integer> {
}
