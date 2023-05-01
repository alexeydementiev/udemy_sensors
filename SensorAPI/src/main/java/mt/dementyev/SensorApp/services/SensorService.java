package mt.dementyev.SensorApp.services;

import mt.dementyev.SensorApp.model.Sensor;
import mt.dementyev.SensorApp.util.SensorNotFoundException;

import java.util.List;


public interface SensorService {

	List<Sensor> getSensors();

	Sensor getUserById(int id) throws SensorNotFoundException;

	void save(Sensor sensor);
}
