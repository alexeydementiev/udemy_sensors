package mt.dementyev.SensorApp.services;

import mt.dementyev.SensorApp.model.Measurement;

import java.util.List;


public interface MeasurementService {

  List<Measurement> getMeasures();

  Integer countRainyDays();

  void save(Measurement measurement);
}
