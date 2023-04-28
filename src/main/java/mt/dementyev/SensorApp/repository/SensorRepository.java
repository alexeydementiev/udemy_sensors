package mt.dementyev.SensorApp.repository;

import mt.dementyev.SensorApp.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer> {
  Optional<Sensor> findByName(String name);
}
