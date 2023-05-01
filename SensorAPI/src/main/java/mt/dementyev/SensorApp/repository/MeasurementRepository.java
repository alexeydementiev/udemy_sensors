package mt.dementyev.SensorApp.repository;

import mt.dementyev.SensorApp.model.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {

  Optional<List<Measurement>> findAllByRaining(Boolean rainy);
}
